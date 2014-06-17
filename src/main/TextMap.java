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
    
    /**
     * Error messages.
     */
    private final String FILE_NOT_FOUND_MSG = "File not found, or specified path is a directory.";
    private final String EMPTY_MAP_MSG = "No lines found in Ascii Map.";
    /**
     * Map symbols.
     */
    private final char ROOM_SYMBOL = 'O';
    private final char HORIZONTAL_EXIT_SYMBOL = '-';
    private final char VERTICAL_EXIT_SYMBOL = '|';
    private final char BACK_DIAGONAL_EXIT_SYMBOL = '\\';
    private final char FORWARD_DIAGONAL_EXIT_SYMBOL = '/';
    private final char MULTI_DIAGONAL_EXIT_SYMBOL = 'X';
    /**
     * Class variables.
     */
    private List<String> linesInSourceFile = new ArrayList<>();
    private final List<Room> rooms;
    
    /**
     * Constructor. Checks to see if the file exists before extracting the data from it.
     * @param path - The path of the text file containing the map.
     * @throws IOException 
     */
    public TextMap(String path) throws IOException {
        rooms = new ArrayList<>();
        // Make sure the text file exists.
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
        return Files.readAllLines(Paths.get(path), Charset.defaultCharset());
    }
 
    /**
     * Returns a List of all coordinates (as Points) matching the passed symbol.
     * @param symbol - The symbol to be matched.
     * @return - A List of Points matching the symbol.
     */
    public List<Point> getCoordinatesOf(char symbol) {
        List<Point> points = new ArrayList<>();
        // Check to see if there are any elements in the List.
        if(linesInSourceFile.isEmpty()) {
            System.out.println(EMPTY_MAP_MSG);
            return points;
        }
        // Iterate through elements in the Array List.
        for(int counter = 0; counter < linesInSourceFile.size(); counter++) {
            // Iterate through each character in the element.
            for(int innerCounter = 0; innerCounter < linesInSourceFile.get(counter).length(); innerCounter++) {
                if(linesInSourceFile.get(counter).charAt(innerCounter) == symbol) {
                    Point point = new Point(innerCounter, counter);
                    System.out.println("Coordinates found at x"+String.valueOf(innerCounter)+", y"+String.valueOf(counter));
                    points.add(point);
                }
            }
        }
        return points;
    }
    
    /**
     * Generates Room objects for each instance of the room symbol within the map.
     * @return - A List of all rooms within the map, as Room objects.
     */
    public List<Room> getRooms() {
        // Get the coordinates of all the rooms.
        List<Point> points = getCoordinatesOf(ROOM_SYMBOL);
        // For each coordinate, create a new Room object.
        for(Point point : points) {
            Room room = new Room(point);
            System.out.println("Room created at "+String.valueOf(point.x)+", "+String.valueOf(point.y));
            rooms.add(room);
        }
        // Return the Rooms.
        return rooms;
    }
    
    /**
     * Generates Exit objects for each instance of each exit symbol within the map.
     */
    public void getExits() {
        // Establish locations of all symbols.
        List<Point> horizontalExits = getCoordinatesOf(HORIZONTAL_EXIT_SYMBOL);
        List<Point> verticalExits = getCoordinatesOf(VERTICAL_EXIT_SYMBOL);
        List<Point> backDiagonalExits = getCoordinatesOf(BACK_DIAGONAL_EXIT_SYMBOL);
        List<Point> forwardDiagonalExits = getCoordinatesOf(FORWARD_DIAGONAL_EXIT_SYMBOL);
        List<Point> multiDiagonalExits = getCoordinatesOf(MULTI_DIAGONAL_EXIT_SYMBOL);
        
        // Add horizontal exits.
        if(!horizontalExits.isEmpty()) {
            for(Point point : horizontalExits) {
                // Add exit to west room.
                getRoomWestOf(point).addExit(Direction.EAST, getRoomEastOf(point));
                echoAddExit(Direction.EAST, getRoomWestOf(point));
                // Add exit to east room.
                getRoomEastOf(point).addExit(Direction.WEST, getRoomWestOf(point));
                echoAddExit(Direction.WEST, getRoomEastOf(point));
            }
        }
        // Add veritical exits.
        if(!verticalExits.isEmpty()) {
            for(Point point : verticalExits) {
                // Add exit to north room.
                getRoomNorthOf(point).addExit(Direction.SOUTH, getRoomSouthOf(point));
                echoAddExit(Direction.SOUTH, getRoomNorthOf(point));
                // Add exit to south room.
                getRoomSouthOf(point).addExit(Direction.NORTH, getRoomNorthOf(point));
                echoAddExit(Direction.NORTH, getRoomSouthOf(point));
            }
        }
        // Add back diagonal (\) exits.
        if(!backDiagonalExits.isEmpty()) {
            for(Point point : backDiagonalExits) {
                // Add exit to northwest room.
                getRoomNorthwestOf(point).addExit(Direction.SOUTHEAST, getRoomSoutheastOf(point));
                echoAddExit(Direction.SOUTHEAST, getRoomNorthwestOf(point));
                // Add exit to southeast room.
                getRoomSoutheastOf(point).addExit(Direction.NORTHWEST, getRoomNorthwestOf(point));
                echoAddExit(Direction.NORTHWEST, getRoomSoutheastOf(point));
            }
        }
        // Add forward diagonal (/) exits.
        if(!forwardDiagonalExits.isEmpty()) {
            for(Point point : forwardDiagonalExits) {
                // Add exit to northeast room.
                getRoomNortheastOf(point).addExit(Direction.SOUTHWEST, getRoomSouthwestOf(point));
                echoAddExit(Direction.SOUTHWEST, getRoomNortheastOf(point));
                // Add exit to southwest room.
                getRoomSouthwestOf(point).addExit(Direction.NORTHEAST, getRoomNortheastOf(point));
                echoAddExit(Direction.NORTHEAST, getRoomSouthwestOf(point));
            }
        }
        // Add multidiagonal (X) exits.
        if(!multiDiagonalExits.isEmpty()) {
            for(Point point : multiDiagonalExits) {
                // Add exit to northwest room.
                getRoomNorthwestOf(point).addExit(Direction.SOUTHEAST, getRoomSoutheastOf(point));
                echoAddExit(Direction.SOUTHEAST, getRoomNorthwestOf(point));
                // Add exit to southwest room.
                getRoomSouthwestOf(point).addExit(Direction.NORTHEAST, getRoomNortheastOf(point));
                echoAddExit(Direction.NORTHEAST, getRoomSouthwestOf(point));
                // Add exit to northeast room.
                getRoomNortheastOf(point).addExit(Direction.SOUTHWEST, getRoomSouthwestOf(point));
                echoAddExit(Direction.SOUTHWEST, getRoomNortheastOf(point));
                // Add exit to southeast room.
                getRoomSoutheastOf(point).addExit(Direction.NORTHWEST, getRoomNorthwestOf(point));
                echoAddExit(Direction.NORTHWEST, getRoomSoutheastOf(point));
            }
        }
    }
    
    /**
     * Echos adding of an exit to a room to the command line.
     * @param direction - The direction of the exit.
     * @param room  - The destination of the exit.
     */
    public void echoAddExit(Direction direction, Room room) {
        System.out.println("Adding "+ direction.toString() + " exit to room at "+ room.toString());
    }
    
    /**
     * Find and return the Room to the west (x-1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the west (x-1) of the specified Point.
     */
    public Room getRoomWestOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x - 1 && room.getPosition().y == point.y) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the east (x+1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return  - The Room to the east (x+1) of the specified Point.
     */
    public Room getRoomEastOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x + 1 && room.getPosition().y == point.y) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the north (y-1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the north (y-1) of the specified Point.
     */
    public Room getRoomNorthOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x && room.getPosition().y == point.y - 1) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the south (y+1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the south (y+1) of the specified Point.
     */
    public Room getRoomSouthOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x && room.getPosition().y == point.y + 1) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the northwest (x-1, y-1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the northwest (x-1, y-1) of the specified Point.
     */
    private Room getRoomNorthwestOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x - 1 && room.getPosition().y == point.y - 1) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the northeast (x+1, y-1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the northeast (x+1, y-1) of the specified Point.
     */
    private Room getRoomNortheastOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x + 1 && room.getPosition().y == point.y - 1) {
               return room;
           }
        }
        return null;
    }
    /**
     * Find and return the Room to the southwest (x-1, y+1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the southwest (x-1, y+1) of the specified Point.
     */
    private Room getRoomSouthwestOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x - 1 && room.getPosition().y == point.y + 1) {
               return room;
           }
        }
        return null;
    }
    
    /**
     * Find and return the Room to the southeast (x+1, y+1) of the specified exit.
     * @param point - A Point describing the position of an exit.
     * @return - The Room to the southeast (x+1, y+1) of the specified Point.
     */
    private Room getRoomSoutheastOf(Point point) {
        for(Room room : rooms) {
           if(room.getPosition().x == point.x + 1 && room.getPosition().y == point.y + 1) {
               return room;
           }
        }
        return null;
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