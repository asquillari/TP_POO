package TP_POO.backend.model;

public abstract class Ellipse extends Figure {
    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, BackColor fillColor, BackColor lineColor, double lineWidth) {
        super(fillColor, lineColor, lineWidth);
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    @Override
    public void move(double deltax, double deltay) {
        centerPoint.move(deltax, deltay);
    }

    @Override
    public boolean contains(Point point) {
        return ((Math.pow(point.getX() - getCenterPoint().getX(), 2) / Math.pow(getsMayorAxis(), 2)) +
                (Math.pow(point.getY() - getCenterPoint().getY(), 2) / Math.pow(getsMinorAxis(), 2))) <= 0.30;
    }

    @Override
    public boolean isContained(Figure figure) {
        Point left = new Point(centerPoint.getX() - sMayorAxis/2, centerPoint.getY());
        Point right = new Point(centerPoint.getX() + sMayorAxis/2, centerPoint.getY());
        Point bottom = new Point(centerPoint.getX(), centerPoint.getY() + sMinorAxis/2);
        Point top = new Point(centerPoint.getX(), centerPoint.getY() - sMinorAxis/2);
        return figure.contains(left) && figure.contains(right) && figure.contains(bottom) && figure.contains(top);
    }
}
