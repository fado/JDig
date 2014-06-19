package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Cell extends JPanel {
    
    private Color defaultBackground;
    
    public Cell() {
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                defaultBackground = getBackground();
                setBackground(Color.BLACK);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
        });

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }
}
