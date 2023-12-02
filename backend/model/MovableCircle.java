package TP_POO.backend.model;

public class MovableCircle extends Circle<MovablePoint> implements MovableFigure {

    public MovableCircle(MovablePoint centerPoint, double radius) {
        super(centerPoint, radius);
    }

    @Override
    public MovablePoint[] getPoints() {
        return new MovablePoint[]{centerPoint};
    }

}
