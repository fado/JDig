package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * The MapGrid class specifies a collection of MapSquare that comprise the user
 * interface for drawing maps within JDig.
 */
public class MapUI extends JPanel {
    
    private List<SquareUI> squares;

    public MapUI(int gridSize, int maxRows, int maxColumns) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        if (squares == null) {
            squares = new ArrayList<>();
        }

        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxColumns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;
                SquareUI square = new SquareUI(gridSize, row, column);
                squares.add(square);
                add(square, constraints);
            }
        }
    }
    
}
