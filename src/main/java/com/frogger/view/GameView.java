package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.frogger.model.Grille;
import com.frogger.model.Joueur;
import com.frogger.model.Obstacle;

public class GameView extends Canvas {
    private Grille grille;
    private Joueur joueur;
    private int tileSize;

    public GameView(Grille grille, int tileSize, Joueur joueur) {
        super(grille.getWidth() * tileSize, grille.getHeight() * tileSize); // Définir la taille du canvas
        this.grille = grille;
        this.tileSize = tileSize;
        this.joueur = joueur;
    }

    public void render() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Dessiner la grille
        drawGrid(gc);

        // Dessiner le joueur
        drawPlayer(gc);

        // Dessiner les obstacles
        drawObstacles(gc);
    }

    public Joueur getJoueur() {
        return this.joueur;
    }

    private void drawGrid(GraphicsContext gc) {
        gc.setStroke(Color.GRAY);
        for (int i = 0; i <= grille.getWidth(); i++) {
            gc.strokeLine(i * tileSize, 0, i * tileSize, grille.getHeight() * tileSize);
        }
        for (int i = 0; i <= grille.getHeight(); i++) {
            gc.strokeLine(0, i * tileSize, grille.getWidth() * tileSize, i * tileSize);
        }
    }

    private void drawPlayer(GraphicsContext gc) {
        gc.setFill(Color.BLACK);  // Utiliser la couleur du joueur
        gc.fillRect(joueur.getX() * tileSize, joueur.getY() * tileSize, tileSize, tileSize);
    }

    private void drawObstacles(GraphicsContext gc) {
        for (Obstacle obstacle : grille.getObstacles()) {
            gc.setFill(obstacle.getColor());  // Utiliser la couleur de l'obstacle
            gc.fillRect(obstacle.getX() * tileSize, obstacle.getY() * tileSize, tileSize, tileSize);
        }
    }
}
