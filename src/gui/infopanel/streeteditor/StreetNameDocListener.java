package gui.infopanel.streeteditor;

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Created by Administrator on 28/08/2014.
 */
public class StreetNameDocListener implements DocumentListener {

    private StreetEditor editor;

    public StreetNameDocListener(StreetEditor editor) {
        this.editor = editor;
    }

    /**
     * Enable to the AddButton in the StreetEditor if anything is entered into the
     * StreetName field.
     * @param event The event originating the call.
     */
    @Override
    public void insertUpdate(DocumentEvent event) {
        enableAddButton();
    }

    /**
     * Disable the AddButton if all text is removed from the StreetName field.
     * @param event The event originating the call.
     */
    @Override
    public void removeUpdate(DocumentEvent event) {
        JButton addButton = editor.getAddButton();
        if (streetFieldIsEmpty()) {
            addButton.setEnabled(false);
        }
    }

    /**
     * If the Document changes, check to see if the StreetName field is empty. If
     * it is, disable the AddButton, otherwise make sure it's still enabled.
     * @param event The event originating the call.
     */
    @Override
    public void changedUpdate(DocumentEvent event) {
        JButton addButton = editor.getAddButton();
        if (streetFieldIsEmpty()) {
            addButton.setEnabled(false);
        } else {
            enableAddButton();
        }
    }

    /**
     * Determines whether or not the StreetName field is empty.
     * @return true if empty, otherwise false.
     */
    private boolean streetFieldIsEmpty() {
        return editor.getStreetNameField().getText().length() <= 0;
    }

    /**
     * Enable the AddButton if it is not enabled already.
     */
    private void enableAddButton() {
        JButton addButton = editor.getAddButton();
        if (!addButton.isEnabled()) {
            addButton.setEnabled(true);
        }
    }
}
