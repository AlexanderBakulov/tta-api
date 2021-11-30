package com.bakulovas.tta.dto.request;


import com.bakulovas.tta.dto.validation.annotations.MinLength;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


import static com.bakulovas.tta.config.ServerConfig.*;

@Getter
@NoArgsConstructor
public class AddUserDtoRequest {

    @NotBlank
    @Size(min=1, max=32)
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
    @NotEmpty
    private String role;


    public AddUserDtoRequest(String login, String password, String email, String firstName,
                             String lastName, String office, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.office = office;
        this.role = role;
    }

}
