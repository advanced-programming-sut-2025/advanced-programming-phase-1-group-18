package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;

public class RainEffect {
    private static class Drop {
        float x, y, speed;
        float width, height;
    }

    private Texture dropTexture;
    private ArrayList<Drop> drops;
    private boolean enabled = false;

    private int screenWidth, screenHeight;

    public RainEffect(String texturePath, int dropCount) {
        dropTexture = new Texture(Gdx.files.internal(texturePath));
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        drops = new ArrayList<>();
        for (int i = 0; i < dropCount; i++) {
            Drop d = new Drop();
            d.x = MathUtils.random(0, screenWidth);
            d.y = MathUtils.random(0, screenHeight);
            d.speed = MathUtils.random(200f, 500f);


            float scale = MathUtils.random(0.01f, 0.03f);
            d.width = dropTexture.getWidth() * scale;
            d.height = dropTexture.getHeight() * scale;

            drops.add(d);
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void update(float delta) {
        if (!enabled) return;
        for (Drop d : drops) {
            d.y -= d.speed * delta;
            if (d.y < -d.height) {
                d.y = screenHeight + MathUtils.random(0, 200);
                d.x = MathUtils.random(0, screenWidth);
            }
        }
    }

    public void render(Batch batch) {
        if (!enabled) return;
        batch.begin();
        for (Drop d : drops) {
            batch.draw(dropTexture, d.x, d.y, d.width, d.height);
        }
        batch.end();
    }

    public void dispose() {
        dropTexture.dispose();
    }
}
