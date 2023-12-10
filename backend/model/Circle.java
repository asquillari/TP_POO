package TP_POO.backend.model;

public abstract class Circle extends Ellipse{

    public Circle(Point centerPoint, double radius, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient,boolean arch) {
        super(centerPoint, TWO*radius, TWO*radius, fillColor, lineColor, lineWidth, shadow, gradient, arch);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, sMayorAxis/TWO);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getRadius() {
        return sMayorAxis/TWO;
    }
    public double getDiamiter(){return getRadius() * TWO;}

}
