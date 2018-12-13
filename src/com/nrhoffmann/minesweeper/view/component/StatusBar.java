package com.nrhoffmann.minesweeper.view.component;

import javax.swing.*;

public class StatusBar extends JPanel {
    public StatusBar() {
        add(new Stopwatch());
    }

    class Stopwatch extends JLabel {
        int seconds = 0;
        Stopwatch(){
            new Timer(1000, e -> {
                seconds++;
                refresh();
            }).start();
            refresh();
        }

        void refresh(){
            SwingUtilities.invokeLater(() -> {
                setText(String.format("<html><h1>%02d:%02d</h1></html>", seconds / 60, seconds % 60));
            });
        }
    }
}