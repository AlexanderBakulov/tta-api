package com.bakulovas.tta.repository;

import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.entity.UserOptions;
import com.bakulovas.tta.repository.jpa.UserOptionsRepository;
import com.bakulovas.tta.repository.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/test-ttabase.sql", "/users.sql"})
public class UserOptionsRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOptionsRepository userOptionsRepository;




    @Test
    public void testUserOptionsRepository() {
        User user = userRepository.getById(3);
        UserOptions userOptions = new UserOptions();
        userOptions.setFree(false);
        userOptions.setRejectCounter(1);
        userOptions.setTicketCounter(10);
        userOptions.setUser(user);
        userOptionsRepository.save(userOptions);
        UserOptions optionsFromDB = userOptionsRepository.getByUser(user);
        assertEquals(user, optionsFromDB.getUser());

    }






}
