package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.MultipartForm;

import io.quarkus.logging.Log;

@Path("/gpx")
public class GpxUploadResource {
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/upload")
    public Response upload(@MultipartForm FormData formData) {
        Log.debug("UPLOAD " + formData.file.fileName());
        return Response.ok("{}").build();
    }
}
