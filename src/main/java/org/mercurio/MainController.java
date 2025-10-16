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

    @FXML private ToggleButton btnHome;
    @FXML private ToggleButton btnSettings;
    @FXML private ToggleButton btnReports;

    private final ToggleGroup navGroup = new ToggleGroup();
    private final Map<String, Parent> cache = new HashMap<>();

    @FXML
    private void initialize() {
        // Agrupar los ToggleButton
        btnHome.setToggleGroup(navGroup);
        btnSettings.setToggleGroup(navGroup);
        btnReports.setToggleGroup(navGroup);

        // --- NUEVO ---
        // Añadir o quitar la clase "active" para estilo visual (sin usar :selected)
        navGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (oldToggle instanceof ToggleButton) {
                ((ToggleButton) oldToggle).getStyleClass().remove("active");
            }
            if (newToggle instanceof ToggleButton) {
                ((ToggleButton) newToggle).getStyleClass().add("active");
            }
        });
        // --- FIN NUEVO ---

        // Eventos de navegación
        btnHome.setOnAction(e -> navigateTo("Home", "/org/mercurio/views/HomeView.fxml"));
        btnSettings.setOnAction(e -> navigateTo("Ajustes", "/org/mercurio/views/SettingsView.fxml"));
        btnReports.setOnAction(e -> navigateTo("Informes", "/org/mercurio/views/ReportsView.fxml"));

        // Carga inicial
        btnHome.setSelected(true);
        btnHome.getStyleClass().add("active"); // <-- Marca Home como activo al iniciar
        navigateTo("Home", "/org/mercurio/views/HomeView.fxml");
    }

    private void navigateTo(String title, String fxmlPath) {
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
