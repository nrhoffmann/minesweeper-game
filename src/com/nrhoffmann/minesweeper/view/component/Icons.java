package com.nrhoffmann.minesweeper.view.component;

import javax.swing.*;

public class Icons {
    public static final Icon UNCLICKED;
    public static final Icon[] NUMBER = new Icon[9];
    public static final Icon FLAG;
    public static final Icon MINE_INERT;
    public static final Icon MINE_ACTIVE;
    public static final Icon QUESTION_MARK;

    static {
        UNCLICKED = new ImageIcon(Icons.class.getResource("img/unclickeds.png"));
        NUMBER[0] = new ImageIcon(Icons.class.getResource("img/0s.png"));
        NUMBER[1] = new ImageIcon(Icons.class.getResource("img/1s.png"));
        NUMBER[2] = new ImageIcon(Icons.class.getResource("img/2s.png"));
        NUMBER[3] = new ImageIcon(Icons.class.getResource("img/3s.png"));
        NUMBER[4] = new ImageIcon(Icons.class.getResource("img/4s.png"));
        NUMBER[5] = new ImageIcon(Icons.class.getResource("img/5s.png"));
        NUMBER[6] = new ImageIcon(Icons.class.getResource("img/6s.png"));
        NUMBER[7] = new ImageIcon(Icons.class.getResource("img/7s.png"));
        NUMBER[8] = new ImageIcon(Icons.class.getResource("img/8s.png"));
        FLAG = new ImageIcon(Icons.class.getResource("img/flags.png"));
        MINE_INERT = new ImageIcon(Icons.class.getResource("img/falses.png"));
        MINE_ACTIVE = new ImageIcon(Icons.class.getResource("img/inerts.png"));
        QUESTION_MARK = new ImageIcon(Icons.class.getResource("img/questionmarks.png"));
    }
}
