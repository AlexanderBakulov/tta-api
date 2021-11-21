package com.bakulovas.tta.dto.validation;

import com.bakulovas.tta.config.ServerConfiguration;
import com.bakulovas.tta.dto.validation.annotations.MinLength;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MinLengthValidator implements ConstraintValidator<MinLength, String> {

    private final ServerConfiguration serverConfiguration;

    @Autowired
    public MinLengthValidator(ServerConfiguration serverConfiguration) {
        this.serverConfiguration = serverConfiguration;
    }

    @Override
    public void initialize(MinLength minLength) {
    }

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate("Length must be lager or equals then " + serverConfiguration.getMinPasswordLength()).addConstraintViolation();
        return field.length() >= serverConfiguration.getMinPasswordLength();

    }

}