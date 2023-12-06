package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Circle;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;


public class DrawableCircle extends Circle {

    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth) {
        super(centerPoint, radius, fillColor, lineColor, lineWidth);
        this.gc = gc;
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - getRadius() + 10.0,
                    getCenterPoint().getY() - getRadius() + 10.0, getDiamiter(), getDiamiter());

        }else{
            gc.fillOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
            gc.strokeOval(getCenterPoint().getX() - getRadius(), getCenterPoint().getY() - getRadius(), getRadius() * 2, getRadius() * 2);
        }
    }
}
