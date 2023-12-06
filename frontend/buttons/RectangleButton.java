package TP_POO.frontend.buttons;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Rectangle;
import TP_POO.frontend.model.DrawableEllipse;
import TP_POO.frontend.model.DrawableRectangle;
import javafx.scene.canvas.GraphicsContext;

public class RectangleButton extends FigureButton {
    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public Figure create(Point startPoint, Point endPoint, GraphicsContext gc, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch) {
        return new DrawableRectangle(startPoint, endPoint, gc, fillColor, lineColor, lineWidth, shadow, gradient, arch);
    }
}
