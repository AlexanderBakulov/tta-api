package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.Division;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.jpa.DivisionRepository;
import com.bakulovas.tta.repository.jpa.OfficeRepository;
import com.bakulovas.tta.repository.jpa.RoleRepository;
import com.bakulovas.tta.repository.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql({"/test-ttabase.sql", "/users.sql"})
public class UserRepositoryTest {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testUserRepository() {
        Optional<Office> of = officeRepository.findById((int) 1);
        Optional<Division> div = divisionRepository.findById((int) 1);
        Office office = of.get();
        Division division = div.get();
        User u = new User("Vasya", "11aA", "a@a.a", "vasya", "Vasya", office, division);
        userRepository.save(u);
        User user = userRepository.findByLogin("Vasya");
        assertEquals(u.getLogin(), user.getLogin());
        userRepository.delete(u);
    }

    @Test
    public void testUserRepository_incorrectUser() {
        assertNull(userRepository.findByLogin("Petya"));
    }


    @Test
    public void testFindByLogin() {
        String login = "manager";
        User user = userRepository.findByLogin(login);
        assertEquals(login, user.getLogin());
    }

    @Test
    public void testFindByLogin_wrongLogin() {
        String login = "Wrong";
        User user = userRepository.findByLogin(login);
        assertNull(user);

    }

}