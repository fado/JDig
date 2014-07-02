package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import tools.DefaultPointer;
import tools.ExitTool;
import tools.RoomTool;
import tools.Tool;
import tools.ToolEvent;
import tools.ToolListener;

/**
 * The MapEditorToolbar class specifies the tool bar for the map editor.
 *
 * @author Administrator
 */
public class MapToolbar extends JToolBar {

    private final String IMAGES = "./resources/images/";
    private final String ROOM_TOOL_ICON = IMAGES + "room_tool.png";
    private final String EXIT_TOOL_ICON = IMAGES + "exit_tool.png";
    private final List<JButton> buttons;
    private final List<ToolListener> listeners;
    private Tool selectedTool;
    
    public MapToolbar() {
        this.setFloatable(false);
        this.listeners = new ArrayList<>();
        this.selectedTool = new DefaultPointer();

        ImageIcon roomToolIcon = new ImageIcon(ROOM_TOOL_ICON);
        ImageIcon exitToolIcon = new ImageIcon(EXIT_TOOL_ICON);

        final JButton roomToolButton = new JButton(roomToolIcon);
        final JButton exitToolButton = new JButton(exitToolIcon);

        this.add(roomToolButton);
        this.add(exitToolButton);

        buttons = new ArrayList<>();
        buttons.add(roomToolButton);
        buttons.add(exitToolButton);

        roomToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(new RoomTool());
            }
        });

        exitToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(new ExitTool());
            }
        });
    }

    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        fireToolChanged();
    }
    
    public void addToolListener(ToolListener obj) {
        listeners.add(obj);
    }

    protected void fireToolChanged() {
        ToolEvent toolEvent = new ToolEvent(this, getSelectedTool());
        for (ToolListener eachListener : listeners) {
            eachListener.toolChanged(toolEvent);
        }
    }

}
