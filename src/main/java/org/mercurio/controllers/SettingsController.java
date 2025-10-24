package org.mercurio.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsController {

    @FXML private StackPane contentContainer;
    @FXML private ToggleButton btnGeneral;
    @FXML private ToggleButton btnDetail;
    @FXML private ToggleButton btnAbout;

    @FXML
    private final ToggleGroup navGroup = new ToggleGroup();
    private final Map<String, Parent> cache = new HashMap<>();

    @FXML
    private void initialize() {
        // Agrupar los ToggleButton
        btnGeneral.setToggleGroup(navGroup);
        btnDetail.setToggleGroup(navGroup);
        btnAbout.setToggleGroup(navGroup);

        navGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (oldToggle instanceof ToggleButton) {
                ((ToggleButton) oldToggle).getStyleClass().remove("active");
            }
            if (newToggle instanceof ToggleButton) {
                ((ToggleButton) newToggle).getStyleClass().add("active");
            }
        });

        // Eventos de navegación
        btnGeneral.setOnAction(e -> navigateTo("General", "/org/mercurio/views/settings/GeneralSettingsView.fxml"));
        btnDetail.setOnAction(e -> navigateTo("AccountDetail", "/org/mercurio/views/settings/AccountDetailView.fxml"));
        btnAbout.setOnAction(e -> navigateTo("AboutUs", "/org/mercurio/views/settings/AboutUsView.fxml"));

        // Carga inicial
        btnGeneral.setSelected(true);
        btnGeneral.getStyleClass().add("active"); // <-- Marca Home como activo al iniciar
        navigateTo("General", "/org/mercurio/views/settings/GeneralSettingsView.fxml");

    }

    private void navigateTo(String title, String fxmlPath) {
        Parent view = cache.computeIfAbsent(fxmlPath, path -> {
            try {
                return FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            } catch (IOException ex) {
                throw new RuntimeException("No se pudo cargar " + path, ex);
            }
        });

        contentContainer.getChildren().setAll(view);
    }
}