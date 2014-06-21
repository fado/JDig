package gui;

import gui.tools.DefaultPointer;
import gui.tools.Tool;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * The MapGrid class specifies a collection of Cells that comprise the user 
 * interface for drawing maps within JDig.
 */
public class MapGrid extends JPanel {
    
    // The default dimensions of each cell, expressed in pixels.
    private final int SIZE = 15;
    private Tool selectedTool;
    
    /**
     * Creates a Grid object with its fields set to the passed-in arguments.
     * @param maxRows - Size of the grid on the y axis.
     * @param maxColumns - Size of the grid on the x axis.
     */
    public MapGrid(int maxRows, int maxColumns) {
        // Set default tool selection.
        selectedTool = new DefaultPointer();
        
        // Setup grid.
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        for(int row = 0; row < maxRows; row++) {
            for(int column = 0; column < maxColumns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;
                
                Cell cell = new Cell(this, SIZE, row, column);
                add(cell, constraints);
            }
        }
    }
    
    /**
     * Returns the currently selected tool.
     * @return - The currently selected tool.
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    }
    
    /**
     * Sets the currently selected tool.
     * @param tool - The currently selected tool.
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
    }
}