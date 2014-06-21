package gui.tools;

import gui.Cell;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class RoomTool implements Tool {

    private Color defaultBackground;
    
    @Override
    public void mouseEntered(Cell cell) {
        defaultBackground = cell.getBackground();
        cell.setBackground(Color.BLACK);
    }
    
    @Override
    public void mouseExited(Cell cell) {
        cell.setBackground(defaultBackground);
    }

    @Override
    public void mouseClicked(Cell cell) {
        if (!cell.isFilled()) {
            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            cell.setBorder(blackBorder);
            cell.setFilled(true);
        }
    }

}