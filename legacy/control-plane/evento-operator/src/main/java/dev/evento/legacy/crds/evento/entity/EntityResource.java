package dev.evento.legacy.crds.evento.entity;

import dev.evento.legacy.crds.evento.entity.spec.KindEntity;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("evento.dev")
@Version("v1alpha1")
@Kind("Entity")
public class EntityResource extends CustomResource {

  private KindEntity spec;

  public KindEntity getSpec() {
    return spec;
  }

  public void setSpec(KindEntity spec) {
    this.spec = spec;
  }

  @Override
  public String toString() {
    String name = getMetadata() != null ? getMetadata().getName() : "unknown";
    String version = getMetadata() != null ? getMetadata().getResourceVersion() : "unknown";
    return "name=" + name + " version=" + version + " entity=" + spec;
  }
}