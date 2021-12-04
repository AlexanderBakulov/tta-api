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
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6MSwiaXNBY3RpdmUiOnRydWUsImlzVGVtcFBhc3N3b3JkIjp0cnVlLCJvZmZpY2VJZCI6MSwicm9sZSI6IkFETUlOIn0.rrpZRhVTVrnpPKijqdQQcpupkzb9kVdAeXyq0Gx1zW9s1okEVx9A1DPGhO7AShgKxKvyE4IEzZA_N8JsYulhvA";
        User user = jwtProvider.getUserFromToken(token);
        System.out.println(user);
        assertEquals("admin", user.getLogin());

    }
}
