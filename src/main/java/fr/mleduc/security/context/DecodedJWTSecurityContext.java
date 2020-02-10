package fr.mleduc.security.context;

import com.auth0.jwt.interfaces.DecodedJWT;
import fr.mleduc.security.profile.Role;
import fr.mleduc.service.user.TokenProvider;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class DecodedJWTSecurityContext implements SecurityContext {

  private DecodedJWT decodedJWT;
  private TokenProvider tokenProvider;

  public DecodedJWTSecurityContext(DecodedJWT decodedJWT, TokenProvider tokenProvider) {
    this.decodedJWT = decodedJWT;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public Principal getUserPrincipal() {
    return decodedJWT::getSubject;
  }

  @Override
  public boolean isUserInRole(String role) {
    Role[] tokenRoles = tokenProvider.extractRoles(decodedJWT);
    for (Role tokenRole : tokenRoles) {
      if (role.equals(tokenRole.name())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isSecure() {
    return false;
  }

  @Override
  public String getAuthenticationScheme() {
    return null;
  }
}
