package com.frogger.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import com.frogger.model.Game;
import com.frogger.model.Obstacle;
import com.frogger.model.Joueur;
import com.frogger.model.Grille;
import view.GameView;

public class GameController {
    private Game game;
    private GameView gameView;
    private Joueur joueur;
    private AnimationTimer timer;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
        this.joueur = gameView.getJoueur();
        this.timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };
    }

    public void startGame() {
        System.out.println("Game started!");
        timer.start();
    }

    private void updateGame() {
        for (Obstacle obstacle : game.getGrille().getObstacles()) {
            obstacle.move();
            if (obstacle.collidesWith(joueur)) {
                endGame();
            }
        }
        gameView.render();
    }

    private void endGame() {
        System.out.println("Game Over!");
        timer.stop();  // Arrêter la boucle de jeu
    }
}
