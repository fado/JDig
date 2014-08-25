package gui;

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
import tools.CellTool;
import tools.LevelTool;
import tools.LevelToolEvent;
import tools.LevelToolListener;
import properties.Images;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Represents a single Cell within a Level.
 */
public class CellPanel extends JPanel implements LevelToolListener {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private LevelTool selectedLevelTool;
    private boolean selected = false;
    private Cell cell;
    private JLabel entityImage;

    public CellPanel(Cell cell) {
        this.cell = cell;
        setDefaultProperties();
        // Check if the Cell already has an Entity.
        if(cell.getEntity() != null) {
            restoreCell(cell);
        }
    }

    /**
     * If we're loading a Level from memory and the Cell already has an Entity
     * in it, this method controls how the CellPanel is rendered.
     * @param cell The Cell corresponding to this CellPanel.
     */
    private void restoreCell(Cell cell) {
        CellTool cellTool = new CellTool();
        if(cell.isConnectible()) {
            Images images = new Images();
            cellTool.addImage(this, images.getImagePath("Room"));
            if(cell.getColor() != null) {
                this.setBackground(cell.getColor());
            }
            cellTool.removeBorder(this);
        }
        if(cell.isExit()) {
            cellTool.addImage(this, cell.getEntity().getNormalImage());
            cellTool.setBorder(this, (cell.getEntity()));
        }
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
     * Sets the image for the Entity contained by the Cell.
     * @param image The image for the Entity contained by the Cell.
     */
    public void setEntityImage(JLabel image) {
        this.entityImage = image;
    }

    /**
     * Adds the entity image to the CellPanel component.
     */
    public void addEntityImage() {
        if(entityImage != null) {
            this.add(entityImage);
        }
    }

    /**
     * Removes the entity image from the CellPanel component.
     */
    public void removeEntityImage() {
        if(entityImage != null) {
            this.remove(entityImage);
        }
    }

    /**
     * Returns the Cell that corresponds to this CellPanel.
     * @return the Cell that corresponds to this CellPanel.
     */
    public Cell getCell() {
        return this.cell;
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
