package TP_POO.frontend.model;

import TP_POO.backend.model.Ellipse;
import TP_POO.backend.model.Point;

public class DrawableEllipse extends Ellipse {
    public DrawableEllipse(Point centerPoint, double sMayorAxis, double sMinorAxis){
        super(centerPoint, sMayorAxis, sMinorAxis);
    }
}
