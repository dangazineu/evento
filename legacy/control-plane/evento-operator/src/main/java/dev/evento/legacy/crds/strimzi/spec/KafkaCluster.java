package dev.evento.legacy.crds.strimzi.spec;

import java.util.HashMap;
import java.util.Map;

public class KafkaCluster {
    private int replicas;
    protected Storage storage;
    private ArrayOrObjectKafkaListeners listeners;
    private Map<String, Object> config = new HashMap<>(0);

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public ArrayOrObjectKafkaListeners getListeners() {
        return listeners;
    }

    public void setListeners(ArrayOrObjectKafkaListeners listeners) {
        this.listeners = listeners;
    }

    //    private String version;

//    private String brokerRackInitImage;
//    private Rack rack;
//    private Logging logging;
//    private TlsSidecar tlsSidecar;

//    private String image;
//    private ResourceRequirements resources;
//    private Probe livenessProbe;
//    private Probe readinessProbe;
//    private JvmOptions jvmOptions;
//    private KafkaJmxOptions jmxOptions;
//    private Map<String, Object> metrics;
//    private Affinity affinity;
//    private List<Toleration> tolerations;

//    private KafkaAuthorization authorization;
//    private KafkaClusterTemplate template;
}