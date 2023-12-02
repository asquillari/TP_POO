package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import TP_POO.backend.model.Point;

public abstract class Tool{
    protected final CanvasState canvasState;
    protected final PaintPane paintPane;

    public Tool(CanvasState canvasState, PaintPane paintPane) {
        this.canvasState = canvasState;
        this.paintPane = paintPane;
    }

}
