package figures;

public class Rectangle extends Quadrilateral {

    public Rectangle(double a, double b) {
        super(a, b);
    }

    public double area() {
        return sideA * sideB;
    }

    public double perimeter() {
        return sideA * 2 + sideB * 2;
    }

}