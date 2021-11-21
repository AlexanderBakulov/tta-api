package com.bakulovas.tta.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginUserDtoRequest {

    @NotBlank
    private String loginName;
    @NotBlank
    private String password;

    public LoginUserDtoRequest() {
    }

    public LoginUserDtoRequest(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

}
