package TP_POO.frontend.buttons;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;

@FunctionalInterface
public interface FigureCreator {
    Figure create(Point startPoint, Point endPoint);
}
