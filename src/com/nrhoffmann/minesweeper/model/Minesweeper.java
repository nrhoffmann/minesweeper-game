package com.nrhoffmann.minesweeper.model;

public class Minesweeper {
    private Minefield minefield;
    
    public Minesweeper(Minefield minefield) {
        this.minefield = minefield;
    }

    public SweepResult sweep(Location location) {
        return minefield.isMine(location)
                ? SweepResult.MINE
                : SweepResult.valueOf("_" + minefield.getAdjacentMineCount(location));
    }
}

