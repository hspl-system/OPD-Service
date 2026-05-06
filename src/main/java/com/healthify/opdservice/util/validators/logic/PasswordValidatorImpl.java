package com.healthify.opdservice.util.validators.logic;

import com.healthify.opdservice.util.validators.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidatorImpl implements ConstraintValidator<PasswordValidator,String> {

    @Override
    public boolean isValid(String password , ConstraintValidatorContext context){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$");
    }
}
