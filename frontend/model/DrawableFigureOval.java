package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public interface DrawableFigureOval {
    static final int LINE_WIDTH_ARCH=5;
    default void drawOval(boolean shadow, boolean gradient, boolean arch, GraphicsContext gc, Point centerPoint, double sMayorAxis, double sMinorAxis, BackColor fillColor, double lineWith, double offset) {
        implementShadowOval(shadow, gc, centerPoint, sMayorAxis, sMinorAxis);
        gc.setFill(fillColor.toFxColor());
        gc.setLineWidth(lineWith);
        implementGradientOval(gradient, gc, fillColor);
        gc.strokeOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        gc.fillOval(centerPoint.getX() - (sMayorAxis / 2), centerPoint.getY() - (sMinorAxis / 2), sMayorAxis, sMinorAxis);
        implementArchOval(arch, centerPoint, sMayorAxis, sMinorAxis, gc, LINE_WIDTH_ARCH, offset);
    }
    default void implementShadowOval(boolean shadow, GraphicsContext gc, Point centerPoint, double sMayorAxis, double sMinorAxis){
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillOval(centerPoint.getX() - (sMayorAxis / 2) + 10.0, centerPoint.getY() - (sMinorAxis / 2) + 10.0, sMayorAxis, sMinorAxis);
        }
    }
    default void implementArchOval(boolean arch, Point centerPoint, double sMayorAxis, double sMinorAxis, GraphicsContext gc, double lineWith, double offset) {
        if(arch) {
            double arcX = centerPoint.getX() - sMayorAxis/2;
            double arcY = centerPoint.getY() - sMinorAxis/2;
            gc.setLineWidth(lineWith);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 45, 180, ArcType.OPEN);
            gc.setStroke(Color.BLACK);
            gc.strokeArc(arcX - offset, arcY - offset, sMayorAxis + 2 * offset, sMinorAxis + 2 * offset, 225, 180, ArcType.OPEN);
        }
    }
    default void implementGradientOval(boolean gradient, GraphicsContext gc, BackColor fillColor) {
        if (gradient){
            RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, fillColor.toFxColor()),
                    new Stop(1, fillColor.toFxColor().invert()));
            gc.setFill(radialGradient);
        }
    }

}
