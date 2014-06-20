package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
     * Creates a MapEditorToolbar object.
     */
    public MapEditorToolbar() {
        // Load images.
        ImageIcon roomToolIcon = new ImageIcon(ROOM_TOOL_ICON);
        ImageIcon exitToolIcon = new ImageIcon(EXIT_TOOL_ICON);
        // Create buttons and assign images.
        JButton roomToolButton = new JButton(roomToolIcon);
        JButton exitToolButton = new JButton(exitToolIcon);
        // Add buttons to the tool bar.
        this.add(roomToolButton);
        this.add(exitToolButton);
        // Setup behaviours.
        roomToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Testing room tool action listener.");
            }
        });
    }
}