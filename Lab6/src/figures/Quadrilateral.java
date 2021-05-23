package figures;

import static java.lang.Math.*;

public class Quadrilateral {

    protected double sideA;

    protected double sideB;

    protected double sideC;

    protected double sideD;

    /**
     * Угл между сторонами a и b в радианах
     */
    private double angle1 = 0.0;

    /**
     * Угл между сторонами c и d в радианах
     */
    private double angle2 = 0.0;

    public Quadrilateral(double a, double b, double c, double d, double angle1, double angle2) {
        sideA = a;
        sideB = b;
        sideC = c;
        sideD = d;
        this.angle1 = toRadians(angle1);
        this.angle2 = toRadians(angle2);
    }

    protected Quadrilateral(double a, double b) {
        sideA = a;
        sideB = b;
    }

    public Quadrilateral(double a, double b, double c, double d) {
        sideA = a;
        sideB = b;
        sideC = c;
        sideD = d;
    }

    public double area() {
        if(angle1 == 0.0 || angle2 == 0.0) {
          System.out.println("Error");
          return 0.0;
        }
        else {
          double p = perimeter() / 2;
          return sqrt((p - sideA)*(p - sideB)*(p-sideC)*(p-sideD) - sideA*sideB*sideC*sideD*(cos((angle1+angle2)/2))*(cos((angle1+angle2)/2)));
        }
    }

    public double perimeter() {
        return sideA + sideB + sideC + sideD;
    }
}