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
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        implementArch(arch);
    }
    @Override
    public void implementShadow(boolean shadow) {
        if(shadow){
            gc.setFill(Color.GRAY);
            gc.fillRect(getTopLeft().getX() + 10.0,
                    getTopLeft().getY() + 10.0,
                    Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                    Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        }
    }
    @Override
    public void implementArch(boolean arch) {
        if (arch){
            double x = getTopLeft().getX();
            double y = getTopLeft().getY();
            double width = Math.abs(x - getBottomRight().getX());
            double height = Math.abs(y - getBottomRight().getY());
            gc.setLineWidth(10);
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeLine(x, y, x + width, y);
            gc.strokeLine(x, y, x, y + height);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x + width, y, x + width, y + height);
            gc.strokeLine(x, y + height, x + width, y + height);
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
