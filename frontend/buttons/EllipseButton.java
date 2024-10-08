package TP_POO.frontend.buttons;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.frontend.model.DrawableEllipse;
import javafx.scene.canvas.GraphicsContext;

public class EllipseButton extends FigureButton {
    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new DrawableEllipse(centerPoint, sMayorAxis, sMinorAxis, gc, fillColor, lineColor, lineWidth, shadow, gradient, arch);
    }
}
