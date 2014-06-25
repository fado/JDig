package gui;

import globals.Entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * The MapSquare class specifies one square in the MapGrid that makes up the
 * user-interface for drawing maps.
 */
public class MapSquare extends JPanel {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final String CONFIG_PROPERTIES = "./config/config.properties";
   
    private final int size;
    private final Point position;
    private final MapGrid grid;
    private Entity entity;
    private JLabel entityImage;
    private final Border defaultBorder;
    private final Properties properties;

    /**
     * Creates a MapSquare object with its fields set to the passed-in
     * parameters.
     *
     * @param grid - The MapGrid in which the MapSquare is contained.
     * @param row - The row of the MapGrid in which this MapSquare is contained.
     * @param column - The column of the MapGrid in which this MapSquare is
     * contained.
     */
    public MapSquare(MapGrid grid, int row, int column) {
        // Load properties.
        properties = new Properties();
        try {
            properties.load(new FileInputStream(CONFIG_PROPERTIES));
        } catch (IOException ex) {
            // TO-DO: Something.
        }

        this.size = Integer.parseInt(properties.getProperty("map_grid_size"));

        // Save a reference to the MapGrid containing this MapSquare.
        this.grid = grid;

        // Record the position.
        position = new Point(column, row);

        // Remove default vGap that the FlowLayout puts in.
        ((FlowLayout) this.getLayout()).setVgap(0);

        // Set default border color.
        this.defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);

        // Setup behaviours.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                if (!MapSquare.this.containsAnyEntity()) {
                    getMapGrid().getSelectedTool().mouseEntered(MapSquare.this, event);
                }
            }

            @Override
            public void mouseExited(MouseEvent event) {
                if (!MapSquare.this.containsAnyEntity()) {
                    getMapGrid().getSelectedTool().mouseExited(MapSquare.this, event);
                }
            }

            @Override
            public void mousePressed(MouseEvent event) {
                getMapGrid().getSelectedTool().mousePressed(MapSquare.this, event);
            }
        });
    }

    /**
     * Set the Entity contained by the MapSquare.
     *
     * @param entity - The Entity to be contained by this MapSquare.
     */
    public void addEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Remove the entity contained by the MapSquare.
     */
    public void removeEntity() {
        this.entity = null;
    }

    /**
     * Determines whether or not the MapSquare contains the passed-in Entity.
     *
     * @param entity - Entity whose presence in the MapSquare is to be tested.
     * @return - True if the MapSquare contains the specified Entity.
     */
    public boolean containsEntity(Entity entity) {
        return this.entity == entity;
    }

    /**
     * Determines whether or not the MapSquare contains any entity.
     *
     * @return - True if the MapSquare contains any entity.
     */
    public boolean containsAnyEntity() {
        return entity != null;
    }

    /**
     * Adds the image at the passed-in path to the MapSquare.
     *
     * @param path - The path of the image to be added.
     */
    public void addImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            if (image != null) {
                Image scaledImage = image.getScaledInstance(size, size, 1);
                entityImage = new JLabel(new ImageIcon(scaledImage));
                this.add(entityImage);
                this.validate();
            }
        } catch (IOException e) {
            // TO-DO: Something.
        }
    }

    /**
     * Removes any entityImage that has been added to the MapSquare.
     */
    public void removeImage() {
        if (entityImage != null) {
            this.remove(entityImage);
            this.validate();
            this.repaint();
        }
    }

    /**
     * Sets a border appropriate to the presence of a vertical exit. The width
     * of the top and bottom edges of the border become zero, while the right
     * and left edges remain the same.
     */
    public void setVerticalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, VERY_LIGHT_GRAY));
    }

    /**
     * Sets a border appropriate to the presence of a horizontal exit. The width
     * of the right and left edges becomes zero, while the top and bottom edges
     * remain the same.
     */
    public void setHorizontalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, VERY_LIGHT_GRAY));
    }

    /**
     * Removes the current border applied to the MapSquare.
     */
    public void removeBorder() {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Restores the default border of the MapSquare.
     */
    public void restoreDefaultBorder() {
        this.setBorder(defaultBorder);
    }

    /**
     * Returns the MapGrid associated with this MapSquare.
     *
     * @return - The MapGrid associated with this MapSquare.
     */
    public MapGrid getMapGrid() {
        return this.grid;
    }

    /**
     * Returns the position of the MapSquare in the MapGrid.
     *
     * @return - Point object specifying position of the MapSquare in the
     * MapGrid.
     */
    public Point getPosition() {
        return this.position;
    }
    
    /**
     * Determines whether or not the MapSquare is within the bounds of the MapGrid.
     * 
     * @return - True if the x and y coordinates are not less than zero, false otherwise.
     */
    public boolean isInBounds() {
        return this.getPosition().x >= 0 && this.getPosition().y >= 0;
    }

    /**
     * Returns the preferred size of the MapSquare, as dictated by the
     * parameters passed to the constructor.
     *
     * @return - Dimension object corresponding to the size of the cell.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}
