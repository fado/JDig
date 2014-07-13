package main;

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
import data.Exit;
import java.util.Map;

public class ExitBuilder {
    
    private ExitBuilder() {
        
    }
    
    public static void build(Cell cell) {
        ExitDirection exitDirection = cell.getPotentialExitDirection();
        Map <String, Cell> cells = cell.getAdjacentCells();
        
        if (exitDirection == ExitDirection.HORIZONTAL_EXIT) {
            cells.get("westCell").getRoom().addExit(new Exit(Direction.EAST, 
                    cell.getRoom(), ExitType.PATH));
            cells.get("eastCell").getRoom().addExit(new Exit(Direction.WEST, 
                    cell.getRoom(), ExitType.PATH));
        }
        if (exitDirection == ExitDirection.VERTICAL_EXIT) {
            cells.get("northCell").getRoom().addExit(new Exit(Direction.SOUTH, 
                    cell.getRoom(), ExitType.PATH));
            cells.get("southCell").getRoom().addExit(new Exit(Direction.NORTH, 
                    cell.getRoom(), ExitType.PATH));
        }
        if (exitDirection == ExitDirection.BACKWARD_DIAGONAL_EXIT) {
            buildBackwardDiagonal(cells, cell);
        }
        if (exitDirection == ExitDirection.FORWARD_DIAGONAL_EXIT) {
            buildForwardDiagonal(cells, cell);
        }
        if (exitDirection == ExitDirection.X_EXIT) {
            buildBackwardDiagonal(cells, cell);
            buildForwardDiagonal(cells, cell);
        }
    }
    
    private static void buildForwardDiagonal(Map <String, Cell> cells, Cell cell) {
        cells.get("northeastCell").getRoom().addExit(new Exit(Direction.SOUTHWEST, 
                cell.getRoom(), ExitType.PATH));
        cells.get("southwestCell").getRoom().addExit(new Exit(Direction.NORTHEAST, 
                cell.getRoom(), ExitType.PATH));
    }
    
    private static void buildBackwardDiagonal(Map<String, Cell> cells, Cell cell) {
        cells.get("northwestCell").getRoom().addExit(new Exit(Direction.SOUTHEAST, 
                cell.getRoom(), ExitType.PATH));
        cells.get("southeastCell").getRoom().addExit(new Exit(Direction.NORTHWEST, 
                cell.getRoom(), ExitType.PATH));
    }
}