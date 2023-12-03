package TP_POO.backend.interfaces;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;

public interface Selectable {
    boolean contains(Point point);

    boolean isContained(Figure figure);
}
