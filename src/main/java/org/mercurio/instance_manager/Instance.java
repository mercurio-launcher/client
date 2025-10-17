package org.mercurio.instance_manager;

public class Instance {
    public String version;
    private float maxRam;
    private float minRam;


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

}

