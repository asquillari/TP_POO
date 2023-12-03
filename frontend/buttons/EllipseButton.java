package TP_POO.frontend.buttons;

import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.frontend.model.DrawableEllipse;

public class EllipseButton extends FigureButton {
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint) {
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new DrawableEllipse(centerPoint, sMayorAxis, sMinorAxis);
    }
}
