package com.bakulovas.tta.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class LoginUserDtoResponse {

    private long id;
    private String loginName;
    private String email;
    private boolean tempPassword;
    private String firstName;
    private String lastName;
    private boolean active;
    private String office;
    private String division;
    private Set<String> roles;
    private String token;

    public LoginUserDtoResponse() {
    }

    public LoginUserDtoResponse(long id, String loginName, String email, boolean tempPassword,
                                String firstName, String lastName, boolean active, String office,
                                String division, Set<String> roles, String token) {
        this.id = id;
        this.loginName = loginName;
        this.email = email;
        this.tempPassword = tempPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.office = office;
        this.division = division;
        this.roles = roles;
        this.token = token;
    }
}
