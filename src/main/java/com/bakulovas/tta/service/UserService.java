package com.bakulovas.tta.service;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.User;
import com.bakulovas.tta.errors.ServerException;

import java.util.Set;

public interface UserService {

    LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException;

//    User getUser(String username);

    UserDtoResponse addUser(AddUserDtoRequest request) throws ServerException;

    UserDtoResponse getUser(int id) throws ServerException;

    Set<UserDtoResponse> getUsers(String login, String lastname);

}
