package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * The MapEditorToolbar class specifies the tool bar for the map editor.
 *
 * @author Administrator
 */
public class MapToolbarUI extends JToolBar {

    private final String IMAGES = "./resources/images/";
    private final String ROOM_TOOL_ICON = IMAGES + "room_tool.png";
    private final String EXIT_TOOL_ICON = IMAGES + "exit_tool.png";
    private final List<JButton> buttons;
    private final MapPanel mapGrid;

    /**
     * Creates a MapEditorToolbar object.
     *
     * @param grid - The MapGrid to which the tool bar is attached.
     */
    public MapToolbarUI(MapPanel grid) {
        this.mapGrid = grid;
        this.setFloatable(false);

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
                // TO-DO: Something.
            }
        });

        exitToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // TO-DO: Something.
            }
        });
    }

}
