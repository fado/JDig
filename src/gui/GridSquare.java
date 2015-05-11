package gui;

import data.ConnectionType;
import data.Level;
import data.Room;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Point;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class GridSquare extends Rectangle {

    static final int CELL_SIZE = 15;
    static final double DEFAULT_STROKE_WIDTH = 0.25;
    static final double SELECTED_STROKE_WIDTH = 2;
    public final int x;
    public final int y;
    private boolean isEmpty = true;
    private Level level;
    Logger logger = LoggerFactory.getLogger(GridSquare.class);

    public GridSquare(int x, int y, Level level) {
        this.level = level;
        this.x = x;
        this.y = y;
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
        ConnectionType connectionType = level.getPotentialConnectionTypeAt(this);
        if(connectionType != ConnectionType.NONE) {
            logger.info("Cell can potentially hold a {} exit.", connectionType);
        } else {
            setStrokeWidth(SELECTED_STROKE_WIDTH);
        }
    }

    private void doMouseExited() {
        if(this.isEmpty) {
            setStrokeWidth(DEFAULT_STROKE_WIDTH);
        }
    }

    private void doMouseClicked(MouseEvent event, int x, int y) {
        if(event.getButton() == MouseButton.PRIMARY) {
            logger.trace("Adding room to level at " + x + "," + y);
            level.addEntity(new Room(new Point(x, y)));
            this.isEmpty = false;
        }
        if(event.getButton() == MouseButton.SECONDARY) {
            logger.trace("Deleting cell from level at "+ x +","+ y);
            level.removeEntityAt(new Point(x, y));
            this.isEmpty = true;
        }
    }

}
