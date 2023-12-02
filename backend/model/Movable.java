package TP_POO.backend.model;


public interface Movable {
    void moveNorth(double delta);

    void moveSouth(double delta);

    void moveWest(double delta);

    void moveEast(double delta);
    default void MoveNorthEast(double deltaX, double deltaY){
        moveEast(deltaX);
        moveNorth(deltaY);
    }


}
