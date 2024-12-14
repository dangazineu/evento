package dev.evento.legacy.crds.strimzi.spec;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JbodStorage extends Storage {

    private List<SingleVolumeStorage> volumes;

    public List<SingleVolumeStorage> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<SingleVolumeStorage> volumes) {
        this.volumes = volumes;
    }

    @Override
    public String getType() {
        return TYPE_JBOD;
    }

}