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

import java.util.List;
import java.util.Map;

public class ExitBuilder {

    private static Map<String, Cell> cells;

    private ExitBuilder() {

    }

    public static void build(Cell cell) {
        ConnectionType connectionType = cell.getPotentialConnectionType();
        cells = cell.getAdjacentCells();

        if (connectionType == ConnectionType.HORIZONTAL) {
            buildExit("westCell", "eastCell", Direction.EAST);
            buildExit("eastCell", "westCell", Direction.WEST);
        }
        if (connectionType == ConnectionType.VERTICAL) {
            buildExit("northCell", "southCell", Direction.SOUTH);
            buildExit("southCell", "northCell", Direction.NORTH);
        }
        if (connectionType == ConnectionType.BACKWARD_DIAGONAL) {
            buildBackwardDiagonal();
        }
        if (connectionType == ConnectionType.FORWARD_DIAGONAL) {
            buildForwardDiagonal();
        }
        if (connectionType == ConnectionType.X) {
            buildBackwardDiagonal();
            buildForwardDiagonal();
        }
    }

    public static void buildOnlyForwardDiagonal(Cell cell) {
        cells = cell.getAdjacentCells();
        buildForwardDiagonal();
    }

    public static void buildOnlyBackwardDiagonal(Cell cell) {
        cells = cell.getAdjacentCells();
        buildBackwardDiagonal();
    }

    private static void buildForwardDiagonal() {
        buildExit("northeastCell", "southwestCell", Direction.SOUTHWEST);
        buildExit("southwestCell", "northeastCell", Direction.NORTHEAST);
    }

    private static void buildBackwardDiagonal() {
        buildExit("northwestCell", "southeastCell", Direction.SOUTHEAST);
        buildExit("southeastCell", "northwestCell", Direction.NORTHWEST);
    }

    private static void buildExit(String origin, String destination, Direction direction) {
        Room originRoom = (Room)cells.get(origin).getEntity();
        List<Exit> exits = originRoom.getExits();
        for (Exit exit : exits) {
            if (exit.getDirection() == direction) {
                return;
            }
        }
        Room destinationRoom = (Room)cells.get(destination).getEntity();
        originRoom.addExit(new Exit(direction, destinationRoom, ExitType.PATH));
    }

}
