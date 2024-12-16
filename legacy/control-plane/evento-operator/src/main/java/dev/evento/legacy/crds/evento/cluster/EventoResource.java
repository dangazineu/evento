package dev.evento.legacy.crds.evento.cluster;

import dev.evento.legacy.crds.evento.cluster.spec.KindEvento;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("evento.dev")
@Version("v1alpha1")
@Kind("Evento")
public class EventoResource extends CustomResource {

  private KindEvento spec;

  public KindEvento getSpec() {
    return spec;
  }

  public void setSpec(KindEvento spec) {
    this.spec = spec;
  }

  @Override
  public String toString() {
    String name = getMetadata() != null ? getMetadata().getName() : "unknown";
    String version = getMetadata() != null ? getMetadata().getResourceVersion() : "unknown";
    return "name=" + name + " version=" + version + " evento=" + spec;
  }
}