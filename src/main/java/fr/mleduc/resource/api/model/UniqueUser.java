package fr.mleduc.resource.api.model;

import fr.mleduc.service.user.UniqueUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserValidator.class)
public @interface UniqueUser {
    String message() default "username must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
