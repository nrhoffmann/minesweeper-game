package com.nrhoffmann.minesweeper;

import com.nrhoffmann.minesweeper.model.Minefield;
import com.nrhoffmann.minesweeper.model.Minesweeper;
import com.nrhoffmann.minesweeper.view.Game;

public class App {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private static final int MINES = 10;

    public static void main(String[] args) {
        Minefield minefield = new Minefield(ROWS, COLUMNS, MINES);
        Minesweeper minesweeper = new Minesweeper(minefield);

        new Game(minesweeper, ROWS, COLUMNS);
    }

}
