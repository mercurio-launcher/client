package org.mercurio.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.mercurio.models.Instance;

public class InstanceCardController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label versionLabel;

    @FXML
    private Label lastPlayedLabel;

    @FXML
    private Button playButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    private Instance instance;

    public void setData(Instance instance) {
        this.instance = instance;
        titleLabel.setText(instance.getName());
        versionLabel.setText("Versión: " + instance.getVersion());

        playButton.setOnAction(event -> {
            System.out.println("Playing: " + instance.getName());

        });
        deleteButton.setOnAction(event -> {
            System.out.println("Deleting:  " + instance.getName());

        });

    }
}