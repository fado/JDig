package gui.infopanel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

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
        JTextField textField =(JTextField)this.component;
        textField.setText(string);
    }
    
    public void addtoPanel(JPanel panel) {
        panel.add(label);
        panel.add(component, "wrap");
    }
    
    
}