package com.bakulovas.tta.integrationTests.security;

import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;
    @MockBean
    private OfficeRepository officeRepository;
    @MockBean
    private RoleRepository roleRepository;

//    @Test
//    public void testGetUserFromToken() {
//        User user = new User();
//        user.setLogin("Login");
//        user.setId(1);
//        user.setRole(new Role(1,"Role1"));
//        user.setOffice(new Office(1,"Office",0));
//        String token = jwtProvider.generateToken(user);
//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbiIsInVzZXJJZCI6MCwib2ZmaWNlIjoiTVNLIiwicm9sZSI6IkFETUlOIn0.Sek5LOVe-arFoOq0-Jb_PcdFU94TMbDfvD5diKHoXXmhvJZwryghwcjesHV-7ViW8tULQROzD6S_s1prmfxZCw";
//        User userFromToken = jwtProvider.getUserFromToken(token);
//        System.out.println(user);
//        assertEquals(user.getLogin(), userFromToken.getLogin());
//        assertEquals(user.getRole().getName(), userFromToken.getRole().getName());
//        assertEquals(user.getOffice().getName(), userFromToken.getRole().getName());
//        assertEquals(user.getId(), userFromToken.getId());
//
//    }

    @Test
    public void testGetUserFromToken_manager() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW4xIiwidXNlcklkIjoxLCJvZmZpY2UiOiJNU0siLCJyb2xlIjoiTUFOQUdFUiJ9.t8j3XNHF6sKbngqN1b-VugGYWAY9eGHO75qk-ghPAWR4OGIED1A35CbMjVoypyLuFRU665MP10wWmtYSlHOoYw";
        User user = jwtProvider.getUserFromToken(token);
        System.out.println(user);
        assertEquals("man1", user.getLogin());
        assertEquals("MANAGER", user.getRole().getName());

    }
}
