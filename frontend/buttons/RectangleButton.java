package TP_POO.frontend.buttons;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Rectangle;
import TP_POO.frontend.model.DrawableEllipse;
import TP_POO.frontend.model.DrawableRectangle;

public class RectangleButton extends FigureButton {
    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint) {
        return new DrawableRectangle(startPoint, endPoint);
    }
}
