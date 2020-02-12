package fr.mleduc.service.user;

import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashProviderImpl implements HashProvider {
    @Override
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
