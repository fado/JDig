package data;

import properties.Images;
import properties.Localization;
import javax.swing.border.Border;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Room extends Entity implements Connectible {

    private String include, inherit, name, streetName, longDescription,
            shortDescription, determinate, light;
    private final List<Exit> exits = new ArrayList<>();
    private static final Localization localization = new Localization();

    public Room(Point point) {
        this.X = point.x;
        this.Y = point.y;

        // Pull default values from the localisation properties to ensure everything is
        // in the correct language.
        this.include = localization.get("DefaultInclude");
        this.inherit = localization.get("DefaultInherit");
        this.longDescription = localization.get("DefaultLongDescription");
        this.light = localization.get("DefaultLight");
    }

    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }

    public void setInclude(String include) { this.include = include; }
    public String getInclude() { return this.include; }

    public void setInherit(String inherit) { this.inherit = inherit; }
    public String getInherit() { return this.inherit; }

    public void setStreetName(String streetName) { this.streetName = streetName; }
    public String getStreetName() { return this.streetName; }

    public void setShort(String description) { this.shortDescription = description; }
    public String getShort() { return this.shortDescription; }

    public void setDeterminate(String determinate) { this.determinate = determinate; }
    public String getDeterminate() { return this.determinate; }

    public void setLight(String light) { this.light = light; }
    public String getLight() { return this.light; }

    public void setLong(String description) { this.longDescription = description; }
    public String getLong() { return this.longDescription; }

    public void addExit(Exit exit) { exits.add(exit); }
    public void removeExit(Exit exit) { exits.remove(exit); }

    public List<Exit> getExits() { return this.exits; }

    public Exit getExit(Direction direction) {
        Exit exitToBeReturned = null;
        for (Exit exit : exits) {
            if (exit.getDirection() == direction) {
                exitToBeReturned = exit;
            }
        }
        return exitToBeReturned;
    }

    @Override
    boolean isConnectible() { return true; }

}
