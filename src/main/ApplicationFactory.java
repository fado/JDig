package main;

import data.Level;
import gui.MainUI;
import gui.JdigMenuBar;
import gui.MapGrid;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public enum ApplicationFactory {
    INSTANCE;

    public void build(Stage primaryStage) {
        // Instantiate all the things...
        Level level = new Level();
        MapGrid mapGrid = new MapGrid(level);
        JdigMenuBar jdigMenuBar = new JdigMenuBar();
        MainUI mainUI = new MainUI(jdigMenuBar, mapGrid);
        Scene mainApplicationScene = new Scene(mainUI);

        // Setup the primary stage and show it.
        primaryStage.setScene(mainApplicationScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

}
