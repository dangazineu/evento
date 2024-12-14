package dev.evento.legacy.crds.evento.entity.spec;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.evento.legacy.crds.evento.cluster.spec.ExternalMessaging;
import dev.evento.legacy.crds.evento.cluster.spec.ManagedMessaging;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ExternalMessaging.class, name = "external"),
        @JsonSubTypes.Type(value = ManagedMessaging.class, name= "managed")
})
public abstract class FieldTypeDefinition { }
