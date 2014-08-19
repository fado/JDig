package tools;

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

import data.Entity;
import gui.CellPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CellTool {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private final Border defaultBorder = BorderFactory.createLineBorder(VERY_LIGHT_GRAY);
    static final Logger logger = LoggerFactory.getLogger(LevelTool.class);

    public void addImage(CellPanel cellPanel, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            if (image != null) {
                int SIZE = 15;
                Image scaledImage = image.getScaledInstance(SIZE, SIZE, 1);
                cellPanel.setEntityImage(new JLabel(new ImageIcon(scaledImage)));
                cellPanel.addEntityImage();
                cellPanel.validate();
                cellPanel.repaint();
            }
        } catch (IOException ex) {
            logger.error(ex.toString());
            ex.printStackTrace();
        }
    }

    public void removeImage(CellPanel cellPanel) {
        cellPanel.removeEntityImage();
        cellPanel.validate();
        cellPanel.repaint();
    }

    public void setBorder(CellPanel cellPanel, Entity entity) {
        cellPanel.setBorder(entity.getBorder());
    }

    public void removeBorder(CellPanel cellPanel) {
        cellPanel.setBorder(BorderFactory.createEmptyBorder());
    }

    public void restoreDefaultBorder(CellPanel cellPanel) {
        cellPanel.setBorder(defaultBorder);
    }

    public void setSelected(CellPanel cellPanel) {
        removeImage(cellPanel);
        addImage(cellPanel, cellPanel.getCell().getEntity().getSelectedImage());
        cellPanel.setSelected(true);
    }

    public void setDeselected(CellPanel cellPanel) {
        removeImage(cellPanel);
        if(cellPanel.getCell().isConnectible()) {
            addImage(cellPanel, cellPanel.getCell().getEntity().getNormalImage());
        }
        cellPanel.setSelected(false);
    }

}
