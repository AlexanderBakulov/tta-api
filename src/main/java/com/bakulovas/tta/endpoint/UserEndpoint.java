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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;


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

    @GetMapping(value = "/{id}")
    @ApiOperation(value="Get user by id")
    public UserDtoResponse getUserById(@PathVariable("id") @Min(1) int id ) throws ServerException {
        return userService.getUser(id);
    }

    @GetMapping
    @ApiOperation(value="Get users by login and/or last name")
    public Set<UserDtoResponse> getUsers(@RequestParam(name = "login", required = false, defaultValue = "") String login,
                                         @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname) {
        return userService.getUsers(login, lastname);
    }


}
