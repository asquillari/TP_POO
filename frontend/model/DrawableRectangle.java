package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;

public class DrawableRectangle extends Rectangle {

    private final GraphicsContext gc;
    public DrawableRectangle(Point topLeft, Point bottomRight, GraphicsContext gc, BackColor fillColor , BackColor lineColor){
        super(topLeft, bottomRight, fillColor, lineColor);
        this.gc = gc;
    }

    @Override
    public void draw() {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }
}
