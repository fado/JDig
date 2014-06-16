package main;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class takes a text file containing an area map and stores the data contained therein as a List.
 */
public class TextMap {
    
    private final String FILE_NOT_FOUND_MSG = "File not found, or specified path is a directory.";
    private final String EMPTY_MAP_MSG = "No lines found in Ascii Map.";
    private final String CHARSET = "UTF-8";
    private List<String> linesInSourceFile = new ArrayList<>();
    
    /**
     * Constructor. Checks to see if the file exists before extracting the data from it.
     * @param path - The path of the text file containing the map.
     * @throws IOException 
     */
    public TextMap(String path) throws IOException {
        File mapSourceFile = new File(path);
        if(mapSourceFile.exists() && !mapSourceFile.isDirectory()) {
            linesInSourceFile = read(path);
            System.out.println(linesInSourceFile.size() +" lines added to Map.");
        } else {
            System.out.println(FILE_NOT_FOUND_MSG);
        }
    }
    
    /**
     * Reads all lines in the text file and returns them as a List.
     * @param path - The path of the text file containing the map.
     * @return - An ArrayList<String> of all lines in the text file.
     * @throws IOException 
     */
    private List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), Charset.forName(CHARSET));
    }
 
    /**
     * Returns a List of all coordinates (as Points) matching the passed symbol.
     * @param symbol - The symbol to be matched.
     * @return - A List of Points matching the symbol.
     */
    public List<Point> getCoordinatesOf(char symbol) {
        List<Point> points = new ArrayList<>();
        // Check to see if there are any elements in the List.
        if(linesInSourceFile.size() <= 0) {
            System.out.println(EMPTY_MAP_MSG);
            return points;
        }
        // Iterate through elements in the Array List.
        for(int counter = 0; counter < linesInSourceFile.size(); counter++) {
            // Iterate through each character in the element.
            for(int innerCounter = 0; innerCounter < linesInSourceFile.get(counter).length(); innerCounter++) {
                if(linesInSourceFile.get(counter).charAt(innerCounter) == symbol) {
                    Point point = new Point(counter, innerCounter);
                    System.out.println("Coordinates found at "+String.valueOf(counter)+", "+String.valueOf(innerCounter));
                    points.add(point);
                }
            }
        }
        return points;
    }
    
    
    /**
     * Custom toString().
     * @return - A String containing all lines stores by the Map.
     */
    @Override
    public String toString() {
        String returnString = "";
        for (String line : linesInSourceFile) {
            returnString += line+"\n";
        }
        return returnString;
    }
    
}