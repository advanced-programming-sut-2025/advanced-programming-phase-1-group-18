package io.github.group18.Model;

public class PlayerPosition {
    double first;
    double second;
    int movingDirection;
    int state;
    float faintTimer;
    float eatingTimer;
    Buff foodBuff;

    public PlayerPosition(double first, double second, int movingDirection, int state, float faintTimer, float eatingTimer, Buff foodBuff) {
        this.first = first;
        this.second = second;
        this.movingDirection = movingDirection;
        this.state = state;
        this.faintTimer = faintTimer;
        this.eatingTimer = eatingTimer;
        this.foodBuff = foodBuff;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public int getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(int movingDirection) {
        this.movingDirection = movingDirection;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getFaintTimer() {
        return faintTimer;
    }

    public void setFaintTimer(float faintTimer) {
        this.faintTimer = faintTimer;
    }

    public float getEatingTimer() {
        return eatingTimer;
    }

    public void setEatingTimer(float eatingTimer) {
        this.eatingTimer = eatingTimer;
    }

    public Buff getFoodBuff() {
        return foodBuff;
    }

    public void setFoodBuff(Buff foodBuff) {
        this.foodBuff = foodBuff;
    }
}
