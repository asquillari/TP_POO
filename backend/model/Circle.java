package TP_POO.backend.model;

public abstract class Circle extends Ellipse{

    public Circle(Point centerPoint, double radius, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient,boolean arch) {
        super(centerPoint, 2*radius, 2*radius, fillColor, lineColor, lineWidth, shadow, gradient, arch);
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
    public double getDiamiter(){return getRadius() * 2;}

}
