package TP_POO.backend.model;

public class Circle<P extends Point> extends Ellipse<P> {

    public Circle(P centerPoint, double radius) {
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

    public Figure activate(Point startPoint, Point endPoint){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new Circle<>(startPoint, circleRadius);
    }

}
