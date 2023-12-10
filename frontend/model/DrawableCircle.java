package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Circle;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;


public class DrawableCircle extends Circle {

    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        super(centerPoint, radius, fillColor, lineColor, lineWidth, shadow, gradient, arch);
        this.gc = gc;
        draw(shadow, gradient, arch);
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        implementShadow(shadow);
        gc.setFill(getFillColor().toFxColor());
        gc.setLineWidth(this.getLineWidth());
        implementGradient(gradient);

        gc.strokeOval(getCenterPoint().getX() - (getRadius()), getCenterPoint().getY() - (getRadius()), getRadius()*2, getRadius()*2);
        gc.fillOval(getCenterPoint().getX() - (getRadius()), getCenterPoint().getY() - (getRadius()), getRadius()*2, getRadius()*2);
        implementArch(arch);
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
        if(arch) {
            double arcX = getCenterPoint().getX() - getsMayorAxis()/2;
            double arcY = getCenterPoint().getY() - getsMinorAxis()/2;
            gc.setLineWidth(LINE_WIDTH);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX - OFFSET, arcY - OFFSET, sMayorAxis + 2 * OFFSET, sMinorAxis + 2 * OFFSET, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX - OFFSET, arcY - OFFSET, sMayorAxis + 2 * OFFSET, sMinorAxis + 2 * OFFSET, 225, 180, ArcType.OPEN);
        }
    }
    @Override
    public void implementGradient(boolean gradient) {
        if (gradient){
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, getFillColor().toFxColor()),
                    new Stop(1, getFillColor().toFxColor().invert()));
            gc.setFill(radialGradient);
        }
    }
}
