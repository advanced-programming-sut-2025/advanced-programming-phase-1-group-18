package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.Random;

public class FishingMiniGame {
    private float barX;
    private float greenBarY;
    private float orangeBarY;
    private float progress =10;

    private static final float BAR_WIDTH = 40;
    private static final float BAR_HEIGHT = 75;
    private static final float GREEN_BAR_HEIGHT = 120;

    private static final float PROGRESS_BAR_WIDTH = 10;
    private static final float PROGRESS_BAR_HEIGHT = 200;

    private static final float SCREEN_WIDTH = 140;
    private static final float SCREEN_HEIGHT = 480;

    private float fishSpeed = 100f;
    private float fishTime = 0f;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture fishTexture;
    private Texture fishTextureRare;
    private Texture fishTextureNormal;
    private Random random;

    private boolean perfectCatch;


    enum FishType {
        MIXED, SMOOTH, SINKER, FLOATER, DART
    }

    private FishType fishType;
    private String fishName;


    public FishingMiniGame() {
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        fishTextureNormal = new Texture("fish.png");
        fishTextureRare = new Texture("crownfish.png");
        random = new Random();
        reset();
    }

    public void reset() {
        barX = SCREEN_WIDTH / 2f - BAR_WIDTH / 2f;
        greenBarY = SCREEN_HEIGHT / 2f - BAR_HEIGHT / 2f;
        orangeBarY = SCREEN_HEIGHT / 2f - BAR_HEIGHT / 2f;
        progress = 10;
        fishSpeed = 50f;
        fishTime = 0f;
        fishType = FishType.values()[random.nextInt(FishType.values().length)];

        perfectCatch = true;

        fishTexture = (random.nextFloat() < 1f / 2f) ? fishTextureRare : fishTextureNormal;

        switch (fishType) {
            case MIXED: fishName = "Mixed"; break;
            case SMOOTH: fishName = "Smooth"; break;
            case SINKER: fishName = "Sinker"; break;
            case FLOATER: fishName = "Floater"; break;
            case DART: fishName = "Dart"; break;
        }
    }

    public void update(float delta) {
        fishTime += delta;

        float minY = 0;
        float maxY = SCREEN_HEIGHT - BAR_HEIGHT;

        switch (fishType) {
            case SMOOTH:

                float sineValue = (float) Math.sin(fishTime * 2); // -1 تا 1
                orangeBarY = minY + ((sineValue + 1) / 2f) * (maxY - minY);
                break;
            case DART:
                if ((int) (fishTime * 5) % 2 == 0) {
                    orangeBarY += fishSpeed * delta * 5;
                } else {
                    orangeBarY -= fishSpeed * delta * 5;
                }
                orangeBarY = MathUtils.clamp(orangeBarY, minY, maxY);
                break;
            case MIXED:
                if ((int) (fishTime * 2) % 2 == 0) {
                    orangeBarY += fishSpeed * delta;
                } else {
                    orangeBarY -= fishSpeed * delta;
                }
                orangeBarY = MathUtils.clamp(orangeBarY, minY, maxY);
                break;

            case SINKER:
                orangeBarY -= fishSpeed * delta * 1.5f;
                if (orangeBarY < minY) orangeBarY = maxY;
                break;

            case FLOATER:
                orangeBarY += fishSpeed * delta * 1.5f;
                if (orangeBarY > maxY) orangeBarY = minY;
                break;
        }

        float moveAmount = 200 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) greenBarY += moveAmount;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) greenBarY -= moveAmount;

        greenBarY = MathUtils.clamp(greenBarY, 0, SCREEN_HEIGHT - GREEN_BAR_HEIGHT);


        boolean overlap = (greenBarY + BAR_HEIGHT > orangeBarY) && (greenBarY < orangeBarY + BAR_HEIGHT);

        if (!overlap) {
            perfectCatch = false;
        }

        if (overlap) progress += 40 * delta;
        else progress -= 20 * delta;

        progress = MathUtils.clamp(progress, 0, 100);
    }

    public void render() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        shapeRenderer.setColor(new Color(0, 0.4f, 0.8f, 1));
        shapeRenderer.rect(barX, 0, BAR_WIDTH, SCREEN_HEIGHT);


        //
 //
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(barX, greenBarY, BAR_WIDTH, GREEN_BAR_HEIGHT);

        Color progressColor = (progress < 35) ? Color.RED : Color.PINK;
        shapeRenderer.setColor(progressColor);
        float progressHeight = (SCREEN_HEIGHT - 20) * (progress / 100f);
        shapeRenderer.rect(barX + BAR_WIDTH + 5, 10, 10, progressHeight);

        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.draw(fishTexture, barX, orangeBarY, BAR_WIDTH, BAR_HEIGHT);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(3f);
        font.setColor(Color.WHITE);


        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, fishName);


        float x = (SCREEN_WIDTH - layout.width) / 2f;
        float y = (SCREEN_HEIGHT + layout.height) / 2f;

        font.draw(spriteBatch, layout, x, y);

        spriteBatch.end();
    }

    public boolean isSuccess() {
        return progress >= 100;
    }

    public boolean isFailed() {
        return progress <= 0;
    }

    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
        fishTexture.dispose();
    }

    public boolean isPerfectCatch() {
        return perfectCatch && isSuccess();
    }


}
