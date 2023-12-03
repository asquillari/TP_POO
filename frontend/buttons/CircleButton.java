package TP_POO.frontend.buttons;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.frontend.model.DrawableCircle;
import javafx.scene.canvas.GraphicsContext;

public class CircleButton extends FigureButton {
    public CircleButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint, GraphicsContext gc) {
        double radius = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableCircle(startPoint, radius, gc);
    }

}
