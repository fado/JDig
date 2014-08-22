package tools;

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

import data.Cell;
import data.ConnectionType;
import data.Direction;
import data.Exit;
import data.ExitType;
import data.Room;
import java.util.List;
import java.util.Map;

/**
 * Builds exits/adds exits to Rooms.
 */
public class ExitBuilder {

    private static Map<String, Cell> cells;

    /**
     * Build exits for the passed-in cell.  Looks at the potential connection
     * type of the passed-in cell then builds exits appropriately, depending
     * on the direction.
     * @param cell The cell around which exits should be built.
     */
    public void build(Cell cell) {
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

    /**
     * Public method allowing the building of exits only along the forward diagonal
     * axis (southwest/northeast).
     * @param cell
     */
    public void buildOnlyForwardDiagonal(Cell cell) {
        cells = cell.getAdjacentCells();
        buildForwardDiagonal();
    }

    /**
     * Public method allowing the building of exits only along the backward diagonal
     * axis (southeast/northwest).
     * @param cell
     */
    public void buildOnlyBackwardDiagonal(Cell cell) {
        cells = cell.getAdjacentCells();
        buildBackwardDiagonal();
    }

    /**
     * Builds a forward diagonal exit (southwest/northeast).
     */
    private void buildForwardDiagonal() {
        buildExit("northeastCell", "southwestCell", Direction.SOUTHWEST);
        buildExit("southwestCell", "northeastCell", Direction.NORTHEAST);
    }

    /**
     * Builds a backward diagonal exist (southeast/northwest).
     */
    private void buildBackwardDiagonal() {
        buildExit("northwestCell", "southeastCell", Direction.SOUTHEAST);
        buildExit("southeastCell", "northwestCell", Direction.NORTHWEST);
    }

    /**
     * Builds an exit in the origin room.
     * @param origin The name of the origin cell.
     * @param destination The name of the destination cell.
     * @param direction The direction in which the exit should travel.
     */
    private void buildExit(String origin, String destination, Direction direction) {
        // Get the origin room from the list of adjacent cells.
        Room originRoom = (Room)cells.get(origin).getEntity();
        List<Exit> exits = originRoom.getExits();
        // Check to see if an exit already exists going in the passed-in direction.
        for (Exit exit : exits) {
            if (exit.getDirection() == direction) {
                return;
            }
        }
        // Get the destination room.
        Room destinationRoom = (Room)cells.get(destination).getEntity();
        // Add the exit to the origin room.
        originRoom.addExit(new Exit(direction, destinationRoom, ExitType.PATH));
    }

}
