package org.mercurio;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainController {

    @FXML private StackPane contentStack;

    @FXML private ToggleButton btnHome;
    @FXML private ToggleButton btnSettings;
    @FXML private ToggleButton btnReports;
    @FXML private ToggleButton btnProfile;
    @FXML private ToggleButton btnAnalytics;
    @FXML private ToggleButton btnTasks;
    @FXML private ToggleButton btnHelp;
    @FXML private HBox windowBar;
    @FXML private Button btnMinimize;
    @FXML private Button btnMaximize;
    @FXML private Button btnClose;


    private final ToggleGroup navGroup = new ToggleGroup();
    private final Map<String, Parent> cache = new HashMap<>();

    //offsets para arrastrar la ventana
    private double dragOffsetX = 0;
    private double dragOffsetY = 0;

    @FXML
    private void initialize() {
        // Agrupar los ToggleButton
        btnHome.setToggleGroup(navGroup);
        btnSettings.setToggleGroup(navGroup);
        btnReports.setToggleGroup(navGroup);
        btnProfile.setToggleGroup(navGroup);
        btnAnalytics.setToggleGroup(navGroup);
        btnTasks.setToggleGroup(navGroup);
        btnHelp.setToggleGroup(navGroup);


        navGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (oldToggle instanceof ToggleButton) {
                ((ToggleButton) oldToggle).getStyleClass().remove("active");
            }
            if (newToggle instanceof ToggleButton) {
                ((ToggleButton) newToggle).getStyleClass().add("active");
            }
        });

        // Eventos de navegación
        btnHome.setOnAction(e -> navigateTo("Home", "/org/mercurio/views/HomeView.fxml"));
        btnSettings.setOnAction(e -> navigateTo("Settings", "/org/mercurio/views/SettingsView.fxml"));
        btnReports.setOnAction(e -> navigateTo("Reports", "/org/mercurio/views/ReportsView.fxml"));
        btnProfile.setOnAction(e -> navigateTo("Profile","/org/mercurio/views/ProfileView.fxml"));
        btnAnalytics.setOnAction(e -> navigateTo("Analalytics","/org/mercurio/views/AnalyticsView.fxml"));
        btnTasks.setOnAction(e -> navigateTo("Tasks","/org/mercurio/views/TasksView.fxml"));
        btnHelp.setOnAction(e -> navigateTo("Help","/org/mercurio/views/HelpView.fxml"));

        // Carga inicial
        btnHome.setSelected(true);
        btnHome.getStyleClass().add("active"); // <-- Marca Home como activo al iniciar
        navigateTo("Home", "/org/mercurio/views/HomeView.fxml");

        Platform.runLater(this::setupWindowControls);
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

    // Lógica de ventana sin bordes (mover/min/max/cerrar)
    private void setupWindowControls() {
        // Si no has añadido la barra/btns en el FXML, sal sin romper
        if (windowBar == null || contentStack == null) return;

        Scene scene = contentStack.getScene();
        if (scene == null) return;

        Stage stage = (Stage) scene.getWindow();
        if (stage == null) return;

        // Arrastrar ventana desde la barra
        windowBar.setOnMousePressed(ev -> {
            dragOffsetX = ev.getSceneX();
            dragOffsetY = ev.getSceneY();
        });
        windowBar.setOnMouseDragged(ev -> {
            stage.setX(ev.getScreenX() - dragOffsetX);
            stage.setY(ev.getScreenY() - dragOffsetY);
        });

        // Doble click para maximizar/restaurar
        windowBar.setOnMouseClicked(ev -> {
            if (ev.getClickCount() == 2) {
                stage.setMaximized(!stage.isMaximized());
            }
        });

        // Botones (si existen en el FXML)
        if (btnMinimize != null) btnMinimize.setOnAction(e -> stage.setIconified(true));
        if (btnMaximize != null) btnMaximize.setOnAction(e -> stage.setMaximized(!stage.isMaximized()));
        if (btnClose != null)    btnClose.setOnAction(e -> stage.close());
    }
}
