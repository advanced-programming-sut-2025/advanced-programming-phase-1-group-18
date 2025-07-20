package io.github.group18.Model;

public class BarnModel {
    private int x;
    private int y;
    private int width;
    private int height;

    public BarnModel(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 3;  // چون بارن 3x3 هست در کد شما
        this.height = 3;
    }

    // getters and setters
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

    public int getHeight() {
        return height;
    }

}
