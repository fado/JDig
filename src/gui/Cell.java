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
 * The Cell class specifies one cell in the MapGrid that makes up the 
 * user-interface for drawing maps.
 */
public class Cell extends JPanel {
    
    /**
     * Resource paths.
     */
    //private final String IMAGES = "./resources/images/";
    //private final String HORIZONTAL_EXIT = IMAGES + "horizontal_exit.png";
    //private final String BACK_DIAG_EXIT = IMAGES + "back_diagonal_exit.png";
    //private final String FWD_DIAG_EXIT = IMAGES + "forward_diagonal_exit.png";
    //private final String X_EXIT = IMAGES + "x_exit.png";
    /**
     * Constants.
     */
    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    /**
     * Instance variables.
     */
    private Color defaultBackground;
    private final int size;
    private Point position;
    
    /**
     * Creates a Cell object with its fields set to the passed-in parameters.
     * @param size - The size in pixels of the Cell.
     * @param row - The row of the MapGrid in which this Cell is contained.
     * @param column - The column of the MapGrid in which this Cell is contained.
     */
    public Cell(int size, int row, int column) {
        // Set the cell size.
        this.size = size;
        // Record the position.
        position = new Point(column, row);
        // Remove default vGap that the FlowLayout puts in.
        ((FlowLayout)this.getLayout()).setVgap(0);
        // Set default border color.
        Border greyBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(greyBorder);
        this.setBackground(Color.WHITE);
                
        // Setup behaviours.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                defaultBackground = getBackground();
                setBackground(Color.BLACK);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                drawRoom();
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
     * Returns the position of the Cell in the MapGrid.
     * @return - Point object specifying position of the Cell in the MapGrid.
     */
    public Point getPosition() {
        return this.position;
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
