package com.healthify.opdservice.util.validators.annotations;

import com.healthify.opdservice.util.validators.logic.ListNotNullValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ListNotNullValidation.class)
@Target({ElementType.FIELD})

public @interface ListNotNull {
    String message() default "List can't be null or empty size must me atleast {min}";
    int min() default 1;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
