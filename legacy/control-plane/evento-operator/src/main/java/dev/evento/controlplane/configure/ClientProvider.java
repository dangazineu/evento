package dev.evento.controlplane.configure;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.quarkus.logging.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ClientProvider {

    @ConfigProperty(name = "override.namespace")
    public Optional<String> namespaceOverride;

    @Singleton
    @Named("namespace")
    public String loadNamespace() throws IOException {
        return namespaceOverride.orElse(new String(
                Files.readAllBytes(Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/namespace")))
        );
    }

    @Singleton @Named("k8s")
    NamespacedKubernetesClient k8s(@Named("namespace") String namespace) {
        Log.debugv("Initializing DefaultKubernetesClient");
        return new DefaultKubernetesClient().inNamespace(namespace);
    }



//    @Produces
//    @Singleton
//    ClusterController clusterController(@Named("namespace") String namespace, KubernetesClient k8s) {
//        List<CustomResourceDefinition> crds = k8s.apiextensions().v1().customResourceDefinitions().list().getItems()
//                .stream()
//                .filter(crd -> crd.getSpec().getGroup().equals(Evento.GROUP)) // all evento CRDs
//                .filter(crd -> crd.getSpec().getNames().getKind().equals("Cluster")) //all Cluster CRD definitions
//                .collect(Collectors.toList());
//
//        for (CustomResourceDefinition clusterDefinition: crds) {
//
//        }
//        //return k8s.resources(Cluster.class).inNamespace(namespace);
//        return null;
//    }
}
