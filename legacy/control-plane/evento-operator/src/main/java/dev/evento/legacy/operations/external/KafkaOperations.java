package dev.evento.legacy.operations.external;

import dev.evento.legacy.operations.ResourceOperations;
import dev.evento.legacy.crds.strimzi.KafkaResource;
import dev.evento.legacy.crds.strimzi.KafkaResourceList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

public class KafkaOperations extends ResourceOperations<
        KafkaResource,
        KafkaResourceList,
        Resource<KafkaResource>
        > {

    public KafkaOperations(NonNamespaceOperation<
            KafkaResource,
            KafkaResourceList,
            Resource<KafkaResource>> operation) {
        super(operation, "kafka");
    }

    @Override
    public KafkaResource construct() {
        return super.constructFromTemplate("/template.kafka.cr.yaml");
    }
}
