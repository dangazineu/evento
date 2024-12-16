package dev.evento.legacy.crds.evento.context;

import dev.evento.legacy.crds.evento.context.spec.KindContext;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("evento.dev")
@Version("v1alpha1")
@Kind("Context")
public class ContextResource extends CustomResource {

  private KindContext spec;

  public KindContext getSpec() {
    return spec;
  }

  public void setSpec(KindContext spec) {
    this.spec = spec;
  }

  @Override
  public String toString() {
    String name = getMetadata() != null ? getMetadata().getName() : "unknown";
    String version = getMetadata() != null ? getMetadata().getResourceVersion() : "unknown";
    return "name=" + name + " version=" + version + " context=" + spec;
  }
}