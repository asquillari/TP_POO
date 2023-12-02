package TP_POO.backend.model;

public class Square<P extends Point> extends Rectangle<P> {

    public Square(P topLeft, double size) {
        Point aux = new Point(topLeft.x + size, topLeft.y + size);
        super(topLeft, aux);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
