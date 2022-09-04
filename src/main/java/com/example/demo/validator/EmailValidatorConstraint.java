package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidatorConstraint implements ConstraintValidator<EmailValidator,String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.isEmpty()){
            return false;
        }
        return true;
    }


}
