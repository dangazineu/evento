package dev.evento.legacy.crds.apicurio;

import dev.evento.legacy.crds.apicurio.spec.KindApicurioRegistry;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;

@Group("registry.apicur.io")
@Version("v1")
@Kind("ApicurioRegistry")
public class ApicurioRegistryResource extends CustomResource {

  private KindApicurioRegistry spec;

  public KindApicurioRegistry getSpec() {
    return spec;
  }

  public void setSpec(KindApicurioRegistry spec) {
    this.spec = spec;
  }

  @Override
  public String toString() {
    String name = getMetadata() != null ? getMetadata().getName() : "unknown";
    String version = getMetadata() != null ? getMetadata().getResourceVersion() : "unknown";
    return "name=" + name + " version=" + version + " apicurio=" + spec;
  }
}