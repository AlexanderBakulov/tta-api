package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.Office;
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
        User userFromDb = userRepository.getByLogin(user.getLogin());
        assertEquals(user.getId(), userFromDb.getId());
        userRepository.delete(user);
        userFromDb = userRepository.getByLogin(user.getLogin());
        assertNull(userFromDb);
    }

    @Test
    public void testGetOfficeByName() {
        String name = "NSK";
        Office office = officeRepository.getByName(name);
        assertEquals(name, office.getName());
    }

    @Test
    public void testGetOfficeById() {
        int id = 2;
        Office office = officeRepository.getById(id);
        assertEquals(id, office.getId());
    }

}
