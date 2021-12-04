package com.bakulovas.tta.mappers;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CommonMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CommonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public LoginUserDtoResponse convertToDto(User user, String token, String officeName) {
        LoginUserDtoResponse response = modelMapper.map(user, LoginUserDtoResponse.class);
        response.setOffice(officeName);
        response.setToken(token);
        return response;
    }

    public UserDtoResponse convertToDto(User user, String officeName) {
        UserDtoResponse response = modelMapper.map(user, UserDtoResponse.class);
        response.setOffice(officeName);
        return response;
    }

    public User convertToUser(AddUserDtoRequest request, int officeId) {
        User user = modelMapper.map(request, User.class);
        user.setOfficeId(officeId);
        user.setActive(true);
        user.setTempPassword(true);
        return user;
    }

}
