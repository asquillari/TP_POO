package TP_POO.frontend.model;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Point;
import TP_POO.backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;

public class DrawableRectangle extends Rectangle implements DrawableFigure{

    private final GraphicsContext gc;
    public DrawableRectangle(Point topLeft, Point bottomRight, GraphicsContext gc, BackColor fillColor , BackColor lineColor, double lineWidth, boolean shadow, boolean gradient, boolean arch){
        super(topLeft, bottomRight, fillColor, lineColor, lineWidth, shadow, gradient, arch);
        this.gc = gc;
        draw(shadow, gradient, arch);
    }


    @Override
    public void draw(boolean shadow, boolean gradient, boolean arch) {
        drawRect(shadow, gradient, arch, gc, getTopLeft(), getBottomRight(), getFillColor(), getLineWidth(), OFFSET);
    }

}
