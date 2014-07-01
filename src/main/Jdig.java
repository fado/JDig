package main;

public class Jdig {
    
    private static MapLoader loader = new MapLoader();

    public static void main(String[] args) {
        loader.newMap();
    }
    
    static void setMapLoader(MapLoader loader) {
        Jdig.loader = loader;
    }
    
}