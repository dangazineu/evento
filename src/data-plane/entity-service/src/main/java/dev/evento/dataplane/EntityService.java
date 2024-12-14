package dev.evento.dataplane;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.eclipse.microprofile.config.ConfigProvider;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Path("/entity")
public class EntityService {

    public static final String KAFKA_GROUP_ID = "entity-service";

    private final Properties BASE_CONFIG;
    private final String EVENTS_TOPIC;

    public EntityService() {
        String kafkaBootstrapServers = ConfigProvider.getConfig().getValue("kafka.bootstrap.servers", String.class);
        EVENTS_TOPIC = ConfigProvider.getConfig().getValue("EVENTS_TOPIC", String.class);
        System.out.println("EVENTS_TOPIC = " + EVENTS_TOPIC);

        BASE_CONFIG = new Properties();
        BASE_CONFIG.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_GROUP_ID);
        BASE_CONFIG.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        BASE_CONFIG.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        BASE_CONFIG.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        BASE_CONFIG.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
    }

    @GET
    @Path("/{entityId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String get(@PathParam String entityId) {
        Properties config = (Properties) BASE_CONFIG.clone();
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        config.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, UUID.randomUUID().toString());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(config);
        Integer value = 0;
        try {

            /*
             TODO revisit this: it seems odd that I need to list all partitions,
              then convert them from TopicInfo to TopicPartition objects, and then
              assign them to my consumer for it to be able to consume from all partitions.
              But when I attempted to just call consumer.subscribe("events"),
              poll didn't return any results. Need to investigate this further, for now this will do.
             */
            Set<TopicPartition> partitions = consumer.partitionsFor(EVENTS_TOPIC).stream()
                    .map(info -> new TopicPartition(info.topic(), info.partition()))
                    .collect(Collectors.toSet());

            consumer.assign(partitions);

            consumer.seekToEnd(partitions);
            Map<TopicPartition, Long> offsets = partitions.stream()
                    .map(tp -> Map.entry(tp, consumer.position(tp)))
                    .peek(k -> System.out.println("Topic "+k.getKey() + " is at offset "+k.getValue()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            consumer.seekToBeginning(partitions);

            ConsumerRecords<String, String> records;

            POLLING: while(true) {
                records = consumer.poll(Duration.ofMillis(0));
                System.out.println("Polled " + records.count());
                value = StreamSupport.stream(records.spliterator(), false)
                        .sequential()
                        .filter(r -> entityId.equals(r.key()))
                        .map(r -> r.value())
                        .reduce(
                                value,
                                (accumulator, element) -> accumulator + element.length(), //just counting length of string for now
                                (acc1, acc2) -> {
                                    throw new IllegalStateException("sequential stream, combiner should never be called");
                                }
                        );
                for(Map.Entry<TopicPartition, Long> offset : offsets.entrySet()) {
                    long position = consumer.position(offset.getKey());
                    if(position < offset.getValue()) {
                        //still has work to do
                        System.out.println(
                                "Decided to continue polling because " + offset.getKey()
                                        + " is at " + position
                                        + " expected to reach " + offset.getValue()
                        );
                        continue POLLING;
                    }
                }
                break;
            }
        }finally {
            consumer.assign(Collections.emptyList());
        }
        return "Entity " +entityId +" has accumulated " + value + " chars so far";
    }
}