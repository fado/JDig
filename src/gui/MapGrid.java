package gui;

import data.Level;
import javafx.scene.layout.GridPane;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class MapGrid extends GridPane {

    static final int ROWS = 40;
    static final int COLUMNS = 40;

    public MapGrid(Level level) {
        for(int y=0; y<ROWS; y++) {
            for(int x=0; x<COLUMNS; x++) {
                GridSquare gridSquare = new GridSquare(x, y, level);
                GridPane.setConstraints(gridSquare, x, y);
                this.getChildren().add(gridSquare);
            }
        }
    }

}
