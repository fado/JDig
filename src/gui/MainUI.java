package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.Map;

/**
 * The Gui class specifies the user interface for JDig.
 */
public class MainUI implements Runnable {

    private final Map map;

    
    public MainUI(Map map) {
        this.map = map;
    }

    /**
     * Adds components to the passed-in Container.
     *
     * @param pane - The Container to which the components are to be added.
     */
    private void addComponentsToPane(Container pane) throws IOException {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;

        MapPanel mapPanel = new MapPanel(this.map);
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(mapPanel, constraints);

        MapToolbarUI toolbar = new MapToolbarUI(mapPanel);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(toolbar, constraints);
    }

    /**
     * Creates a Gui object.
     */
    private void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("JDig");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Sets the look and feel to the system default, then creates a Gui object.
     */
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            createAndShowGui();
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException |
                IOException ex) {
            // TO-DO: Stuff.
        }
    }

}
