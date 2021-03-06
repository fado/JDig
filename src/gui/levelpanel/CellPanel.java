package gui.levelpanel;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph 
 * developers.
 * Copyright (C) 2014 Fado@Epitaph.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import data.Cell;
import data.Entity;
import tools.LevelTool;
import tools.LevelToolEvent;
import tools.LevelToolListener;
import properties.Images;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Represents a single Cell within a Level.
 */
public class CellPanel extends JPanel implements LevelToolListener {

    private final int SIZE = 15;
    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final Border defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
    private LevelTool selectedLevelTool;
    private boolean selected = false;
    private JLabel entityImage;
    private String imagePath;
    static final Logger logger = LoggerFactory.getLogger(CellPanel.class);
    private int panelX;
    private int panelY;

    /**
     * Constructor takes as an argument the Cell which this CellPanel represents.
     */
    public CellPanel(int x, int y) {
        this.panelX = x;
        this.panelY = y;
        setDefaultProperties();
    }

    /**
     * Sets up the default properties for the CellPanel.  Sets up the border,
     * background, layout and mouse listeners.
     */
    private void setDefaultProperties() {
        Border defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);
        ((FlowLayout) this.getLayout()).setVgap(0);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                selectedLevelTool.mouseEntered(event);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                selectedLevelTool.mouseExited(event);
            }

            @Override
            public void mousePressed(MouseEvent event) {
                selectedLevelTool.mousePressed(event);
            }
        });
    }

    /**
     * Determines whether or not the CellPanel is selected.
     * @return true if the CellPanel is selected, false otherwise.
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * Sets whether or not the CellPanel is selected.
     * @param bool True if the CellPanel is selected, false otherwise.
     */
    public void setSelected(Boolean bool) {
        this.selected = bool;
    }

    /**
     * Determines whether or not the CellPanel has an Entity image set.
     * @return true if an Entity image has been set, otherwise false.
     */
    public boolean hasEntityImage() {
        return entityImage != null;
    }

    /**
     * Returns the path of the current entity image.
     * @return the path of the current Entity image, or null if no image has
     * been set.
     */
    public String getEntityImagePath() {
        return this.imagePath;
    }

    /**
     * Adds the image at the passed-in path to the CellPanel.
     * @param path The path of the image.
     */
    public void addImage(String path) {
        this.imagePath = path;
        try {
            BufferedImage image = ImageIO.read(new File(path));
            if (image != null) {
                Image scaledImage = image.getScaledInstance(SIZE, SIZE, 1);
                this.entityImage = new JLabel(new ImageIcon(scaledImage));
                add(entityImage);
                validate();
                repaint();
            }
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    /**
     * Removes the entity image from the CellPanel.
     */
    public void removeImage() {
        if(entityImage != null) {
            remove(entityImage);
            validate();
            repaint();
            this.entityImage = null;
            this.imagePath = null;
        }
    }

    /**
     * Sets the border of the CellPanel to the border appropriate for the
     * passed-in Entity.
     * @param entity The Entity for which the border should be drawn.
     */
    public void setBorder(Entity entity) {
        setBorder(entity.getBorder());
    }

    /**
     * Removes any border currently applied to the CellPanel.
     */
    public void removeBorder() {
        setBorder(BorderFactory.createEmptyBorder());
    }

    /**
     * Restores the default border of the CellPanel.
     */
    public void restoreDefaultBorder() {
        setBorder(defaultBorder);
    }

    /**
     * Returns the X coordinate of the panel.
     * @return the X coordinate of the panel.
     */
    public int getPanelX() {
        return this.panelX;
    }

    /**
     * Returns the Y coordinate of the panel.
     * @return the Y coordinate of the panel.
     */
    public int getPanelY() {
        return this.panelY;
    }

    /**
     * Restores the default color of the CellPanel.  Figured it as better
     * to keep it localized here rather than calling setBackground from the
     * deletion tool.  Makes more sense to keep state about this object
     * in the object.  Not sure how to farm the colour out to a config file.
     */
    public void restoreDefaultBackground() {
        this.setBackground(Color.WHITE);
    }

    /**
     * Gets the preferred size of this component.
     * @return the preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        int SIZE = 15;
        return new Dimension(SIZE, SIZE);
    }

    /**
     * Fires when the LevelTool is changed.
     * @param event The event originating the method call.
     */
    @Override
    public void toolChanged(LevelToolEvent event) {
        this.selectedLevelTool = event.getLevelTool();
    }

}
