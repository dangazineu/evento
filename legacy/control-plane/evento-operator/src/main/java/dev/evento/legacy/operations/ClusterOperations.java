package dev.evento.legacy.operations;

import dev.evento.legacy.crds.evento.cluster.EventoResource;
import dev.evento.legacy.crds.evento.cluster.EventoResourceList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

public class ClusterOperations extends ResourceOperations<
        EventoResource,
        EventoResourceList,
        Resource<EventoResource>
        > {

    public ClusterOperations(NonNamespaceOperation<
            EventoResource,
            EventoResourceList,
            Resource<EventoResource>> operation) {
        super(operation, "evento");
    }

    @Override
    public EventoResource construct() {
        return new EventoResource();
    }
}
