package com.bakulovas.tta.api;


import com.bakulovas.tta.api.dto.request.AddUserDtoRequest;
import com.bakulovas.tta.api.dto.request.UpdateUserDtoRequest;
import com.bakulovas.tta.api.dto.response.UserDtoResponse;
import com.bakulovas.tta.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;


@RestController
@RequestMapping("api")
@Api(value="User management")
public class UserEndpoint {


    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users")
    @ApiOperation(value="Add new user")
    public UserDtoResponse addUser(@Valid @RequestBody AddUserDtoRequest request) {

        return userService.addUser(request);
    }

    @GetMapping(value = "users/{id}")
    @ApiOperation(value="Get user by id")
    public UserDtoResponse getUserById(@PathVariable("id") @Min(1) int id ) {
        return userService.getUser(id);
    }

    @GetMapping(value = "users")
    @ApiOperation(value="Get users by login and/or last name")
    public Set<UserDtoResponse> getUsers(@RequestParam(name = "login", required = false, defaultValue = "") String login,
                                         @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname) {
        return userService.getUsers(login, lastname);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value="Update user")
    public UserDtoResponse updateUser(@PathVariable("id") @Min(1) int id ,
                                      @Valid @RequestBody UpdateUserDtoRequest request) {
        return userService.updateUser(id, request);
    }
}
