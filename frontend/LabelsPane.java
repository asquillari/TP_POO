package TP_POO.frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

import java.awt.*;

public class LabelsPane extends BorderPane {
    private static final String SHOW="Mostrar Etiquetas:";
    private static final String ALL="Todas";
    private static final String ONLY="Sólo";
    private static final int SPACING=10;
    private static final int WIDTH=100;
    private static final double HEIGHT=5;
    private static final Label showLabel= new Label(SHOW);

    private final RadioButton allButton = new RadioButton(ALL);
    private final RadioButton onlyButton = new RadioButton(ONLY);
    private final TextArea labelText= new TextArea();


    public LabelsPane(){
        setStyle("-fx-background-color: #999");
        HBox lowBox= new HBox();
        lowBox.setPadding(new Insets(SPACING));
        lowBox.setSpacing(SPACING);
        ToggleGroup toggleGroup = new ToggleGroup();
        allButton.setToggleGroup(toggleGroup);
        onlyButton.setToggleGroup(toggleGroup);
        labelText.setPrefWidth(WIDTH);
        labelText.setPrefHeight(HEIGHT);
        getChildren().add(labelText);
        lowBox.getChildren().addAll(showLabel, allButton, onlyButton, labelText);
        lowBox.setAlignment(Pos.CENTER);
        setBottom(lowBox);
    }

    public boolean onlyButtonIsSelected(){
        return onlyButton.isSelected();
    }

    public String getText(){
        return labelText.getText();
    }

    public void onlyAction(EventHandler<ActionEvent> action){
        onlyButton.setOnAction(action);
    }

    public void allAction(EventHandler<ActionEvent> action){
        allButton.setOnAction(action);
    }


}
