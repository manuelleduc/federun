package fr.mleduc.service.user;

import com.auth0.jwt.interfaces.DecodedJWT;
import fr.mleduc.security.profile.Role;

public interface TokenProvider {
    DecodedJWT verify(String token);

    String createUserToken(String userId);

    Role[] extractRoles(DecodedJWT decodedJWT);
}
