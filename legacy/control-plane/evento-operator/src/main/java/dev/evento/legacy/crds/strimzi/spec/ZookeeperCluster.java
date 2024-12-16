package dev.evento.legacy.crds.strimzi.spec;

public class ZookeeperCluster {
    //REQUIRED
    private int replicas;
    protected SingleVolumeStorage storage;

    public int getReplicas() {
        return replicas;
    }

    public void setReplicas(int replicas) {
        this.replicas = replicas;
    }

    public SingleVolumeStorage getStorage() {
        return storage;
    }

    public void setStorage(SingleVolumeStorage storage) {
        this.storage = storage;
    }

    //    private String image;
//    private Map<String, Object> config = new HashMap<>(0);
//    private Affinity affinity;
//    private List<Toleration> tolerations;
//    private Probe livenessProbe;
//    private Probe readinessProbe;
//    private JvmOptions jvmOptions;
//    private ResourceRequirements resources;
//    private Map<String, Object> metrics;
//    private Logging logging;
//    private ZookeeperClusterTemplate template;
//    private TlsSidecar tlsSidecar;

}