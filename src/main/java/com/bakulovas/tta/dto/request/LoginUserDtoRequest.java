package com.bakulovas.tta.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDtoRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
