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
import data.Connection;
import data.ConnectionType;
import data.Level;
import data.Room;
import gui.CellPanel;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
public class CellToolTest {

    private Cell testCell;
    private CellPanel testCellPanel;

    @Before
    public void setUp() {
        Level testLevel = new Level(1, 1);
        Point testPoint = new Point(0, 0);
        testCell = new Cell(testPoint, testLevel);
        testCellPanel = new CellPanel(testCell);
    }

    /**
     * Tests that addImage is called when a CellPanel that is connectible
     * is set deselected.
     */
    @Test
    public void testSetDeselectedConnectible() {
        CellTool cellTool = new CellTool();
        CellTool cellToolSpy = Mockito.spy(cellTool);
        testCell.setEntity(new Room(testCellPanel));
        cellToolSpy.setDeselected(testCellPanel);
        verify(cellToolSpy).addImage(testCellPanel, testCell.getEntity()
                .getNormalImage());
    }

    /**
     * Tests that addImage does not get called when a CellPanel that is not
     * connectible is set deselected.
     */
    @Test
    public void testSetDeselectedNotConnectible() {
        CellTool cellTool = new CellTool();
        CellTool cellToolSpy = Mockito.spy(cellTool);
        testCell.setEntity(new Connection(ConnectionType.HORIZONTAL));
        cellToolSpy.setDeselected(testCellPanel);
        verify(cellToolSpy, never()).addImage(testCellPanel, testCell.getEntity()
                .getNormalImage());
    }

}
