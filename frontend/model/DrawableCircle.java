package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Circle;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.*;


public class DrawableCircle extends Circle {

    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth) {
        super(centerPoint, radius, fillColor, lineColor, lineWidth);
        this.gc = gc;
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        implementShadow(shadow);
        gc.setFill(implementGradient(gradient));
        gc.fillOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
        gc.strokeOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }

    @Override
    public void implementShadow(boolean shadow) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - getRadius() + 10.0,
                    getCenterPoint().getY() - getRadius() + 10.0, getDiamiter(), getDiamiter());

        }
    }
    @Override
    public void implementArch(boolean arch) {

    }
    @Override
    public Paint implementGradient(boolean gradient) {
        if (gradient){
            RadialGradient radialGradient = new RadialGradient(getCenterPoint().getX(), getCenterPoint().getY(), getRadius(), getCenterPoint().getX(), getCenterPoint().getY(), true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, getFillColor().toFxColor()),
                    new Stop(1, getFillColor().toFxColor().invert()));
            return radialGradient;
        }
        return getFillColor().toFxColor();
    }
}
