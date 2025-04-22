package com.frogger;

import com.frogger.controller.SceneController;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
    private MediaPlayer backgroundMusicPlayer;

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

        // Démarrer la musique de fond
        playBackgroundMusic();

        // Création du contrôleur de scène
        SceneController sceneController = new SceneController(stage);
        sceneController.showMenu();

        // Affichage de la fenêtre
        stage.show();
    }

    private void playBackgroundMusic() {
        if (backgroundMusicPlayer == null) {
            String musicPath = getClass().getResource("/audio/background-music.mp3").toExternalForm();
            Media backgroundMusic = new Media(musicPath);
            backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Répéter en boucle
        }
        backgroundMusicPlayer.play();
    }

    @Override
    public void stop() throws Exception {
        // Arrêter la musique lorsque l'application est fermée
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}