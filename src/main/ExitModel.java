package main;

public class ExitModel {
    
    private final Direction direction;
    private final Room destination;
    private final ExitType exitType;
    
    public ExitModel(Direction direction, Room destination, ExitType exitType) {
        this.direction = direction;
        this.destination = destination;
        this.exitType = exitType;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public Room getDestination() {
        return this.destination;
    }
    
    public ExitType getExitType() {
        return this.exitType;
    }
}