package TP_POO.frontend;

import TP_POO.backend.model.Figure;
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
    private final CheckBox cbShadow = new CheckBox(SHADOW);

    private final CheckBox cbGradient = new CheckBox(GRADIENT);

    private final CheckBox cbArch = new CheckBox(ARCH);

    private boolean commonShadowState;
    private boolean commonGradientState;
    private boolean commonArchState;

    public CheckPointPane(){
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

    public boolean isShadowSelected(){
        return cbShadow.isSelected();
    }
    public boolean isGradientSelected(){
        return cbGradient.isSelected();
    }
    public boolean isArchSelected(){
        return cbArch.isSelected();
    }

    public void updateBoxesStatus(Iterable<Figure> selected){
        resetState();
        resetBoxes();

        boolean indeterminate = false;

        Figure first = selected.iterator().next();

        for(Figure current : selected){
            if(current.hasShadow() != first.hasShadow()){
                indeterminate = true;
                cbShadow.allowIndeterminateProperty();
                cbShadow.setIndeterminate(true);
            }if(current.hasGradient() != first.hasGradient()){
                indeterminate = true;
                cbGradient.allowIndeterminateProperty();
                cbGradient.setIndeterminate(true);
            }if(current.hasArched() != first.hasArched()){
                indeterminate = true;
                cbArch.allowIndeterminateProperty();
                cbArch.setIndeterminate(true);
            }
            if(!indeterminate){
                updateStatus(current);
            }
        }

        cbShadow.setSelected(commonShadowState);
        cbArch.setSelected(commonArchState);
        cbGradient.setSelected(commonGradientState);
    }

    private void updateStatus(Figure figure) {
        commonArchState &= figure.hasArched();
        commonShadowState &= figure.hasShadow();
        commonGradientState &= figure.hasGradient();
    }

    private void resetState() {
        commonShadowState=true;
        commonGradientState=true;
        commonArchState=true;
    }

    public void resetBoxes() {
        cbShadow.setSelected(false);
        cbGradient.setSelected(false);
        cbArch.setSelected(false);
        cbShadow.setIndeterminate(false);
        cbShadow.setAllowIndeterminate(false);
        cbGradient.setIndeterminate(false);
        cbGradient.setAllowIndeterminate(false);
        cbArch.setIndeterminate(false);
        cbArch.setAllowIndeterminate(false);
    }
}
