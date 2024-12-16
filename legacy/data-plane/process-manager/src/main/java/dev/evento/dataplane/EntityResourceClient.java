package dev.evento.dataplane;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "entity-service")
public interface EntityResourceClient {

    @GET
    @Path("/entity/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    String get(@PathParam("id") String id);
}
