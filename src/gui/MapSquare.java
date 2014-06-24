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
import java.io.IOException;
import java.util.Arrays;
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
    
    /**
     * Constants.
     */
    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    /**
     * Instance variables.
     */
    private final int size;
    private final Point position;
    private final MapGrid grid;
    private Entity entity;
    private JLabel entityImage;
    private final Border defaultBorder;
    
    /**
     * Creates a MapSquare object with its fields set to the passed-in parameters.
     * @param grid - The MapGrid in which the MapSquare is contained.
     * @param size - The size in pixels of the MapSquare.
     * @param row - The row of the MapGrid in which this MapSquare is contained.
     * @param column - The column of the MapGrid in which this MapSquare is contained.
     */
    public MapSquare(MapGrid grid, int size, int row, int column) {
        // Set the square size.
        this.size = size;
        
        // Save a reference to the MapGrid containing this MapSquare.
        this.grid = grid;
        
        // Record the position.
        position = new Point(column, row);
        
        // Remove default vGap that the FlowLayout puts in.
        ((FlowLayout)this.getLayout()).setVgap(0);
        
        // Set default border color.
        this.defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);
        
        // Setup behaviours.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                getMapGrid().getSelectedTool().mouseEntered(MapSquare.this, event);
            }
            
            @Override
            public void mouseExited(MouseEvent event) {
                getMapGrid().getSelectedTool().mouseExited(MapSquare.this, event);
            }
            
            @Override
            public void mouseClicked(MouseEvent event) {
                getMapGrid().getSelectedTool().mouseClicked(MapSquare.this, event);
            }
        });
    }
    
    /**
     * Determines whether or not the MapSquare contains the passed-in Entity.
     * @param entity - Entity whose presence in the MapSquare is to be tested.
     * @return - True if the MapSquare contains the specified Entity.
     */
    public boolean hasEntity(Entity entity) {
        return this.entity == entity;
    }
    
    /**
     * Set the Entity contained by the MapSquare.
     * @param entity  - The Entity to be contained by this MapSquare.
     */
    public void setEntity(Entity entity) {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.entity = entity;
    }
    
    /**
     * Determines whether or not the MapSquare contains an entity.
     * @return - True if the MapSquare contains an entity.
     */
    public boolean isFilled() {
        return this.entity != null;
    }
    
    /**
     * Adds the image at the passed-in path to the MapSquare.
     * @param path - The path of the image to be added.
     * @throws IOException 
     */
     public void addImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        Image scaledImage = image.getScaledInstance(size, size, 1);
        entityImage = new JLabel(new ImageIcon(scaledImage));
        this.add(entityImage);
        this.validate();
    }
     
    /**
     * Removes any entityImage that has been added to the MapSquare.
     */
    public void removeImage() {
        if(!this.isFilled() && entityImage != null) {
            this.remove(entityImage);
            this.validate();
            this.repaint();
        }
    }
    
    /**
     * Sets a border appropriate to the presence of a vertical exit.  The width
     * of the top and bottom edges of the border become zero, while the right 
     * and left edges remain the same.
     */
    public void setVerticalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, VERY_LIGHT_GRAY));
    }
    
    public void setHorizontalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, VERY_LIGHT_GRAY));
    }
    
    public void restoreDefaultBorder() {
        this.setBorder(defaultBorder);
    }
       
    /**
     * Returns the MapGrid associated with this MapSquare.
     * @return - The MapGrid associated with this MapSquare.
     */
    public MapGrid getMapGrid() {
        return this.grid;
    }
    
    /**
     * Returns the position of the MapSquare in the MapGrid.
     * @return - Point object specifying position of the MapSquare in the MapGrid.
     */
    public Point getPosition() {
        return this.position;
    }
   
    /**
     * Returns the preferred size of the MapSquare, as dictated by the parameters 
     * passed to the constructor.
     * @return - Dimension object corresponding to the size of the cell.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}
