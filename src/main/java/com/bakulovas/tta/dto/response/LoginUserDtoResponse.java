package com.bakulovas.tta.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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
    private String role;
    private String token;

}
