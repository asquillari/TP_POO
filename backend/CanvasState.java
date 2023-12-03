package TP_POO.backend;

import TP_POO.backend.model.Figure;

import java.util.ArrayList;
import java.util.List;

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

    //devuelvo copia de las figuras que estan seleccionadas
    public List<Figure> SelectedFigures(){ return new ArrayList<>(selectedFigures);}
    public boolean SelectedFiguresIsEmpty(){
        return selectedFigures.isEmpty();
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(figures);
    }

    public void moveSelectedFigures(double diffX, double diffY) {
        for(Figure selectedFigure : selectedFigures)
            selectedFigure.move(diffX, diffY);
    }

}
