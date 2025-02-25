package com.frogger;

import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String FILE_NAME = "highscores.txt";
    private List<Integer> highScores;

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }

    public void addScore(int score) {
        highScores.add(score);
        highScores.sort(Collections.reverseOrder());
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10); // Garde les 10 meilleurs scores
        }
        saveHighScores();
    }

    public List<Integer> getHighScores() {
        return highScores;
    }

    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            // Fichier non trouvé, pas de problème
        }
    }

    private void saveHighScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int score : highScores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}