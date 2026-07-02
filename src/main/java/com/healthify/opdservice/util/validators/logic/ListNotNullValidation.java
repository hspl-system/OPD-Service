package com.healthify.opdservice.util.validators.logic;

import jakarta.validation.ConstraintValidator;
import com.healthify.opdservice.util.validators.annotations.*;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListNotNullValidation implements ConstraintValidator<ListNotNull, List<?>> {

    @Override
    public boolean isValid(List<?> list, ConstraintValidatorContext context){

        return list!=null && list.size()>=1;
    }

}
