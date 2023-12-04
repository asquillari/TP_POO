package TP_POO.backend;

import TP_POO.backend.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class CanvasState {
    //ordenadas por cual aparece al frente:
    //con la primera figura siendo la del fondo y la ultima la que se encuentra más al frente

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

    public Iterable<Figure> figures() {
        return new ArrayList<>(figures);
    }
    //iterador por figuras
    private Iterator<Figure> reversedIterator(){
        return new Iterator<Figure>() {
            private int index=figures.size()-1;
            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public Figure next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                Figure toReturn= figures.get(index);
                index--;
                return toReturn;
            }
        };
    }

    public Figure getFigureOnPoint(Point eventPoint){
        Iterator<Figure> reversedIterator= reversedIterator();
        Figure figure;
        while(reversedIterator.hasNext()){
            figure=reversedIterator.next();
            if(figure.contains(eventPoint))
                return figure;
        }
        return null;
    }









    //devuelvo copia de las figuras que estan seleccionadas
    public List<Figure> SelectedFigures(){ return new ArrayList<>(selectedFigures);}
    public boolean SelectedFiguresIsEmpty(){
        return selectedFigures.isEmpty();
    }

    public void deleteSelected() {
        figures.removeAll(selectedFigures);
        resetSelectedFigures();
    }

    public void resetSelectedFigures() {
        selectedFigures = new ArrayList<>();
    }

    public boolean selectFigures(Figure selectionFigure) {
        boolean found = false;
        for (Figure figure : figures) {
            if(figure.isContained(selectionFigure)) {
                found = true;
                addSelectedFigures(figure);
            }
        }
        return found;
    }

    public boolean SelectAndUnselect(Point selectPoint){
        Iterator<Figure> reversedIterator= reversedIterator();
        Figure figure;
        while(reversedIterator.hasNext()){
            figure=reversedIterator.next();
            if(figure.contains(selectPoint)){

                if(selectedFigures.contains(figure))
                    selectedFigures.remove(figure);
                else
                    selectedFigures.add(figure);
                return true;
            }
        }
        return false;
    }

    public void addSelectedFigures(Figure figure){
        selectedFigures.add(figure);
    }

    public boolean belongsToASelectedFigure(Point eventPoint){
        for(Figure figure: selectedFigures){
            if(figure.contains(eventPoint))
                return true;
        }
        return false;
    }

    public void moveSelectedFigures(double diffX, double diffY) {
        for(Figure selectedFigure : selectedFigures)
            selectedFigure.move(diffX, diffY);
    }



}
