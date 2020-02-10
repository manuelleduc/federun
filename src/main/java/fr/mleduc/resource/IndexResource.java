package fr.mleduc.resource;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @Inject
    Template index;

    @GET
    @PermitAll
    public TemplateInstance index() {
        return index.instance();
    }
}
