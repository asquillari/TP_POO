package TP_POO.frontend.buttons;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Square;
import TP_POO.frontend.model.DrawableSquare;

public class SquareButton extends FigureButton{
    public SquareButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint) {
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableSquare(startPoint, size);
    }
}
