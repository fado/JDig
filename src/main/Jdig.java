package main;

/**
 * Jdig, a tool for the automatic generation of LPC class files for Epitaph
 * developers.
 * Copyright (C) 2014 Fado@Epitaph.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import gui.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jdig extends Application {

    static final Logger logger = LoggerFactory.getLogger(Jdig.class);

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainApplicationScene = new Scene(new MainUI(), WIDTH, HEIGHT);
        primaryStage.setScene(mainApplicationScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> doExit());
    }

    public static void main(String[] args) {
        logger.info("Starting application...");
        launch(args);
    }

    private void doExit() {
        System.exit(0);
    }

}
