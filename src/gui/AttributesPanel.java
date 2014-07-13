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