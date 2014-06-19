package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class Grid extends JPanel {
    
    private int rows;
    private int columns;
    
    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;
                
                Cell cell = new Cell();
                add(cell, constraints);
            }
        }
    }
}