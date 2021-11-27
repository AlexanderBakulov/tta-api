package com.bakulovas.tta.mappers;


import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.entity.Role;
import com.bakulovas.tta.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class CommonMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CommonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoginUserDtoResponse convertToDto(User user, String token) {
        LoginUserDtoResponse response = modelMapper.map(user, LoginUserDtoResponse.class);
        response.setOffice(user.getOffice().getName());
        response.setDivision(user.getDivision().getName());
        response.setRole(user.getRole().getName());
        response.setToken(token);
        return response;
    }

}
