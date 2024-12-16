package dev.evento.legacy.crds;

import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.client.CustomResource;

import java.util.HashMap;
import java.util.Map;

public class DSL {

    public static <CR extends CustomResource, O extends CustomResource> CR addOwnerReference(CR cr, O owner) {
        OwnerReference ownerReference = new OwnerReference();
        ownerReference.setName(owner.getMetadata().getName());
        ownerReference.setUid(owner.getMetadata().getUid());
        ownerReference.setKind(owner.getKind());
        ownerReference.setApiVersion(owner.getApiVersion());
        cr.getMetadata().getOwnerReferences().add(ownerReference);
        return cr;
    }

    public static <D extends CustomResource, P extends CustomResource> D establishDependency(D dependent, P provider) {
        Map<String, String> labels = dependent.getMetadata().getLabels();
        if(labels == null) {
            labels = new HashMap<>();
            dependent.getMetadata().setLabels(labels);
        }
        labels.put("evento.dev/" + provider.getSingular(), provider.getMetadata().getName());
        return dependent;
    }
}
