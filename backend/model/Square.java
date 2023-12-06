package TP_POO.backend.model;

public abstract class Square extends Rectangle {

    public Square(Point topLeft, double size, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        super(topLeft, new Point(topLeft.x + size, topLeft.y + size), fillColor, lineColor, lineWidth, shadow, gradient, arch);
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

}
