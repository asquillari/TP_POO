package TP_POO.frontend.buttons;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.frontend.model.DrawableCircle;

public class CircleButton extends FigureButton {
    public CircleButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint) {
        double radius = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableCircle(startPoint, radius);
    }

}
