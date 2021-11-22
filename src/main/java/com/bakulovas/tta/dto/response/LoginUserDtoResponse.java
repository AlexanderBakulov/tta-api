package com.bakulovas.tta.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
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

    public LoginUserDtoResponse() {
    }

    public LoginUserDtoResponse(long id, String login, String email, boolean isTempPassword,
                                String firstName, String lastName, boolean isActive, String office,
                                String division, Set<String> roles, String token) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.isTempPassword = isTempPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.office = office;
        this.division = division;
        this.roles = roles;
        this.token = token;
    }
}
