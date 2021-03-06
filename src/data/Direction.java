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
 * Describes each possible direction along which a Connection can be made.
 */
public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTHEAST(1, -1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(-1, 1);

    public final int xOffset;
    public final int yOffset;

    /**
     * Each value of this enumerator describes an offset along the x and y axis
     * that should be applied to a Cell in order to derive the Cell that lies
     * in the named direction.
     * @param xOffset The coordinate offset on the x axis.
     * @param yOffset The coordinate offset on the y axis.
     */
    Direction(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    /**
     * Returns the x coordinate of the Cell that lies in the named Direction from
     * the passed-in Cell.
     * @param cell The reference Cell.
     * @return the x coordinate that lies in the named Direction.
     */
    public int translateX(Cell cell) {
        return cell.X + this.xOffset;
    }

    /**
     * Returns the y coordinate of the Cell that lies in the named Direction from
     * the passed-in Cell.
     * @param cell The reference Cell.
     * @return the y coordinate that lies in the named Direction.
     */
    public int translateY(Cell cell) {
        return cell.Y + this.yOffset;
    }

}
