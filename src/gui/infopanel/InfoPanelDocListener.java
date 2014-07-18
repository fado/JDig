package gui.infopanel;

import gui.infopanel.commands.SetterCommand;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class InfoPanelDocListener implements DocumentListener {

    private final InfoPanel infoPanel;
    private final SetterCommand command;
    
    public InfoPanelDocListener(InfoPanel infoPanel, SetterCommand command) {
        this.infoPanel = infoPanel;
        this.command = command;
    }
    
    @Override
    public void insertUpdate(DocumentEvent event) {
        update(event);
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        update(event);
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        update(event);
    }
    
    private void update(DocumentEvent event) {
        try {
            String update = event.getDocument().getText(0, event.getDocument().getLength());
            if(infoPanel.getCurrentRoom() != null) {
                command.set(infoPanel.getCurrentRoom(), update);
            }
        } catch (BadLocationException ex) {
            
        }
    }
    
}