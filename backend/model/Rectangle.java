package TP_POO.backend.model;

public class Rectangle extends Figure {

    protected final Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //tengo una duda si aca devolvemos P o Point
    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    public static Figure create(Point startPoint, Point endPoint){
        return new Rectangle(startPoint, endPoint);
    }

    @Override
    public void move(double deltax, double deltay) {
        topLeft.move(deltax, deltay);
        bottomRight.move(deltax, deltay);
    }
}
