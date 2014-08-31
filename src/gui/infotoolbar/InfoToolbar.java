package gui.infotoolbar;

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

import data.Level;
import gui.JdigComponent;
import gui.ToolbarButtonBuilder;
import properties.JdigProperties;

import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.util.Properties;

public class InfoToolbar extends JToolBar implements JdigComponent {

    public InfoToolbar(Level level) {
        setDefaultProperties();
        Properties jdigProperties = new JdigProperties();
        String templatePath = jdigProperties.getProperty("EpitaphTemplate");
        String destinationPath = jdigProperties.getProperty("LpcOutputFolder");
        this.add(ToolbarButtonBuilder.build("Generate",
                new GenerationListener(level, new DefaultGenerationDialog(),
                        templatePath, destinationPath)));
    }

    /**
     * Sets up the default properties for the tool bar.
     */
    private void setDefaultProperties() {
        this.setFloatable(false);
    }

    /**
     * Get the GridBagConstraints for this object.
     */
    public GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        return constraints;
    }

}
