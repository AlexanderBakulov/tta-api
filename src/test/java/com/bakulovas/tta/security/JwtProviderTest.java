package com.bakulovas.tta.security;

import com.bakulovas.tta.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void testGetUserFromToken() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6MSwib2ZmaWNlIjoiTVNLIiwicm9sZSI6IkFETUlOIn0._8cszY8JlFRiVeZ0DId5m4FXi5WkiHuuZXbJAYAvSjkWMDKVVr9-kGUXcm0z_2ZTX84NmEEKlt_S0_30U51xLg";
        User user = jwtProvider.getUserFromToken(token);
        System.out.println(user);
        assertEquals("admin", user.getLogin());
        assertEquals("ADMIN", user.getRole().getName());

    }

    @Test
    public void testGetUserFromToken_manager() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5hZ2VyMyIsInVzZXJJZCI6NSwib2ZmaWNlIjoiTVNLIiwicm9sZSI6Ik1BTkFHRVIifQ.MdpZR_UNA217Xy5B6pzw8ocU-0A6hO09Y-9kVREi56jfbJuimTpUwq0ddTXnvr3im2IuRtNGSQ_iOaYX4iqYCA";
        User user = jwtProvider.getUserFromToken(token);
        System.out.println(user);
        assertEquals("manager3", user.getLogin());
        assertEquals("MANAGER", user.getRole().getName());

    }
}
