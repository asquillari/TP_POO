package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

public class DrawableRectangle extends Rectangle {

    private final GraphicsContext gc;
    public DrawableRectangle(Point topLeft, Point bottomRight, GraphicsContext gc, BackColor fillColor , BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch){
        super(topLeft, bottomRight, fillColor, lineColor, lineWidth, shadow, gradient, arch);
        this.gc = gc;
        draw(shadow, gradient, arch);
    }


    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
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
    public void implementShadow(boolean shadow) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillRect(getTopLeft().getX() + 10.0,
                    getTopLeft().getY() + 10.0,
                    distance(getTopLeft().getX(), getBottomRight().getX()),
                    distance(getTopLeft().getY(), getBottomRight().getY()));
        }
    }
    @Override
    public void implementArch(boolean arch) {
        if (arch){
            double x = getTopLeft().getX();
            double y = getTopLeft().getY();
            double width = distance(x , getBottomRight().getX());
            double height = distance(y , getBottomRight().getY());
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
    public void implementGradient(boolean gradient) {
        if (gradient){
            LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, getFillColor().toFxColor()),
                    new Stop(1, getFillColor().toFxColor().invert()));
            gc.setFill(linearGradient);
        }
    }

}
