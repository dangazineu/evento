package dev.evento.legacy.operations;

import dev.evento.legacy.crds.evento.context.ContextResource;
import dev.evento.legacy.crds.evento.context.ContextResourceList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

public class ContextOperations extends ResourceOperations<
        ContextResource,
        ContextResourceList,
        Resource<ContextResource>
        > {

    public ContextOperations(NonNamespaceOperation<
            ContextResource,
            ContextResourceList,
            Resource<ContextResource>> operation) {
        super(operation, "evento-ctx");
    }

    @Override
    public ContextResource construct() {
        return new ContextResource();
    }
}
