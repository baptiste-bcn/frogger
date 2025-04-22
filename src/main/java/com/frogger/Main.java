package com.frogger;

import com.frogger.controller.SceneController;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Chargement asynchrone des images pour gagner en performance
        preloadImagesAsync();

        // Ajout de l'icône de l'application
        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/frog.png")));
        stage.getIcons().add(icon);

        // Configuration de la fenêtre
        stage.setResizable(false);
        stage.setTitle("Froggy Road");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        // Création du contrôleur de scène
        SceneController sceneController = new SceneController(stage);
        sceneController.showMenu();

        // Affichage de la fenêtre
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void preloadImagesAsync() {
        Task<Void> preloadTask = new Task<>() {
            @Override
            protected Void call() {
                new Image(getClass().getResourceAsStream("/images/GrassTile.png"));
                new Image(getClass().getResourceAsStream("/images/RoadTile.png"));
                new Image(getClass().getResourceAsStream("/images/BlueCar.png"));
                new Image(getClass().getResourceAsStream("/images/Tree.png"));
                new Image(getClass().getResourceAsStream("/images/frog.png"));
                new Image(getClass().getResourceAsStream("/images/Background.png"));

                return null;
            }
        };

        new Thread(preloadTask).start();
    }
}