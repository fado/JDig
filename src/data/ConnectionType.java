package data;

/**
 * Jdig, a tool for the automatic generation of LPC class files for Epitaph
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

import properties.ImageProperties;

/**
 * Represents the types of Connection that can exist between rooms.
 */
public enum ConnectionType {
    NONE(null),
    HORIZONTAL(new ImageProperties().getImagePath("HorizontalExit")),
    VERTICAL(new ImageProperties().getImagePath("VerticalExit")),
    FORWARD_DIAGONAL(new ImageProperties().getImagePath("ForwardDiagonalExit")),
    BACKWARD_DIAGONAL(new ImageProperties().getImagePath("BackwardDiagonalExit")),
    X(new ImageProperties().getImagePath("XExit"));

    private String exitImagePath;

    /**
     * Constructor.
     * @param exitImagePath The path where the associated image lies.
     */
    ConnectionType(String exitImagePath) {
         this.exitImagePath = exitImagePath;
    }

    /**
     * Returns the path of the image associated with the ConnectionType.
     * @return the path of the image associated with the ConnectionType.
     */
    public String getPath() {
        return this.exitImagePath;
    }

}
