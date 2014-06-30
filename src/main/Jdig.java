package main;

import gui.MainUI;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Jdig {
    
    private static Properties properties;
    private static final String CONFIG_PROPERTIES = "./config/config.properties";
    
    public static void main(String[] args) throws IOException {
        if (properties == null) {
            properties = new Properties();
        }
        try {
            properties.load(new FileInputStream(CONFIG_PROPERTIES));
        } catch (IOException ex) {
            // TO-DO: Something.
        }
        
        MainUI gui = new MainUI(properties);
        gui.run();
    }
   
}