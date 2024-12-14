package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"class"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersistentClaimStorageOverride {

    private Integer broker;
    private String storageClass;
    private Map<String, Object> additionalProperties = new HashMap<>(0);

    public Integer getBroker() {
        return broker;
    }

    public void setBroker(Integer broker) {
        this.broker = broker;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}