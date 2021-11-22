package com.bakulovas.tta.endpoint;


import com.bakulovas.tta.dto.request.AddUserDtoRequest;
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

@RestController
@RequestMapping("api/users")
@Api(value="User management")
public class UserEndpoint {

    private final UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value="Add user")
//    public UserDtoResponse addUser(@Valid @RequestBody AddUserDtoRequest request) throws ServerException {
//
//        return userService.addUser(request);
//    }

//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value="Get user by id")
//    public UserDtoResponse getUserById(@PathVariable("id") @Min(1) long id ) throws ServerException {
//        return userService.getUser(id);
//    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value="Get users by login and/or last name")
//    public List<UserDtoResponse> getUsers(@RequestParam(name = "login", required = false, defaultValue = "") String login,
//                                          @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname) {
//        return userService.getUsers(login, lastname);
//    }

//    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value="Change user")
//    public UserDtoResponse changeUser(@PathVariable("id") @Min(1) long id ,
//                                      @Valid @RequestBody ChangeUserDtoRequest request) throws ServerException {
//
//        return userService.updateUser(id, request);
//    }



}
