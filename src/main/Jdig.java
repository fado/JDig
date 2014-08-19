package main;

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
import gui.MainUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;

public class Jdig {

    static final Logger logger = LoggerFactory.getLogger(Jdig.class);
    private static JdigProperties properties = new JdigProperties();

    /**
     * Start of the thread of execution.  Pulls the default number of
     * rows and columns from config.properties and creates a new
     * Level with those constraints.  That Level is passed to the MainUI
     * class and the runnable is executed.
     * @param args
     */
    public static void main(String[] args) {
        logger.info("Starting application...");
        int defaultX = Integer.parseInt(properties.get("DefaultX"));
        int defaultY = Integer.parseInt(properties.get("DefaultY"));
        MainUI ui = new MainUI(new Level(defaultX, defaultY));
        ui.run();
    }
}
