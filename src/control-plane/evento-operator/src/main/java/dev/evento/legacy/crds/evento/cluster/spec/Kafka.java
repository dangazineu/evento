package dev.evento.legacy.crds.evento.cluster.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Kafka {

    @JsonProperty("replicas") private Integer replicas;
    public Integer getReplicas() { return replicas; }

    @Override public String toString() { return "{ replicas = " + replicas + " }"; }

}
