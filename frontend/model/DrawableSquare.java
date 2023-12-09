package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class DrawableSquare extends Square {

    private final GraphicsContext gc;
    public DrawableSquare(Point topLeft, double size, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch){
        super(topLeft, size, fillColor, lineColor, lineWidth, shadow, gradient, arch);
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
            double width = distance(x , getBottomRight().getX());
            double height = distance(y , getBottomRight().getY());
            gc.setLineWidth(10);
            double offset = 6;
            gc.setStroke(Color.LIGHTGRAY);
            gc.strokeLine(x - offset, y - offset, x + width + offset, y - offset);
            gc.strokeLine(x - offset, y - offset, x - offset, y + height + offset);
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x + width + offset, y - offset, x + width + offset, y + height + offset);
            gc.strokeLine(x - offset, y + height + offset, x + width + offset, y + height + offset);
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
