package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;

public class FigureTool extends Tool{
    private final Class<?> figure;

    public FigureTool(CanvasState canvasState, PaintPane paintPane, Class<?> figure) {
        super(canvasState, paintPane);
        this.figure=figure;
    }

    public Figure activate(Point startPoint, Point endPoint){
        return figure.activate(startPoint, endPoint);
    }
}
