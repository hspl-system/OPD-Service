package com.healthify.opdservice.util.validators.annotations;

import com.healthify.opdservice.util.validators.logic.PasswordValidatorImpl;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidatorImpl.class)
@Target({ElementType.FIELD})
public @interface PasswordValidator {
}
