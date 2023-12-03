package TP_POO.backend.model;

public abstract class Rectangle extends Figure {

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

    @Override
    public void move(double deltax, double deltay) {
        topLeft.move(deltax, deltay);
        bottomRight.move(deltax, deltay);
    }

    @Override
    public boolean contains(Point point) {
        return point.getX() > getTopLeft().getX() && point.getX() < getBottomRight().getX() &&
                point.getY() > getTopLeft().getY() && point.getY() < getBottomRight().getY();
    }

    @Override
    public boolean isContained(Figure figure) {
        return figure.contains(topLeft) && figure.contains(bottomRight);
    }
}
