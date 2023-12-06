package TP_POO.backend.interfaces;

import TP_POO.backend.model.BackColor;

public interface Colorable {
    BackColor getLineColor();

    BackColor getFillColor();

    double getLineWidth();

    void setLineWidth(double lineWidth);

    void setLineColor(BackColor lineColor);

    void setFillColor(BackColor fillColor);

    //no tengo muy en claro todavia si esto va aca
    public void setShadow(boolean shadow);

    public void setGradient(boolean gradient);

    public void setArched(boolean arched);

    public boolean hasShadow();

    public boolean hasGradient();

    public boolean hasArched();
}
