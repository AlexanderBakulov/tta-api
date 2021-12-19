package com.bakulovas.tta.api.dto.validation;

import com.bakulovas.tta.config.ServerConfig;
import com.bakulovas.tta.api.dto.validation.annotations.MinLength;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MinLengthValidator implements ConstraintValidator<MinLength, String> {

    private final ServerConfig serverConfig;

    @Autowired
    public MinLengthValidator(ServerConfig serverConfiguration) {
        this.serverConfig = serverConfiguration;
    }

    @Override
    public void initialize(MinLength minLength) {
    }

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        cxt.buildConstraintViolationWithTemplate("Length must be lager or equals then " + serverConfig.getMinPasswordLength()).addConstraintViolation();
        return field.length() >= serverConfig.getMinPasswordLength();

    }

}
