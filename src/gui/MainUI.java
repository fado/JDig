package gui;

import gui.levelpanel.JdigMenuBar;
import gui.levelpanel.LevelPanel;
import javafx.scene.layout.GridPane;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class MainUI extends GridPane {

    public MainUI(JdigMenuBar menuBar, LevelPanel levelPanel) {
        GridPane.setConstraints(menuBar, 0, 0);
        GridPane.setConstraints(levelPanel, 0, 1);
        this.getChildren().addAll(menuBar, levelPanel);
    }


}
