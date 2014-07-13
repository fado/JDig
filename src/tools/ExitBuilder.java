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

import data.ExitType;
import data.ExitDirection;
import data.Direction;
import data.Cell;
import data.Exit;
import java.util.Map;

public class ExitBuilder {
    
    private static Map <String, Cell> cells;
    
    private ExitBuilder() {
        
    }
    
    public static void build(Cell cell) {
        ExitDirection exitDirection = cell.getPotentialExitDirection();
        cells = cell.getAdjacentCells();
        
        if (exitDirection == ExitDirection.HORIZONTAL_EXIT) {
            buildExit("westCell", Direction.EAST, cell);
            buildExit("eastCell", Direction.WEST, cell);
        }
        if (exitDirection == ExitDirection.VERTICAL_EXIT) {
            buildExit("northCell", Direction.SOUTH, cell);
            buildExit("southCell", Direction.NORTH, cell);
        }
        if (exitDirection == ExitDirection.BACKWARD_DIAGONAL_EXIT) {
            buildBackwardDiagonal(cell);
        }
        if (exitDirection == ExitDirection.FORWARD_DIAGONAL_EXIT) {
            buildForwardDiagonal(cell);
        }
        if (exitDirection == ExitDirection.X_EXIT) {
            buildBackwardDiagonal(cell);
            buildForwardDiagonal(cell);
        }
    }
    
    private static void buildForwardDiagonal(Cell cell) {
        buildExit("northeastCell", Direction.SOUTHWEST, cell);
        buildExit("southwestCell", Direction.NORTHEAST, cell);
    }
    
    private static void buildBackwardDiagonal(Cell cell) {
        buildExit("northwestCell", Direction.SOUTHEAST, cell);
        buildExit("southeastCell", Direction.NORTHWEST, cell);
    }
    
    private static void buildExit(String exit, Direction direction, Cell cell) {
        cells.get(exit).getRoom().addExit(new Exit(direction, cell.getRoom(), ExitType.PATH));
    }
}