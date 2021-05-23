package figures;

public class Square extends Quadrilateral {

    public Square(double a) {
        super(a, a);
    }

    public double area() {
        return sideA * sideA;
    }

    public double perimeter() {
        return sideA * 4;
    }

}