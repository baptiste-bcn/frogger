package com.frogger;

import com.frogger.controller.SceneController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Ajout de l'icône de l'application
        Image icon = new Image(getClass().getResourceAsStream("/images/frog.png"));
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
}