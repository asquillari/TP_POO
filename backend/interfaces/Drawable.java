package TP_POO.backend.interfaces;

import javafx.scene.canvas.GraphicsContext;

@FunctionalInterface
public interface Drawable {
    void draw(boolean shadow, boolean gradient, boolean arch);
}
