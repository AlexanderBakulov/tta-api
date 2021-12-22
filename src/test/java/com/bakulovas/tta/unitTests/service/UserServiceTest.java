package com.bakulovas.tta.unitTests.service;

import com.bakulovas.tta.api.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.config.ServerConfig;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.mappers.UserMapper;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.security.JwtProvider;
import com.bakulovas.tta.security.PasswordService;
import com.bakulovas.tta.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @MockBean
    private ServerConfig serverConfig;
    @MockBean
    private PasswordService passwordService;
    @MockBean
    private OfficeRepository officeRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    String login = "Admin";
    String password = "password";
    Office office = new Office(1, "MSK", 0);
    Role role = new Role(1, "ADMIN");
    String token = "1234";

    @Test
    public void testLoginUser_Admin_successful() {

        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(serverConfig.getAdminLogin()).thenReturn(login);
        when(serverConfig.getAdminPassword()).thenReturn(password);
        when(passwordService.validatePassword(serverConfig.getAdminPassword(), request.getPassword())).thenReturn(true);
        when(serverConfig.getDefaultOffice()).thenReturn("MSK");
        when(officeRepository.getByName(serverConfig.getDefaultOffice())).thenReturn(office);
        when(roleRepository.getByName(anyString())).thenReturn(role);
        when(jwtProvider.generateToken(Mockito.any(User.class))).thenReturn(token);

        LoginUserDtoResponse response = userService.loginUser(request);

        assertEquals(response.getLogin(), login);
        assertEquals(response.getOffice(), office.getName());
        assertEquals(response.getRole(), role.getName());
        assertEquals(response.getToken(), token);
    }

    @Test
    public void testLoginUser_Admin_wrongLogin() {

        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(serverConfig.getAdminLogin()).thenReturn("wrong login");
        when(serverConfig.getAdminPassword()).thenReturn(password);
        when(passwordService.validatePassword(serverConfig.getAdminPassword(), request.getPassword())).thenReturn(true);
        try {
            userService.loginUser(request);
        } catch (ServerException ex) {
            assertEquals(ex.getMessage(), ServerError.INCORRECT_LOGIN_OR_PASSWORD.getErrorString());
        }
    }

    @Test
    public void testLoginUser_Admin_wrongPassword() {

        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(serverConfig.getAdminLogin()).thenReturn(login);
        when(serverConfig.getAdminPassword()).thenReturn("wrong password");
        when(passwordService.validatePassword(serverConfig.getAdminPassword(), request.getPassword())).thenReturn(false);
        try {
            userService.loginUser(request);
        } catch (ServerException ex) {
            assertEquals(ex.getMessage(), ServerError.INCORRECT_LOGIN_OR_PASSWORD.getErrorString());
        }
    }

}
