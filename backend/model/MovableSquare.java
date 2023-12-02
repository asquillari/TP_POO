package TP_POO.backend.model;

public class MovableSquare extends Square<MovablePoint> implements MovableFigure {

    public MovableSquare(MovablePoint topLeft, double size) {
        super(topLeft, size);
    }

    @Override
    public MovablePoint[] getPoints() {
        return new MovablePoint[]{topLeft, bottomRight};
    }

}
