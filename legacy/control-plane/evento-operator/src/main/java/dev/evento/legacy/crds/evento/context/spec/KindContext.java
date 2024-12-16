package dev.evento.legacy.crds.evento.context.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.evento.legacy.crds.evento.typedef.Type;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;
import java.util.stream.Collectors;

@JsonDeserialize
@JsonInclude(JsonInclude.Include.NON_NULL)
@RegisterForReflection
public class KindContext {

  private String doc;
  private List<Type> objectTypes;

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public List<Type> getObjectTypes() {
    return objectTypes;
  }

  public void setObjectTypes(List<Type> objectTypes) {
    this.objectTypes = objectTypes;
  }

  @Override
  public String toString() {
    return "{ types=[ "+objectTypes.stream().map(Type::toString).collect(Collectors.joining(", "))+" ] }";
  }
}

