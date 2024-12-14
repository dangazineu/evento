package dev.evento.legacy.crds.evento.cluster.spec;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonSubTypes({
    @JsonSubTypes.Type(value = ExternalMessaging.class, name = "external"),
    @JsonSubTypes.Type(value = ManagedMessaging.class, name= "managed")
})
public abstract class Messaging {}