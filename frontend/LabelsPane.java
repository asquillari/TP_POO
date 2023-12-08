package TP_POO.frontend;

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
    private static final Label showLabel= new Label(SHOW);

    public LabelsPane(){
        setStyle("-fx-background-color: #999");
        HBox lowBox= new HBox();
        lowBox.setPadding(new Insets(SPACING));
        lowBox.setSpacing(SPACING);

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton allButton = new RadioButton(ALL);
        allButton.setToggleGroup(toggleGroup);
        RadioButton onlyButton = new RadioButton(ONLY);
        onlyButton.setToggleGroup(toggleGroup);
        javafx.scene.control.TextArea labelText= new TextArea();
        labelText.setPrefWidth(WIDTH);
        labelText.setPrefHeight(15);
        getChildren().add(labelText);

        lowBox.getChildren().addAll(showLabel, allButton, onlyButton, labelText);
        lowBox.setAlignment(Pos.CENTER);
        setBottom(lowBox);
    }
}
