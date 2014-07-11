package gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class AttributesPanel extends JPanel {
    
    public final JTextField roomNameField;
    public final JComboBox streetNameField;
    public final JButton addEditStreetsButton;
    
    public AttributesPanel() {
        setLayout(new MigLayout());
        setPreferredSize(new Dimension(400,400));
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new MigLayout());
        contentPanel.setOpaque(true);
        //contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(
                BorderFactory.createTitledBorder("Attributes"));
        
        roomNameField = new JTextField(20);
        streetNameField = new JComboBox();
        addEditStreetsButton = new JButton("Add/Edit Streets");
        
        JLabel roomNameLabel = new JLabel("Room name:", JLabel.RIGHT);
        JLabel streetNameLabel = new JLabel("Street name:", JLabel.RIGHT);
        roomNameLabel.setLabelFor(roomNameField);
        streetNameLabel.setLabelFor(streetNameField);
        
        contentPanel.add(roomNameLabel);
        contentPanel.add(roomNameField, "wrap");
        contentPanel.add(streetNameLabel);
        contentPanel.add(streetNameField);
        contentPanel.add(addEditStreetsButton);
        
        this.add(contentPanel);
    }
    
}