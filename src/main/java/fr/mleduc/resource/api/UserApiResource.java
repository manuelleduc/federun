package fr.mleduc.resource.api;

import fr.mleduc.resource.api.model.User;
import fr.mleduc.resource.api.response.Result;
import fr.mleduc.service.user.UserService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

@Path("/api/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApiResource {

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed({"User"})
    public Map<String, String> me(@Context SecurityContext securityContext) {
        Map<String, String> ret = new HashMap<>();
        ret.put("name", securityContext.getUserPrincipal().getName());
        return ret;
    }


    @PUT
    @PermitAll
    public Result createUser(@Valid User user) {
        userService.createUser(user);
        return new Result("User " + user.username + " created.");
    }

}
