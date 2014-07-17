package gui.streets;

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
import gui.InfoPanel;
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

public class StreetEditor implements Runnable {

    private DefaultListModel listModel;
    private JList list;
    private final String DELETE_STRING = "Delete";
    private final String ADD_STRING = "Add";
    private JButton deleteButton;
    private JButton addButton;
    private JTextField streetName;
    private final Level level;
    private final InfoPanel infoPanel;

    public StreetEditor(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        this.level = infoPanel.getLevel();
    }
    
    private void createAndShowGui() {
        JFrame frame = new JFrame("Street Editor");
        addComponentsToPane(frame.getContentPane());
        frame.setVisible(true);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                infoPanel.populateStreetNames();
            }
        });
    }
    
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            createAndShowGui();
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            //TO-DO: Stuff.
        }
        
    }
    
    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout());
        listModel = new DefaultListModel();

        for (Street street : level.getStreets()) {
            listModel.addElement(street.getName());
        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (event.getValueIsAdjusting() == false) {
                    if (list.getSelectedIndex() == -1) {
                        deleteButton.setEnabled(false);
                    } else {
                        deleteButton.setEnabled(true);
                    }
                }
            }
        });
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        addButton = new JButton(ADD_STRING);
        AddListener addListener = new AddListener(this, level);
        addButton.addActionListener(addListener);

        streetName = new JTextField(10);
        streetName.addActionListener(addListener);
        streetName.getDocument().addDocumentListener(addListener);

        deleteButton = new JButton(DELETE_STRING);
        deleteButton.setActionCommand(DELETE_STRING);
        deleteButton.addActionListener(new DeleteListener(this));
        
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(streetName);
        buttonPane.add(deleteButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        pane.add(listScrollPane, BorderLayout.CENTER);
        pane.add(buttonPane, BorderLayout.PAGE_END);
    }

    public JTextField getStreetNameField() {
        return this.streetName;
    }

    public JList getList() {
        return this.list;
    }

    public DefaultListModel getListModel() {
        return this.listModel;
    }

    public JButton getAddButton() {
        return this.addButton;
    }
    
    public JButton getDeleteButton() {
        return this.deleteButton;
    }
    
}
