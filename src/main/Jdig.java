package main;

import javafx.application.Application;
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

    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationFactory.INSTANCE.build(primaryStage);
    }

    public static void main(String[] args) {
        logger.info("Starting application...");
        launch(args);
    }

}
