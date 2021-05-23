package figures;

public class Parallelogram extends Quadrilateral {

    public double height;

    public Parallelogram(double a, double b, double h) {
        super(a, b);
        height = h;
    }

    public double area() {
        return sideA * height;
    }

    public double perimeter() {
        return sideA * 2 + sideB * 2;
    }

}