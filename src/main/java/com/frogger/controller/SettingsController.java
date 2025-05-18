package com.frogger.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class SettingsController {

    @FXML
    private ComboBox<String> difficultyComboBox;

    @FXML
    private Button btnBack;

    private SceneController sceneController;

    private static String selectedDifficulty = "Normal"; // Par défaut

    @FXML
    public void initialize() {
        // Initialiser le menu déroulant de difficulté
        difficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Normal", "Hard"));
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