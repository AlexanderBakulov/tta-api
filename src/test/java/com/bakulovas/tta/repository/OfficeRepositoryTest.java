package com.bakulovas.tta.repository;


import com.bakulovas.tta.entity.Division;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.jpa.DivisionRepository;
import com.bakulovas.tta.repository.jpa.OfficeRepository;
import com.bakulovas.tta.repository.jpa.RoleRepository;
import com.bakulovas.tta.repository.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@Sql({"/test-ttabase.sql"})
public class OfficeRepositoryTest {


    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testRepository() {

        Optional<Office> of = officeRepository.findById(1);
        Office office = null;
        if(of.isPresent()) {
            office = of.get();
        }

        Optional<Division> div = divisionRepository.findById(1);
        Division division = null;
        if(div.isPresent()) {
            division = div.get();
        }

        Set<Role> roles = new HashSet<>();
        Optional<Role> role = roleRepository.findById(1);
        roles.add(role.get());

        User user = new User("login", "password", "email", "firstname", "lastname", office, division);
        user.setRoles(roles);
        userRepository.save(user);
        User userFromDb = userRepository.findByLogin(user.getLogin());
        assertEquals(user.getId(), userFromDb.getId());
        userRepository.delete(user);
        userFromDb = userRepository.findByLogin(user.getLogin());
        assertNull(userFromDb);
    }

}
