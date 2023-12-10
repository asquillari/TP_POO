package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

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
        draw(shadow, gradient, arch, gc, getsMayorAxis() / TWO, getsMinorAxis() / TWO, getsMayorAxis(), getsMinorAxis());
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
            setProperties(gc,arcX, arcY, ELLIPSE_OFFSET);
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
