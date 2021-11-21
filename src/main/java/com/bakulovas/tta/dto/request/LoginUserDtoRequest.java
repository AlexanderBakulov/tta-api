package com.bakulovas.tta.dto.request;

import javax.validation.constraints.NotBlank;


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

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }
}
