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

    private Cell emptyCell, emptyMiddleCell, emptyCellSpy, emptyMiddleCellSpy;
    private CellPanel emptyCellPanel, emptyCellPanelSpy, emptyMiddleCellPanelSpy;
    private ConnectionTool connectionTool;
    private MouseEvent eventEntered, eventEnteredMiddle, eventExited, eventLeftPressed,
        eventLeftPressedEntity, eventRightPressed;
    private ExitBuilder exitBuilderSpy;
    private DeletionTool deletionToolSpy;

    @Before
    public void setUp() {
        // Empty Cell and CellPanel.
        Level testLevel = new Level(1, 1);
        Point testPoint = new Point(0, 0);
        emptyCell = new Cell(testPoint, testLevel);
        emptyCellSpy = Mockito.spy(emptyCell);
        emptyCellPanel = new CellPanel(emptyCell);
        emptyCellPanelSpy = Mockito.spy(emptyCellPanel);

        // ConnectionTool.
        CellTool cellTool = new CellTool();
        DeletionTool deletionTool = new DeletionTool();
        ExitBuilder exitBuilder = new ExitBuilder();
        exitBuilderSpy = Mockito.spy(exitBuilder);
        deletionToolSpy = Mockito.spy(deletionTool);
        connectionTool = new ConnectionTool(cellTool, deletionToolSpy, exitBuilderSpy);

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
        emptyMiddleCellSpy = Mockito.spy(emptyMiddleCell);
        CellPanel emptyMiddleCellPanel = new CellPanel(emptyMiddleCell);
        emptyMiddleCellPanelSpy = Mockito.spy(emptyMiddleCellPanel);
        emptyMiddleCell.setCellPanel(emptyMiddleCellPanel);
        // South Cell.
        Cell southCell = level.getCellAt(new Point(0, 2));
        southCell.setEntity(new Room(southCell.getCellPanel()));

        // MouseEvent - Enter emptyMiddleCellPanel.
        eventEnteredMiddle = new MouseEvent(emptyMiddleCellPanelSpy,
                MouseEvent.MOUSE_ENTERED, 0, 0, 0, 0, 1, false);

        // MouseEvent - Exit emptyCellPanel.
        eventExited = new MouseEvent(emptyCellPanelSpy, MouseEvent.MOUSE_EXITED,
                0, 0, 0, 0, 1, false);

        // MouseEvent - Left mouse button pressed.
        eventLeftPressed = new MouseEvent(emptyCellPanelSpy, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);

        // MouseEvent - Left mouse button pressed with potential Connection.
        eventLeftPressedEntity = new MouseEvent(emptyMiddleCellPanelSpy,
                MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);

        // MouseEvent - Right mouse button pressed.
        eventRightPressed = new MouseEvent(emptyCellPanelSpy, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON3);
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
     * Mouse entering empty Cell and valid ConnectionType.
     */
    @Test
    public void testMouseEnteredWithNoEntityAndConnection() {
        connectionTool.mouseEntered(emptyMiddleCell, eventEnteredMiddle);
        verify(emptyMiddleCellPanelSpy).setBorder(any(Border.class));
    }

    /**
     * Mouse exiting Cell with no Entity.
     */
    @Test
    public void testMouseExitedNoEntity() {
        connectionTool.mouseExited(emptyCell, eventExited);
        verify(emptyCellPanelSpy).removeEntityImage();
    }

    /**
     * Mouse exiting Cell with an Entity.
     */
    @Test
    public void testMouseExitedWithEntity() {
        emptyCell.setEntity(new Room(emptyCellPanel));
        connectionTool.mouseExited(emptyCell, eventExited);
        verify(emptyCellPanelSpy, never()).removeEntityImage();
    }

    /**
     * Test left mouse button pressed with no potential connection.
     */
    @Test
    public void testLeftMousePressed() {
        connectionTool.mousePressed(emptyCell, eventLeftPressed);
        verify(emptyCellPanelSpy).getCell();
    }

    /**
     * Test left mouse pressed with potential connection.
     */
    @Test
    public void testLeftMousePressedWithPotentialConnection() {
        connectionTool.mousePressed(emptyMiddleCell, eventLeftPressedEntity);
        verify(exitBuilderSpy).build(any(Cell.class));
    }

    /**
     * Test left mouse pressed with potential connection but connection already placed
     * in the Cell.
     */
    @Test
    public void testLeftMousePressedWithPotentialConnectionAndExit() {
        emptyMiddleCell.setEntity(new Connection(ConnectionType.VERTICAL));
        connectionTool.mousePressed(emptyMiddleCell, eventLeftPressedEntity);
        verify(exitBuilderSpy, never()).build(any(Cell.class));
    }

    /**
     * Test right mouse pressed.
     */
    @Test
    public void testRightMousePressed() {
        connectionTool.mousePressed(emptyCell, eventRightPressed);
        verify(deletionToolSpy).deleteEntity(any(CellPanel.class));
    }

}
