package fr.mleduc.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.mleduc.expt.NotImplementedException;
import fr.mleduc.security.profile.Role;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class TokenProviderImpl implements TokenProvider {


    private final String COMPLEMENTARY_SUBSCRIPTION = "complementary-subscription";
    private final String CLAIM_ROLES = "ROLES";
    private Algorithm algorithm;
    private JWTVerifier jwtVerifier;
    private String issuer;
    private Integer expirationTimeInMinutes;

    @Override
    public DecodedJWT verify(String token) {
        return jwtVerifier.verify(token);
    }

    @Inject
    public TokenProviderImpl(
            @ConfigProperty(name = "jwt.issuer") String issuer,
            @ConfigProperty(name = "jwt.secret") String secret,
            @ConfigProperty(name = "jwt.expiration.time.minutes") Integer expirationTimeInMinutes) {

        this.issuer = issuer;
        this.algorithm = Algorithm.HMAC512(secret);
        this.jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
        this.expirationTimeInMinutes = expirationTimeInMinutes;
    }

    @Override
    public String createUserToken(String userId) {
        JWTCreator.Builder builder;

        builder = JWT.create()
                .withIssuer(issuer)
                .withSubject(userId)
                .withIssuedAt(new Date())
                .withClaim(COMPLEMENTARY_SUBSCRIPTION, UUID.randomUUID().toString());

        builder.withArrayClaim(CLAIM_ROLES, toArrayNames(Role.USER));

        if (expirationTimeInMinutes != null) {
            builder.withExpiresAt(plusMinutes(expirationTimeInMinutes));
        }

        return builder.sign(algorithm);
    }

    @Override
    public Role[] extractRoles(DecodedJWT decodedJWT) {
        Claim claim = decodedJWT.getClaim(CLAIM_ROLES);
        return claim.asArray(Role.class);
    }

    private String[] toArrayNames(final Role... allowedRoles) {
        final String[] ret = new String[allowedRoles.length];
        for (int i = 0; i < allowedRoles.length; i++) ret[i] = allowedRoles[i].name();
        return ret;
    }

    private Date plusMinutes(Integer minutes) {
        int oneMinuteInMillis = 60000;
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTimeInMillis() + (minutes * oneMinuteInMillis));
    }
}
