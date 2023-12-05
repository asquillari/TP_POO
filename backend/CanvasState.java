package TP_POO.backend;

import TP_POO.backend.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class CanvasState {

    private final List<List<Figure>> figures = new ArrayList<>();
    private List<List<Figure>> selectedFigures= new ArrayList<>();

    //agrego una lista con una sola figura a nuestra lista de figuras
    public void addFigure(Figure figure) {
        List<Figure> newList = new ArrayList<>();
        newList.add(figure);
        figures.add(newList);
    }

    //agrego una lista de figuras a la lista figures para considerarlas un grupo
    public void addFigure(List<Figure> list){
        figures.add(list);
    }


    //devuelvo copia de las figuras que estan seleccionadas
    public boolean SelectedFiguresIsEmpty(){
        return selectedFigures.isEmpty();
    }

    public Iterable<Figure> figures() {
        return iterate(figures);
    }
    public List<Figure> selectedFigures() {
        return iterate(selectedFigures);
    }

    private List<Figure> iterate(List<List<Figure>> list){
        List<Figure> toReturn = new ArrayList<>();
        for(List<Figure> figureArr : list){
            toReturn.addAll(figureArr);
        }
        return toReturn;
    }

    public boolean selectFigures(Figure selectionFigure) {
        boolean found = false;
        for (List<Figure> figuresArr : figures) {
            if (isAllContained(figuresArr, selectionFigure)) {
                found = true;
                List<Figure> toAdd = new ArrayList<>(figuresArr);
                selectedFigures.add(toAdd);
            }
        }
        return found;
    }

    private boolean isAllContained(List<Figure> figuresArr, Figure selectionFigure) {
        for (Figure figure : figuresArr){
            if(!figure.isContained(selectionFigure)){
                return false;
            }
        }
        return true;
    }

    public void resetSelectedFigures() {
        selectedFigures = new ArrayList<>();
    }

    public void moveSelectedFigures(double diffX, double diffY) {
        for(List<Figure> selectedFigures : selectedFigures) {
            for(Figure selectedFigure : selectedFigures) {
                selectedFigure.move(diffX, diffY);
            }
        }
    }

    public void addSelectedFigure(Figure selectedFigure) {
        selectedFigures.clear();
        List<Figure> toAdd = new ArrayList<>();
        for(List<Figure> list: figures){
            if(list.contains(selectedFigure))
                toAdd.addAll(list);
        }
        selectedFigures.add(toAdd);
    }


    public void deleteSelected() {
        figures.removeAll(selectedFigures);
        resetSelectedFigures();
    }
    public boolean belongsToASelectedFigure(Point eventPoint) {
        for(List<Figure> selectedFigures : selectedFigures) {
            for (Figure selectedFigure : selectedFigures) {
                if (selectedFigure.contains(eventPoint))
                    return true;
            }
        }
        return false;
    }

    public void deleteFigure(Figure figure){
        figures.removeIf(list -> list.contains(figure));
    }
}

