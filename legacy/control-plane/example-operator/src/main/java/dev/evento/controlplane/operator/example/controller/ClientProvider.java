package dev.evento.controlplane.operator.example.controller;

import dev.evento.controlplane.operator.example.model.ExampleResource;
import dev.evento.controlplane.operator.example.model.ExampleResourceDoneable;
import dev.evento.controlplane.operator.example.model.ExampleResourceList;

import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import jdk.jfr.Name;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientProvider {

  @Singleton
  @Named("namespace")
  String findNamespace() throws IOException {
    return new String(Files.readAllBytes(Paths.get("/var/run/secrets/kubernetes.io/serviceaccount/namespace")));
  }

  @Produces
  @Singleton
  KubernetesClient newClient(@Named("namespace") String namespace) {
    System.out.println("Building default K8S client in namespace " + namespace);
    return new DefaultKubernetesClient().inNamespace(namespace);
  }

  @Produces
  @Singleton
  NonNamespaceOperation<ExampleResource, ExampleResourceList, ExampleResourceDoneable, Resource<ExampleResource, ExampleResourceDoneable>> makeCustomResourceClient(KubernetesClient defaultClient, @Named("namespace") String namespace) {

    System.out.println("makeCustomResourceClient was called, will it find the CRD?");
    KubernetesDeserializer.registerCustomKind("evento.dev/v1alpha1", "Example", ExampleResource.class);

    CustomResourceDefinition crd = defaultClient
            .customResourceDefinitions()
            .list()
            .getItems()
            .stream()
            .peek(_crd-> System.out.println("This is one of the CRDs the default client found " + _crd.getMetadata().getName()))
            .filter(d -> "examples.evento.dev".equals(d.getMetadata().getName()))
            .findAny()
            .orElseThrow(
                    () -> new RuntimeException("Deployment error: Custom resource definition examples.instana.com not found."));

    System.out.println("CRD found! What a great day!");
    return defaultClient
            .customResources(crd, ExampleResource.class, ExampleResourceList.class, ExampleResourceDoneable.class)
            .inNamespace(namespace);
  }
}