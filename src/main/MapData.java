package main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class takes a text file containing an ASCII map as a parameter and stores the data contained
 * therein as a List<String>.
 */
public class MapData {
    
    private final String FILE_NOT_FOUND_MSG = "File not found, or specified path is a directory.";
    private final String CHARSET = "UTF-8";
    private List<String> linesInSourceFile = new ArrayList<>();
    
    /**
     * Constructor. Checks to see if the file exists before extracting the data from it.
     * @param path - The path of the text file containing the ASCII map.
     * @throws IOException 
     */
    public MapData(String path) throws IOException {
        File mapSourceFile = new File(path);
        if(mapSourceFile.exists() && !mapSourceFile.isDirectory()) {
            linesInSourceFile = read(path);
            System.out.println(linesInSourceFile.size() +" lines added to Map.");
        } else {
            System.out.println(FILE_NOT_FOUND_MSG);
        }
    }
    
    /**
     * Reads all lines in the text file and returns them as a List<String>.
     * @param path - The path of the text file containing the ASCII map.
     * @return - An ArrayList<String> of all lines in the text file.
     * @throws IOException 
     */
    public List<String> read(String path) throws IOException {
        return Files.readAllLines(Paths.get(path), Charset.forName(CHARSET));
    }
 
    /**
     * 
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