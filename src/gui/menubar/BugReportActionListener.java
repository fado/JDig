package gui.menubar;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Listener for the JMenuItem which allows bug reporting.  Opens a new browser
 * window pointing to the website where bugs should be reported.
 */
public class BugReportActionListener implements ActionListener {

    static final Logger logger = LoggerFactory.getLogger(BugReportActionListener.class);

    /**
     * Uses the Desktop library to open the default browser and directs it
     * to the bug reporting website.
     * @param actionEvent The event originating this call.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            JdigProperties jdigProperties = new JdigProperties();
            URL url;
            try {
                url = new URL(jdigProperties.getProperty("BugReportURL"));
                desktop.browse(url.toURI());
            } catch (URISyntaxException | IOException ex) {
                logger.error(ex.toString());
            }

        }
    }

}
