package io.github.RafaGois.validation.constraintvalidation;

import io.github.RafaGois.validation.NotEmpityList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class NotEmpityListValidation implements ConstraintValidator <NotEmpityList, List> {
    @Override
    public void initialize(NotEmpityList constraintAnnotation) {
        constraintAnnotation.message();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
