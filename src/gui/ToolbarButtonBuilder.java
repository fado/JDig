package gui;

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

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import properties.Images;
import properties.Localization;

/**
 * Builder for toolbar buttons.
 */
public class ToolbarButtonBuilder {

    private static final Images images = new Images();
    private static final Localization localization = new Localization();

    /**
     * Static factory for JButtons.
     * @param buttonName The name to be added to the button.
     * @param actionListener The ActionListener to be added to the button.
     * @return the button built.
     */
    public static JButton build(String buttonName, ActionListener actionListener) {
        JButton button = new JButton(new ImageIcon(images.getImagePath(buttonName)));
        button.setToolTipText(localization.get(buttonName));
        button.addActionListener(actionListener);
        return button;
    }

}
