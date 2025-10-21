package org.mercurio.instance_manager;

import org.mercurio.config.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    private void saveConfig(Instance instance) {

    }

    public List<Instance> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    public void deleteInstance(Instance instance) {

    }
}
