package dev.evento.dataplane;

import io.apicurio.registry.client.RegistryRestClient;
import io.apicurio.registry.client.RegistryRestClientFactory;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecordMetadata;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("/command")
public class CommandService {

    @Inject @Channel("commands") Emitter<Integer> emitter;

    @Inject @RestClient SchemaRepositoryClient schemaRepositoryClient;

    @POST
    @Path("/{entityId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(@PathParam String entityId, String json) throws IOException {

        OutgoingKafkaRecordMetadata<String> metadata = OutgoingKafkaRecordMetadata.<String>builder()
            .withKey(entityId)
            .build();

        InputStream input = new ByteArrayInputStream(json.getBytes());
        DataInputStream din = new DataInputStream(input);

        String id = schemaRepositoryClient.listArtifacts().iterator().next();
        System.out.println("Artifact ID is  " + id);
        System.out.println("Artifact metadata is  " + schemaRepositoryClient.getArtifactMetaData(id));

        Schema schema = new Schema.Parser().parse(schemaRepositoryClient.getLatestArtifact(id));

        Decoder decoder = DecoderFactory.get().jsonDecoder(schema, din);
        DatumReader<GenericRecord> reader = new GenericDatumReader<GenericRecord>(schema);
        GenericRecord datum = reader.read(null, decoder);
        System.out.println("Operation is  " + datum.get("operation"));

        return json;
    }
}