package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * The MapGrid class specifies a collection of Cells that comprise the user 
 * interface for drawing maps within JDig.
 */
public class MapGrid extends JPanel {
    
    /**
     * The default dimensions of each cell, expressed in pixels.
     */
    private final int SIZE = 15;
    
    /**
     * Creates a Grid object with its fields set to the passed-in arguments.
     * @param maxRows - Size of the grid on the y axis.
     * @param maxColumns - Size of the grid on the x axis.
     */
    public MapGrid(int maxRows, int maxColumns) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        for(int row = 0; row < maxRows; row++) {
            for(int column = 0; column < maxColumns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;
                
                Cell cell = new Cell(SIZE, row, column);
                add(cell, constraints);
            }
        }
    }
}