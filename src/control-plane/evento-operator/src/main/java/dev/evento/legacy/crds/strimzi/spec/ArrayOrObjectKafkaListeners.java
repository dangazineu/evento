package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.util.List;

@JsonDeserialize(using = ArrayOrObjectKafkaListeners.Deserializer.class)
@JsonSerialize(using = ArrayOrObjectKafkaListeners.Serializer.class)
public class ArrayOrObjectKafkaListeners {

    private final List<GenericKafkaListener> genericKafkaListeners;
    private final KafkaListeners kafkaListeners;

    ArrayOrObjectKafkaListeners(List<GenericKafkaListener> genericKafkaListeners, KafkaListeners kafkaListeners)   {
        this.genericKafkaListeners = genericKafkaListeners;
        this.kafkaListeners = kafkaListeners;
    }

    @Deprecated
    public ArrayOrObjectKafkaListeners(KafkaListeners kafkaListeners)   {
        this(null, kafkaListeners);
    }

    public ArrayOrObjectKafkaListeners(List<GenericKafkaListener> genericKafkaListeners)   {
        this(genericKafkaListeners, null);
    }

    public List<GenericKafkaListener> getGenericKafkaListeners() {
        return genericKafkaListeners;
    }

    public KafkaListeners getKafkaListeners() {
        return kafkaListeners;
    }


    public static class Serializer extends JsonSerializer<ArrayOrObjectKafkaListeners> {
        @Override
        public void serialize(ArrayOrObjectKafkaListeners value, JsonGenerator generator, SerializerProvider provider) throws IOException {
            if (value == null)  {
                generator.writeNull();
                return;
            }

            if (value.genericKafkaListeners != null)    {
                generator.writeObject(value.genericKafkaListeners);
            } else if (value.kafkaListeners != null)  {
                generator.writeObject(value.kafkaListeners);
            } else {
                generator.writeNull();
            }
        }
    }

    public static class Deserializer extends JsonDeserializer<ArrayOrObjectKafkaListeners> {
        @Override
        public ArrayOrObjectKafkaListeners deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
            ObjectCodec oc = jsonParser.getCodec();

            if (jsonParser.currentToken() == JsonToken.START_ARRAY) {
                return new ArrayOrObjectKafkaListeners(oc.readValue(jsonParser, new TypeReference<List<GenericKafkaListener>>() { }));
            } else if (jsonParser.currentToken() == JsonToken.START_OBJECT) {
                return new ArrayOrObjectKafkaListeners(oc.readValue(jsonParser, new TypeReference<KafkaListeners>() { }));
            } else {
                throw new RuntimeException("Failed to deserialize ArrayOrObjectKafkaListeners. Please check .spec.kafka.listeners configuration.");
            }
        }
    }
}
