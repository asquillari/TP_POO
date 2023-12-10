package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;

public interface DrawableFigure {

    static final int LINE_WIDTH_ARCH=5;

    default void drawRect(boolean shadow, boolean gradient, boolean arch, GraphicsContext gc, Point TopLeft, Point BottomRight, BackColor fillColor, double lineWith, double offset) {
        implementShadowRect(shadow, TopLeft, BottomRight, gc);
        gc.setFill(fillColor.toFxColor());
        gc.setLineWidth(lineWith);
        implementGradientRect(gradient, gc, fillColor);
        gc.fillRect(TopLeft.getX(), TopLeft.getY(),
                distance(TopLeft.getX(), BottomRight.getX()), distance(TopLeft.getY(), BottomRight.getY()));
        gc.strokeRect(TopLeft.getX(), TopLeft.getY(),
                distance(TopLeft.getX(), BottomRight.getX()), distance(TopLeft.getY(), BottomRight.getY()));
        implementArchRect(arch, TopLeft, BottomRight, gc, LINE_WIDTH_ARCH, offset);
    }
    default void implementShadowRect(boolean shadow, Point TopLeft, Point BottomRight, GraphicsContext gc) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillRect(TopLeft.getX() + 10.0,
                    TopLeft.getY() + 10.0,
                    Math.abs(TopLeft.getX() - BottomRight.getX()),
                    Math.abs(TopLeft.getY() - BottomRight.getY()));
        }
    }
    default void implementArchRect(boolean arch, Point TopLeft, Point BottomRight, GraphicsContext gc, double lineWith, double offset) {
        if (arch){
            double x = TopLeft.getX();
            double y = TopLeft.getY();
            double width = distance(x , BottomRight.getX());
            double height = distance(y , BottomRight.getY());
            gc.setLineWidth(lineWith);
            gc.setStroke(Color.LIGHTGRAY);
            double x_offSet=x-offset;
            double y_offSet=y-offset;
            x+=width+offset;
            y+=height+offset;
            gc.strokeLine(x_offSet, y_offSet, x, y_offSet);
            gc.strokeLine(x_offSet, y_offSet, x_offSet, y);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x, y_offSet, x, y);
            gc.strokeLine(x_offSet, y, x, y);
        }
    }
    default void implementGradientRect(boolean gradient, GraphicsContext gc, BackColor fillColor) {
        if (gradient){
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, fillColor.toFxColor()),
                    new Stop(1, fillColor.toFxColor().invert()));
            gc.setFill(linearGradient);
        }
    }

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

    private double distance(double p1, double p2){
        return Math.abs(p1 - p2);
    }
}
