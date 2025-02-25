package com.frogger;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameController game = new GameController(primaryStage);
        game.lancerJeu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
