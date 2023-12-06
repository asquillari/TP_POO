package TP_POO.frontend;

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
    private final Label label= new Label(LABEL);

    public CheckPointPane(){
        setStyle("-fx-background-color: #999");
        HBox topBox = new HBox();
        topBox.setPadding(new Insets(SPACING));
        topBox.setSpacing(SPACING);


        // CheckBox para efecto 1
        CheckBox checkBox1 = new CheckBox(SHADOW);
        // Configura el evento o lógica cuando se selecciona/deselecciona

        // CheckBox para efecto 2
        CheckBox checkBox2 = new CheckBox(GRADIENT);
        // Configura el evento o lógica cuando se selecciona/deselecciona

        // CheckBox para efecto 3
        CheckBox checkBox3 = new CheckBox(ARCH);
        // Configura el evento o lógica cuando se selecciona/deselecciona

        // Agrega los CheckBox al HBox
        topBox.getChildren().addAll(label, checkBox1, checkBox2, checkBox3);
        topBox.setAlignment(Pos.CENTER);

        // Configura el HBox en la parte superior del BorderPane
        setTop(topBox);
    }
    
}
