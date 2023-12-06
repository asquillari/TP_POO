package TP_POO.backend.model;

public abstract class Square extends Rectangle {

    public Square(Point topLeft, double size, BackColor fillColor, BackColor lineColor, double lineWidth) {
        super(topLeft, new Point(topLeft.x + size, topLeft.y + size), fillColor, lineColor, lineWidth);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
