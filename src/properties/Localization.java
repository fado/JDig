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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a means by which we can reference text in a non-absolute fashion.
 * Accessing text in this way makes it trivial to translate the application
 * into other languages, simply by swapping out the localization.properties
 * file.
 */
public class Localization {

    private final Properties properties = new Properties();
    static final Logger logger = LoggerFactory.getLogger(Localization.class);

    /**
     * Constructor loads in the localization.properties file.
     */
    public Localization() {
        try {
            properties.load(new FileInputStream("./config/localization.properties"));
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }

    /**
     * Returns the property corresponding to the passed-in key.
     * @param key The key that corresponds to the desired property.
     * @return the property corresponding to the passed-in key.
     */
    public String get(String key) {
        return properties.getProperty(key);
    }

}