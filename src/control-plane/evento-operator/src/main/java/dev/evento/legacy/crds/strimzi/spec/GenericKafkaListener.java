package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "port", "type", "tls", "authentication", "configuration", "networkPolicyPeers"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericKafkaListener {

    //REQUIRED
    private String name;
    private int port;
    private KafkaListenerType type;
    private boolean tls = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public KafkaListenerType getType() {
        return type;
    }

    public void setType(KafkaListenerType type) {
        this.type = type;
    }

    public boolean isTls() {
        return tls;
    }

    public void setTls(boolean tls) {
        this.tls = tls;
    }

    //    private KafkaListenerAuthentication auth;
//    private GenericKafkaListenerConfiguration configuration;
//    private List<NetworkPolicyPeer> networkPolicyPeers;
//    private Map<String, Object> additionalProperties;
}