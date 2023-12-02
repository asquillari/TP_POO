package TP_POO.frontend;

import TP_POO.backend.model.Figure;
import TP_POO.backend.model.Point;

import java.lang.reflect.Method;

public class FigureTool {
    private Class<? extends  Figure> figureClass;
    public FigureTool(Class<? extends Figure> figureClass) {
        this.figureClass=figureClass;
    }

    public Figure createFigure(Point startPoint, Point endPoint){
        try {
            Method createMethod = figureClass.getMethod("create", Point.class, Point.class);
            return (Figure) createMethod.invoke(null, startPoint, endPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
