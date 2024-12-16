package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EphemeralStorage.class, name = Storage.TYPE_EPHEMERAL),
        @JsonSubTypes.Type(value = PersistentClaimStorage.class, name = Storage.TYPE_PERSISTENT_CLAIM)}
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class SingleVolumeStorage extends Storage {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}