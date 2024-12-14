package dev.evento.legacy.operations;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Syntax sugar to avoid long generics declarations.
 * @param <T>
 * @param <L>
 * @param <R>
 */
public abstract class ResourceOperations<
        T extends HasMetadata,
        L extends KubernetesResourceList<T>,
        R extends Resource<T>
        > {

    private final NonNamespaceOperation<T, L, R> operation;
    private final String resourceNameSuffix;

    public ResourceOperations(NonNamespaceOperation<T,L,R> operation, String resourceNameSuffix) {
        this.operation = operation;
        this.resourceNameSuffix = resourceNameSuffix;
    }

    public NonNamespaceOperation<T,L,R> get() {
        return this.operation;
    }

    public L list() { return get().list(); }

    public Watch watch(Watcher<T> w) { return this.get().watch(w); }

    public Optional<T> findByOwnerUid(String uid) {
        return listByOwnerUid(uid).stream().findAny();
    }

    public List<T> listByOwnerUid(OwnerReference ownerReference) {
        return listByOwnerUid(ownerReference.getUid());
    }

    public List<T> listByOwnerUid(String ownerUid) {
        return this.get().list().getItems().stream()
                .filter(
                        cr-> cr.getMetadata().getOwnerReferences().stream().filter(
                                or -> ownerUid.equals(or.getUid())
                        ).count() > 0
                )
                .collect(Collectors.toList());
    }

    public Boolean deleteByOwnerUid(String uid) {
        return this.get().delete(listByOwnerUid(uid));
    }

    public <CR extends CustomResource> T findOrConstructForOwner(CR owner) {
        return findByOwnerUid(owner.getMetadata().getUid())
                .orElse(constructForOwner(owner));
    }

    // This is likely applicable to any resource to be constructed by the operator.
    // It may be moved to a different hierarchy.
    private <CR extends CustomResource> T constructForOwner(CR owner) {
        T customResource = construct();

        //TODO this code is duplicated because this use of method type parameters is not supported
        OwnerReference ownerReference = new OwnerReference();
        ownerReference.setName(owner.getMetadata().getName());
        ownerReference.setUid(owner.getMetadata().getUid());
        ownerReference.setKind(owner.getKind());
        ownerReference.setApiVersion(owner.getApiVersion());
        customResource.getMetadata().getOwnerReferences().add(ownerReference);

        customResource.getMetadata().setNamespace(owner.getMetadata().getNamespace());
        customResource.getMetadata().setName(owner.getMetadata().getName()+"-"+this.resourceNameSuffix);

        return customResource;
    }

    public T constructFromTemplate(String template) {
        System.out.println("Constructing from template " + template);
        InputStream is = getClass().getResourceAsStream(template);
        return get().load(is).get();
    }

    public abstract T construct();

}
