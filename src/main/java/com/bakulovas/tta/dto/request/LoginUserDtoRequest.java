package com.bakulovas.tta.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginUserDtoRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public LoginUserDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
