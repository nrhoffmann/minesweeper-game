package com.nrhoffmann.minesweeper.view;

import com.nrhoffmann.minesweeper.model.Minesweeper;
import com.nrhoffmann.minesweeper.view.component.Gameboard;
import com.nrhoffmann.minesweeper.view.component.StatusBar;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {
    public Game(Minesweeper minesweeper, int rows, int columns){
        super("Minesweeper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(new Gameboard(minesweeper, rows, columns));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
