package globals;

public enum Entity {
    
    ROOM("./resources/images/room.png"),
    HORIZONTAL_EXIT("./resources/images/horizontal_exit.png"),
    VERTICAL_EXIT("./resources/images/vertical_exit.png"),
    FORWARD_DIAGONAL_EXIT("./resources/images/forward_diagonal_exit.png"),
    BACK_DIAGONAL_EXIT("./resources/images/back_diagonal_exit.png"),
    X_EXIT("./resources/images/x_exit.png");
    
    private final String imagePath;
    
    Entity(String path) {
        this.imagePath = path;
    }
    
    public String getImagePath() {
        return imagePath;
    }
}