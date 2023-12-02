package TP_POO.backend.model;

public interface MovableFigure extends Movable {
    MovablePoint[] getPoints();
    @Override
    default void moveNorth(double delta){
        for(MovablePoint mov: getPoints()){
            mov.moveNorth(delta);
        }
    }
    @Override
    default void moveSouth(double delta){
        for(MovablePoint mov : getPoints()){
            mov.moveSouth(delta);
        }
    }
    @Override
    default void moveEast(double delta){
        for(MovablePoint mov: getPoints()){
            mov.moveEast(delta);
        }
    }
    @Override
    default void moveWest(double delta){
        for(MovablePoint mov: getPoints()){
            mov.moveWest(delta);
        }
    }
}
