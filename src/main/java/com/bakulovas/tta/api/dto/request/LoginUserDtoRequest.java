package com.bakulovas.tta.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginUserDtoRequest {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

}
