package gui.menubar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class BugReportActionListener implements ActionListener {

    static final Logger logger = LoggerFactory.getLogger(BugReportActionListener.class);

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            JdigProperties jdigProperties = new JdigProperties();
            URL url;
            try {
                url = new URL(jdigProperties.getProperty("BugReportURL"));
                desktop.browse(url.toURI());
            } catch (URISyntaxException | IOException ex) {
                logger.error(ex.toString());
            }

        }
    }
}
