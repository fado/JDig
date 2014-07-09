package gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class AttributesPanel extends JPanel {
    
    public AttributesPanel() {
        setLayout(new MigLayout());
        setPreferredSize(new Dimension(400,400));
        
        JTextField roomNameField = new JTextField(10);
        roomNameField.setActionCommand("Room name...");
        
        this.add(roomNameField);
        
    }
    
}