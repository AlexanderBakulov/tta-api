package com.bakulovas.tta.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserDtoResponse {

    private long id;
    private String login;
    private String email;
    private boolean isTempPassword;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private boolean isOnDuty;
    private String office;
    private String role;


    public UserDtoResponse(long id, String login, String email, boolean isTempPassword,
                           String firstName, String lastName, boolean isActive, boolean isOnDuty,
                           String office, String role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.isTempPassword = isTempPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.isOnDuty = isOnDuty;
        this.office = office;
        this.role = role;
    }
}
