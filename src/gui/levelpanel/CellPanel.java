package gui.levelpanel;

import data.Cell;
import data.ConnectionType;
import data.Level;
import data.Room;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import main.ApplicationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Point;

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
    private int x;
    private int y;
    Logger logger = LoggerFactory.getLogger(CellPanel.class);

    public CellPanel(int x, int y) {
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
        setStrokeWidth(SELECTED_STROKE_WIDTH);
        Level level = ApplicationFactory.INSTANCE.getLevel();
        logger.trace(""+level.getPotentialConnectionTypeAt(new Point(x, y)));
        if(level.getPotentialConnectionTypeAt(new Point(x, y)) != ConnectionType.NONE) {
            logger.info("Cell can potentially hold an exit.");
        }
    }

    private void doMouseExited() {
        if(this.isEmpty) {
            setStrokeWidth(DEFAULT_STROKE_WIDTH);
        }
    }

    private void doMouseClicked(MouseEvent event, int x, int y) {
        if(event.getButton() == MouseButton.PRIMARY) {
            logger.trace("Adding cell to level at " + x + "," + y);

            Level level = ApplicationFactory.INSTANCE.getLevel();
            Cell cell = new Cell(new Point(x, y));
            cell.setEntity(new Room());
            level.addCell(cell);

            this.isEmpty = false;
        }
        if(event.getButton() == MouseButton.SECONDARY) {
            logger.trace("Deleting cell from level at "+ x +","+ y);
            Level level = ApplicationFactory.INSTANCE.getLevel();
            level.removeCellAt(new Point(x, y));
            this.isEmpty = true;
        }
    }

}
