package main;

import gui.Gui;
import java.io.IOException;

/**
 * The Jdig class is the main class in the Jdig application.
 */
public class Jdig {
        
    /**
     * Create a new Jdig object with the specified arguments.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //TextMap map = new TextMap(args[0]);
        //map.getRooms();   
        //map.addAllExits();
        Gui gui = new Gui();
        gui.run();
    }
   
}