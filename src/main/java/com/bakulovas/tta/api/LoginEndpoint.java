package com.bakulovas.tta.api;

import com.bakulovas.tta.api.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.api.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginEndpoint {

    private final UserService userService;

    @Autowired
    public LoginEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/login")
    @ApiOperation(value="Login user")
    public LoginUserDtoResponse login(@Valid @RequestBody LoginUserDtoRequest request) {

        return userService.loginUser(request);
    }
}
