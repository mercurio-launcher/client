package org.mercurio.models;

import java.io.Serializable;

public class Instance implements Serializable {
    private String version;
    private float maxRam;
    private float minRam;
    private transient String name;

    public Instance(String name, String version, float maxRam, float minRam) {
        this.name = name;
        this.version = version;
        this.maxRam = maxRam;
        this.minRam = minRam;
    }

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

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

