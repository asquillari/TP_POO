package TP_POO.frontend.buttons;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Square;
import TP_POO.frontend.model.DrawableSquare;
import javafx.scene.canvas.GraphicsContext;

public class SquareButton extends FigureButton{
    public SquareButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth) {
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new DrawableSquare(startPoint, size, gc, fillColor, lineColor, lineWidth);
    }
}
