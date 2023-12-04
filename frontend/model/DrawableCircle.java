package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Circle;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawableCircle extends Circle {

    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, GraphicsContext gc, BackColor fillColor, BackColor lineColor) {
        super(centerPoint, radius, fillColor, lineColor);
        this.gc = gc;
    }

    @Override
    public void draw() {
        gc.fillOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
        gc.strokeOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }
}
