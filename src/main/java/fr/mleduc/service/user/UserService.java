package fr.mleduc.service.user;

import fr.mleduc.expt.EmailAlreadyExistsException;
import fr.mleduc.expt.InvalidPasswordException;
import fr.mleduc.expt.UserNotFoundException;
import fr.mleduc.expt.UsernameAlreadyExistsException;
import fr.mleduc.panache.FederunUser;

public interface UserService {
    FederunUser login(String email, String password) throws UserNotFoundException, InvalidPasswordException;

    FederunUser create(String username, String email, String password) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
}
