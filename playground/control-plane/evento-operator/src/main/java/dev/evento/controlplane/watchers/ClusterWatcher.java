package dev.evento.controlplane.watchers;

import dev.evento.controlplane.ManagedResourcesCache;
import dev.evento.controlplane.crds.Evento;
import dev.evento.controlplane.crds.v1alpha1.Cluster;
import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionVersion;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ClusterWatcher extends AbstractResourceWatcher {

    @Inject KubernetesClient k8s;
    @Inject @Named("namespace") String namespace;
    @Inject ManagedResourcesCache cache;

    public void watch () {

        k8s.resources(Cluster.class).inNamespace(namespace).watch(null);
        Log.debug("onStartup!!!!");
        Log.debug("Initializing ClusterController");
        List<CustomResourceDefinition> crds = k8s.apiextensions().v1().customResourceDefinitions().list().getItems()
                .stream()
                .filter(crd -> crd.getSpec().getGroup().equals(Evento.GROUP)) // all evento CRDs
                .filter(crd -> crd.getSpec().getNames().getKind().equals("Cluster")) //all Cluster CRD definitions
                .collect(Collectors.toList());

        for (CustomResourceDefinition clusterDefinition: crds) {
            for(CustomResourceDefinitionVersion version: clusterDefinition.getSpec().getVersions()) {
                Log.debugv("Encountered crd for {0} ", version.getName());
            }
        }
    }
}
