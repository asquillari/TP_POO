package TP_POO.backend.model;

import TP_POO.backend.interfaces.Drawable;
import TP_POO.backend.interfaces.Movable;
import TP_POO.backend.interfaces.Selectable;

public abstract class Figure implements Movable, Drawable, Selectable {

    BackColor fillColor;
    BackColor lineColor;

    public Figure(BackColor fillColor, BackColor lineColor){
        setFigureProperties(fillColor, lineColor);
    }

    public void setFigureProperties(BackColor fillColor, BackColor lineColor) {
        setFillColor(fillColor);
        setLineColor(lineColor);
    }

    public BackColor getFillColor() {
        return fillColor;
    }

    public BackColor getLineColor() {
        return lineColor;
    }

    public void setFillColor(BackColor fillColor){
        this.fillColor = fillColor;
    }

    public void setLineColor(BackColor lineColor) {
        this.lineColor = lineColor;
    }
}
