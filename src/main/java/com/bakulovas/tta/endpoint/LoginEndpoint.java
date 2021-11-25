package com.bakulovas.tta.endpoint;

import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Api(value="Authorization")
public class LoginEndpoint {

    private final UserService userService;

    @Autowired
    public LoginEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value="Login user")
    public LoginUserDtoResponse login(@Valid @RequestBody LoginUserDtoRequest request) throws ServerException {
        return userService.loginUser(request);
    }
}
