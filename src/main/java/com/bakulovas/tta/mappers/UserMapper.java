package com.bakulovas.tta.mappers;


import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.Office;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoginUserDtoResponse convertToDto(User user, String token) {
        LoginUserDtoResponse response = modelMapper.map(user, LoginUserDtoResponse.class);
        response.setOffice(user.getOffice().getName());
        response.setRole(user.getRole().getName());
        response.setToken(token);
        return response;
    }

    public UserDtoResponse convertToDto(User user) {
        UserDtoResponse response = modelMapper.map(user, UserDtoResponse.class);
        response.setOffice(user.getOffice().getName());
        response.setRole(user.getRole().getName());
        response.setCreated(user.getCreated().toString());
        return response;
    }


    public User convertToUser(AddUserDtoRequest request, Office office, Role role) {
        User user = modelMapper.map(request, User.class);
        user.setOffice(office);
        user.setRole(role);
        user.setActive(true);
        user.setTempPassword(true);
        return user;
    }

    public Set<UserDtoResponse> usersToDto(Set<User> users) {
        return users.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

}
