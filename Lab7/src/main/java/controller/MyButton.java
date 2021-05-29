package controller;

public class MyButton {

    private String name;
    private int x;
    private int y;
    private int h;
    private int w;
    private boolean clicked;

    public MyButton(String name, int x, int y, int h, int w) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
        clicked = false;
    }


    public String getName() {
        return name;
    }

    public int getStartX() {
        return x;
    }

    public int getStartY() {
        return y;
    }

    public int getEndX() {
        return x + w;
    }

    public int getEndY() {
        return y + h;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean getClicked() {
        return clicked;
    }

    public boolean isClicked(double event_x, double event_y) {
        return (event_x > x && event_x < x + w) && (event_y > y && event_y < y + h);
    }
}
