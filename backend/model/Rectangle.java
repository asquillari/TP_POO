package TP_POO.backend.model;

public class Rectangle<P extends Point> extends Figure {

    protected final P topLeft, bottomRight;

    public Rectangle(P topLeft, P bottomRight) {
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

}
