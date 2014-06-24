package gui.tools;

import globals.Entity;
import gui.MapGrid;
import gui.MapSquare;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(MapSquare square) {

        MapGrid grid = square.getMapGrid();

        // Check for horizontal connection.
        if (grid.getSquareWestOf(square.getPosition()).hasEntity(Entity.ROOM)
                && grid.getSquareEastOf(square.getPosition()).hasEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.HORIZONTAL_EXIT.getPath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
        }
    }

    @Override
    public void mouseExited(MapSquare square) {
        square.removeImage();
    }

    @Override
    public void mouseClicked(MapSquare square) {
        
    }
  
}