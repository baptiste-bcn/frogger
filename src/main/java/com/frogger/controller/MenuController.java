package com.frogger.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class MenuController {

    @FXML
    private Button btnSolo;

    @FXML
    private Button btnDuo;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnQuit;

    @FXML
    public void initialize() {
        btnSolo.setOnAction(event -> loadGameScene());
        btnDuo.setOnAction(event -> loadGameScene());
        btnSettings.setOnAction(event -> loadSettingsScene());
        btnQuit.setOnAction(event -> quitApplication());
    }

    private void loadGameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frogger/view/GameLayout.fxml"));
            VBox gameLayout = loader.load();
            Scene gameScene = new Scene(gameLayout);
            Stage stage = (Stage) btnSolo.getScene().getWindow();
            stage.setScene(gameScene);
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSettingsScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frogger/view/SettingsLayout.fxml"));
            VBox settingsLayout = loader.load();
            Scene settingsScene = new Scene(settingsLayout);
            Stage stage = (Stage) btnSettings.getScene().getWindow();
            stage.setScene(settingsScene);
            stage.setFullScreen(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void quitApplication() {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}