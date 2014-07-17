package gui.info;

import gui.info.InfoPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class RoomNameListener implements DocumentListener {

    private final InfoPanel infoPanel;
    
    public RoomNameListener(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
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
                infoPanel.getCurrentRoom().setName(update);
            }
        } catch (BadLocationException ex) {
            
        }
    }
    
}