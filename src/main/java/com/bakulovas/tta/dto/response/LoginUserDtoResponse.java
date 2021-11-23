package com.bakulovas.tta.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class LoginUserDtoResponse {

    private long id;
    private String login;
    private String email;
    private boolean isTempPassword;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String office;
    private String division;
    private Set<String> roles;
    private String token;

}
