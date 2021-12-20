package com.bakulovas.tta.integrationTests.repository;


import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@Transactional
@Sql({"/test-ttabase.sql"})
public class OfficeRepositoryTest {


    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testRepository() {
        Role role = roleRepository.getById(3);
        Office office = officeRepository.getById(1);
        User user = new User("login", "password", "email",
                "firstname", "lastname", office, role);
        userRepository.save(user);
        User userFromDb = userRepository.findByLogin(user.getLogin()).get();
        assertEquals(user.getId(), userFromDb.getId());
        userRepository.delete(user);
        assertEquals(Optional.empty(), userRepository.findByLogin(user.getLogin()));
    }

    @Test
    public void testGetOfficeByName() {
        String name = "NSK";
        Office office = officeRepository.findByName(name).get();
        assertEquals(name, office.getName());
    }

    @Test
    public void testGetOfficeById() {
        int id = 2;
        Office office = officeRepository.findById(id).get();
        assertEquals(id, office.getId());
    }

}
