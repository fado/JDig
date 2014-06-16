package main;

import java.awt.Point;
import java.io.IOException;
import java.util.List;

public class Jdig {
        
    /**
     * Configured to pass testmap.txt as a parameter for testing purposes.
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        TextMap map = new TextMap(args[0]);
        List<Point> coordinate = map.getCoordinatesOf('O');
    }
   
}