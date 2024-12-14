package dev.evento.dataplane;

import io.apicurio.registry.rest.beans.ArtifactMetaData;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.List;

/**
 * Replaces io.apicurio.registry.client.RegistryRestClient, as that class isn't compatible with Quarkus Native.
 */
@RegisterRestClient(configKey = "schema-repository")
@Path("/artifacts")
@Produces(MediaType.APPLICATION_JSON)
public interface SchemaRepositoryClient {

    @GET @Path("/")
    List<String> listArtifacts();

    @GET @Path("/{artifactId}")
    InputStream getLatestArtifact(@PathParam("artifactId") String artifactId);

    @GET @Path("/{artifactId}/meta")
    ArtifactMetaData getArtifactMetaData(@PathParam("artifactId") String artifactId);
}
