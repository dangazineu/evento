package dev.evento.legacy.operations.external;

/*
 TODO: This class manipulates apicurio deployments directly.
 It should be creating custom resources instead, and leaving specialized resource manipulation to the apicurio operator.
 */

import dev.evento.legacy.operations.ResourceOperations;
import dev.evento.legacy.crds.apicurio.ApicurioRegistryResource;
import dev.evento.legacy.crds.apicurio.ApicurioRegistryResourceList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

public class ApicurioRegistryOperations extends
        ResourceOperations<
                                ApicurioRegistryResource,
                                ApicurioRegistryResourceList,
                            Resource<ApicurioRegistryResource>
                        > {


    public ApicurioRegistryOperations(
            NonNamespaceOperation<
                    ApicurioRegistryResource,
                    ApicurioRegistryResourceList,
                    Resource<ApicurioRegistryResource>
            > operation) {

        super(operation, "apicurioregistry");
    }

    @Override
    public ApicurioRegistryResource construct() {
        return super.constructFromTemplate("/template.apicurioregistry.cr.yaml");
    }
}
