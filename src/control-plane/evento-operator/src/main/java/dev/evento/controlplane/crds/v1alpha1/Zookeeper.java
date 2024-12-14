package dev.evento.controlplane.crds.v1alpha1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Zookeeper {

    @JsonProperty("replicas") private Integer replicas;
    public Integer getReplicas() { return replicas; }

    @Override public String toString() { return "{ replicas = " + replicas + " }"; }
}
