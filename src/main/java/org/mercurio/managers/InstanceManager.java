package org.mercurio.managers;

import org.mercurio.config.Configuration;
import org.mercurio.models.Instance;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class InstanceManager {
    private final List<Instance> instances = new ArrayList<>();

    public Instance createInstance(Instance instance) {
        createDir(instance);
        saveConfig(instance);
        instances.add(instance);
        return instance;
    }

    private void createDir(Instance instance) {
        Path path = Configuration.getInstance().getDataPath();
        try {
            Files.createDirectories(path.resolve("/instances/", instance.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDir(File file) {
        try {
            for (File subfile : Objects.requireNonNull(file.listFiles())) {
                if (subfile.isDirectory()) {
                    deleteDir(subfile);
                }
                subfile.delete();
            }
    } catch(Exception e) {
        e.printStackTrace();
    }

    }

    public void deleteDir(Instance instance) {
        Path path = Configuration.getInstance().getDataPath();
        try {
            Files.delete(path.resolve("/instances/", instance.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveConfig(Instance instance) {
        Path path = Configuration.getInstance().getDataPath();
        try {
            Files.createDirectories(path.resolve("/instances/", instance.getName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Instance> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    public void deleteInstance(Instance instance) {

    }
}
