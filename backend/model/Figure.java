package TP_POO.backend.model;

import TP_POO.backend.interfaces.Colorable;
import TP_POO.backend.interfaces.Drawable;
import TP_POO.backend.interfaces.Movable;
import TP_POO.backend.interfaces.Selectable;

public abstract class Figure implements Movable, Drawable, Selectable, Colorable {
    private double lineWidth;
    private BackColor lineColor;
    private BackColor fillColor;

    private boolean shadow;
    private boolean gradient;
    private boolean arched;


    public Figure(BackColor lineColor, BackColor fillColor, double lineWidth){
        setFigureProperties(lineColor, fillColor, lineWidth);
    }

    public void setFigureProperties(BackColor lineColor, BackColor fillColor, double lineWidth) {
        setLineWidth(lineWidth);
        setLineColor(lineColor);
        setFillColor(fillColor);
    }

    public BackColor getLineColor() {
        return lineColor;
    }

    public BackColor getFillColor() {
        return fillColor;
    }

    public double getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(double lineWidth){
        this.lineWidth = lineWidth;
    }

    public void setLineColor(BackColor lineColor){
        this.lineColor = lineColor;
    }

    public void setFillColor(BackColor fillColor){
        this.fillColor = fillColor;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public void setGradient(boolean gradient) {
        this.gradient = gradient;
    }

    public void setArched(boolean arched) {
        this.arched = arched;
    }

    public boolean hasShadow() {
        return shadow;
    }

    public boolean hasGradient() {
        return gradient;
    }

    public boolean hasArched() {
        return arched;
    }
}
