package gui;

import data.Connection;
import data.ConnectionType;
import data.ExitBuilder;
import data.Level;
import data.Room;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.ImageProperties;
import java.awt.Point;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class GridSquare extends StackPane {

    static final int GRID_SQUARE_SIZE = 15;
    public final int x;
    public final int y;
    private boolean isEmpty = true;
    private Level level;
    private ImageView currentCursor;
    private ImageProperties imageProperties = new ImageProperties();
    private Connection connection;
    private ConnectionType connectionType;
    Logger logger = LoggerFactory.getLogger(GridSquare.class);

    public GridSquare(int x, int y, Level level) {
        this.level = level;
        this.x = x;
        this.y = y;
        this.setMinWidth(GRID_SQUARE_SIZE);
        this.setMinHeight(GRID_SQUARE_SIZE);

        this.getChildren().add(getRectangle());

        setOnMouseEntered(event -> doMouseEntered());
        setOnMouseExited(event -> doMouseExited());
        setOnMouseClicked(event -> doMouseClicked(event, x, y));
    }

    private void doMouseEntered() {
        connectionType = level.getPotentialConnectionTypeAt(this);
        if(connectionType != ConnectionType.NONE) {
            currentCursor = getImageView(connectionType.getPath(), 15);
            connection = new Connection(new Point(x, y), connectionType);
            this.getChildren().add(currentCursor);
        } else {
            currentCursor = getImageView(imageProperties.getImagePath("Room"), 15);
            this.getChildren().add(currentCursor);
        }
    }

    private void doMouseExited() {
        if(this.isEmpty) {
            this.getChildren().remove(currentCursor);
        }
    }

    private void doMouseClicked(MouseEvent event, int x, int y) {
        if(event.getButton() == MouseButton.PRIMARY) {
            logger.trace("Adding room to level at " + x + "," + y);
            if(this.isEmpty) {
                if (connection == null) {
                    level.addEntity(new Room(new Point(x, y)));
                } else {
                    new ExitBuilder(level).build(this, connection);
                    level.addEntity(connection);
                }
                this.isEmpty = false;
            } else {
                ExitBuilder exitBuilder = new ExitBuilder(level);
                if (connection.getConnectionType() == ConnectionType.X) {
                    connection = new Connection(new Point(x, y), ConnectionType.FORWARD_DIAGONAL);
                    exitBuilder.build(this, connection);
                } else if (connection.getConnectionType() == ConnectionType.FORWARD_DIAGONAL) {
                    connection = new Connection(new Point(x, y), ConnectionType.BACKWARD_DIAGONAL);
                    exitBuilder.build(this, connection);
                } else if (connection.getConnectionType() == ConnectionType.BACKWARD_DIAGONAL) {
                    connection = new Connection(new Point(x, y), ConnectionType.X);
                    exitBuilder.build(this, connection);
                }
                this.getChildren().clear();
                this.getChildren().add(getImageView(connection.getConnectionType().getPath(), 15));
            }
        }
        if(event.getButton() == MouseButton.SECONDARY) {
            logger.trace("Deleting entity from level at "+ x +","+ y);
            level.removeEntityAt(new Point(x, y));
            this.getChildren().clear();
            this.getChildren().add(getRectangle());
            this.isEmpty = true;
        }
    }

    private ImageView getImageView(String path, int fitWidth) {
        Image image = new Image("file:"+ path);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setCache(true);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(fitWidth);
        return imageView;
    }

    private Rectangle getRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(GRID_SQUARE_SIZE);
        rectangle.setWidth(GRID_SQUARE_SIZE);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(0.25);
        return rectangle;
    }
}
