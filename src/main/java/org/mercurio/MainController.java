package org.mercurio;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainController {

    @FXML private StackPane contentStack;
    @FXML private Label lblSectionTitle;

    @FXML private ToggleButton btnHome;
    @FXML private ToggleButton btnSettings;

    private final ToggleGroup navGroup = new ToggleGroup();
    private final Map<String, Parent> cache = new HashMap<>();

    @FXML
    private void initialize() {
        // Grupo de navegación para estado "seleccionado" exclusivo
        btnHome.setToggleGroup(navGroup);
        btnSettings.setToggleGroup(navGroup);

        // Eventos de navegación
        btnHome.setOnAction(e -> navigateTo("Home", "/org/mercurio/views/HomeView.fxml"));
        btnSettings.setOnAction(e -> navigateTo("Ajustes", "/org/mercurio/views/SettingsView.fxml"));

        // Carga inicial
        btnHome.setSelected(true);
        navigateTo("Home", "/org/mercurio/views/HomeView.fxml");
    }

    private void navigateTo(String title, String fxmlPath) {
        lblSectionTitle.setText(title);

        Parent view = cache.computeIfAbsent(fxmlPath, path -> {
            try {
                return FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            } catch (IOException ex) {
                throw new RuntimeException("No se pudo cargar " + path, ex);
            }
        });

        contentStack.getChildren().setAll(view);
    }
}
