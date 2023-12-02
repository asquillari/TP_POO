package TP_POO.backend.model;

public class MovablePoint extends Point implements Movable {
    public MovablePoint(double x, double y){
        super(x, y);
    }
    @Override
    public void move(double deltax, double deltay){
        x += deltax;
        y += deltay;
    }


}
