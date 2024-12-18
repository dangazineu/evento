package dev.evento.controlplane.operator.example.model;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class ExampleResourceDoneable extends CustomResourceDoneable<ExampleResource> {

  public ExampleResourceDoneable(ExampleResource resource, Function<ExampleResource, ExampleResource> function) {
    super(resource, function);
  }
}