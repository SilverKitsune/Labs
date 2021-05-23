package figures;

public class Rhombus extends Quadrilateral {

    double height;

    public Rhombus(double a, double h) {
        super(a, a);
        height = h;
    }

    public double area() {
        return sideA * height;
    }

    public double perimeter() {
        return sideA * 4;
    }

}