package com.bakulovas.tta.integrationTests.security;

import com.bakulovas.tta.config.ServerConfig;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JwtProviderTest {

    @MockBean
    private OfficeRepository officeRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private ServerConfig serverConfig;
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void testGetUserFromToken() {
        User user = new User();
        Office office = new Office(1,"Office",0);
        Role role = new Role(1,"Role1");
        user.setLogin("Login");
        user.setId(1);
        user.setRole(role);
        user.setOffice(office);
        when(serverConfig.getJwtSecret()).thenReturn("secret");
        when(officeRepository.getByName(Mockito.anyString())).thenReturn(office);
        when(roleRepository.getByName(Mockito.anyString())).thenReturn(role);
        String token = jwtProvider.generateToken(user);
        User userFromToken = jwtProvider.getUserFromToken(token);
        System.out.println(user);
        assertEquals(user.getLogin(), userFromToken.getLogin());
        assertEquals(user.getRole().getName(), userFromToken.getRole().getName());
        assertEquals(user.getOffice().getName(), userFromToken.getOffice().getName());
        assertEquals(user.getId(), userFromToken.getId());

    }

}
