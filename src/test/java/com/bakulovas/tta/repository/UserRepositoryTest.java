package com.bakulovas.tta.repository;

import com.bakulovas.tta.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql({"/test-ttabase.sql", "/users.sql"})
public class UserRepositoryTest {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUserRepository() {

        User u = new User("Vasya", "11aA", "a@a.a", "vasya", "Vasya", "USER", 1);
        userRepository.save(u);
        User user = userRepository.getByLogin("Vasya");
        assertEquals(u.getLogin(), user.getLogin());
        userRepository.delete(u);
    }

    @Test
    public void testUserRepository_incorrectUser() {
        assertNull(userRepository.getByLogin("Petya"));
    }


    @Test
    public void testGetByLogin() {
        String login = "manager";
        User user = userRepository.getByLogin(login);
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testGetByLogin_wrongLogin() {
        String login = "Wrong";
        User user = userRepository.getByLogin(login);
        assertNull(user);

    }

    @Test
    public void testGetById() {
        int id = 3;
        User user = userRepository.getById(id);
        assertEquals(id, user.getId());
        System.out.println(user.getFirstName());
    }

}
