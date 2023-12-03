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
    private final static String SELECCIONAR = "Seleccionar";
    private final static String RECTANGULO = "Rectángulo";
    private final static String CIRCULO = "Círculo";
    private final static String CUADRADO = "Cuadrado";
    private final static String ELIPSE = "Elipse";
    private final static String BORRAR = "Borrar";
    private final static int MIN_WIDTH = 90;
    private final static int INSETS_PADDING = 5;
    private final static int PREF_WIDTH = 100;
    private final static int SPACING = 10;

    // Botones Barra Izquierda
    private final ToggleButton selectionButton = new ToggleButton(SELECCIONAR);
    private final FigureButton rectangleButton = new RectangleButton(RECTANGULO);
    private final FigureButton circleButton = new CircleButton(CIRCULO);
    private final FigureButton squareButton = new SquareButton(CUADRADO);
    private final FigureButton ellipseButton = new EllipseButton(ELIPSE);
    private final ToggleButton deleteButton = new ToggleButton(BORRAR);

    private final FigureButton[] figureButtonsArr = {rectangleButton, circleButton, squareButton, ellipseButton};
    private final static Color DEFAULT_FILL_COLOR = Color.YELLOW;
    private final ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR);
    private final static Cursor HAND = Cursor.HAND;
    public Tools(){

        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(MIN_WIDTH);
            tool.setToggleGroup(tools);
            tool.setCursor(HAND);
        }
        getChildren().addAll(toolsArr);
        getChildren().add(fillColorPicker);
        setPadding(new Insets(INSETS_PADDING));
        setStyle("-fx-background-color: #999");
        setPrefWidth(PREF_WIDTH);
        setSpacing(SPACING);
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
