package TP_POO.frontend;

import TP_POO.backend.CanvasState;

public abstract class Tool{
    protected final CanvasState canvasState;
    protected final PaintPane paintPane;

    public Tool(CanvasState canvasState, PaintPane paintPane) {
        this.canvasState = canvasState;
        this.paintPane = paintPane;
    }

    public abstract void activate();
}
