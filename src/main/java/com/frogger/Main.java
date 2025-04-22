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
                // Précharger les images nécessaires
                Image grassTile = new Image(getClass().getResourceAsStream("/images/GrassTile.png"));
                Image roadTile = new Image(getClass().getResourceAsStream("/images/RoadTile.png"));
                Image blueCar = new Image(getClass().getResourceAsStream("/images/BlueCar.png"));
                Image tree = new Image(getClass().getResourceAsStream("/images/Tree.png"));
                Image frog = new Image(getClass().getResourceAsStream("/images/frog.png"));
                Image background = new Image(getClass().getResourceAsStream("/images/Background.png"),
                        1920, 1080, true, true); // Charger avec des dimensions adaptées

                return null;
            }
        };

        new Thread(preloadTask).start();
    }
}