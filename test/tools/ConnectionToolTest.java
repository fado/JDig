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
import data.Level;
import data.Room;
import gui.CellPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import javax.swing.border.Border;
import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class ConnectionToolTest {

    private Cell emptyCell, emptyMiddleCell;
    private CellPanel emptyCellPanel, emptyCellPanelSpy, emptyMiddleCellPanelSpy;
    private ConnectionTool connectionTool;
    private MouseEvent eventEntered, eventEnteredMiddle;

    @Before
    public void setUp() {
        // Empty Cell and CellPanel.
        Level testLevel = new Level(1, 1);
        Point testPoint = new Point(0, 0);
        emptyCell = new Cell(testPoint, testLevel);
        emptyCellPanel = new CellPanel(emptyCell);
        emptyCellPanelSpy = Mockito.spy(emptyCellPanel);

        // ConnectionTool.
        CellTool cellTool = new CellTool();
        DeletionTool deletionTool = new DeletionTool();
        connectionTool = new ConnectionTool(cellTool, deletionTool);

        // MouseEvent - Enter emptyCellPanel.
        eventEntered = new MouseEvent(emptyCellPanelSpy, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // 1x3 Level with Rooms to North and South.
        Level level = new Level(1, 3);
        // North Cell.
        Cell northCell = level.getCellAt(new Point(0, 0));
        northCell.setEntity(new Room(northCell.getCellPanel()));
        // Middle Cell.  Remember you have to set up the CellPanel manually as it
        // only gets created when the GUI is launched.
        emptyMiddleCell = level.getCellAt(new Point(0, 1));
        CellPanel emptyMiddleCellPanel = new CellPanel(emptyMiddleCell);
        emptyMiddleCellPanelSpy = Mockito.spy(emptyMiddleCellPanel);
        emptyMiddleCell.setCellPanel(emptyMiddleCellPanelSpy);
        // South Cell.
        Cell southCell = level.getCellAt(new Point(0, 2));
        southCell.setEntity(new Room(southCell.getCellPanel()));

        // MouseEvent - Enter emptyMiddleCellPanel.
        eventEnteredMiddle = new MouseEvent(emptyMiddleCellPanelSpy,
                MouseEvent.MOUSE_ENTERED, 0, 0, 0, 0, 1, false);
    }

    /**
     * Mouse entering empty Cell with ConnectionType.NONE.
     */
    @Test
    public void testMouseEntered() {
        connectionTool.mouseEntered(emptyCell, eventEntered);
        verify(emptyCellPanelSpy, never()).setBorder(any(Border.class));
    }

    /**
     * Mouse entering Cell with Entity and ConnectionType.NONE.
     */
    @Test
    public void testMouseEnteredWithEntity() {
        emptyCell.setEntity(new Room(emptyCellPanel));
        connectionTool.mouseEntered(emptyCell, eventEntered);
        verify(emptyCellPanelSpy, never()).setBorder(any(Border.class));
    }

    /**
     * Mouse entering Cell with Entity and valid ConnectionType.
     */
    @Test
    public void testMouseEnteredWithEntityAndConnection() {
        connectionTool.mouseEntered(emptyMiddleCell, eventEnteredMiddle);
        verify(emptyMiddleCellPanelSpy).setBorder(any(Border.class));
    }

}
