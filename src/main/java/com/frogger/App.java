package com.frogger;

import com.frogger.views.Menu;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Frogger");
        Menu menu = new Menu(primaryStage);
        menu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}