package TP_POO.backend;

import TP_POO.backend.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class CanvasState {

    private final List<Figure> figures = new ArrayList<>();
    private List<Figure> selectedFigures= new ArrayList<>();

    //agrego figura
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    //elimino figura
    public void deleteFigure(Figure figure) {
        figures.remove(figure);
    }

    //devuelvo copia de las figuras que estan seleccionadas
    public boolean SelectedFiguresIsEmpty(){
        return selectedFigures.isEmpty();
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(figures);
    }
    public List<Figure> selectedFigures() {
        return new ArrayList<>(selectedFigures);
    }

    public boolean selectFigures(Figure selectionFigure) {
        boolean found = false;
        for (Figure figure : figures) {
            if(figure.isContained(selectionFigure)) {
                found = true;
                selectedFigures.add(figure);
            }
        }
        return found;
    }

    public void resetSelectedFigures() {
        selectedFigures = new ArrayList<>();
    }

    public void moveSelectedFigures(double diffX, double diffY) {
        for(Figure selectedFigure : selectedFigures)
            selectedFigure.move(diffX, diffY);
    }

    public void addSelectedFigure(Figure selectedFigure) {
        selectedFigures.clear();
        selectedFigures.add(selectedFigure);
    }

    public void deleteSelected() {
        figures.removeAll(selectedFigures);
        resetSelectedFigures();
    }
}

