package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;

public class DrawableEllipse extends Ellipse {
    private final GraphicsContext gc;
    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch){
        super(centerPoint, sMayorAxis, sMinorAxis, fillColor, lineColor, lineWidth, shadow, gradient, arch);
        this.gc = gc;
        draw(shadow, gradient, arch);
    }

    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        implementShadow(shadow);
        gc.setFill(getFillColor().toFxColor());
        gc.setLineWidth(this.getLineWidth());
        implementGradient(gradient);
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        implementArch(arch);
    }
    @Override
    public void implementShadow(boolean shadow) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2) + 10.0, getCenterPoint().getY() - (getsMinorAxis() / 2) + 10.0, getsMayorAxis(), getsMinorAxis());
        }
    }
    @Override
    public void implementArch(boolean arch) {
        if(arch) {
            double arcX = getCenterPoint().getX() - getsMayorAxis()/2;
            double arcY = getCenterPoint().getY() - getsMinorAxis()/2;
            double offset = 6.5;
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 225, 180, ArcType.OPEN);
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
