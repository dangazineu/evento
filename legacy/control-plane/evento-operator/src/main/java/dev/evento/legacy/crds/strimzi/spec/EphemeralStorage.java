package dev.evento.legacy.crds.strimzi.spec;

public class EphemeralStorage extends SingleVolumeStorage {

    private Integer id;
    private String sizeLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeLimit() {
        return sizeLimit;
    }

    public void setSizeLimit(String sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public String getType() {
        return TYPE_EPHEMERAL;
    }

}