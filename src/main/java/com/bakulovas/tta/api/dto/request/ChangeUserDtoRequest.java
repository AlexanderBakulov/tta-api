package com.bakulovas.tta.api.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeUserDtoRequest {

    @Email
    @Size(min=1, max=64)
    private String email;
    @Size(min=1, max=32)
    private String firstName;
    @Size(min=1, max=32)
    private String lastName;
    @Size(min=1, max=5)
    private String office;
    @Size(min=1, max=32)
    private String role;


}
