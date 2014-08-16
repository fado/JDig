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
import data.Entity;
import gui.leveltools.LevelTool;
import gui.leveltools.LevelToolEvent;
import gui.leveltools.LevelToolListener;
import properties.Images;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellPanel extends JPanel implements LevelToolListener {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final int SIZE = 15;
    private JLabel entityImage;
    private Border defaultBorder;
    private LevelTool selectedLevelTool;
    private boolean selected = false;
    private Cell cell;
    private Images images = new Images();
    private final String ROOM_IMAGE = images.getImagePath("Room");
    static final Logger logger = LoggerFactory.getLogger(CellPanel.class);

    public CellPanel(final Cell cell) {
        this.cell = cell;
        setDefaultProperties();

        if(cell.isConnectible()) {
            this.addImage(ROOM_IMAGE);
            if(cell.getColor() != null) {
                this.setBackground(cell.getColor());
            }
            this.removeBorder();
        }
        if(cell.isExit()) {
            this.addImage(cell.getEntity().getNormalImage());
            this.setBorder((cell.getEntity()));
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                selectedLevelTool.mouseEntered(cell, event);
            }
            @Override
            public void mouseExited(MouseEvent event) {
                if (cell.getEntity() == null) {
                    CellPanel.this.removeImage();
                    CellPanel.this.restoreDefaultBorder();
                }
            }
            @Override
            public void mousePressed(MouseEvent event) {
                selectedLevelTool.mousePressed(cell, event);
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
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    public void removeImage() {
        if (entityImage != null) {
            this.remove(entityImage);
            this.validate();
            this.repaint();
        }
    }

    public void removeBorder() {
        this.setBorder(BorderFactory.createEmptyBorder());
    }

    public void restoreDefaultBorder() {
        this.setBorder(defaultBorder);
    }

    public void setBorder(Entity entity) {
        this.setBorder(entity.getBorder());
    }

    public void setSelected() {
        this.removeImage();
        this.addImage(cell.getEntity().getSelectedImage());
        this.selected = true;
    }

    public void setDeselected() {
        this.removeImage();
        if(cell.isConnectible()) {
            this.addImage(cell.getEntity().getNormalImage());
        }
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
    public void toolChanged(LevelToolEvent event) {
        this.selectedLevelTool = event.getLevelTool();
    }

}
