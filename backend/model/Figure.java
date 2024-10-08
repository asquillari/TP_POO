package TP_POO.backend.model;

import TP_POO.backend.interfaces.Colorable;
import TP_POO.backend.interfaces.Drawable;
import TP_POO.backend.interfaces.Movable;
import TP_POO.backend.interfaces.Selectable;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Figure implements Movable, Drawable, Selectable, Colorable {
    private double lineWidth;
    private BackColor lineColor;
    private BackColor fillColor;

    private boolean shadow;
    private boolean gradient;
    private boolean arched;
    protected static final double INCREASE_FACTOR=1.25;
    protected static final double DECREASE_FACTOR=0.75;
    protected static final int POSITIVE=1;
    protected static final int NEGATIVE=-1;
    protected static final double OFFSET=3.0;
    protected static final double ELLIPSE_OFFSET=3.5;

    private List<String> labels;

    public Figure(BackColor lineColor, BackColor fillColor, double lineWidth,boolean shadow, boolean gradient, boolean arched){
        setFigureProperties(lineColor, fillColor, lineWidth, shadow, gradient, arched);
        this.labels = new ArrayList<>();
    }

    public void setFigureProperties(BackColor lineColor, BackColor fillColor, double lineWidth, boolean shadow, boolean gradient, boolean arched) {
        setLineWidth(lineWidth);
        setLineColor(lineColor);
        setFillColor(fillColor);
        this.shadow=shadow;
        this.gradient=gradient;
        this.arched=arched;
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

    public boolean hasLabel(String label){
        return labels.contains(label);
    }

    public List<String> getOldLabels(String[] newLabels){
        List<String> toReturn = labels;
        resetLabels();
        for (String label : newLabels){
            labels.add(label);
        }
        return toReturn;
    }

    public List<String> getLabels(){
        return labels;
    }
    private void resetLabels(){
        labels = new ArrayList<>();
    }

    public abstract void rotate();
    public abstract void flipH();
    public abstract void flipV();
    public abstract void resizeM();
    public abstract void resizeP();


}
