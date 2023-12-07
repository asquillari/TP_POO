package TP_POO.backend.model;

public abstract class Ellipse extends Figure {
    protected Point centerPoint;
    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        super(lineColor, fillColor, lineWidth, shadow, gradient, arch);
        setCenterPoint(centerPoint);
        setsAxis(sMayorAxis, sMinorAxis);
    }

    private void setsAxis(double sMayorAxis, double sMinorAxis) {
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis= sMinorAxis;
    }

    private void setCenterPoint(Point centerPoint){
        this.centerPoint=centerPoint;
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
    public void rotate() {
        setsAxis(sMinorAxis, sMayorAxis);
    }

    @Override
    public void flipV() {
        setCenterPoint(new Point(centerPoint.getX(), centerPoint.getY()+sMinorAxis));
    }

    @Override
    public void flipH() {
        setCenterPoint(new Point(centerPoint.getX()+ sMayorAxis, centerPoint.getY()));
    }

    @Override
    public void resizeP() {
        setsAxis(sMayorAxis*1.25, sMinorAxis*1.25);
    }

    @Override
    public void resizeM() {
        setsAxis(sMayorAxis*0.75, sMinorAxis*0.75);
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
