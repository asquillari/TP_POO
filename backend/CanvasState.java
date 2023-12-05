package TP_POO.backend;

import TP_POO.backend.model.*;

import java.util.*;


public class CanvasState {

    private final List<Set<Figure>> figures = new ArrayList<>();
    private List<Set<Figure>> selectedFigures= new ArrayList<>();

    //agrego una lista con una sola figura a nuestra lista de figuras
    public void addFigure(Figure figure) {
        Set<Figure> newSet = new HashSet<>();
        newSet.add(figure);
        figures.add(newSet);
    }

    //agrego una lista de figuras a la lista figures para considerarlas un grupo
    public void addFigure(Set<Figure> set){
        figures.add(set);
    }


    //devuelvo copia de las figuras que estan seleccionadas
    public boolean SelectedFiguresIsEmpty(){
        return selectedFigures.isEmpty();
    }

    public Iterable<Figure> figures() {
        return iterate(figures);
    }
    public Set<Figure> selectedFigures() {
        return iterate(selectedFigures);
    }

    private Set<Figure> iterate(List<Set<Figure>> list){
        Set<Figure> toReturn = new HashSet<>();
        for(Set<Figure> figureArr : list){
            toReturn.addAll(figureArr);
        }
        return toReturn;
    }

    public boolean selectFigures(Figure selectionFigure) {
        boolean found = false;
        for (Set<Figure> figuresArr : figures) {
            if (isAllContained(figuresArr, selectionFigure)) {
                found = true;
                Set<Figure> toAdd = new HashSet<>(figuresArr);
                selectedFigures.add(toAdd);
            }
        }
        return found;
    }

    private boolean isAllContained(Set<Figure> figuresArr, Figure selectionFigure) {
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
        for(Set<Figure> selectedFigures : selectedFigures) {
            for(Figure selectedFigure : selectedFigures) {
                selectedFigure.move(diffX, diffY);
            }
        }
    }

    public void addSelectedFigure(Figure selectedFigure) {
        selectedFigures.clear();
        Set<Figure> toAdd = new HashSet<>();
        for(Set<Figure> set: figures){
            if(set.contains(selectedFigure))
                toAdd.addAll(set);
        }
        selectedFigures.add(toAdd);
    }


    public void deleteSelected() {
        figures.removeAll(selectedFigures);
        resetSelectedFigures();
    }
    public boolean belongsToASelectedFigure(Point eventPoint) {
        for(Set<Figure> selectedFigures : selectedFigures) {
            for (Figure selectedFigure : selectedFigures) {
                if (selectedFigure.contains(eventPoint))
                    return true;
            }
        }
        return false;
    }

    public void deleteFigure(Figure figure){
        figures.removeIf(set -> set.contains(figure));
    }
}

