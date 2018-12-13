package com.nrhoffmann.minesweeper.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Location {
    private final int ROW, COLUMN;

    public Location(int row, int column) {
        ROW = row;
        COLUMN = column;
    }

    public int getRow() {
        return ROW;
    }

    public int getColumn() {
        return COLUMN;
    }

    public List<Location> getNeighbors(){
        List<Location> neighbors = new ArrayList<>();
        neighbors.add(getNorthNeighbor());
        neighbors.add(getNorthEastNeighbor());
        neighbors.add(getEastNeighbor());
        neighbors.add(getSouthEastNeighbor());
        neighbors.add(getSouthNeighbor());
        neighbors.add(getSouthWestNeighbor());
        neighbors.add(getWestNeighbor());
        neighbors.add(getNorthWestNeighbor());
        return neighbors;
    }

    private Location getNorthNeighbor() {
        return new Location(ROW - 1, COLUMN);
    }

    private Location getNorthEastNeighbor() {
        return new Location(ROW - 1, COLUMN + 1);
    }

    private Location getEastNeighbor() {
        return new Location(ROW, COLUMN + 1);
    }

    private Location getSouthEastNeighbor() {
        return new Location(ROW + 1, COLUMN + 1);
    }

    private Location getSouthNeighbor() {
        return new Location(ROW + 1, COLUMN);
    }

    private Location getSouthWestNeighbor() {
        return new Location(ROW + 1, COLUMN - 1);
    }

    private Location getWestNeighbor() {
        return new Location(ROW, COLUMN - 1);
    }

    private Location getNorthWestNeighbor() {
        return new Location(ROW - 1, COLUMN - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Location) {
            Location that = (Location) o;
            return ROW == that.ROW &&
                    COLUMN == that.COLUMN;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ROW, COLUMN);
    }
}
