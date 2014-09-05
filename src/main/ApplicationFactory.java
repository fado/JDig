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
import data.Level;
import data.Room;
import gui.JdigComponent;
import gui.infopanel.InfoPanel;
import gui.infotoolbar.InfoToolbar;
import gui.levelpanel.CellPanel;
import gui.levelpanel.LevelPanel;
import gui.leveltoolbar.LevelToolbar;
import gui.menubar.DefaultLoadDialog;
import gui.menubar.DeleteCommand;
import gui.menubar.ExitCommand;
import gui.menubar.LoadCommand;
import gui.menubar.SaveCommand;
import persistence.LevelLoader;
import persistence.LevelSaver;
import properties.Images;
import properties.JdigProperties;
import tools.*;
import tools.ExitBuilder;
import javax.swing.JFileChooser;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.util.Properties;

/**
 * Builds the application, ensuring that the objects involved are instantiated
 * in the correct order.  Virtually all object creation has been moved into
 * this builder, consolidating it in one place.
 */
public enum ApplicationFactory {
    INSTANCE;

    Level level;
    InfoPanel infoPanel;
    InfoToolbar infoToolbar;
    LevelToolbar levelToolbar;
    LevelPanel levelPanel;
    SelectionTool selectionTool;
    RoomTool roomTool;
    ConnectionTool connectionTool;
    DeletionTool deletionTool;
    PlacementRestriction placementRestriction;
    ExitBuilder exitBuilder;
    DefaultLoadDialog defaultLoadDialog;
    JFileChooser jFileChooser;
    LevelSaver levelSaver;
    BindingService bindingService;

    /**
     * Builds the application, ensuring objects are instantiated in the correct order.
     * @param level The currently loaded level.
     */
    public void buildApplication(Level level) {
        bindingService = new BindingService();
        if(level.getAllCells().isEmpty()) {
            // If it's a new level, populate it.
            Properties properties = new JdigProperties();
            int defaultX = Integer.parseInt(properties.getProperty("DefaultX"));
            int defaultY = Integer.parseInt(properties.getProperty("DefaultY"));
            this.level = populateLevel(defaultX, defaultY, level);
        } else {
            // If it's a saved level, register the cells with the BindingService.
            for(Cell cell : level.getAllCells()) {
                bindingService.bindToCellPanel(cell);
            }
            this.level = level;
        }
        infoPanel = new InfoPanel(level);
        selectionTool = new SelectionTool(infoPanel);
        infoToolbar = new InfoToolbar(level);
        selectionTool = new SelectionTool(infoPanel);
        deletionTool = new DeletionTool(level);
        placementRestriction = new PlacementRestriction();
        roomTool = new RoomTool(level, deletionTool, placementRestriction);
        exitBuilder = new ExitBuilder();
        connectionTool = new ConnectionTool(deletionTool, exitBuilder);
        levelToolbar = new LevelToolbar(selectionTool, roomTool, connectionTool);
        levelPanel = buildLevelPanel(level);
        defaultLoadDialog = new DefaultLoadDialog();
        jFileChooser = new JFileChooser();
        levelSaver = new LevelSaver();
    }

    /**
     * Populates the Level with Cells and register them with the BindingService.
     * @param maxColumns Maximum number of columns within the Level.
     * @param maxRows Maximum number of Rows within the Level.
     */
    private Level populateLevel(int maxColumns, int maxRows, Level level) {
        for (int rows = 0; rows < maxRows; rows++) {
            for (int columns = 0; columns < maxColumns; columns++) {
                Cell cell = new Cell(new Point(columns, rows), level);
                level.addCell(cell);
                bindingService.bindToCellPanel(cell);
            }
        }
        return level;
    }

    /**
     * Creates a new LevelPanel.  Adds a CellPanel to it for each Cell
     * in the passed-in Level.  If the Cell already contains an Entity
     * (i.e. it's being loaded from a save file), then the appearance
     * of the CellPanel will be restored to what it was when the Level
     * was saved.
     * @param level The level the LevelPanel is being built from.
     * @return the LevelPanel populated with CellPanels.
     */
    private LevelPanel buildLevelPanel(Level level) {
        LevelPanel levelPanel = new LevelPanel();
        GridBagConstraints constraints = new GridBagConstraints();

        for(Cell cell : level.getAllCells()) {
            constraints.gridx = cell.X;
            constraints.gridy = cell.Y;
            CellPanel cellPanel = bindingService.getBoundCellPanel(cell);
            cell.setCellPanel(cellPanel);
            levelPanel.add(cellPanel, constraints);
            levelToolbar.addToolListener(cellPanel);
        }
        return levelPanel;
    }

    /**
     * Returns the currently loaded level.
     * @return the currently loaded level.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Returns the primary components of the user interface.
     * @return the primary components of the user interface.
     */
    public JdigComponent[] getGuiComponents() {
        levelToolbar.setSelectedLevelTool(selectionTool);
        return new JdigComponent[]{ infoPanel, infoToolbar, levelPanel, levelToolbar };
    }

    /**
     * Returns a new instance of ExitCommand.
     * @return a new instance of ExitCommand.
     */
    public ExitCommand getNewExitCommand() {
        return new ExitCommand(level, defaultLoadDialog, getNewSaveCommand());
    }

    /**
     * Returns a new instance of SaveCommand.
     * @return a new instance of SaveCommand.
     */
    public SaveCommand getNewSaveCommand() {
        return new SaveCommand(level, jFileChooser, levelSaver);
    }

    /**
     * Returns a new instance of LoadCommand.
     * @return a new instance of LoadCommand.
     */
    public LoadCommand getNewLoadCommand() {
        return new LoadCommand(jFileChooser, new LevelLoader());
    }

    /**
     * Returns a new instance of LoadCommand.
     * @return a new instance of LoadCommand.
     */
    public DeleteCommand getNewDeleteCommand() {
        return new DeleteCommand(selectionTool);
    }

    /**
     * Returns a new instance of DeletionTool.
     * @return a new instance of DeletionTool.
     */
    public DeletionTool getDeletionTool() {
        return deletionTool;
    }

}
