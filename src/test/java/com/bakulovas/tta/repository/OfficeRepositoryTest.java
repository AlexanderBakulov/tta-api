package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.jpa.OfficeRepository;
import com.bakulovas.tta.repository.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@Sql({"/test-ttabase.sql"})
public class OfficeRepositoryTest {


    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testRepository() {

        User user = new User("login", "password", "email", "firstname", "lastname", 1, "USER");
        userRepository.save(user);
        User userFromDb = userRepository.findByLogin(user.getLogin());
        assertEquals(user.getId(), userFromDb.getId());
        userRepository.delete(user);
        userFromDb = userRepository.findByLogin(user.getLogin());
        assertNull(userFromDb);
    }

}
