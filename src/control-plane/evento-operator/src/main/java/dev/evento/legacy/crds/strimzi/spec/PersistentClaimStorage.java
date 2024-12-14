package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.Map;

@JsonPropertyOrder({"type", "size", "storageClass", "selector", "deleteClaim"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersistentClaimStorage extends SingleVolumeStorage {

    private static final long serialVersionUID = 1L;

    private String size;
    private String storageClass;
    private Map<String, String> selector;
    private boolean deleteClaim;
    private List<PersistentClaimStorageOverride> overrides;

    private Integer id;

    public void setSize(String size) {
        this.size = size;
    }

    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    public void setSelector(Map<String, String> selector) {
        this.selector = selector;
    }

    public void setDeleteClaim(boolean deleteClaim) {
        this.deleteClaim = deleteClaim;
    }

    public void setOverrides(List<PersistentClaimStorageOverride> overrides) {
        this.overrides = overrides;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return TYPE_PERSISTENT_CLAIM;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSize() {
        return size;
    }

    public String getStorageClass() {
        return storageClass;
    }

    public Map<String, String> getSelector() {
        return selector;
    }

    public boolean isDeleteClaim() {
        return deleteClaim;
    }

    public List<PersistentClaimStorageOverride> getOverrides() {
        return overrides;
    }

    public Integer getId() {
        return id;
    }
}