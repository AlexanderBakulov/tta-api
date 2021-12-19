package com.bakulovas.tta.service;


import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.request.UpdateUserDtoRequest;
import com.bakulovas.tta.api.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.entity.User;

import java.util.Set;

public interface UserService {

    LoginUserDtoResponse loginUser(LoginUserDtoRequest request);

    UserDtoResponse addUser(AddUserDtoRequest request);

    UserDtoResponse getUser(int id);

    Set<UserDtoResponse> getUsers(String login, String lastname);

    User getUser(String username);

    UserDtoResponse updateUser(int id, UpdateUserDtoRequest request);
}
