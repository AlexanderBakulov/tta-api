package com.bakulovas.tta.repository;

import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql({"/test-ttabase.sql", "/users.sql"})
public class UserRepositoryTest {

    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUserRepository() {
        Role role = roleRepository.findById(3).get();
        Office office = officeRepository.findById(1).get();
        User user = new User("Vasya", "11aA", "a@a.a", "vasya", "Vasya", office, role);
        userRepository.save(user);
        User userFromDB = userRepository.findByLogin("Vasya").get();
        assertEquals(user.getLogin(), userFromDB.getLogin());
        userRepository.delete(user);
    }

    @Test
    public void testUserRepository_incorrectUser() {
        assertEquals(Optional.empty(), userRepository.findByLogin("Petya"));
    }


    @Test
    public void testGetByLogin() {
        String login = "manager";
        User user = userRepository.findByLogin(login).get();
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testGetByLogin_wrongLogin() {
        String login = "Wrong";
        Optional<User> user = userRepository.findByLogin(login);
        assertEquals(Optional.empty(), user);

    }

    @Test
    public void testGetById() {
        int id = 3;
        User user = userRepository.findById(id).get();
        assertEquals(id, user.getId());
        System.out.println(user.getFirstName());
    }

}
