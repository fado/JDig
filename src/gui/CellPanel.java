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
import data.Cell;
import data.Entity;
import gui.toolbars.maptools.MapTool;
import gui.toolbars.maptools.MapToolEvent;
import gui.toolbars.maptools.MapToolListener;

public class CellPanel extends JPanel implements MapToolListener {

    private final String ROOM_IMAGE = "./resources/images/room.png";
    private final String SELECTED_ROOM_IMAGE = "./resources/images/selected_room.png";
    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final int SIZE = 15;
    private JLabel entityImage;
    private Border defaultBorder;
    private MapTool selectedMapTool;
    private boolean selected = false;
    private Cell cell;
    private Color previousColor;

    /**
     * Constructor takes as a parameter the Cell object associated with this
     * CellPanel.
     *
     * @param cell - The Cell object associated with this Cell panel.
     */
    public CellPanel(final Cell cell) {
        this.cell = cell;
        setDefaultProperties();

        if(cell.isRoom()) {
            this.addImage(ROOM_IMAGE);
            if(cell.getColor() != null) {
                this.setBackground(cell.getColor());
            }
            this.removeBorder();
        }
        if(cell.isExit()) {
            this.addImage(cell.getEntity().getPath());
            this.setBorder((cell.getEntity()));
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                selectedMapTool.mouseEntered(cell, event);
            }
            @Override
            public void mouseExited(MouseEvent event) {
                if (cell.getEntity().equals(Entity.NO_ENTITY)) {
                    CellPanel.this.removeImage();
                    CellPanel.this.restoreDefaultBorder();
                }
            }
            @Override
            public void mousePressed(MouseEvent event) {
                selectedMapTool.mousePressed(cell, event);
            }
            @Override
            public void mouseReleased(MouseEvent event) {
                selectedMapTool.mouseReleased(cell, event);
            }
        });
    }

    private void setDefaultProperties() {
        this.defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
        this.setBorder(defaultBorder);
        this.setBackground(Color.WHITE);
        ((FlowLayout) this.getLayout()).setVgap(0);
    }
    
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

    public void removeImage() {
        if (entityImage != null) {
            this.remove(entityImage);
            this.validate();
            this.repaint();
        }
    }

    public void setVerticalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, VERY_LIGHT_GRAY));
    }

    public void setHorizontalExitBorder() {
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, VERY_LIGHT_GRAY));
    }

    public void removeBorder() {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void restoreDefaultBorder() {
        this.setBorder(defaultBorder);
    }

    public void setBorder(Entity exit) {
        if (exit == Entity.VERTICAL_EXIT) {
            setVerticalExitBorder();
        } else if (exit == Entity.HORIZONTAL_EXIT) {
            setHorizontalExitBorder();
        }
    }

    public void setSelected() {
        this.removeImage();
        this.addImage(SELECTED_ROOM_IMAGE);
        this.selected = true;
    }

    public void setDeselected() {
        this.removeImage();
        this.addImage(ROOM_IMAGE);
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public Cell getCell() {
        return this.cell;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }

    @Override
    public void toolChanged(MapToolEvent event) {
        this.selectedMapTool = event.getMapTool();
    }
}
