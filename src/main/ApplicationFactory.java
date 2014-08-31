package main;

import data.Level;
import gui.JdigComponent;
import gui.infopanel.InfoPanel;
import gui.infotoolbar.InfoToolbar;
import gui.levelpanel.LevelPanel;
import gui.leveltoolbar.LevelToolbar;
import gui.menubar.DefaultLoadDialog;
import gui.menubar.ExitCommand;
import gui.menubar.MenuBar;
import gui.menubar.SaveCommand;
import persistence.LevelSaver;
import tools.*;
import tools.ExitBuilder;
import javax.swing.JFileChooser;

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
    ExitCommand exitCommand;
    DefaultLoadDialog defaultLoadDialog;
    SaveCommand saveCommand;
    JFileChooser jFileChooser;
    LevelSaver levelSaver;

    public void buildApplication(Level level) {
        this.level = level;
        infoPanel = new InfoPanel(level);
        selectionTool = new SelectionTool(infoPanel);
        infoToolbar = new InfoToolbar(level);
        selectionTool = new SelectionTool(infoPanel);
        deletionTool = new DeletionTool();
        placementRestriction = new PlacementRestriction();
        roomTool = new RoomTool(level, deletionTool, placementRestriction);
        exitBuilder = new ExitBuilder();
        connectionTool = new ConnectionTool(deletionTool, exitBuilder);
        levelToolbar = new LevelToolbar(selectionTool, roomTool, connectionTool);
        levelPanel = new LevelPanel(level, levelToolbar);
        defaultLoadDialog = new DefaultLoadDialog();
        jFileChooser = new JFileChooser();
        levelSaver = new LevelSaver();
        saveCommand = new SaveCommand(level, jFileChooser, levelSaver);
        exitCommand = new ExitCommand(level, defaultLoadDialog, saveCommand);
    }

    public JdigComponent[] getGuiComponents() {
        levelToolbar.setSelectedLevelTool(selectionTool);
        return new JdigComponent[]{ infoPanel, infoToolbar, levelPanel, levelToolbar };
    }

    public MenuBar getNewMenuBar() {
        return new MenuBar(level, selectionTool);
    }

    public ExitCommand getNewExitCommand() {
        return new ExitCommand(level, defaultLoadDialog, getNewSaveCommand());
    }

    public SaveCommand getNewSaveCommand() {
        return new SaveCommand(level, jFileChooser, levelSaver);
    }
}