package gui.levelpanel;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class CellPanel extends Rectangle {

    static final int CELL_SIZE = 15;
    static final double DEFAULT_STROKE_WIDTH = 0.25;
    static final double SELECTED_STROKE_WIDTH = 2;
    private boolean isEmpty = true;
    Logger logger = LoggerFactory.getLogger(CellPanel.class);

    public CellPanel(int x, int y) {
        setHeight(CELL_SIZE);
        setWidth(CELL_SIZE);
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        setStrokeWidth(DEFAULT_STROKE_WIDTH);

        setOnMouseEntered(event -> doMouseEntered());
        setOnMouseExited(event -> doMouseExited());
        setOnMouseClicked(event -> doMouseClicked(event, x, y));
    }

    private void doMouseEntered() {
        setStrokeWidth(SELECTED_STROKE_WIDTH);
    }

    private void doMouseExited() {
        if(this.isEmpty) {
            setStrokeWidth(DEFAULT_STROKE_WIDTH);
        }
    }

    private void doMouseClicked(MouseEvent event, int x, int y) {
        if(event.getButton() == MouseButton.PRIMARY) {
            logger.trace("Will eventually draw a room at " + x + "," + y);
            this.isEmpty = false;
        }
        if(event.getButton() == MouseButton.SECONDARY) {
            logger.trace("Will eventually delete a room at "+ x +","+ y);
            this.isEmpty = true;
        }
    }

}
