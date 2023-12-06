package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawableEllipse extends Ellipse {
    private final GraphicsContext gc;
    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth){
        super(centerPoint, sMayorAxis, sMinorAxis, fillColor, lineColor, lineWidth);
        this.gc = gc;
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2) + 10.0, getCenterPoint().getY() - (getsMinorAxis() / 2) + 10.0, getsMayorAxis(), getsMinorAxis());
        }
        gc.setFill(getFillColor().toFxColor());
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }
}
