package data;

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

/**
 * Represents the types of Connection that can exist between rooms.
 */
public enum ConnectionType {
    NONE(null),
    HORIZONTAL("./resources/images/horizontal_exit.png"),
    VERTICAL("./resources/images/vertical_exit.png"),
    FORWARD_DIAGONAL("./resources/images/forward_diagonal_exit.png"),
    BACKWARD_DIAGONAL("./resources/images/back_diagonal_exit.png"),
    X("./resources/images/x_exit.png");

    private final String path;

    ConnectionType(String path) {
        this.path = path;
    }

    /**
     * Returns the path of the image that corresponds to the ConnectionType.
     * @return the path of the image that corresponds to the ConnectionType.
     */
    public String getPath() {
        return this.path;
    }

}
