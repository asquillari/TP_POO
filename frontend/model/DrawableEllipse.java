package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;

public class DrawableEllipse extends Ellipse {
    private final GraphicsContext gc;
    private static final double ELLIPSE_OFFSET=3.5;
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
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / TWO), getCenterPoint().getY() - (getsMinorAxis() / TWO), getsMayorAxis(), getsMinorAxis());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / TWO), getCenterPoint().getY() - (getsMinorAxis() / TWO), getsMayorAxis(), getsMinorAxis());
        implementArch(arch);
    }
    @Override
    public void implementShadow(boolean shadow) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / TWO) + 10.0, getCenterPoint().getY() - (getsMinorAxis() / TWO) + 10.0, getsMayorAxis(), getsMinorAxis());
        }
    }
    @Override
    public void implementArch(boolean arch) {
        if(arch) {
            double arcX = getCenterPoint().getX() - getsMayorAxis()/TWO;
            double arcY = getCenterPoint().getY() - getsMinorAxis()/TWO;
            gc.setLineWidth(LINE_WIDTH);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX - ELLIPSE_OFFSET, arcY - ELLIPSE_OFFSET, sMayorAxis + TWO * ELLIPSE_OFFSET, sMinorAxis + TWO * ELLIPSE_OFFSET, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX - ELLIPSE_OFFSET, arcY - ELLIPSE_OFFSET, sMayorAxis + TWO * ELLIPSE_OFFSET, sMinorAxis + TWO * ELLIPSE_OFFSET, 225, 180, ArcType.OPEN);
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
