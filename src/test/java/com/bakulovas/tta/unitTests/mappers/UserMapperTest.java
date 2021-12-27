package com.bakulovas.tta.unitTests.mappers;

import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class UserMapperTest {


    private final UserMapper userMapper = new UserMapper(new ModelMapper());

    User user = new User();
    Role role = new Role();
    Office office = new Office();
    String token = "token";
    AddUserDtoRequest request = new AddUserDtoRequest();

    @BeforeEach
    public void initial() {
        role.setId(1);
        role.setName("USER");
        office.setId(1);
        office.setName("MSK");
        user.setCreated(LocalDateTime.now());
        user.setCreator("Admin");
        user.setId(1);
        user.setLogin("User1");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setEmail("email");
        user.setOffice(office);
        user.setRole(role);
        request.setLogin("login");
        request.setEmail("email");
        request.setFirstName("FirstName");
        request.setLastName("LastName");
        request.setOffice(office.getName());
        request.setRole(role.getName());
        request.setPassword("password");
    }

    @Test
    public void testConvertToDto_LoginUserDtoResponse() {
        LoginUserDtoResponse response = userMapper.convertToDto(user, token);
        assertAll(
                () -> assertEquals(response.getLogin(), user.getLogin()),
                () -> assertEquals(response.getToken(), token),
                () -> assertEquals(response.getRole(), user.getRole().getName()),
                () -> assertEquals(response.getOffice(), user.getOffice().getName())
        );

    }

    @Test
    public void testConvertToDto_UserDtoResponse() {
        UserDtoResponse response = userMapper.convertToDto(user);
        assertAll(
                () -> assertEquals(response.getLogin(), user.getLogin()),
                () -> assertEquals(response.getRole(), user.getRole().getName()),
                () -> assertEquals(response.getOffice(), user.getOffice().getName())
        );

    }

    @Test
    public void testConvertToUser() {
        User user1 = userMapper.convertToUser(request, office, role);
        assertAll(
                () -> assertEquals(user1.getLogin(), request.getLogin()),
                () -> assertEquals(user1.getRole(), role),
                () -> assertEquals(user1.getOffice(), office),
                () -> assertTrue(user1.isActive()),
                () -> assertTrue(user1.isTempPassword())
        );

    }

}
