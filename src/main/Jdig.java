package main;

import gui.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */

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
