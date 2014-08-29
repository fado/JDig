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

import properties.Images;
import properties.JdigProperties;

/**
 * Represents the types of Connection that can exist between rooms.
 */
public enum ConnectionType {
    NONE() {
        @Override
        public String getPath() {
            return null;
        }
    },
    HORIZONTAL() {
        @Override
        public String getPath() {
            return new Images().getImagePath("HorizontalExit");
        }
    },
    VERTICAL() {
        @Override
        public String getPath() {
            return new Images().getImagePath("VerticalExit");
        }
    },
    FORWARD_DIAGONAL() {
        @Override
        public String getPath() {
            return new Images().getImagePath("ForwardDiagonalExit");
        }
    },
    BACKWARD_DIAGONAL() {
        @Override
        public String getPath() {
            return new Images().getImagePath("BackwardDiagonalExit");
        }
    },
    X() {
        @Override
        public String getPath() {
            return new Images().getImagePath("XExit");
        }
    };

    public abstract String getPath();

}
