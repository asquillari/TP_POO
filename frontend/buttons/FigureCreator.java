package TP_POO.frontend.buttons;

import TP_POO.backend.model.BackColor;
import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;
import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface FigureCreator {
    Figure create(Point startPoint, Point endPoint, GraphicsContext gc, BackColor fillColor, BackColor lineColor);
}
