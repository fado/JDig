package gui;

import gui.levelpanel.LevelPanel;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import main.Jdig;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class MainUI extends GridPane {

    public MainUI() {
        MenuBar menuBar = getMenuBar();
        GridPane.setConstraints(menuBar, 0, 0);

        GridPane levelPane = new LevelPanel();
        GridPane.setConstraints(levelPane, 0, 1);

        this.getChildren().addAll(menuBar, levelPane);
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem quitMenuItem = new MenuItem("Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(event -> System.exit(0));
        fileMenu.getItems().add(quitMenuItem);

        menuBar.getMenus().add(fileMenu);
        menuBar.setUseSystemMenuBar(true);
        menuBar.setMinWidth(Jdig.WIDTH);

        return menuBar;
    }

}
