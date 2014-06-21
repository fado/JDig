package gui;

import gui.tools.Tool;
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
 * The Cell class specifies one cell in the MapGrid that makes up the 
 * user-interface for drawing maps.
 */
public class Cell extends JPanel {
    
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
    private Tool selectedTool;
    private boolean filled = false;
    
    /**
     * Creates a Cell object with its fields set to the passed-in parameters.
     * @param grid - The MapGrid in which the Cell is contained.
     * @param size - The size in pixels of the Cell.
     * @param row - The row of the MapGrid in which this Cell is contained.
     * @param column - The column of the MapGrid in which this Cell is contained.
     */
    public Cell(MapGrid grid, int size, int row, int column) {
        // Set the cell size.
        this.size = size;
        
        // Save a reference to the MapGrid containing this Cell.
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
                getMapGrid().getSelectedTool().mouseEntered(Cell.this);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                getMapGrid().getSelectedTool().mouseExited(Cell.this);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
               getMapGrid().getSelectedTool().mouseClicked(Cell.this);
            }
        });
    }
    
    /**
     * This method changes the border of the Cell to illustrate that a room 
     * has been drawn in it.
     */
    public void drawRoom() {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        this.setBorder(blackBorder);
    }
    
    /**
     * Returns the MapGrid associated with this Cell.
     * @return - The MapGrid associated with this Cell.
     */
    public MapGrid getMapGrid() {
        return this.grid;
    }
    
    /**
     * Returns the position of the Cell in the MapGrid.
     * @return - Point object specifying position of the Cell in the MapGrid.
     */
    public Point getPosition() {
        return this.position;
    }
    
    /**
     * Sets whether or not the cell has been filled with an entity.
     * @param bool - Boolean value specifying whether or not the cell has been filled.
     */
    public void setFilled(boolean bool) {
        this.filled = bool;
    }
    
    /**
     * Determines whether or not the cell has been filled with an entity.
     * @return - True if the cell has been filled with an entity, false otherwise.
     */
    public boolean isFilled() {
        return this.filled;
    }

    /**
     * Returns the preferred size of the cell, as dictated by the parameters 
     * passed to the constructor.
     * @return - Dimension object corresponding to the size of the cell.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}
