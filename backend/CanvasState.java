package TP_POO.backend;

import TP_POO.backend.interfaces.SetEffect;
import TP_POO.backend.model.*;

import java.util.*;


public class CanvasState {

    private final List<Set<Figure>> figures = new ArrayList<>();
    private List<Set<Figure>> selectedFigures= new ArrayList<>();
    private Map<String,Set<Figure>> figuresByLabel = new HashMap<>();

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

    // Métodos específicos para tus operaciones
    public void moveSelectedFigures(double diffX, double diffY) {
        setFigures(figure -> figure.move(diffX, diffY));
    }

    public void setSelectedFiguresShadow(boolean shadow) {
        setFigures(figure -> figure.setShadow(shadow));
    }

    public void setSelectedFiguresGradient(boolean gradient) {
        setFigures(figure -> figure.setGradient(gradient));
    }

    public void setSelectedFiguresArched(boolean arched) {
        setFigures(figure -> figure.setArched(arched));
    }

    private void setFigures(SetEffect setter){
        for (Set<Figure> selectedSet : selectedFigures) {
            for (Figure selectedFigure : selectedSet) {
                setter.setEffect(selectedFigure);
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

    // metodos de rotacion
    public void rotateSelected() {
        turnFigures(Figure::rotate);
    }
    public void flipHSelected() {
        turnFigures(Figure::flipH);
    }
    public void flipVSelected() {
        turnFigures(Figure::flipV);
    }
    public void resizePSelected() {
        turnFigures(Figure::resizeP);
    }
    public void resizeMSelected() {
        turnFigures(Figure::resizeM);
    }
    private void turnFigures(SetEffect setter){
        for(Figure figure: selectedFigures()){
            setter.setEffect(figure);
        }
    }

    public void addByLabel(String[] labels) {
        for(String label : labels) {
            for(Figure selected : selectedFigures()){
                //eliminamos las figuras viejas
                List<String> toDelete = selected.getOldLabels(labels);
                deleteOldLabels(toDelete, selected);
                //agregamos las nuevas
                figuresByLabel.putIfAbsent(label, new HashSet<>());
                figuresByLabel.get(label).add(selected);

            }
        }
    }

    private void deleteOldLabels(List<String> labels, Figure figure) {
        for(String label : labels){
            figuresByLabel.getOrDefault(label, new HashSet<>()).remove(figure);
            removeEmptyLabel(label);
        }
    }

    private void removeEmptyLabel(String label) {
        if (figuresByLabel.containsKey(label) && figuresByLabel.get(label).isEmpty()) {
            figuresByLabel.remove(label);
        }
    }

    public boolean figuresAreGrouped(){
        for (Set<Figure> figuresGroup : figures){
            if(figuresGroup.equals(selectedFigures())){
                return true;
            }
        }
        return false;
    }

    public List<String> getLabels() {
        List<String> toReturn = new ArrayList<>();
        for (Figure figure : selectedFigures()){
            toReturn.addAll(figure.getLabels());
        }
        return toReturn;
    }

    public Iterable<Figure> labelFigures(String firstWord) {
        return figuresByLabel.getOrDefault(firstWord, new HashSet<>());
    }
}

