package TP_POO.backend.model;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, double radius) {
        super(centerPoint, 2*radius, 2*radius);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, sMayorAxis/2);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return sMayorAxis/2;
    }

}
