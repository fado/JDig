package gui.menubar;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class XmlFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        if(file.isDirectory()) {
            return true;
        } else {
            return file.getName().toLowerCase().endsWith(".xml");
        }
    }

    @Override
    public String getDescription() {
        return "Extensible Markup Language (*.XML)";
    }

}
