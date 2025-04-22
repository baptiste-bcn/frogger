package com.frogger.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private CheckBox checkFullScreen;

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button btnBack;

    private SceneController sceneController;

    private static String selectedDifficulty = "Normal"; // Par défaut

    // filepath:
    // c:\Users\trist\Desktop\Coding\02-Java\frogger\src\main\java\com\frogger\controller\SettingsController.java
    @FXML
    public void initialize() {
        // Gestion du plein écran
        checkFullScreen.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                Stage stage = (Stage) checkFullScreen.getScene().getWindow();

                checkFullScreen.setSelected(stage.isFullScreen());
                checkFullScreen.setOnAction(event -> {
                    stage.setFullScreen(checkFullScreen.isSelected());
                });
            }
        });

        // Initialiser le menu déroulant de difficulté
        difficultyComboBox.setItems(FXCollections.observableArrayList("Facile", "Normal", "Difficile"));
        difficultyComboBox.setValue(selectedDifficulty); // Pré-sélectionner la difficulté actuelle
        difficultyComboBox.setOnAction(event -> {
            selectedDifficulty = difficultyComboBox.getValue(); // Mettre à jour la difficulté sélectionnée
        });

        // Bouton retour
        btnBack.setOnAction(event -> {
            if (sceneController != null) {
                sceneController.showMenu();
            }
        });
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public static String getSelectedDifficulty() {
        return selectedDifficulty;
    }
}