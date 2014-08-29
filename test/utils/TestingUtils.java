package utils;

import gui.infopanel.InfoPanel;

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

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Component;

public class TestingUtils {

    public JComboBox getStreetNameField(InfoPanel infoPanel) {
        // And so we embark on a magical journey.
        // Get the content panel.
        Component[] components = infoPanel.getComponents();
        JPanel contentPanel = null;
        for(Component component : components) {
            if(component.getName() != null
                    && component.getName().equals("contentPanel")) {
                contentPanel = (JPanel)component;
            }
        }
        // Get the streetNameField.
        Component[] internalComponents;
        JComboBox streetNameField = null;
        if (contentPanel != null) {
            internalComponents = contentPanel.getComponents();
            for(Component component : internalComponents) {
                if(component.getName() != null
                        && component.getName().equals("streetNameField")) {
                    streetNameField = (JComboBox)component;
                }
            }
        }
        return streetNameField;
    }
}
