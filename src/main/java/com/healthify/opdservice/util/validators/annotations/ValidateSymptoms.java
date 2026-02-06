package com.healthify.opdservice.util.validators.annotations;


import com.healthify.opdservice.util.validators.logic.ValidateSymptomsImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = ValidateSymptomsImpl.class)
public @interface ValidateSymptoms {
    String message() default "add atleast 2 comma separated symptoms";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
