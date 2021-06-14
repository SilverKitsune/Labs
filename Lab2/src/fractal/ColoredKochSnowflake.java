package fractal;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class ColoredKochSnowflake extends Fractal implements SelfAffinity {
    private Point2D centre;
    private double d;
    private int m;
    private int count;
    private int n;
    private Color beginColor;
    private Color endColor;
    private Color currentColor;
    private int elementCount;

    public ColoredKochSnowflake(int width, int height, int n, int m, int count, Color beginColor, Color endColor) {
        super(width, height);
        this.count = count;
        centre = new Point2D.Double(width / 2.0, height / 2.0);
        d = width / 3.0;
        this.beginColor = beginColor;
        this.endColor = endColor;
        currentColor = beginColor;
        this.n = n;
        this.m = m;
    }

    @Override
    public void draw(double d) {
        elementCount = 0;
        Point2D[] vs = new Point2D[m];
        for (int i = 0; i < m; ++i) {
            vs[i] = new Point2D.Double(
                    centre.getX() + d * Math.cos(2 * Math.PI / m * i),
                    centre.getY() - d * Math.sin(2 * Math.PI / m * i));
        }
        /*Рисуем многоугольник*/
        Path2D path = new Path2D.Double();
        path.moveTo(vs[0].getX(), vs[0].getY());
        for (int i = 0; i < m; ++i) {
            path.lineTo(vs[(i + 1) % m].getX(), vs[(i + 1) % m].getY());
        }
        path.closePath(); //закрытие многоугольника
        graph.setColor(currentColor);
        graph.fill(path);
        for (int i = 0; i < m; ++i) {
            drawKochCurve(vs[(i + 1) % m], vs[i], n, currentColor);
        }
    }

    @Override
    public void drawMegaFractal() {
        for (int i = 0; i < count; ++i) {
            currentColor = new Color(
                    beginColor.getRed() + (endColor.getRed() - beginColor.getRed()) * i / count,
                    beginColor.getGreen() + (endColor.getGreen() - beginColor.getGreen()) * i / count,
                    beginColor.getBlue() + (endColor.getBlue() - beginColor.getBlue()) * i / count);
            draw(d * (count - i) / count);
        }
        System.out.println("Количество элементов в снежинке = " + elementCount);
    }

    private void drawKochCurve(Point2D p, Point2D q, int n, Color color) {
        if (n == 0) {
            graph.setColor(Color.BLACK);
            graph.draw(new Line2D.Double(p, q));
            return;
        }

        Point2D r = new Point2D.Double(
                (2 * p.getX() + q.getX()) / 3,
                (2 * p.getY() + q.getY()) / 3);
        Point2D s = new Point2D.Double(
                (p.getX() + q.getX()) / 2 -
                        (p.getY() - q.getY()) * Math.sqrt(3) / 6,
                ((p.getY() + q.getY()) / 2 +
                        (p.getX() - q.getX()) * Math.sqrt(3) / 6));
        Point2D t = new Point2D.Double(
                (p.getX() + 2 * q.getX()) / 3,
                (p.getY() + 2 * q.getY()) / 3);

        Path2D path = new Path2D.Double();
        path.moveTo(r.getX(), r.getY());
        path.lineTo(s.getX(), s.getY());
        path.lineTo(t.getX(), t.getY());
        path.lineTo(r.getX(), r.getY());
        path.closePath();
        graph.setColor(color);
        graph.fill(path);

        elementCount++;

        drawKochCurve(p, r, n - 1, color);
        drawKochCurve(r, s, n - 1, color);
        drawKochCurve(s, t, n - 1, color);
        drawKochCurve(t, q, n - 1, color);
    }

    public static void main(String[] args) {
        int WIDTH = 640, HEIGHT = 480;
        ColoredKochSnowflake snowflake = new ColoredKochSnowflake(WIDTH, HEIGHT, 2, 4, 3, Color.RED, Color.blue);
        snowflake.drawMegaFractal();
        JFrame frame = new JFrame();
        frame.addNotify();
        frame.setSize(frame.getInsets().left +
                        frame.getInsets().right + WIDTH,
                frame.getInsets().top +
                        frame.getInsets().bottom + HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Graphics2D G = (Graphics2D) g;
                if (ColoredKochSnowflake.image != null) {
                    G.drawImage(image, 0, 0, null);
                }
            }
        });
        frame.setVisible(true);
    }
}
