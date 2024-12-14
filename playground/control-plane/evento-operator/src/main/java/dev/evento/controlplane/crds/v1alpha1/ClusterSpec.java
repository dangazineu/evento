package dev.evento.controlplane.crds.v1alpha1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClusterSpec {

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

