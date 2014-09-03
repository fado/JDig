package utils;

import gui.infopanel.InfoPanel;
import gui.infopanel.streeteditor.StreetEditor;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Container;

public class TestingUtils {

    /**
     * Returns the street name field from the passed in InfoPanel.
     * @param infoPanel The street editor containing the button.
     * @return the street name field from the passed in InfoPanel.
     */
    public JComboBox getStreetNameField(InfoPanel infoPanel) {
        JPanel contentPanel = (JPanel)getComponent(infoPanel, "contentPanel");
        return (JComboBox)getComponent(contentPanel, "streetNameField");
    }

    /**
     * Returns the delete button from the passed in street editor.
     * @param streetEditor The street editor containing the button.
     * @return the delete button from the passed in street editor.
     */
    public JButton getDeleteButton(StreetEditor streetEditor) {
        JPanel buttonPane =
                (JPanel)getComponent(streetEditor.getContentPane(), "buttonPane");
        return (JButton)getComponent(buttonPane, "deleteButton");
    }

    /**
     * Looks through the passed in container for the named component.
     * @param container The container to be searched.
     * @param componentName The name of the component searched for.
     * @return the component found.
     */
    private Component getComponent(Container container, String componentName) {
        Component[] components = container.getComponents();
        Component componentToReturn = null;
        for (Component component : components) {
            if (component.getName() != null
                    && component.getName().equalsIgnoreCase(componentName)) {
                componentToReturn = component;
            }
        }
        return componentToReturn;
    }
}
