package figures;

public class Trapeze extends Quadrilateral {

    public double height;

    public Trapeze(double a, double b, double c, double d, double h) {
        super(a, b, c, d);
        height = h;
    }

    public Trapeze(double a, double b, double h) {
        super(a, b);
        height = h;
    }

    public double area() {
        return 0.5*(sideA+sideB)*height;
    }

    public double perimeter() {
        return super.perimeter();
    }

}