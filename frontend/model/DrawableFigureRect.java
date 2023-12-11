package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public interface DrawableFigureRect {
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

    private double distance(double p1, double p2){
        return Math.abs(p1 - p2);
    }
}
