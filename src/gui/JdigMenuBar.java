package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class JdigMenuBar extends MenuBar {

    private static final int WIDTH = 1024;  // TO-DO: Replace with properties file.

    public JdigMenuBar() {
        Menu fileMenu = new Menu("File");

        MenuItem quitMenuItem = new MenuItem("Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(event -> System.exit(0));
        fileMenu.getItems().add(quitMenuItem);

        getMenus().add(fileMenu);
        setUseSystemMenuBar(true);
        setMinWidth(WIDTH);
    }


}
