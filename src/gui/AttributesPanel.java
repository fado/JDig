package gui;

import java.awt.Dimension;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class AttributesPanel extends JPanel {
    
    public AttributesPanel() {
        setLayout(new MigLayout());
        setPreferredSize(new Dimension(400,400));
    }
    
}