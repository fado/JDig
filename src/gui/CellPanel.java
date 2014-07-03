package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
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
import main.Cell;
import tools.DefaultPointer;
import tools.Tool;
import tools.ToolEvent;
import tools.ToolListener;

public class CellPanel extends JPanel implements ToolListener {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final int SIZE = 15;
    private JLabel entityImage;
    private final Border defaultBorder;
    private Tool selectedTool;

    /**
     * Constructor takes as a parameter the Cell object associated with this
     * CellPanel.
     *
     * @param cellObject - The Cell object associated with this Cell panel.
     */
    public CellPanel(final Cell cellObject) {
        this.defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);
        this.selectedTool = new DefaultPointer();
        ((FlowLayout) this.getLayout()).setVgap(0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                selectedTool.mouseEntered(CellPanel.this, cellObject, event);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                selectedTool.mouseExited(CellPanel.this, cellObject, event);
            }

            @Override
            public void mouseClicked(MouseEvent event) {
                selectedTool.mouseClicked(CellPanel.this, cellObject, event);
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
                Image scaledImage = image.getScaledInstance(SIZE, SIZE, 1);
                entityImage = new JLabel(new ImageIcon(scaledImage));
                this.add(entityImage);
                this.validate();
                this.repaint();
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
     * Returns the preferred size of the MapSquare, as dictated by the
     * parameters passed to the constructor.
     *
     * @return - Dimension object corresponding to the size of the cell.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }

    /**
     * Fires when the currently selected Tool is changed in the MapToolbar.
     *
     * @param event - The ToolEvent containing a reference to the newly selected
     * Tool.
     */
    @Override
    public void toolChanged(ToolEvent event) {
        this.selectedTool = event.getTool();
    }
}
