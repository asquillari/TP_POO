package TP_POO.backend.model;

public class Square<P extends Point> extends Rectangle<P> {

    //chequear bien tema casteo
    public Square(P topLeft, double size) {
        super(topLeft, (P) new Point(topLeft.x + size, topLeft.y + size));
    }


    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", topLeft, bottomRight);
    }

    public Figure activate(Point startPoint, Point endPoint){
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new Square(startPoint, size);
    }
}
