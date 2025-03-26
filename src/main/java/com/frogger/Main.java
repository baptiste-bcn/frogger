package com.frogger;

import javafx.application.Application;
import javafx.stage.Stage;
import com.frogger.controller.SceneController;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Frogger Menu");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");

        SceneController sceneController = new SceneController(stage);
        sceneController.showMenu();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
