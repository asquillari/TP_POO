package TP_POO.backend.model;

public class BackColor {
    private final double red; // valor de rojo del color en escala de [0-1]
    private final double green; // valor de verde del color en escala de [0-1]
    private final double blue; // valor de blue del color en escala de [0-1]
    private final double transparency; // valor de trasparencia del color en escala de [0-1]

    // Macros que vamos a utilizar
    public static final BackColor RED = new BackColor(1.0, 0, 0, 1.0);
    public static final BackColor TRANSPARENT = new BackColor(0, 0, 0, 0);

    public BackColor(double red, double green, double blue, double transparency) {
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.transparency = transparency;
    }

    public double getRed() {
        return red;
    }

    public double getBlue() {
        return blue;
    }

    public double getGreen() {
        return green;
    }

    public double getTransparency() {
        return transparency;
    }
}
