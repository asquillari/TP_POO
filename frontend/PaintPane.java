package TP_POO.frontend;

import TP_POO.backend.CanvasState;
import TP_POO.backend.model.*;
import TP_POO.frontend.buttons.FigureButton;
import TP_POO.frontend.model.DrawableRectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.*;

public class PaintPane extends BorderPane {

	private static final int CANVAS_WIDTH = 800;
	private static final int CANVAS_HEIGHT = 600;
	private static final Color LINE_COLOR = Color.BLACK;
	private static final int LINE_WIDTH = 1;

	// BackEnd
	private final CanvasState canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	Color lineColor = LINE_COLOR;

	private final Tools tools = new Tools();

	// Dibujar una figura
	private Point startPoint;

	private Figure selector;

	// StatusBar
	private final StatusPane statusPane;

	//CheckBox
	private final CheckPointPane checkBoxes;
	private final LabelsPane labelsPane;

	public PaintPane(CanvasState canvasState, StatusPane statusPane, CheckPointPane checkBoxes, LabelsPane labelsPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		this.checkBoxes = checkBoxes;
		this.labelsPane = labelsPane;


		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null ) {
				return ;
			}
			//Si el cursor no termina en una de las figuras seleccionadas entonces deselecciona
			if (!canvasState.SelectedFiguresIsEmpty()) {
				if (!canvasState.belongsToASelectedFigure(endPoint)) {
					canvasState.resetSelectedFigures();
				}
				redrawCanvas();
				return;
			}

			if (selector == null)
				return;

			// Cuando se suelta el mouse si la figura dibujada no es el rectangulo del selector se almacena en el array
			if (tools.isSelectionButtonSelected() && canvasState.SelectedFiguresIsEmpty()) {
				if (!canvasState.selectFigures(selector)) {
					canvasState.resetSelectedFigures();
				}
				if(canvasState.selectedFigures().size() > 1 && !canvasState.figuresAreGrouped()) {
					tools.setButtonsDisable(true);
				}

				redrawCanvas();
				//selector = null;
				return;
			}

			// Una vez suelto el mouse, la figura que creo el usuario se agrega al canvasState
			// Si el botón de selección está activo, 'currentFigure' es el rectángulo de selección, entonces no se agrega
			if(!tools.isSelectionButtonSelected()) {
				canvasState.addFigure(selector);
				selector = null;
			}

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

			if(tools.isSelectionButtonSelected() && event.isStillSincePress()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");

				for (Figure figure : canvasState.figures()) {
					if(figureBelongs(figure, eventPoint)) {
						found = true;
						canvasState.addSelectedFigure(figure);
						label.append(figure.toString());
						checkBoxes.setSelected(figure.hasShadow(), figure.hasGradient(), figure.hasArched());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();

			}

			tools.setTextArea(canvasState.getLabels());
			if(canvasState.selectedFigures().size() == 1 || canvasState.figuresAreGrouped()){
				tools.setButtonsDisable(false);
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
			if (!isEndPointValid(eventPoint)) {
				redrawCanvas();
				selector = null;
				return;
			}
			if (tools.isSelectionButtonSelected() && canvasState.SelectedFiguresIsEmpty()) {
				selector = new DrawableRectangle(startPoint, eventPoint, gc, toBackendColor(Color.TRANSPARENT), toBackendColor(Color.RED), LINE_WIDTH, false, false, false);
			}else{
				selector = createFigure(startPoint,eventPoint);
			}
			if(selector != null) {
				redrawCanvas();
				drawFigure(selector, selector.hasShadow(), selector.hasGradient(), selector.hasArched(), true);
			}

		});

		tools.deleteAction(event -> {
			if(isSelectedActionPossible()){
				canvasState.deleteSelected();
				redrawCanvas();
			}
		});

		tools.groupAction(event -> {
			if(selector != null){
				Set<Figure> toAdd = new HashSet<>();
				for(Figure figure : canvasState.selectedFigures()){
					toAdd.add(figure);
					canvasState.deleteFigure(figure);
				}
				canvasState.addFigure(toAdd);
			}
			selector= null;
			canvasState.resetSelectedFigures();
			redrawCanvas();
		});

		tools.degroupAction(event ->{
				Set<Figure> degroupSet = canvasState.selectedFigures();
				canvasState.deleteSelected();
				for (Figure figure : degroupSet) {
					canvasState.addFigure(figure);
				}
				selector= null;
				canvasState.resetSelectedFigures();
				redrawCanvas();
		});

		tools.saveAction(event -> {
			//separamos las labels entre espacios o saltos de linea
			String[] labels = tools.getText().split("[\\n\\s]+");
			//si no escribieron etiquetas no hace nada
			if(labels.length != 0){
				canvasState.addByLabel(labels);
			}
		});

		checkBoxes.shadowAction(event -> {canvasState.setSelectedFiguresShadow(checkBoxes.isShadowSelected()); redrawCanvas();});
		checkBoxes.gradientAction(event -> {canvasState.setSelectedFiguresGradient(checkBoxes.isGradientSelected()); redrawCanvas();});
		checkBoxes.archAction(event -> {canvasState.setSelectedFiguresArched(checkBoxes.isArchSelected()); redrawCanvas();});

		tools.rotateAction(event -> {canvasState.rotateSelected(); redrawCanvas();});
		tools.flipHAction(event->{canvasState.flipHSelected(); redrawCanvas();});
		tools.flipVAction(event->{canvasState.flipVSelected(); redrawCanvas();});
		tools.resizePAction(event->{canvasState.resizePSelected(); redrawCanvas();});
		tools.resizeMAction(event->{canvasState.resizeMSelected(); redrawCanvas();});

		labelsPane.onlyAction(event -> {redrawCanvas();});
		labelsPane.allAction(event -> {redrawCanvas();});

		setLeft(tools);
		setRight(canvas);

		// Decidimos que al apretar los botones de figuras se deseleccionen todas las figuras
		tools.figureButtonAction(event -> {canvasState.resetSelectedFigures(); redrawCanvas();});
	}

	// metodos auxiliares
	private Figure createFigure(Point startPoint, Point endPoint) {
		for (FigureButton figureButton : tools.getFigureButtons()) {
			if (figureButton.isSelected()) {
				return figureButton.create(startPoint, endPoint, gc, toBackendColor(tools.getFillColor()), toBackendColor(lineColor), LINE_WIDTH, checkBoxes.isShadowSelected(), checkBoxes.isGradientSelected(), checkBoxes.isArchSelected());
			}
		}
		return null;
	}

	private void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		if (labelsPane.onlyButtonIsSelected()){
			String firstWord = labelsPane.getText().split("\\s+", 2)[0];
			for (Figure figure : canvasState.figures()){
				if(figure != null){
					drawFigure(figure, figure.hasShadow(), figure.hasGradient(), figure.hasArched(), figure.hasLabel(firstWord));
				}
			}
		}else {
			for(Figure figure : canvasState.figures()) {
				if(figure != null){
					drawFigure(figure, figure.hasShadow(), figure.hasGradient(), figure.hasArched(), true);
				}
			}
		}
		if(!canvasState.SelectedFiguresIsEmpty()) {
			checkBoxes.updateBoxesStatus(canvasState.selectedFigures());
		}
	}

	private void drawFigure(Figure figure, boolean shadow, boolean gradient, boolean arch, boolean hasLabel) {
		if(hasLabel) {
			gc.setStroke(canvasState.selectedFigures().contains(figure) ? Color.RED : figure.getLineColor().toFxColor());
			figure.draw(shadow, gradient, arch);
		}
	}
	private boolean figureBelongs(Figure figure, Point eventPoint) {
		if(figure != null){
			return figure.contains(eventPoint);
		}
		return false;
	}
	private BackColor toBackendColor(Color fxColor) {
		return new BackColor(fxColor.getRed(), fxColor.getGreen(), fxColor.getBlue(), fxColor.getOpacity());
	}

	private boolean isSelectedActionPossible() {
        return !canvasState.SelectedFiguresIsEmpty();
    }
	private boolean isEndPointValid(Point endPoint) {
		return !(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY());
	}
}
