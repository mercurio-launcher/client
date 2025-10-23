package org.mercurio.models;

public class Instance {
    private String version;
    private float maxRam;
    private float minRam;
    private String name;

    public void setVersion(String version) {
        this.version = version;
    }

    public void setMaxRam(float maxRam) {
        this.maxRam = maxRam;
    }

    public void setMinRam(float minRam) {
        this.minRam = minRam;
    }

    public String getVersion() {
        return this.version;
    }

    public float getMaxRam() {
        return this.maxRam;
    }

    public float getMinRam() {
        return this.minRam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

