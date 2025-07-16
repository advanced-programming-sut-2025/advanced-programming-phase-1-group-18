package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class LightningEffect {
    private float timer = 0;
    private int stage = 0;
    private boolean active = false;

    public void start() {
        active = true;
        timer = 0;
        stage = 0;
    }

    public void update(float delta) {
        if (!active) return;

        timer += delta;

        if (stage == 0 && timer >= 0.1f) { // اولین flash کوتاه
            timer = 0;
            stage = 1;
        } else if (stage == 1 && timer >= 0.1f) { // سیاه شدن سریع
            timer = 0;
            stage = 2;
        } else if (stage == 2 && timer >= 0.1f) { // flash دوم
            timer = 0;
            stage = 3;
        } else if (stage == 3 && timer >= 0.1f) { // پایان افکت
            active = false;

        }
    }

    public void render(SpriteBatch batch) {
        if (!active) return;


        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        if (stage == 0 || stage == 2) {
            shapeRenderer.setColor(1, 1, 1, 1); // سفید کامل
        } else if (stage == 1) {
            shapeRenderer.setColor(0, 0, 0, 1); // سیاه (یا حالت عادی)
        }

        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

        shapeRenderer.dispose();

    }

    public boolean isActive() {
        return active;
    }
}
