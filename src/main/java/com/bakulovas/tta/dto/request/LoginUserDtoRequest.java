package com.bakulovas.tta.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginUserDtoRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public LoginUserDtoRequest() {
    }

    public LoginUserDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
