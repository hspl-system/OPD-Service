package com.healthify.opdservice.util.validators.logic;

import com.healthify.opdservice.util.validators.annotations.ListNotNull;
import com.healthify.opdservice.util.validators.annotations.ValidateSymptoms;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ValidateSymptomsImpl implements ConstraintValidator<ValidateSymptoms, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value.matches(".*,.*,.*");

    }
}
