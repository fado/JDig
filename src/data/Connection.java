package data;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;

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

public class Connection implements Entity {

    private final Color VERY_LIGHT_GRAY = new Color(224, 224, 224);
    private ConnectionType connectionType;

    public Connection(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public ConnectionType getConnectionType() {
        return this.connectionType;
    }

    public String getPath() {
        return connectionType.getPath();
    }

    @Override
    public String getNormalImage() {
        return null;
    }

    @Override
    public String getSelectedImage() {
        return null;
    }

    @Override
    public Border getBorder() {
        if (connectionType.equals(ConnectionType.VERTICAL)) {
            return BorderFactory.createMatteBorder(0, 1, 0, 1, VERY_LIGHT_GRAY);
        }
        if (connectionType.equals(ConnectionType.HORIZONTAL)) {
            return BorderFactory.createMatteBorder(1, 0, 1, 0, VERY_LIGHT_GRAY);
        }
        return BorderFactory.createEmptyBorder();
    }
}
