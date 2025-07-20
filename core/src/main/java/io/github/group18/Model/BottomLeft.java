package io.github.group18.Model;

public class BottomLeft {
    int x;
    int y;
    int width;
    int height;
    boolean balls;

    public BottomLeft(int x, int y, int width, int height,boolean balls) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.balls = balls;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isBalls() {
        return balls;
    }

    public void setBalls(boolean balls) {
        this.balls = balls;
    }
}
