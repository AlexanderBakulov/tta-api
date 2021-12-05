package com.bakulovas.tta.service;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerException;

public interface UserService {

    LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException;

    User getByLogin(String username);

    UserDtoResponse addUser(AddUserDtoRequest request) throws ServerException;

}
