package TP_POO.backend.model;


public abstract class Rectangle extends Figure {

    protected Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, BackColor fillColor, BackColor lineColor, double lineWidth, boolean shadow, boolean gradient,boolean arch) {
        super(lineColor, fillColor, lineWidth, shadow, gradient, arch);
        setPoints(topLeft, bottomRight);
    }

    private void setPoints(Point topLeft, Point bottomRight){
        this.bottomRight=bottomRight;
        this.topLeft=topLeft;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public void move(double deltax, double deltay) {
        topLeft.move(deltax, deltay);
        bottomRight.move(deltax, deltay);
    }

    @Override
    public void rotate() {
        // Obtener las coordenadas de los puntos
        double centerX = (topLeft.getX() + bottomRight.getX()) / 2;
        double centerY = (topLeft.getY() + bottomRight.getY()) / 2;

        // Calcular las nuevas coordenadas después de una rotación de 90 grados
        Point newTopLeft = new Point(centerX - (bottomRight.getY() - centerY), centerY - (centerX - topLeft.getX()));
        Point newBottomRight = new Point(centerX + (centerY - topLeft.getY()), centerY + (bottomRight.getX() - centerX));

        // Actualizar los puntos del rectángulo con las nuevas coordenadas
        setPoints(newTopLeft, newBottomRight);
    }

    @Override
    public void flipH() {
        double distance= distance(bottomRight.getX(),topLeft.getX());
        setPoints(new Point(topLeft.getX()+distance, topLeft.getY()), new Point(distance + bottomRight.getX(), bottomRight.getY()));
    }

    @Override
    public void flipV() {
        double distance= distance(bottomRight.getY(), topLeft.getY());
        setPoints(new Point(topLeft.getX(),  distance + topLeft.getY()), new Point(bottomRight.getX(),  distance + bottomRight.getY()));
    }

    @Override
    public void resizeP() {
       resize(INCREASE_FACTOR, NEGATIVE);
    }

    @Override
    public void resizeM() {
        resize(DECREASE_FACTOR,POSITIVE);
    }

    private void resize(double factor, int sign){
        double ogWidth = distance(bottomRight.getX(),topLeft.getX());
        double ogHeight = distance(bottomRight.getY(), topLeft.getY());
        double newWidth = ogWidth * factor;
        double newHeight = ogHeight * factor;
        double offsetX = distance(newWidth, ogWidth);
        double offsetY = distance(newHeight, ogHeight);
        setPoints(new Point(topLeft.getX()+ (sign * offsetX), topLeft.getY()+ (sign* offsetY)), new Point(bottomRight.getX()-(sign*offsetX), bottomRight.getY()-(sign*offsetY)));
    }

    @Override
    public boolean contains(Point point) {
        return point.getX() > getTopLeft().getX() && point.getX() < getBottomRight().getX() &&
                point.getY() > getTopLeft().getY() && point.getY() < getBottomRight().getY();
    }

    @Override
    public boolean isContained(Figure figure) {
        return figure.contains(topLeft) && figure.contains(bottomRight);
    }

    public double distance(double p1, double p2){
        return Math.abs(p1 - p2);
    }

}
