package main;

import java.io.IOException;

public class Jdig {
        
    /**
     * Configured to pass testmap.txt as a parameter for testing purposes.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        TextMap map = new TextMap(args[0]);
        map.getRooms();   
    }
   
}