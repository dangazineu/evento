package dev.evento.controlplane.crds.v1alpha2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("external")
public class ExternalMessaging extends Messaging {

    @JsonProperty("bootstrapServers")
    private String bootstrapServers;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    @Override
    public String toString() {
        return "{ external = { bootstrapServers = "+this.bootstrapServers+" } }";
    }
}
