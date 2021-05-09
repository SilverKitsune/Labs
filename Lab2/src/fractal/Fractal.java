package fractal;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

abstract class Fractal {
    final ImageSize imageSize;
    static protected BufferedImage image;
    static protected Graphics2D graph;

    public abstract void draw(double d);

    public Fractal(int width, int height) {
        imageSize = new ImageSize(width, height);
        image = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB);
        graph = image.createGraphics();
        graph.setColor(Color.WHITE);
        graph.fill(new Rectangle2D.Double(0, 0, imageSize.width, imageSize.height));
        graph.setColor(Color.BLACK);
    }
}

class ImageSize {
    int width, height;

    ImageSize(int w, int h) {
        width = w;
        height = h;
    }
}

