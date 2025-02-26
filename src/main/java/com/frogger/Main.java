package com.frogger;

import com.frogger.controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MenuController menu = new MenuController(primaryStage);
        menu.showMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}