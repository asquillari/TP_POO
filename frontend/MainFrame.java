package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {
        getChildren().add(new AppMenuBar());
        getChildren().add(new CheckPointPane());
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane));
        getChildren().add(statusPane);
    }

}
