package com.bakulovas.tta.unitTests.service;

import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.api.dto.validation.annotations.MinLength;
import com.bakulovas.tta.config.ServerConfig;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerError;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.repository.OfficeRepository;
import com.bakulovas.tta.repository.RoleRepository;
import com.bakulovas.tta.repository.UserRepository;
import com.bakulovas.tta.security.JwtProvider;
import com.bakulovas.tta.security.PasswordService;
import com.bakulovas.tta.security.UserDetailsImpl;
import com.bakulovas.tta.service.UserService;
import com.bakulovas.tta.serviceimpl.UserServiceImpl;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.*;
import java.util.Optional;

import static com.bakulovas.tta.config.StaticData.*;
import static com.bakulovas.tta.config.StaticData.INVALID_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
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
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    String login;
    String password;
    Office office;
    Role role;
    String token;

    @BeforeEach
    void init() {
        password = "password";
        office = new Office(1, "MSK", 0);
        role = new Role(1, "ADMIN");
        token = "1234";
    }


    @Test
    void testLoginUser_Admin_successful() {
        login = "Admin";
        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(serverConfig.getAdminLogin()).thenReturn(login);
        when(serverConfig.getAdminPassword()).thenReturn(password);
        when(passwordService.validatePassword(serverConfig.getAdminPassword(), request.getPassword())).thenReturn(true);
        when(serverConfig.getDefaultOffice()).thenReturn("MSK");
        when(officeRepository.getByName(serverConfig.getDefaultOffice())).thenReturn(office);
        when(roleRepository.getByName(anyString())).thenReturn(role);
        when(jwtProvider.generateToken(any(User.class))).thenReturn(token);
        LoginUserDtoResponse response = userService.loginUser(request);
        assertAll("Admin",
            () -> assertEquals(response.getLogin(), login),
            () -> assertEquals(response.getOffice(), office.getName()),
            () -> assertEquals(response.getRole(), role.getName()),
            () -> assertEquals(response.getToken(), token)
        );
    }

    @Test
    void testLoginUser_Admin_wrongLogin() {
        login = "Admin";
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
    void testLoginUser_Admin_wrongPassword() {
        login = "Admin";
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

    @Test
    void testLoginUser_User_successful() {
        login ="User";
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        user.setRole(role);
        user.setOffice(office);
        user.setActive(true);
        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
        when(passwordService.validatePassword(password, password)).thenReturn(true);
        when(jwtProvider.generateToken(any(User.class))).thenReturn(token);
        LoginUserDtoResponse response = userService.loginUser(request);
        assertAll("User",
                () -> assertEquals(response.getLogin(), login),
                () -> assertEquals(response.getOffice(), office.getName()),
                () -> assertEquals(response.getRole(), role.getName()),
                () -> assertEquals(response.getToken(), token)
        );
    }

    @Test
    void testLoginUser_User_notFound() {
        login = "User";
        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(userRepository.findByLogin(login)).thenReturn(Optional.empty());
        try {
            userService.loginUser(request);
        } catch (ServerException ex) {
            assertEquals(ex.getMessage(), ServerError.INCORRECT_LOGIN_OR_PASSWORD.getErrorString());
        }
    }

    @Test
    void testLoginUser_User_wrongPassword() {
        login = "User";
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        user.setRole(role);
        user.setOffice(office);
        user.setActive(true);
        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
        when(passwordService.validatePassword(password, password)).thenReturn(false);
        try {
            userService.loginUser(request);
        } catch (ServerException ex) {
            assertEquals(ex.getMessage(), ServerError.INCORRECT_LOGIN_OR_PASSWORD.getErrorString());
        }
    }

    @Test
    void testLoginUser_User_notActive() {
        login = "User";
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        user.setRole(role);
        user.setOffice(office);
        user.setActive(false);
        LoginUserDtoRequest request = new LoginUserDtoRequest(login, password);
        when(userRepository.findByLogin(login)).thenReturn(Optional.of(user));
        when(passwordService.validatePassword(password, password)).thenReturn(false);
        try {
            userService.loginUser(request);
        } catch (ServerException ex) {
            assertEquals(ex.getMessage(), ServerError.INACTIVE_USER.getErrorString());
        }
    }

    @Test
    void testAddUser_successful() {
        login = "Admin";
        User user = new User();
        user.setId(1);
        user.setLogin(login);
        user.setRole(new Role(1,"ADMIN"));
        user.setOffice(office);
        user.setPassword(password);
        var request = new AddUserDtoRequest("Login", "Password", "email",
                                    "FirstName", "LastName", office.getName(), role.getName());
        when(officeRepository.findByName(request.getOffice())).thenReturn(Optional.of(office));
        when(roleRepository.findByName(request.getRole())).thenReturn(Optional.of(role));
        when(userRepository.save(any(User.class))).thenReturn(any(User.class));

        UserDtoResponse response = userService.addUser(request);
        assertAll("Add user",
                () -> assertEquals(response.getLogin(), login),
                () -> assertEquals(response.getOffice(), office.getName()),
                () -> assertEquals(response.getRole(), role.getName())
        );
    }


}
