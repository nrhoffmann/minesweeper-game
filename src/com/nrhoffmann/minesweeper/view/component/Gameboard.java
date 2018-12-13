package com.nrhoffmann.minesweeper.view.component;

import com.nrhoffmann.minesweeper.model.Location;
import com.nrhoffmann.minesweeper.model.Minesweeper;
import com.nrhoffmann.minesweeper.model.SweepResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Gameboard extends JPanel {
    private Map<Location, Space> spaces = new HashMap<>();
    private Minesweeper minesweeper;

    public Gameboard(Minesweeper minesweeper, int rows, int columns) {
        super(new GridLayout(rows, columns));
        this.minesweeper = minesweeper;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                addSpace(new Location(row, column));
            }
        }
    }

    private void addSpace(Location location) {
        Space newSpace = new Space(location);
        spaces.put(location, newSpace);
        add(newSpace);
    }

    private enum SpaceState {DISABLED, DEFAULT, FLAGGED, QUESTION_MARKED}

    private class Space extends JButton {
        private Location correspondingLocation;
        private SpaceState state = SpaceState.DEFAULT;

        private Space(Location location) {
            super(Icons.UNCLICKED);
            this.correspondingLocation = location;
            setPreferredSize(new Dimension(38, 38));
            addMouseListener(MouseButton3Listener);
            addMouseListener(MouseButton1Listener);
        }

        private void setState(SpaceState state) {
            this.state = state;
        }

        private boolean isFlagged() {
            return state == SpaceState.FLAGGED;
        }

        private boolean isDisabled() {
            return state == SpaceState.DISABLED;
        }

        private void disableSpace() {
            setEnabled(false);
            setState(SpaceState.DISABLED);
            removeMouseListener(MouseButton3Listener);
            removeMouseListener(MouseButton1Listener);
        }

        private void setIcon() {
            if (state == SpaceState.DEFAULT)
                Space.this.setIcon(Icons.UNCLICKED);
            else if (state == SpaceState.FLAGGED)
                Space.this.setIcon(Icons.FLAG);
            else if (state == SpaceState.QUESTION_MARKED)
                Space.this.setIcon(Icons.QUESTION_MARK);
        }

        private void setDisabledIcon(SweepResult result) {
            if (result == SweepResult.MINE)
                Space.this.setDisabledIcon(isFlagged()
                        ? Icons.MINE_INERT
                        : Icons.MINE_ACTIVE);
            else
                Space.this.setDisabledIcon(Icons.NUMBER[result.ordinal()]);
        }

        private MouseAdapter MouseButton3Listener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (! wasMouseButton3(e))
                    return;

                handle();
            }

            private void handle() {
                if (state == SpaceState.DEFAULT)
                    setState(SpaceState.FLAGGED);
                else if (state == SpaceState.FLAGGED)
                    setState(SpaceState.QUESTION_MARKED);
                else if (state == SpaceState.QUESTION_MARKED)
                    setState(SpaceState.DEFAULT);

                setIcon();
            }

            private boolean wasMouseButton3(MouseEvent e) {
                return e.getButton() == MouseEvent.BUTTON3;
            }
        };

        private MouseAdapter MouseButton1Listener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (! wasMouseButton1(e) || isFlagged())
                    return;

                handle();
            }

            private void handle() {
                SweepResult result = minesweeper.sweep(correspondingLocation);

                handle(result);
            }

            private void handle(SweepResult result) {
                disableSpace();
                setDisabledIcon(result);

                cascade(result);
            }

            private void cascade(SweepResult result) {
                if (result == SweepResult._0)
                    for (Location adjacent : correspondingLocation.getNeighbors())
                        click(adjacent);
                else if (result == SweepResult.MINE)
                    for (Space space : spaces.values())
                        reveal(space);
            }

            private void click(Location adjacent) {
                Space space = spaces.get(adjacent);
                if (space != null)
                    space.dispatchEvent(
                            new MouseEvent(space,
                                    MouseEvent.MOUSE_PRESSED,
                                    System.currentTimeMillis(),
                                    0,
                                    space.getX(), space.getY(),
                                    1,
                                    false,
                                    MouseEvent.BUTTON1));
            }

            private void reveal(Space space) {
                if (! space.isDisabled()) {
                    SweepResult result = minesweeper.sweep(space.correspondingLocation);
                    space.setDisabledIcon(result);
                    space.disableSpace();
                }
            }


            private boolean wasMouseButton1(MouseEvent e) {
                return e.getButton() == MouseEvent.BUTTON1;
            }
        };
    }
}