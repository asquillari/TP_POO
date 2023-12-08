package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState canvasState) {
        getChildren().add(new AppMenuBar());
        CheckPointPane myCheckPoint = new CheckPointPane();
        getChildren().add(myCheckPoint);
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane, myCheckPoint));
        getChildren().add(new LabelsPane());
        getChildren().add(statusPane);
    }

}
