package io.github.RafaGois.validation;

import io.github.RafaGois.validation.constraintvalidation.NotEmpityListValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmpityListValidation.class)
public @interface NotEmpityList {

    String message() default "Alista não pode ser vazia, BOCÓ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
