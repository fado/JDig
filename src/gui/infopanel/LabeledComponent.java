package gui.infopanel;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph 
 * developers.  Copyright (C) 2014 Fado@Epitaph.
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
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class LabeledComponent {
    
    private final JComponent component;
    private final JLabel label;
    
    private LabeledComponent(JComponent component, String labelText) {
        this.component = component;
        label = new JLabel(labelText, JLabel.RIGHT);
        label.setLabelFor(this.component);
    }
    
    public static LabeledComponent textField(String labelText, DocumentListener documentListener) {
        JTextField component = new JTextField();
        component.getDocument().addDocumentListener(documentListener);
        Dimension dimension = component.getPreferredSize();
        component.setPreferredSize(new Dimension(200, dimension.height));
        return new LabeledComponent(component, labelText);
    }
    
    public static LabeledComponent textArea(String labelText, DocumentListener documentListener) {
        JTextArea component = new JTextArea(5, 60);
        component.getDocument().addDocumentListener(documentListener);
        return new LabeledComponent(component, labelText);
    }
    
    public static LabeledComponent comboBox(String labelText, ActionListener actionListener) {
        JComboBox component = new JComboBox();
        component.addActionListener(actionListener);
        Dimension dimension = component.getPreferredSize();
        component.setPreferredSize(new Dimension(200, dimension.height));
        return new LabeledComponent(component, labelText);
    }
    
    public JComponent getComponent() {
        return this.component;
    }
    
    public JLabel getLabel() {
        return this.label;
    }
    
    public void setText(String string) {
        JTextComponent textField =(JTextComponent)this.component;
        textField.setText(string);
    }
    
    public void addtoPanel(JPanel panel) {
        panel.add(label);
        panel.add(component, "wrap");
    }

}