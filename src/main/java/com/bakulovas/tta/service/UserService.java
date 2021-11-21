package com.bakulovas.tta.service;


import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.errors.ServerException;

public interface UserService {

    LoginUserDtoResponse loginUser(LoginUserDtoRequest request) throws ServerException;

}
