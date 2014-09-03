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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * Generic LabeledComponent to make the creation of the InfoPanel cleaner.
 */
public class LabeledComponent {
    
    private final JComponent component;
    private final JLabel label;

    /**
     * Constructor.
     * @param component The JComponent wrapped by the LabeledComponent.
     * @param labelText The text for the label.
     */
    private LabeledComponent(JComponent component, String labelText) {
        this.component = component;
        label = new JLabel(labelText, JLabel.RIGHT);
        label.setLabelFor(this.component);
    }

    /**
     * Static factory allowing for the creation of a new LabeledComponent based
     * off a JTextField.
     * @param labelText The text for the label.
     * @param documentListener The document listener to be attached to the field.
     * @return The new LabeledComponent.
     */
    public static LabeledComponent textField(String labelText,
                                             DocumentListener documentListener) {
        JTextField component = new JTextField();
        component.getDocument().addDocumentListener(documentListener);
        Dimension dimension = component.getPreferredSize();
        component.setPreferredSize(new Dimension(200, dimension.height));
        return new LabeledComponent(component, labelText);
    }

    /**
     * Static factory allowing for the creation of a new LabeledComponent based
     * off a JTextArea.
     * @param labelText The text for the label.
     * @param documentListener The document listener to be attached to the field.
     * @return The new LabeledComponent.
     */
    public static LabeledComponent textArea(String labelText,
                                            DocumentListener documentListener) {
        JTextArea component = new JTextArea(5, 60);
        component.getDocument().addDocumentListener(documentListener);
        return new LabeledComponent(component, labelText);
    }

    /**
     * Return the base JComponent for this LabeledComponent.
     * @return the base JComponent for this LabeledComponent.
     */
    public JComponent getComponent() {
        return this.component;
    }

    /**
     * Return the label for this LabeledComponent.
     * @return the label for this LabeledComponent.
     */
    public JLabel getLabel() {
        return this.label;
    }

    /**
     * Set the text in the base JComponent for this LabeledComponent.  First
     * casts it as a JTextComponent.
     * @param string
     */
    public void setText(String string) {
        JTextComponent textField = (JTextComponent)this.component;
        textField.setText(string);
    }

    /**
     * Adds this LabeledComponent to the passed-in panel.
     * @param panel The panel the LabeledComponent should be added to.
     */
    public void addToPanel(JPanel panel) {
        panel.add(label);
        panel.add(component, "wrap");
    }

}
