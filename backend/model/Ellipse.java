package TP_POO.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

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

    public void setProperties(GraphicsContext gc, double arcX, double arcY, double offset) {
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + TWO * offset, sMinorAxis + TWO * offset, 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + TWO * offset, sMinorAxis + TWO * offset, 225, 180, ArcType.OPEN);
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
        resize(INCREASE_FACTOR);
    }

    @Override
    public void resizeM() {
        resize(DECREASE_FACTOR);
    }

    private void resize(double factor){
        setsAxis(sMayorAxis*factor, sMinorAxis*factor);
    }

    @Override
    public boolean contains(Point point) {
        return ((Math.pow(point.getX() - getCenterPoint().getX(), TWO) / Math.pow(getsMayorAxis(), TWO)) +
                (Math.pow(point.getY() - getCenterPoint().getY(), TWO) / Math.pow(getsMinorAxis(), TWO))) <= 0.30;
    }

    @Override
    public boolean isContained(Figure figure) {
        Point left = new Point(centerPoint.getX() - sMayorAxis/TWO, centerPoint.getY());
        Point right = new Point(centerPoint.getX() + sMayorAxis/TWO, centerPoint.getY());
        Point bottom = new Point(centerPoint.getX(), centerPoint.getY() + sMinorAxis/TWO);
        Point top = new Point(centerPoint.getX(), centerPoint.getY() - sMinorAxis/TWO);
        return figure.contains(left) && figure.contains(right) && figure.contains(bottom) && figure.contains(top);
    }

    public void draw(boolean shadow, boolean gradient, boolean arch, GraphicsContext gc, double parameter1, double parameter2, double parameter3, double parameter4 ){
        implementShadow(shadow);
        gc.setFill(getFillColor().toFxColor());
        gc.setLineWidth(this.getLineWidth());
        implementGradient(gradient);

        gc.strokeOval(getCenterPoint().getX() - (parameter1), getCenterPoint().getY() - (parameter2), parameter3, parameter4);
        gc.fillOval(getCenterPoint().getX() - (parameter1), getCenterPoint().getY() - (parameter2), parameter3, parameter4);
        implementArch(arch);
    }

}
