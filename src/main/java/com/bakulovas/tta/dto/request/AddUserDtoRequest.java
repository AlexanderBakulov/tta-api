package com.bakulovas.tta.dto.request;


import com.bakulovas.tta.dto.validation.annotations.MinLength;
import lombok.Getter;

import javax.validation.constraints.*;

import java.util.Set;

import static com.bakulovas.tta.config.ServerConfiguration.*;

@Getter
public class AddUserDtoRequest {

    @NotBlank
    @Size(min=1, max=30)
    @Pattern(regexp = NAME_VALIDATION_REGEXP, message = INVALID_LOGIN)
    private String login;
    @NotBlank
    @MinLength
    @Pattern(regexp = PASSWORD_VALIDATION_REGEXP, message = INVALID_PASSWORD)
    private String password;
    @NotBlank
    @Email
    @Size(min=1, max=64)
    private String email;
    @NotBlank
    @Size(min=1, max=32)
    private String firstName;
    @NotBlank
    @Size(min=1, max=32)
    private String lastName;
    @NotBlank
    @Size(min=1, max=5)
    private String office;
    @NotBlank
    @Size(min=1, max=32)
    private String division;
    @NotEmpty
    private Set<String> roles;

    public AddUserDtoRequest() {
    }

    public AddUserDtoRequest(String login, String password, String email, String firstName,
                             String lastName, String office, String division, Set<String> roles) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.division = division;
        this.roles = roles;
    }

}
