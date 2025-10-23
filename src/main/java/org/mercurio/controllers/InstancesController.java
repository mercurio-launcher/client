package org.mercurio.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.mercurio.managers.InstanceManager;
import org.mercurio.models.Instance;

import java.io.IOException;
import java.util.List;

public class InstancesController {
    InstanceManager instanceManager = new InstanceManager();;
    @FXML
    private VBox instancesContainer;

    @FXML
    public void initialize() throws IOException {
        //instanceManager.createInstance(new Instance("Out", "22", 0.5f, 4.0f));
        instanceManager.loadInstances();
        List<Instance> instances = instanceManager.getInstances();
        System.out.println(instances);
        instancesContainer.getChildren().clear();

        for (Instance instance : instances) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/mercurio/views/InstanceCard.fxml"));
                Node cardNode = loader.load();

                InstanceCardController cardController = loader.getController();

                cardController.setData(instance);

                instancesContainer.getChildren().add(cardNode);
        }
    }
}