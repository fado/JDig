package main;

public enum Entity {
    ROOM("./resources/images/room.png"),
    HORIZONTAL_EXIT("./resources/images/horizontal_exit.png"),
    VERTICAL_EXIT("./resources/images/vertical_exit.png"),
    FORWARD_DIAGONAL_EXIT("./resources/images/forward_diagonal_exit.png"),
    BACKWARD_DIAGONAL_EXIT("./resources/images/backward_diagonal_exit.png"),
    X_EXIT("./resources/images/x_exit.png"),
    NO_ENTITY(null);
    
    private final String path;
    
    Entity(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return this.path;
    }
}