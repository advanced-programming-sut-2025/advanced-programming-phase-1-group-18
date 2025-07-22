// FishingMiniGame.java
package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class FishingMiniGame {

    private float barY;
    private float fishY;
    private float progress;
    private float fishSpeed;
    private float fishTime;

    private static final float BAR_HEIGHT = 60;
    private static final float FISH_SIZE = 30;
    private static final float SCREEN_HEIGHT = 480;
    private static final float SCREEN_WIDTH = 800;

    private FishType fishType;
    private Random random;

    public enum FishType {
        MIXED, SMOOTH, SINKER, FLOATER, DART
    }

    private ShapeRenderer shapeRenderer;

    public FishingMiniGame() {
        shapeRenderer = new ShapeRenderer();
        random = new Random();
        reset();
    }

    public void reset() {
        barY = SCREEN_HEIGHT / 2 - BAR_HEIGHT / 2;
        fishY = SCREEN_HEIGHT / 2;
        progress = 0;
        fishSpeed = 100f;
        fishType = FishType.values()[random.nextInt(FishType.values().length)];
        fishTime = 0;
    }

    public void update(float delta) {
        handleInput(delta);
        updateFish(delta);
        checkCollision(delta);
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // نوار سبز (بازیکن)
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(100, barY, 20, BAR_HEIGHT);

        // ماهی
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(100, fishY, 20, FISH_SIZE);

        // نوار پیشرفت
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.rect(200, 400, progress * 2, 20);

        shapeRenderer.end();
    }

    private void handleInput(float delta) {
        float moveAmount = 200 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            barY += moveAmount;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            barY -= moveAmount;
        }
        barY = Math.max(0, Math.min(SCREEN_HEIGHT - BAR_HEIGHT, barY));
    }

    private void updateFish(float delta) {
        fishTime += delta;

        switch (fishType) {
            case SMOOTH:
                fishY = (float) (SCREEN_HEIGHT / 2 + Math.sin(fishTime * 2) * 100);
                break;
            case DART:
                if ((int) (fishTime * 5) % 2 == 0) {
                    fishY += fishSpeed * delta * 5;
                } else {
                    fishY -= fishSpeed * delta * 5;
                }
                break;
            case MIXED:
                if ((int) (fishTime * 2) % 2 == 0) {
                    fishY += fishSpeed * delta;
                } else {
                    fishY -= fishSpeed * delta;
                }
                break;
            case SINKER:
                fishY -= fishSpeed * delta * 1.5f;
                break;
            case FLOATER:
                fishY += fishSpeed * delta * 1.5f;
                break;
        }

        if (fishY < 0) fishY = 0;
        if (fishY > SCREEN_HEIGHT - FISH_SIZE) fishY = SCREEN_HEIGHT - FISH_SIZE;
    }

    private void checkCollision(float delta) {
        boolean overlap = (fishY + FISH_SIZE > barY) && (fishY < barY + BAR_HEIGHT);

        if (overlap) {
            progress += 40 * delta;
        } else {
            progress -= 20 * delta;
        }
        progress = Math.max(0, Math.min(100, progress));
    }

    public boolean isFishingSuccess() {
        return progress >= 100;
    }

    public boolean isFishingFailed() {
        return progress <= 0;
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
