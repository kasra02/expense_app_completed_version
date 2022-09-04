package com.example.demo.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy =EmailValidatorConstraint.class)
@Documented
@Email
public @interface EmailValidator {
    String message() default "{email.notempty}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
