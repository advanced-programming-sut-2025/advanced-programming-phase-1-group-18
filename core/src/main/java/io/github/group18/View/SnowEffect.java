package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class SnowEffect {
    private static class Flake {
        float x, y, speed;
        float scale;
    }

    private Texture flakeTexture;
    private ArrayList<Flake> flakes;
    private boolean enabled = false;

    private int screenWidth, screenHeight;

    public SnowEffect(String texturePath, int flakeCount) {
        flakeTexture = new Texture(Gdx.files.internal(texturePath));
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        flakes = new ArrayList<>();
        for (int i = 0; i < flakeCount; i++) {
            Flake f = new Flake();
            f.x = MathUtils.random(0, screenWidth);
            f.y = MathUtils.random(0, screenHeight);
            f.speed = MathUtils.random(40f, 120f);
            f.scale = MathUtils.random(0.005f, 0.015f);
            flakes.add(f);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void update(float delta) {
        if (!enabled) return;
        for (Flake f : flakes) {
            f.y -= f.speed * delta;
            f.x += MathUtils.sin(f.y * 0.05f) * 20f * delta;

            if (f.y < -flakeTexture.getHeight() * f.scale) {
                f.y = screenHeight + MathUtils.random(0, 100);
                f.x = MathUtils.random(0, screenWidth);
            }
            if (f.x < -flakeTexture.getWidth() * f.scale) f.x = screenWidth;
            else if (f.x > screenWidth) f.x = 0;
        }
    }

    public void render(Batch batch) {
        if (!enabled) return;
        batch.begin();
        for (Flake f : flakes) {
            batch.draw(
                flakeTexture,
                f.x,
                f.y,
                flakeTexture.getWidth() * f.scale,
                flakeTexture.getHeight() * f.scale
            );
        }
        batch.end();
    }

    public void dispose() {
        flakeTexture.dispose();
    }
}
