package com.healthify.opdservice.util.validators.annotations;

import com.healthify.opdservice.util.validators.logic.PasswordValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidatorImpl.class)
@Target({ElementType.FIELD})
public @interface PasswordValidator {
    String message() default "password must have atleast 1 spl char, 1 digit, 1 capital letter , 1 small letter, length min 8";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
