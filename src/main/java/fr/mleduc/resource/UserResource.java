package fr.mleduc.resource;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.TEXT_HTML)
public class UserResource {

    @POST
    @PermitAll
    @Path("/login")
    public String login() {
        return "<h1>Login</h1>";
    }

}
