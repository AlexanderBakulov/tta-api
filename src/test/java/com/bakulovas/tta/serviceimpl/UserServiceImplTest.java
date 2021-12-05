package com.bakulovas.tta.serviceimpl;

import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql({"/test-ttabase.sql"})
class UserServiceImplTest {

    @Autowired
    private UserService userService;




    @Test
    void testLoginUser() throws ServerException {
        LoginUserDtoRequest request = new LoginUserDtoRequest("admin", "pass");
        LoginUserDtoResponse response = userService.loginUser(request);
        assertEquals(request.getLogin(), response.getLogin());

    }

    @Test
    void testAddUser() throws ServerException {
        AddUserDtoRequest request = new AddUserDtoRequest("user1", "Aa11", "user@tta.ru", "User", "User", "MSK", "USER");
        UserDtoResponse response = userService.addUser(request);
        assertEquals(request.getLogin(), response.getLogin());

    }
}