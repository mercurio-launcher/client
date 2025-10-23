package org.mercurio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.mercurio.config.Configuration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(
                Objects.requireNonNull(getClass().getResource("/org/mercurio/main.fxml"))
        );

        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/org/mercurio/styles.css")).toExternalForm()
        );
        stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("Mercurio");
        stage.getIcons().addAll(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/icon32.png"))),
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/icon16.png")))
        );


        stage.setScene(scene);
        stage.show();
    }

    static void main(String[] args) { launch(args); }

    @Override
    public void init() throws Exception {
        super.init();

        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        Path basePath;
        if (os.contains("win")) {
            basePath = Paths.get(userHome,"AppData", "Roaming", ".mercurio");
        } else if (os.contains("mac")) {
            basePath = Paths.get(userHome,"Library", "Application Support", "mercurio");
        } else {
            basePath = Paths.get(userHome,".mercurio");
        }


        File baseDir = new File(basePath.toUri());
        if (!baseDir.exists()) {
            if (baseDir.mkdirs()) {
                System.out.println("Directory created in: " + basePath);
                createInitialDirectoryStructure(baseDir);
            } else {
                System.out.println("Could not create the directory");
            }
        }

        Configuration.instanceConfig(basePath);
    }

    private static void createInitialDirectoryStructure(File baseDir) {
        String[] subDirs = {"instances", "notes", "logs"};
        for (String dir : subDirs) {
            File subDir = new File(baseDir, dir);
            if (subDir.mkdirs()) {
                System.out.println("Directory created: " + subDir.getAbsolutePath());
            }
        }
    }
}
