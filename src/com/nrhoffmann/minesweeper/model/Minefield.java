package com.nrhoffmann.minesweeper.model;

import java.util.*;

public class Minefield {
    private final int ROWS, COLUMNS;
    private Set<Location> mines = new HashSet<>();
    private Map<Location, Integer> adjacentMineCounts = new HashMap<>();

    public Minefield(int rows, int columns, int mines) {
        ROWS = rows;
        COLUMNS = columns;

        placeRandom(mines);
        calculateNeighboringMines();
    }

    public int getRows() {
        return ROWS;
    }

    public int getColumns() {
        return COLUMNS;
    }

    private void calculateNeighboringMines() {
        for (Location mine : mines) {
            for (Location neighbor : mine.getNeighbors()){
                incrementNeighboringMinesCount(neighbor);
            }
        }
    }

    private void incrementNeighboringMinesCount(Location location) {
        int currentCount = getAdjacentMineCount(location);
        adjacentMineCounts.put(location, ++currentCount);
    }

    private void placeRandom(int mines){
        for (int i = 0; i < mines; i++) {
            placeRandom();
        }
    }

    private void placeRandom(){
        Location randomLocation;
        do {
            randomLocation = getRandomSpace();
        } while (isMine(randomLocation));
        placeMine(randomLocation);
    }

    public void placeMine(Location location) {
        ensurePointIsInBounds(location);
        mines.add(location);
    }

    public void clearMine(Location location) {
        ensurePointIsInBounds(location);
        mines.remove(location);
    }

    public boolean isMine(Location location) {
        ensurePointIsInBounds(location);
        return mines.contains(location);
    }

    public int getAdjacentMineCount(Location location) {
        return adjacentMineCounts.getOrDefault(location, 0);
    }

    private void ensurePointIsInBounds(Location location) {
        if (isOutOfBounds(location))
            throw new IndexOutOfBoundsException();
    }

    private boolean isOutOfBounds(Location location) {
        return location.getRow() >= COLUMNS ||
                location.getColumn() >= ROWS;
    }

    private Location getRandomSpace() {
        Random random = new Random();
        int randomRow = random.nextInt(COLUMNS);
        int randomColumn = random.nextInt(ROWS);
        return new Location(randomRow, randomColumn);
    }
}
