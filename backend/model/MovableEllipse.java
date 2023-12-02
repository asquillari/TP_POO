package TP_POO.backend.model;

public class MovableEllipse extends Ellipse<MovablePoint> implements MovableFigure {

    public MovableEllipse(MovablePoint centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint, sMayorAxis, sMinorAxis);
    }

    @Override
    public MovablePoint[] getPoints() {
        return new MovablePoint[]{centerPoint};
    }

}

