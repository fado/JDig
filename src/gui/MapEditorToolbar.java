package gui;

import gui.tools.RoomTool;
import gui.tools.ExitTool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * The MapEditorToolbar class specifies the tool bar for the map editor.
 * @author Administrator
 */
public class MapEditorToolbar extends JToolBar {
   
    /**
     * Resource paths.
     */
    private final String IMAGES = "./resources/images/";
    private final String ROOM_TOOL_ICON = IMAGES+ "room_tool.png";
    private final String EXIT_TOOL_ICON = IMAGES+ "exit_tool.png";
    /**
     * Instance variables.
     */
    private final List<JButton> buttons;
    private final MapGrid mapGrid;
    
    /**
     * Creates a MapEditorToolbar object.
     * @param grid - The MapGrid to which the tool bar is attached.
     */
    public MapEditorToolbar(MapGrid grid) {
        // Make its position immutable.
        this.setFloatable(false);

        // Record the associated MapGrid.
        this.mapGrid = grid;
        
        // Load images.
        ImageIcon roomToolIcon = new ImageIcon(ROOM_TOOL_ICON);
        ImageIcon exitToolIcon = new ImageIcon(EXIT_TOOL_ICON);
        
        // Create buttons and assign images.
        final JButton roomToolButton = new JButton(roomToolIcon);
        final JButton exitToolButton = new JButton(exitToolIcon);
        
        // Add buttons to the tool bar.
        this.add(roomToolButton);
        this.add(exitToolButton);
        
        // Initialize the list...
        buttons = new ArrayList<>();
        // ...and add buttons to it.
        buttons.add(roomToolButton);
        buttons.add(exitToolButton);
        
        // Setup behaviour for roomToolButton.
        roomToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!roomToolButton.isSelected()) {
                    mapGrid.setSelectedTool(new RoomTool());
                }
            }
        });
        
        // Setup behaviour for exitToolButton.
        exitToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(!exitToolButton.isSelected()) {
                    mapGrid.setSelectedTool(new ExitTool());
                }
            }
        });
    }
    
}