package fr.mleduc.service.user;

import fr.mleduc.expt.EmailAlreadyExistsException;
import fr.mleduc.expt.InvalidPasswordException;
import fr.mleduc.expt.UserNotFoundException;
import fr.mleduc.expt.UsernameAlreadyExistsException;
import fr.mleduc.panache.FederunUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    HashProvider hashProvider;
    @Inject
    TokenProvider tokenProvider;


    @Override
    @Transactional
    public FederunUser login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        String upperEmail = email.toUpperCase().trim();
        FederunUser user = FederunUser.<FederunUser>find("upper(email)", upperEmail)
                .firstResultOptional().orElseThrow(UserNotFoundException::new);

        if (isPasswordInvalid(password, user.password)) {
            throw new InvalidPasswordException();
        }

        user.token = createToken(user);
        return user;
    }

    @Override
    @Transactional
    public FederunUser create(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        checkExistingUsername(username);
        checkExistingEmail(email);

        FederunUser user = new FederunUser();
        user.login = username.trim();
        user.email = email.toUpperCase().trim();
        user.password = hashProvider.hashPassword(password);

        user.persist();
        user.token = createToken(user);

        return user;
    }

    private void checkExistingEmail(String email) throws EmailAlreadyExistsException {
        if (FederunUser.count("upper(email)", email.toUpperCase().trim()) > 0) {
            throw new EmailAlreadyExistsException();
        }
    }

    private void checkExistingUsername(String username) throws UsernameAlreadyExistsException {
        if (FederunUser.count("upper(login)", username.toUpperCase().trim()) > 0) {
            throw new UsernameAlreadyExistsException();
        }
    }

    private String createToken(FederunUser user) {
        return tokenProvider.createUserToken(user.id.toString());
    }

    private boolean isPasswordInvalid(String password, String hashedPassword) {
        return !hashProvider.checkPassword(password, hashedPassword);
    }
}
