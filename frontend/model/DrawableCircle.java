package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Circle;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;


public class DrawableCircle extends Circle {

    private final GraphicsContext gc;

    public DrawableCircle(Point centerPoint, double radius, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        super(centerPoint, radius, fillColor, lineColor, lineWidth, shadow, gradient, arch);
        this.gc = gc;
        draw(shadow, gradient, arch);
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        draw(shadow, gradient, arch, gc, getRadius(), getRadius(), getRadius()*TWO, getRadius()*TWO);
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
            double arcX = getCenterPoint().getX() - getsMayorAxis()/TWO;
            double arcY = getCenterPoint().getY() - getsMinorAxis()/TWO;
            gc.setLineWidth(LINE_WIDTH);
            setProperties(gc, arcX, arcY, OFFSET);
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
