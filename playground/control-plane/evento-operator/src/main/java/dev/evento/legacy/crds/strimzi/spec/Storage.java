package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
@JsonSubTypes({@JsonSubTypes.Type(
    value = EphemeralStorage.class,
    name = "ephemeral"
), @JsonSubTypes.Type(
    value = PersistentClaimStorage.class,
    name = "persistent-claim"
), @JsonSubTypes.Type(
    value = JbodStorage.class,
    name = "jbod"
)})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Storage {
    public static final String TYPE_EPHEMERAL = "ephemeral";
    public static final String TYPE_PERSISTENT_CLAIM = "persistent-claim";
    public static final String TYPE_JBOD = "jbod";
    private Map<String, Object> additionalProperties = new HashMap<>(0);

    public Storage() {}

    public abstract String getType();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public static boolean deleteClaim(Storage storage) {
        return storage instanceof PersistentClaimStorage && ((PersistentClaimStorage) storage).isDeleteClaim();
    }

    public static String storageClass(Storage storage) {
        return storage instanceof PersistentClaimStorage ? ((PersistentClaimStorage) storage).getStorageClass() : null;
    }
}