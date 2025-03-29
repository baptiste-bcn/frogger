package com.frogger.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private final int width;
    private final int height;
    private final List<RowType> rows;

    public enum RowType {
        SAFE, ROAD
    }

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.rows = new ArrayList<>();

        int consecutiveRoads = 0;
        int consecutiveSafes = 0;

        for (int i = 0; i < height; i++) {
            if (consecutiveSafes == 2 || i == 2 || i == height - 3) {
                // Limiter à 2 lignes SAFE consécutives
                // ou si on est sur la 3ème ou avant-dernière ligne on met une ligne ROAD
                rows.add(RowType.ROAD);
                consecutiveSafes = 0;
                consecutiveRoads++;
            } else if (i < 2 || i > height - 3 || consecutiveRoads == 3) {
                // Les deux premières et deux dernières lignes sont toujours SAFE
                // ou si on a 3 lignes ROAD consécutives on met une ligne SAFE
                rows.add(RowType.SAFE);
                consecutiveSafes++;
                consecutiveRoads = 0;
            } else {
                // On alterne entre ROAD et SAFE
                if (Math.random() < 0.3) {
                    rows.add(RowType.SAFE);
                    consecutiveSafes++;
                    consecutiveRoads = 0;
                } else {
                    rows.add(RowType.ROAD);
                    consecutiveRoads++;
                    consecutiveSafes = 0;
                }
            }
        }
    }

    /**
     * ============================
     * GETTER SECTION
     * ============================
     **/

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public RowType getRowType(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= height) {
            throw new IndexOutOfBoundsException("Invalid row index");
        }
        return rows.get(rowIndex);
    }
}
