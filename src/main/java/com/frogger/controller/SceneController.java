package com.frogger.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private final Stage stage;
    private final Scene scene;

    public SceneController(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(new Parent() {
        }, 800, 600);
        this.stage.setScene(this.scene);
    }

    /**
     * ============================
     * SWITCH SCENE SECTION
     * ============================
     **/

    public void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu/MenuLayout.fxml"));
            Parent menuLayout = loader.load();
            scene.setRoot(menuLayout);

            MenuController controller = loader.getController();
            controller.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showGame(boolean isDuoMode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/game/GameLayout.fxml"));
            Parent gameLayout = loader.load();
            scene.setRoot(gameLayout);

            GameController controller = loader.getController();
            controller.setSceneController(this);
            controller.setDuoMode(isDuoMode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/view/settings/SettingsLayout.fxml"));
            Parent settingsLayout = loader.load();
            scene.setRoot(settingsLayout);

            SettingsController controller = loader.getController();
            controller.setSceneController(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return stage;
    }
}