package gui.infopanel.streeteditor;

import properties.Localization;

import javax.swing.JOptionPane;
import java.awt.HeadlessException;

/**
 * Created by Administrator on 28/08/2014.
 */
public class ErrorDialog implements MessageDialog {

    Localization localization = new Localization();

    /**
     * Generic method for showing an error message.
     * @param message The error message to be shown.
     * @throws java.awt.HeadlessException
     */
    @Override
    public void showDialog(String message, StreetEditor editor)
            throws HeadlessException {
        JOptionPane.showMessageDialog(null, message, localization.get("ErrorTitle"),
                JOptionPane.WARNING_MESSAGE);
        editor.getStreetNameField().requestFocusInWindow();
        editor.getStreetNameField().selectAll();
    }

}
