package TP_POO.frontend.model;

import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public interface DrawableFigure {

    public void draw(boolean shadow, boolean gradient, boolean arch, GraphicsContext gc) {
        implementShadow(shadow);
        gc.setFill(getFillColor().toFxColor());
        gc.setLineWidth(this.getLineWidth());
        implementGradient(gradient);
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                distance(getTopLeft().getX(), getBottomRight().getX()), distance(getTopLeft().getY(), getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                distance(getTopLeft().getX(), getBottomRight().getX()), distance(getTopLeft().getY(), getBottomRight().getY()));
        implementArch(arch);
    }
    @Override
    public void implementShadow(boolean shadow, Point TopLeft, Point BottomRight, GraphicsContext gc) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillRect(getTopLeft().getX() + 10.0,
                    getTopLeft().getY() + 10.0,
                    Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                    Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        }
    }
    @Override
    public void implementArch(boolean arch, GraphicsContext gc, Point BottomRight, Point TopLeft) {
        if (arch){
            double x = TopLeft.getX();
            double y = TopLeft.getY();
            double width = distance(x , BottomRight.getX());
            double height = distance(y , BottomRight.getY());
            gc.setLineWidth(LINE_WIDTH);
            gc.setStroke(Color.LIGHTGRAY);
            double x_offSet=x-OFFSET;
            double y_offSet=y-OFFSET;
            x+=width+OFFSET;
            y+=height+OFFSET;
            gc.strokeLine(x_offSet, y_offSet, x, y_offSet);
            gc.strokeLine(x_offSet, y_offSet, x_offSet, y);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x, y_offSet, x, y);
            gc.strokeLine(x_offSet, y, x, y);
        }
    }
    @Override
    public void implementGradient(boolean gradient, GraphicsContext gc) {
        if (gradient){
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, getFillColor().toFxColor()),
                    new Stop(1, getFillColor().toFxColor().invert()));
            gc.setFill(linearGradient);
        }
    }

    private double distance(double p1, double p2){
        return Math.abs(p1 - p2);
    }
}
