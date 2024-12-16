package dev.evento.legacy.crds.evento.cluster.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
public class KindEvento {

  @JsonProperty("messaging")
  private Messaging messaging;

  public Messaging getMessaging() {
    return messaging;
  }

  @Override
  public String toString() {
    return "{ messaging = " + messaging + " }";
  }
}

