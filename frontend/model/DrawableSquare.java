package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Square;
import javafx.scene.canvas.GraphicsContext;

public class DrawableSquare extends Square {

    private final GraphicsContext gc;
    public DrawableSquare(Point topLeft, double size, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth){
        super(topLeft, size, fillColor, lineColor, lineWidth);
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
