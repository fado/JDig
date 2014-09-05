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
import gui.levelpanel.CellPanel;
import gui.levelpanel.LevelPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import properties.Images;
import utils.TestingUtils;

import java.awt.Point;
import java.awt.event.MouseEvent;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(PowerMockRunner.class)
public class ConnectionToolTest {

    private Cell emptyCell, emptyMiddleCell;
    private CellPanel emptyCellPanel, emptyMiddleCellPanel;
    private ConnectionTool connectionTool, connectionTool2;
    private MouseEvent eventEntered, eventEnteredMiddle, eventExited, eventLeftPressed,
        eventLeftPressedEntity, eventRightPressed;
    private TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        // Empty Cell and CellPanel.
        Level testLevel = new Level();
        testingUtils.populateLevel(1, 1, testLevel);
        Point testPoint = new Point(0, 0);
        //emptyCell = new Cell(testPoint, testLevel);
        emptyCell = testLevel.getCellAt(testPoint);
        BindingService bindingService = new BindingService();
        bindingService = testingUtils.setupBindingService(bindingService, testLevel);
        //emptyCellPanel = new CellPanel(emptyCell);
        emptyCellPanel = bindingService.getBoundCellPanel(emptyCell);

        // MouseEvent - Enter emptyCellPanel.
        eventEntered = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // 1x3 Level with Rooms to North and South.
        Level level = new Level();
        testingUtils.populateLevel(1, 3, level);
        BindingService bindingService2 = new BindingService();
        bindingService2 = testingUtils.setupBindingService(bindingService2, level);

        // North Cell.
        Cell northCell = level.getCellAt(new Point(0, 0));
        //CellPanel northCellPanel = northCell.getCellPanel();
        CellPanel northCellPanel = bindingService2.getBoundCellPanel(northCell);
        northCell.setEntity(new Room(northCellPanel));
        // Middle Cell.  Remember you have to set up the CellPanel manually as it
        // only gets created when the GUI is launched.
        emptyMiddleCell = level.getCellAt(new Point(0, 1));
        //emptyMiddleCellPanel = new CellPanel(emptyMiddleCell);
        emptyMiddleCellPanel = bindingService2.getBoundCellPanel(emptyMiddleCell);
        // South Cell.
        Cell southCell = level.getCellAt(new Point(0, 2));
        //CellPanel southCellPanel = southCell.getCellPanel();
        CellPanel southCellPanel = bindingService2.getBoundCellPanel(southCell);
        southCell.setEntity(new Room(southCellPanel));

        // ConnectionTool.
        DeletionTool deletionTool = new DeletionTool(level);
        ExitBuilder exitBuilder = new ExitBuilder();
        connectionTool = new ConnectionTool(deletionTool, exitBuilder, bindingService);
        // ConnectionTool2.
        connectionTool2 = new ConnectionTool(deletionTool, exitBuilder, bindingService2);

        // MouseEvent - Enter emptyMiddleCellPanel.
        eventEnteredMiddle = new MouseEvent(emptyMiddleCellPanel,
                MouseEvent.MOUSE_ENTERED, 0, 0, 0, 0, 1, false);

        // MouseEvent - Exit emptyCellPanel.
        eventExited = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_EXITED,
                0, 0, 0, 0, 1, false);

        // MouseEvent - Left mouse button pressed.
        eventLeftPressed = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);

        // MouseEvent - Left mouse button pressed with potential Connection.
        eventLeftPressedEntity = new MouseEvent(emptyMiddleCellPanel,
                MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);

        // MouseEvent - Right mouse button pressed.
        eventRightPressed = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON3);
    }

    /**
     * Mouse entering empty Cell with ConnectionType.NONE.
     */
    @Test
    public void testMouseEntered() {
        connectionTool.mouseEntered(eventEntered);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    /**
     * Mouse entering Cell with Entity and ConnectionType.NONE.
     */
    @Test
    public void testMouseEnteredWithEntity() {
        emptyCell.setEntity(new Room(emptyCellPanel));
        connectionTool.mouseEntered(eventEntered);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    /**
     * Mouse entering empty Cell and valid ConnectionType.
     */
    @Test
    public void testMouseEnteredWithNoEntityAndConnection() {
        connectionTool2.mouseEntered(eventEnteredMiddle);
        assertTrue(emptyMiddleCellPanel.hasEntityImage());
    }

    /**
     * Mouse exiting Cell with no Entity.
     */
    @Test
    public void testMouseExitedNoEntity() {
        connectionTool.mouseExited(eventExited);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    /**
     * Mouse exiting Cell with an Entity.
     */
    @Test
    public void testMouseExitedWithEntity() {
        emptyCell.setEntity(new Room(emptyCellPanel));
        emptyCellPanel.addImage(new Images().getImagePath("Room"));
        connectionTool.mouseExited(eventExited);
        assertTrue(emptyCellPanel.hasEntityImage());
    }

    /**
     * Test left mouse button pressed with no potential connection.
     */
    @Test
    public void testLeftMousePressed() {
        connectionTool.mousePressed(eventLeftPressed);
        assertTrue(emptyCell.getEntity() == null);
    }

    /**
     * Test left mouse pressed with potential connection.
     */
    @Test
    public void testLeftMousePressedWithPotentialConnection() {
        connectionTool2.mousePressed(eventLeftPressedEntity);
        assertFalse(emptyMiddleCell.getEntity() == null);
    }

    /**
     * Test left mouse pressed with potential connection but connection already placed
     * in the Cell.
     */
    @Test
    public void testLeftMousePressedWithPotentialConnectionAndExit() {
        emptyMiddleCell.setEntity(new Connection(ConnectionType.HORIZONTAL));
        connectionTool2.mousePressed(eventLeftPressedEntity);
        Connection actual = (Connection)emptyMiddleCell.getEntity();
        assertFalse(actual.getConnectionType() == ConnectionType.VERTICAL);
    }

    /**
     * Test right mouse pressed.
     */
    @Test
    public void testRightMousePressed() {
        connectionTool.mousePressed(eventRightPressed);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

}
