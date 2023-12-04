package TP_POO.backend.model;

public abstract class Square extends Rectangle {

    public Square(Point topLeft, double size, BackColor fillColor, BackColor lineColor) {
        super(topLeft, new Point(topLeft.x + size, topLeft.y + size), fillColor, lineColor);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
