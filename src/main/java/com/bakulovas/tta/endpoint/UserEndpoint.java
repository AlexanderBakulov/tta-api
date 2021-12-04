package com.bakulovas.tta.endpoint;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.dto.response.UserDtoResponse;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
@Api(value="User management")
public class UserEndpoint {


    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value="Login user")
    public LoginUserDtoResponse login(@RequestBody @Valid LoginUserDtoRequest request) throws ServerException {

        return userService.loginUser(request);
    }

    @PostMapping("/users")
    public UserDtoResponse addUser(@RequestBody @Valid AddUserDtoRequest request) throws ServerException {

        return userService.addUser(request);
    }


}
