package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import TP_POO.backend.model.*;
import TP_POO.frontend.buttons.FigureButton;
import TP_POO.frontend.model.DrawableRectangle;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PaintPane extends BorderPane {

	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 600;
	private static final Color LINE_COLOR = Color.BLACK;
	private static final int LINE_WIDTH = 1;
	private static final int SPEED=100;
	private static final String UNSELECTED_STRING = "Figuras deseleccionadas";
	private static final String NO_FIGURES_FOUND_STRING="Ninguna figura encontrada";
	private static final String START_SELECTION_STRING="Se seleccionó: ";
	private static final String SELECTING_STRING="Seleccionando figuras";
	private static final String CREATING_FIGURE_STRING="Creando figura: ";

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = LINE_COLOR;

	// StatusBar
	StatusPane statusPane;

	//Botones
	private final Tools tools = new Tools();

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;
	Figure currentFigure;


	// Colores de relleno de cada figura
	Map<Figure, Color> figureColorMap = new HashMap<>();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		gc.setLineWidth(LINE_WIDTH);
		setLeft(tools);
		setRight(canvas);

		//al hacer clickear botones de armado de figuras se deselecciona automaticamente
		tools.figureButtonAction(event->{canvasState.resetSelectedFigures();});

		tools.deleteAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		//-----Eventos del Mouse en Canvas------//

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());

			//si no hay punto de inicio no se hace nada
			//tampoco si se suelta el mouse en el mismo lugar que se apreto para evitar confundir con el click
			if(startPoint == null || event.isStillSincePress()) {
				return ;
			}

			Figure newFigure = createFigure(startPoint, endPoint);
			figureColorMap.put(newFigure, tools.getFillColor());
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			//al moverse el mouse teniendo figuras seleccionadas el label queda igual
			if(!canvasState.SelectedFiguresIsEmpty())
				return;

			Point eventPoint = new Point(event.getX(), event.getY());
			//si no hay figuras seleccionadas chequeo si hay figuras en el eventPoint
			Figure figureEventPoint= canvasState.getFigureOnPoint(eventPoint);
			//si no hay figuras en el punto getFiguresOnPoint retorna null
			// en este caso solo imprimo las coordenadas en las que estoy parado
			//si encuentra una figura la imprime
			statusPane.updateStatus(figureEventPoint== null? eventPoint.toString(): figureEventPoint.toString());
		});

		canvas.setOnMouseClicked(event -> {
			//no hago nada si no esta apretado el boton seleccionar
			//si apreto y suelto en el mismo lugar es considerado click
			if(tools.isSelectionButtonSelected() && event.isStillSincePress()) {

				Point eventPoint = new Point(event.getX(), event.getY());

				boolean found = canvasState.SelectAndUnselect(eventPoint);
				if (!found)
					canvasState.resetSelectedFigures();

				updateSelectedFiguresLabel(found);
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());

			//chequeo que haya figuras seleccionadas y que se este seleccionando el boton de seleccion
			if(tools.isSelectionButtonSelected() && !canvasState.SelectedFiguresIsEmpty()) {

				double diffX = (eventPoint.getX() - startPoint.getX()) / SPEED;
				double diffY = (eventPoint.getY() - startPoint.getY()) / SPEED;

				canvasState.moveSelectedFigures(diffX, diffY);
				redrawCanvas();

				//mientras estemos haciendo drag, si el cursor esta en canvas se actualiza el mensaje de estado
				if(onCanvas(eventPoint)){
					statusPane.updateStatus(eventPoint.toString());
				}

				if(tools.isSelectionButtonSelected()){
					currentFigure=new DrawableRectangle(startPoint, eventPoint, gc);
				}else{
					currentFigure=createFigure(startPoint, eventPoint);
				}

				if(currentFigure != null){
					redrawCanvas();
					drawFigure(currentFigure);
					statusPane.updateStatus(tools.isSelectionButtonSelected()? SELECTING_STRING : CREATING_FIGURE_STRING + currentFigure.toString());
				}
			}
		});

	}

	private Figure createFigure(Point startPoint, Point endPoint) {
		for (FigureButton figureButton : tools.getFigureButtons()) {
			if (figureButton.isSelected()) {
				return figureButton.create(startPoint, endPoint, gc);
			}
		}
		return null;
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for(Figure figure : canvasState.figures()) {
			//
			if(figure != null){
				drawFigure(figure);
			}
		}
	}

	private void drawFigure(Figure figure) {
		gc.setStroke(Color.RED);
		//esto no es muy de objetos pero por ahora lo dejo
		if(figure == selectedFigure) {
			gc.setStroke(Color.RED);
		} else {
			gc.setStroke(lineColor);
		}
		gc.setFill(figureColorMap.get(figure));
		figure.draw();
	}


	private boolean figureBelongs(Figure figure, Point eventPoint) {
		if(figure != null){
			return figure.contains(eventPoint);
		}
		return false;
	}

	private void updateSelectedFiguresLabel(boolean found){
		//si no hay figuras en el array de seleccionadas significa
		//que fueron deseleccionadas o que no fueron encontradas
		if(canvasState.SelectedFiguresIsEmpty()){
			statusPane.updateStatus(found? UNSELECTED_STRING: NO_FIGURES_FOUND_STRING);
			return;
		}
		StringBuilder label= new StringBuilder(START_SELECTION_STRING);
		for(Figure figure: canvasState.SelectedFigures()){
			label.append(figure.toString());
		}
		statusPane.updateStatus(label.toString());
	}

	private boolean onCanvas(Point eventPoint){
		return eventPoint.getX()>=0 && eventPoint.getY()>=0 && eventPoint.getX()<=CANVAS_WIDTH && eventPoint.getY()<=CANVAS_HEIGHT;
	}

}
