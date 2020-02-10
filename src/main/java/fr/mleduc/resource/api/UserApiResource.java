package fr.mleduc.resource.api;

import fr.mleduc.expt.EmailAlreadyExistsException;
import fr.mleduc.expt.InvalidPasswordException;
import fr.mleduc.expt.UserNotFoundException;
import fr.mleduc.expt.UsernameAlreadyExistsException;
import fr.mleduc.model.api.request.LoginRequest;
import fr.mleduc.panache.FederunUser;
import fr.mleduc.resource.api.request.NewUserRequest;
import fr.mleduc.resource.api.response.UserResponse;
import fr.mleduc.security.annotation.Secured;
import fr.mleduc.security.profile.Role;
import fr.mleduc.service.user.UserService;
import fr.mleduc.utils.ValidationMessages;
import io.quarkus.security.UnauthorizedException;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApiResource {

    @Inject
    UserService userService;

    @GET
    @Secured({Role.USER})
    public Map<String, String> me(@Context SecurityContext securityContext) {
        Map<String, String> ret = new HashMap<>();
        Principal userPrincipal = securityContext.getUserPrincipal();
        String name = userPrincipal.getName();
        ret.put("name", name);
        return ret;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_BE_NOT_NULL)
                    NewUserRequest newUserRequest,
            @Context SecurityException context) {
        FederunUser createdUser = null;
        try {
            createdUser = userService.create(
                    newUserRequest.getUsername(), newUserRequest.getEmail(), newUserRequest.getPassword());
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
        } catch (EmailAlreadyExistsException e) {
            e.printStackTrace();
        }
        return Response.ok(new UserResponse(createdUser)).status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/login")
    public Response login(@Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_BE_NOT_NULL) LoginRequest loginRequest) {
        FederunUser existingUser;
        try {
            existingUser = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        } catch (UserNotFoundException | InvalidPasswordException ex) {
            throw new UnauthorizedException();
        }
        return Response.ok(new UserResponse(existingUser)).status(Response.Status.OK).build();
    }
}
