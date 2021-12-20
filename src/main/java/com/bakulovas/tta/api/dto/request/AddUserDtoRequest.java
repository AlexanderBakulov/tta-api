package com.bakulovas.tta.api.dto.request;


import com.bakulovas.tta.api.dto.validation.annotations.MinLength;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


import static com.bakulovas.tta.config.StaticData.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDtoRequest {

    @NotBlank
    @Size(min=2, max=32)
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
    @Size(min=1, max=32)
    private String role;


}
