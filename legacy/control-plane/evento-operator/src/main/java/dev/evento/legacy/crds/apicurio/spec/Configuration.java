package dev.evento.legacy.crds.apicurio.spec;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {

    private KafkasqlConfiguration kafkasql;
    private PersistenceType persistence;

    public KafkasqlConfiguration getKafkasql() {
        return kafkasql;
    }

    public void setKafkasql(KafkasqlConfiguration kafkasql) {
        this.kafkasql = kafkasql;
    }

    public PersistenceType getPersistence() {
        return persistence;
    }

    public void setPersistence(PersistenceType persistence) {
        this.persistence = persistence;
    }
}
