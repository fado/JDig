package gui.infopanel.streeteditor;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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

import data.Level;
import data.Street;
import gui.infopanel.InfoPanel;
import properties.Localization;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows streets to be added/removed from a level.
 */
public class StreetEditor implements Runnable {

    private DefaultListModel<String> listModel;
    private JList<String> list;
    private Localization localization = new Localization();
    private final String DELETE_STRING = localization.get("DeleteString");
    private final String ADD_STRING = localization.get("AddString");
    private JButton deleteButton;
    private JButton addButton;
    private JTextField streetName;
    private final Level level;
    private final InfoPanel infoPanel;
    private Container contentPane;
    static final Logger logger = LoggerFactory.getLogger(StreetEditor.class);

    /**
     * Constructor.
     * @param infoPanel The InfoPanel which launched the editor.
     */
    public StreetEditor(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        this.level = infoPanel.getLevel();
    }

    /**
     * Runner.
     */
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            createAndShowGui();
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            logger.error(ex.toString());
        }
    }

    /**
     * Creates and shows the components which make up the UI.
     */
    private void createAndShowGui() {
        JFrame frame = new JFrame(localization.get("EditorWindowTitle"));
        this.contentPane = frame.getContentPane();
        addComponentsToPane(contentPane);
        frame.setVisible(true);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                // Repopulate the street names in the info panel when this closes.
                infoPanel.populateStreetNames();
            }
        });
    }

    /**
     * Creates and adds to the passed-in pane the components that actually make
     * up the StreetEditor.
     * @param pane The content pane to which the components should be added.
     */
    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout());

        // List of streets.
        JScrollPane listScrollPane = new JScrollPane(getStreetNameList());

        // Add the listScrollPane to the passed-in Container.
        pane.add(listScrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setName("buttonPane");
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        // Button to add streets to the list.
        addButton = new JButton(ADD_STRING);
        AddListener addListener = new AddListener(this, new DefaultEditorDialog());
        addButton.addActionListener(addListener);
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));


        // Field to input street names.
        streetName = new JTextField(10);
        streetName.addActionListener(addListener);
        streetName.getDocument().addDocumentListener(new StreetNameDocListener(this));
        buttonPane.add(streetName);

        // Button to remove streets from the list.
        deleteButton = new JButton(DELETE_STRING);
        deleteButton.setName("deleteButton");
        // Disable if there are no streets to delete.
        if(listModel.isEmpty()) {
            deleteButton.setEnabled(false);
        }
        deleteButton.setActionCommand(DELETE_STRING);
        deleteButton.addActionListener(new DeleteListener(this));
        buttonPane.add(deleteButton);

        // Add buttonPane to the passed-in Container.
        pane.add(buttonPane, BorderLayout.PAGE_END);
    }

    /**
     * Creates a list model containing all the street names within the level, then
     * creates a new JList using that list model.  Adds an action listener, then
     * returns the JList.
     * @return a JList of objects that make up the street name list.
     */
    private JList<String> getStreetNameList() {
        listModel = new DefaultListModel<>();
        for (Street street : level.getStreets()) {
            listModel.addElement(street.getName());
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Check changes aren't still being made.
                if (!event.getValueIsAdjusting()) {
                    // Check something is actually selected.
                    if (list.getSelectedIndex() == -1) {
                        deleteButton.setEnabled(false);
                    } else {
                        deleteButton.setEnabled(true);
                    }
                }
            }
        });
        list.setVisibleRowCount(5);
        return list;
    }

    /**
     * Returns the ContentPane of the top level JFrame.
     * @return the ContentPane of the top level JFrame.
     */
    public Container getContentPane() {
        return this.contentPane;
    }

    /**
     * Returns the JTextField for entering street names.
     * @return the JTextField for entering street names.
     */
    public JTextField getStreetNameField() {
        return this.streetName;
    }

    /**
     * Returns the JList of street names.
     * @return the JList of street names.
     */
    public JList getList() {
        return this.list;
    }

    /**
     * Returns the DefaultListModel that maintains the list of street names.
     * @return the DefaultListModel that maintains the list of street names.
     */
    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }

    /**
     * Returns the JButton that adds streets to the level.
     * @return the JButton that adds streets to the level.
     */
    public JButton getAddButton() {
        return this.addButton;
    }

    /**
     * Returns the JButton that deletes streets from the level.
     * @return the JButton that deletes streets from the level.
     */
    public JButton getDeleteButton() {
        return this.deleteButton;
    }

    /**
     * Returns the level to which streets will be added.
     * @return the level to which streets will be added.
     */
    public Level getLevel() {
        return this.level;
    }
    
}
