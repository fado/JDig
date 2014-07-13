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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    
    private final List<CellModel> allCells;
    private final CellModel defaultCell;
    
    public LevelModel(int maxColumns, int maxRows) {
        this.defaultCell = new CellModel(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
        
        for (int rows = 0; rows < maxColumns; rows++) {
            for (int columns = 0; columns < maxRows; columns++) {
                CellModel cell = new CellModel(new Point(columns, rows), this);
                this.addCell(cell);
            }
        }
        
    }
    
    /**
     * Adds a Cell to the Map.
     * 
     * @param cell - The Cell to be added.
     */
    private void addCell(CellModel cell) {
        allCells.add(cell);
    }
    
    /**
     * Gets the Cell at the specified Point.
     * 
     * @param point - The Point at which the cell lies.
     * @return - The Cell at the specified point.
     */
    public CellModel getCellAt(Point point) {
        for (CellModel cell : allCells) {
            if (cell.X == point.x && cell.Y == point.y) {
                return cell;
            }
        }
        return defaultCell;
    }
    
    /**
     * Returns a List of all Cells in the Map.
     * 
     * @return - A List containing all Cells in the Map.
     */
    public List<CellModel> getAllCells() {
        return this.allCells;
    }
    
    /**
     * Returns the Cell adjacent to the passed-in Cell that lies
     * in the passed-in Direction.
     * 
     * @param referenceCell - The point of reference.
     * @param direction - The direction in which we are looking.
     * @return - The Cell found.
     */
    public CellModel getCellAdjacentTo(CellModel referenceCell, Direction direction) {
        for (CellModel cell : allCells) {
            if(cell.X == direction.translateX(referenceCell) && 
                    cell.Y == direction.translateY(referenceCell)) {
                return cell;
            }
        }
        return defaultCell;
    }

}