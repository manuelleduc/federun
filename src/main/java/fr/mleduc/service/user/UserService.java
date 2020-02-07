package fr.mleduc.service.user;

import fr.mleduc.panache.FederunUser;
import fr.mleduc.resource.api.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Transactional
    public void createUser(User user) {
        FederunUser federunUserPanache = new FederunUser();
        federunUserPanache.login = user.username;
        federunUserPanache.password = user.password;
        federunUserPanache.persist();
    }
}
