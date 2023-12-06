package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class DrawableEllipse extends Ellipse {
    private final GraphicsContext gc;
    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth){
        super(centerPoint, sMayorAxis, sMinorAxis, fillColor, lineColor, lineWidth);
        this.gc = gc;
    }

    @Override
    public void draw() {
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }
}
