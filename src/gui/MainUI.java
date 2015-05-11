package gui;

import javafx.scene.layout.GridPane;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class MainUI extends GridPane {

    public MainUI(JdigMenuBar menuBar, MapGrid mapGrid) {
        GridPane.setConstraints(menuBar, 0, 0);
        GridPane.setConstraints(mapGrid, 0, 1);
        this.getChildren().addAll(menuBar, mapGrid);
    }


}
