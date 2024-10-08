package TP_POO.frontend;


import TP_POO.frontend.buttons.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class Tools extends VBox {
    private final static String SELECCIONAR = "Seleccionar";
    private final static String RECTANGULO = "Rectángulo";
    private final static String CIRCULO = "Círculo";
    private final static String CUADRADO = "Cuadrado";
    private final static String ELIPSE = "Elipse";
    private final static String BORRAR = "Borrar";
    private final static  String AGRUPAR = "Agrupar";
    private final static String DESAGRUPAR = "Desagrupar";
    private final static String GIRARD = "Girar D";
    private final static String VOLTEARH = "Voltear H";
    private final static String VOLTEARV = "Voltear V";
    private final static String ESCALARMAS = "Escalar +";
    private final static String ESCALARMENOS = "Escalar -";
    private final static String GUARDAR="Guardar";
    private final static String CHARACTER="\n";
    private final static int MIN_WIDTH = 90;
    private final static int INSETS_PADDING = 5;
    private final static int PREF_WIDTH = 100;
    private final static int SPACING = 10;
    private static final String LABEL="Etiquetas";
    private static final Label label= new Label(LABEL);

    // Botones Barra Izquierda
    private final ToggleButton selectionButton = new ToggleButton(SELECCIONAR);
    private final FigureButton rectangleButton = new RectangleButton(RECTANGULO);
    private final FigureButton circleButton = new CircleButton(CIRCULO);
    private final FigureButton squareButton = new SquareButton(CUADRADO);
    private final FigureButton ellipseButton = new EllipseButton(ELIPSE);
    private final ToggleButton deleteButton = new ToggleButton(BORRAR);
    private final ToggleButton groupButton = new ToggleButton(AGRUPAR);
    private final ToggleButton degroupButton = new ToggleButton(DESAGRUPAR);
    private final ToggleButton rotateButton = new ToggleButton(GIRARD);
    private final ToggleButton flipHButton = new ToggleButton(VOLTEARH);
    private final ToggleButton flipVButton = new ToggleButton(VOLTEARV);
    private final ToggleButton resizePlusButton = new ToggleButton(ESCALARMAS);
    private final ToggleButton resizeMinusButton = new ToggleButton(ESCALARMENOS);
    private final ToggleButton saveButton= new ToggleButton(GUARDAR);

    private final FigureButton[] figureButtonsArr = {rectangleButton, circleButton, squareButton, ellipseButton};
    private final static Color DEFAULT_FILL_COLOR = Color.YELLOW;
    private final ColorPicker fillColorPicker = new ColorPicker(DEFAULT_FILL_COLOR);
    private final TextArea labelText= new TextArea();
    private final static Cursor HAND = Cursor.HAND;

    private void setButton(ToggleButton tool){
        tool.setMinWidth(MIN_WIDTH);
        tool.setCursor(HAND);
    }
    public Tools(){

        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton, groupButton, degroupButton, rotateButton, flipHButton, flipVButton, resizePlusButton, resizeMinusButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            setButton(tool);
            tool.setToggleGroup(tools);
        }

        getChildren().addAll(toolsArr);
        getChildren().add(fillColorPicker);
        getChildren().add(label);
        labelText.setPrefHeight(50);
        getChildren().add(labelText);
        setButton(saveButton);
        getChildren().add(saveButton);
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

    public void saveAction(EventHandler<ActionEvent> action){saveButton.setOnAction(action);}

    public String getText(){
        return labelText.getText();
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

    public void groupAction(EventHandler<ActionEvent> action){ groupButton.setOnAction(action); }
    public void degroupAction(EventHandler<ActionEvent> action){
        degroupButton.setOnAction(action);
    }
    public void rotateAction(EventHandler<ActionEvent> action){
        rotateButton.setOnAction(action);
    }
    public void flipHAction(EventHandler<ActionEvent> action){
        flipHButton.setOnAction(action);
    }
    public void flipVAction(EventHandler<ActionEvent> action){
        flipVButton.setOnAction(action);
    }
    public void resizePAction(EventHandler<ActionEvent> action){
        resizePlusButton.setOnAction(action);
    }
    public void resizeMAction(EventHandler<ActionEvent> action){
        resizeMinusButton.setOnAction(action);
    }

    public void setButtonsDisable(boolean set) {
        saveButton.setDisable(set);
        labelText.setDisable(set);
    }


    public void setTextArea(List<String> labels){
        StringBuilder toSend = new StringBuilder();
        for (String label : labels){
            toSend.append(label);
            toSend.append(CHARACTER);
        }
        labelText.setText(toSend.toString());
    }

}
