package gui.tools;

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

    private JLabel imageLabel;
    private final String HORIZONTAL_EXIT = "./resources/images/horizontal_exit.png";
    
    @Override
    public void mouseEntered(MapSquare square) {
        if(!square.isFilled()) {
            MapGrid grid = square.getMapGrid();
            Dimension size = square.getPreferredSize();
            
            // Check for horizontal connection.
            if(grid.getSquareWestOf(square.getPosition()).isRoom() &&
                    grid.getSquareEastOf(square.getPosition()).isRoom()) {
                try {
                    System.out.println("Test.");
                    swapImage(square, HORIZONTAL_EXIT, size.height);
                } catch (IOException ex) {
                    // TO-DO: Something.
                }
            }
        }
    }

    @Override
    public void mouseExited(MapSquare square) {
        if(!square.isFilled() && imageLabel != null) {
            square.remove(imageLabel);
            square.validate();
            square.repaint();
        }
    }

    @Override
    public void mouseClicked(MapSquare square) {
        
    }
    
    public void swapImage(MapSquare square, String path, int size) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        Image scaledImage = image.getScaledInstance(size, size, 1);
        imageLabel = new JLabel(new ImageIcon(scaledImage));
        square.add(imageLabel);
        square.validate();
        square.repaint();
    }
    
}