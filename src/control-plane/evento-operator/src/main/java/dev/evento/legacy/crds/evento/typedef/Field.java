package dev.evento.legacy.crds.evento.typedef;

public class Field {
    private String name;
    private String doc;
    private PrimitiveFieldType primitiveType;
    private String customType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public PrimitiveFieldType getPrimitiveType() {
        return primitiveType;
    }

    public void setPrimitiveType(PrimitiveFieldType primitiveType) {
        this.primitiveType = primitiveType;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }
}
