package fr.mleduc.service.user;

import fr.mleduc.panache.FederunUser;
import fr.mleduc.resource.api.model.UniqueUser;
import fr.mleduc.resource.api.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueUserValidator implements ConstraintValidator<UniqueUser, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        return FederunUser.count("login", user.username) == 0;
    }
}
