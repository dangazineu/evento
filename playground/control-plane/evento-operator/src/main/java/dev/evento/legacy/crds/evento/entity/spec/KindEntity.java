package dev.evento.legacy.crds.evento.entity.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
public class KindEntity {

  private String doc;
  private List<?> fields;

  @Override
  public String toString() {
    return "{  }";
  }
}

