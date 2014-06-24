package globals;

public enum Entity {
    
    ROOM(null),
    HORIZONTAL_EXIT("./resources/images/horizontal_exit.png"),
    VERTICAL_EXIT("./resources/images/vertical_exit.png"),
    FORWARD_DIAGONAL_EXIT("./resources/images/forward_diagonal_exit.png"),
    BACK_DIAGONAL_EXIT("./resources/images/back_diagonal_exit.png"),
    X_EXIT("./resources/images/x_exit.png");
    
    private final String path;
    
    Entity(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return path;
    }
}