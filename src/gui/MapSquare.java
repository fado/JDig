package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
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
    private boolean filled = false;
    private boolean room = false;
    
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
        Border greyBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(greyBorder);
        this.setBackground(Color.WHITE);
                
        // Determine the currently selected tool.
        // selectedTool = grid.getSelectedTool();
        
        // Setup behaviours.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
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
     * Determines whether or not the MapSquare contains a room.
     * @return - True if the MapSquare contains a room, false otherwise.
     */
    public boolean isRoom() {
        return this.room;
    }
    
    /**
     * Sets whether or not the MapSquare contains with a room.
     * @param bool - Boolean value specifying whether or not the MapSquare 
     * contains a room.
     */
    public void setRoom(boolean bool) {
        this.room = bool;
    }
    
    /**
     * Sets whether or not the MapSquare has been filled with an entity.
     * @param bool - Boolean value specifying whether or not the MapSquare has been filled.
     */
    public void setFilled(boolean bool) {
        this.filled = bool;
    }
    
    /**
     * Determines whether or not the MapSquare has been filled with an entity.
     * @return - True if the MapSquare has been filled with an entity, false otherwise.
     */
    public boolean isFilled() {
        return this.filled;
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
