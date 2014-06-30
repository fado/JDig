package gui;

import tools.ToolListener;
import tools.ToolEvent;
import tools.DefaultPointer;
import tools.Tool;
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
public class SquareUI extends JPanel implements ToolListener {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final int size;
    private final Point position;
    private Tool selectedTool;
    private JLabel entityImage;
    private final Border defaultBorder;

    /**
     * Creates a MapSquare object with its fields set to the passed-in
     * parameters.
     *
     * @param size
     * @param row - The row of the MapGrid in which this MapSquare is contained.
     * @param column - The column of the MapGrid in which this MapSquare is
     * contained.
     */
    public SquareUI(int size, int row, int column) {
        this.size = size;
        this.position = new Point(column, row);
        this.defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);
        this.selectedTool = new DefaultPointer();
        // Remove default vGap that the FlowLayout puts in.
        ((FlowLayout) this.getLayout()).setVgap(0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                
            }

            @Override
            public void mouseExited(MouseEvent event) {
                
            }

            @Override
            public void mousePressed(MouseEvent event) {
            
            }
        });
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
     * Fires when the selectedTool is changed in the MapGrid.
     * 
     * @param event - Event object containing a reference to the new selected Tool.
     */
    @Override
    public void toolChanged(ToolEvent event) {
        this.selectedTool = event.getTool();
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
