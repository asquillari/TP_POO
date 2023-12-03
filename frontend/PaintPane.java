package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import TP_POO.backend.model.*;
import TP_POO.frontend.buttons.FigureButton;
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

	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = Color.BLACK;

	private final Tools tools = new Tools();

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	Figure selectedFigure;

	// StatusBar
	StatusPane statusPane;

	// Colores de relleno de cada figura
	Map<Figure, Color> figureColorMap = new HashMap<>();

	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;

		gc.setLineWidth(1);

		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}
			Figure newFigure = createFigure(startPoint, endPoint);
			figureColorMap.put(newFigure, tools.getFillColor());
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(Figure figure : canvasState.figures()) {
				if(figureBelongs(figure, eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(tools.isSelectionButtonSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (Figure figure : canvasState.figures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		canvas.setOnMouseDragged(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());

			//chequeo que haya figuras seleccionadas y que se este seleccionando el boton de seleccion
			if(tools.isSelectionButtonSelected() && !canvasState.SelectedFiguresIsEmpty()) {

				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;

				canvasState.moveSelectedFigures(diffX, diffY);
				redrawCanvas();
			}
		});

		tools.deleteAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		setLeft(tools);
		setRight(canvas);
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

	boolean figureBelongs(Figure figure, Point eventPoint) {
		if(figure != null){
			return figure.contains(eventPoint);
		}
		return false;
	}

}
