package TP_POO.frontend;

import TP_POO.frontend.buttons.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Tools extends VBox {

    // Botones Barra Izquierda
    private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
    private final FigureButton rectangleButton = new RectangleButton("Rectángulo");
    private final FigureButton circleButton = new CircleButton("Círculo");
    private final FigureButton squareButton = new SquareButton("Cuadrado");
    private final FigureButton ellipseButton = new EllipseButton("Elipse");
    private final ToggleButton deleteButton = new ToggleButton("Borrar");

    private final FigureButton[] figureButtonsArr = {rectangleButton, circleButton, squareButton, ellipseButton};
    private final Color defaultFillColor = Color.YELLOW;
    private final ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);
    public Tools(){

        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        getChildren().addAll(toolsArr);
        getChildren().add(fillColorPicker);
        setPadding(new Insets(5));
        setStyle("-fx-background-color: #999");
        setPrefWidth(100);
        setSpacing(10);
    }

    public void figureButtonAction(EventHandler<ActionEvent> action) {
        for (FigureButton figureToggleButton : figureButtonsArr) {
            figureToggleButton.setOnAction(action);
        }
    }

    public void deleteAction(EventHandler<ActionEvent> action){
        deleteButton.setOnAction(action);
    }

    public Color getFillColor() {
        return fillColorPicker.getValue();
    }

    public FigureButton[] getFigureButtons() {
        return figureButtonsArr;
    }

    public boolean isSelectionButtonSelected(){
        return selectionButton.isSelected();
    }

}
