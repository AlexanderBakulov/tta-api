package com.bakulovas.tta.endpoint;


import com.bakulovas.tta.dto.request.LoginUserDtoRequest;
import com.bakulovas.tta.dto.response.LoginUserDtoResponse;
import com.bakulovas.tta.errors.ServerException;
import com.bakulovas.tta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/")
@Api(value="User management")
public class UserEndpoint {


    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(value="Login user")
    public LoginUserDtoResponse login(@Valid @RequestBody LoginUserDtoRequest request) throws ServerException {

        return userService.loginUser(request);
    }


}
