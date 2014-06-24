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
    private JLabel imageLabel;
    private Border defaultBorder;
    
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
            public void mouseEntered(MouseEvent e) {
                getMapGrid().getSelectedTool().mouseEntered(MapSquare.this);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                getMapGrid().getSelectedTool().mouseExited(MapSquare.this);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               getMapGrid().getSelectedTool().mouseClicked(MapSquare.this);
            }
        });
    }
    
    public boolean hasEntity(Entity entity) {
        if(this.entity == entity) {
            return true;
        }
        return false;
    }
    
    public void setEntity(Entity entity) {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.entity = entity;
    }
    
    public boolean isFilled() {
        if(this.entity != null) {
            return true;
        }
        return false;
    }
    
     public void addImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        Image scaledImage = image.getScaledInstance(size, size, 1);
        imageLabel = new JLabel(new ImageIcon(scaledImage));
        this.add(imageLabel);
        this.validate();
        this.repaint();
    }
     
    public void removeImage() {
        if(!this.isFilled() && imageLabel != null) {
            this.remove(imageLabel);
            this.validate();
            this.repaint();
        }
    }
    
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
