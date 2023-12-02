package TP_POO.backend.model;

public class Rectangle<P extends Point> extends Figure {

    protected final P topLeft, bottomRight;

    public Rectangle(P topLeft, P bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    //tengo una duda si aca devolvemos P o Point
    public P getTopLeft() {
        return topLeft;
    }

    public P getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    public static Figure create(Point startPoint, Point endPoint){
        return new Rectangle<>(startPoint, endPoint);
    }
}
