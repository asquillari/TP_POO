package TP_POO.frontend;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class CheckPointPane extends BorderPane {
    private static final String SHADOW="Sombra";
    private static final String GRADIENT="Gradiente";
    private static final String ARCH="Biseleado";
    private static final String LABEL="Efectos: ";
    private static final int SPACING=10;
    private static final Label label= new Label(LABEL);
    private CheckBox cbShadow = new CheckBox(SHADOW);

    private CheckBox cbGradient = new CheckBox(GRADIENT);

    private CheckBox cbArch = new CheckBox(ARCH);

    public CheckPointPane(){
        initialize();
    }
    public void initialize(){
        setStyle("-fx-background-color: #999");
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(SPACING));
        topBox.setSpacing(SPACING);


        // Agrega los CheckBox al HBox
        topBox.getChildren().addAll(label, cbShadow, cbGradient, cbArch);
        topBox.setAlignment(Pos.CENTER);

        // Configura el HBox en la parte superior del BorderPane
        setTop(topBox);
    }

    public void setSelected(boolean hasShadow, boolean hasGradient, boolean hasArch){
        cbShadow.setSelected(hasShadow);
        cbGradient.setSelected(hasGradient);
        cbArch.setSelected(hasArch);
    }

    public void shadowAction(EventHandler<ActionEvent> action){
        cbShadow.setOnAction(action);
    }

    public void gradientAction(EventHandler<ActionEvent> action){
        cbGradient.setOnAction(action);
    }

    public void archAction(EventHandler<ActionEvent> action){
        cbArch.setOnAction(action);
    }


    
}
