package TP_POO.backend.model;

public interface MovableFigure extends Movable {
    MovablePoint[] getPoints();
    @Override
    default void move(double deltax, double deltay){
        for(MovablePoint mov: getPoints()){
            mov.move(deltax, deltay);
        }
    }

}
