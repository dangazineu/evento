package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum KafkaListenerType {
    INTERNAL,
    ROUTE,
    LOADBALANCER,
    NODEPORT,
    INGRESS;

    @JsonCreator
    public static KafkaListenerType forValue(String value) {
        switch (value) {
            case "internal":
                return INTERNAL;
            case "route":
                return ROUTE;
            case "loadbalancer":
                return LOADBALANCER;
            case "nodeport":
                return NODEPORT;
            case "ingress":
                return INGRESS;
            default:
                return null;
        }
    }

    @JsonValue
    public String toValue() {
        switch (this) {
            case INTERNAL:
                return "internal";
            case ROUTE:
                return "route";
            case LOADBALANCER:
                return "loadbalancer";
            case NODEPORT:
                return "nodeport";
            case INGRESS:
                return "ingress";
            default:
                return null;
        }
    }
}