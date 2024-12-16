package dev.evento.legacy.crds.strimzi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.evento.legacy.crds.strimzi.spec.KindKafka;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Group("kafka.strimzi.io")
@Version("v1beta2")
@Kind("Kafka")
@JsonDeserialize
@RegisterForReflection
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaResource extends CustomResource {

  private KindKafka spec;

  public KindKafka getSpec() {
    return spec;
  }

  public void setSpec(KindKafka spec) {
    this.spec = spec;
  }

  @Override
  public String toString() {
    String name = getMetadata() != null ? getMetadata().getName() : "unknown";
    String version = getMetadata() != null ? getMetadata().getResourceVersion() : "unknown";
    return "name=" + name + " version=" + version + " kafka=" + spec;
  }
}