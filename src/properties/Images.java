package properties;

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

import java.io.IOException;
import java.util.Properties;

public class Images {
    
    private final Properties properties = new Properties();
    private final String BASE_PATH = "./resources/images/";
    
    public Images() {
        try {
            properties.load(Images.class.getResourceAsStream("images.properties"));
        } catch (IOException e) {
            // TO-DO: Something.
        }
    }
    
    public String getImage(String key) {
        return BASE_PATH + properties.getProperty(key);
    }
    
}