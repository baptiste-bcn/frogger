package com.frogger;

import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import com.frogger.controller.SceneController;
import javafx.scene.image.Image;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image(getClass().getResourceAsStream("/images/frog.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Frogger");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        SceneController sceneController = new SceneController(stage);
        sceneController.showMenu();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}