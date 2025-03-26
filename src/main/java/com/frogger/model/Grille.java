    package com.frogger.model;

    import java.util.List;
    import java.util.ArrayList;
    import javafx.scene.paint.Color;
    import java.util.Random;

    public class Grille {
        private final int width;
        private final int height;
        private List<Obstacle> obstacles;

        public Grille(int width, int height) {
            this.width = width;
            this.height = height;
            // Initialiser les obstacles ou autres éléments nécessaires ici
            this.obstacles = new ArrayList<>();
            generateRandomObstacles();
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public List<Obstacle> getObstacles() {
            return obstacles;
        }

        private void generateRandomObstacles() {
            Random rand = new Random();
            //les buissons
            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(width-1); //pour ne pas apparaitre sur le joueur
                int y = rand.nextInt(height-1);
                int obstacleWidth = 1;
                float speed = 0;
                Color color = Color.GREEN;
                obstacles.add(new Obstacle(this, x, y, obstacleWidth, speed, color));
            }

            //les voitures
            for (int i = 0; i < 20; i++) {
                int x = rand.nextInt(width-1);
                int y = rand.nextInt(height-1);
                int obstacleWidth = rand.nextInt(2)+ 2; // 2 ou 3
                float speed = rand.nextInt(2) - 0.5f; // -0.5 ou 0.5
                Color color = Color.GREEN; //pas important car random couleur dans obstacle
                obstacles.add(new Obstacle(this, x, y, obstacleWidth, speed, color));
            }
        }
    }
