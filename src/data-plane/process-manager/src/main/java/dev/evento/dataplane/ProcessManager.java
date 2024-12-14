package dev.evento.dataplane;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.IncomingKafkaRecordMetadata;
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecordMetadata;
import org.eclipse.microprofile.reactive.messaging.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class ProcessManager {

    @Inject @Channel("events") Emitter<String> emitter;
    @Inject @RestClient EntityResourceClient entityService;

    //TODO is this blocking?
    @Incoming("commands")
    public CompletionStage<Void> consume(Message<Integer> command) throws ExecutionException, InterruptedException {
        IncomingKafkaRecordMetadata<String, Integer> meta = command.getMetadata(IncomingKafkaRecordMetadata.class).get();
        String entityId = meta.getKey();

        //TODO exception handling
        String currentLength = entityService.get(entityId);
        System.out.println(currentLength);

        String event = "Entity " + entityId + " has current length of "+currentLength.length() +" and payload " + command.getPayload();
        OutgoingKafkaRecordMetadata<String> metadata = OutgoingKafkaRecordMetadata.<String>builder()
                .withKey(meta.getKey())
                .build();

        System.out.println(event);

        emitter.send(
                Message.of(
                        event,
                        Metadata.of(metadata)
                )
        );

        return command.ack();
    }
}