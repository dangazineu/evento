package dev.evento.controlplane.crds.v1alpha1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("managed")
public class ManagedMessaging extends Messaging {

    @JsonProperty("kafka")
    private Kafka kafka;
    @JsonProperty("zookeeper")
    private Zookeeper zookeeper;

    public Kafka getKafka() {
        return kafka;
    }

    public Zookeeper getZookeeper() {
        return zookeeper;
    }

    @Override
    public String toString() {
        return "{ kafka = "+ kafka +", zookeeper = "+zookeeper+" }";
    }
}
