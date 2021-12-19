package com.bakulovas.tta.api.dto.validation.annotations;


import com.bakulovas.tta.api.dto.validation.MinLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MinLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {

    String message() default "Name is too short";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
