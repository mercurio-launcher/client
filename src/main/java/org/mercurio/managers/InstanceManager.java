package org.mercurio.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.mercurio.config.Configuration;
import org.mercurio.models.Instance;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

public class InstanceManager {
    private final List<Instance> instances = new ArrayList<>();

    public void createInstance(Instance instance) throws IOException{
        createDir(instance);
        saveInstance(instance);
    }

    public void loadInstances() throws IOException {
        Path path = Configuration.getInstance().getDataPath();
        Path instancesDir = path.resolve("instances");
        List<Path> directories;
        try (Stream<Path> stream = Files.walk(instancesDir, 1)) {
            directories = stream
                    .filter(Files::isDirectory)
                    .filter(dirPath -> !dirPath.equals(instancesDir))
                    .toList();
        }

        for (Path dirPath : directories) {
            this.loadInstanceFromDir(dirPath);
        }
    }

    private void loadInstanceFromDir(Path instanceDir) throws IOException {
        String instanceName = instanceDir.getFileName().toString();
        File configFile = instanceDir.resolve("config.json").toFile();
        if (!configFile.exists()) return;
        Instance instance = loadInstance(configFile);
        instance.setName(instanceName);
        instances.add(instance);
    }

    private void createDir(Instance instance) throws IOException {
        Path path = Configuration.getInstance().getDataPath();
        Files.createDirectories(path.resolve("instances", instance.getName()));
    }

    public Instance loadInstance(File file) throws IOException {
        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Instance.class);
        }
    }


    public void saveInstance(Instance instance) throws IOException {
        Path path = Configuration.getInstance().getDataPath();
        Path pathToFile = path.resolve("instances", instance.getName(), "config.json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(pathToFile.toFile())) {
            gson.toJson(instance, writer);
        }
    }

    private void deleteDir(Instance instance) throws IOException {
        Path path = Configuration.getInstance().getDataPath();
        Path realPath = path.resolve("instances", instance.getName());
        if (!Files.exists(realPath)) return;
        Files.walkFileTree(realPath, new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) throw exc;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
            });

    }

    public List<Instance> getInstances() {
        return Collections.unmodifiableList(instances);
    }

    public void deleteInstance(Instance instance) throws IOException {
        deleteDir(instance);
        instances.remove(instance);
    }
}
