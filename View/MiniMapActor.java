package io.github.group18.View;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

public class MiniMapActor extends Actor {
    private final TextureRegion minimapTextureRegion;
    private final ShapeRenderer shapeRenderer;

    private float playerX, playerY;
    private int mapWidthTiles, mapHeightTiles;

    public MiniMapActor(TextureRegion minimapTextureRegion, int mapWidthTiles, int mapHeightTiles) {
        this.minimapTextureRegion = minimapTextureRegion;
        this.mapWidthTiles = mapWidthTiles;
        this.mapHeightTiles = mapHeightTiles;
        shapeRenderer = new ShapeRenderer();

        setSize(300, 300);
    }

    public void setPlayerPosition(float x, float y) {
        this.playerX = x;
        this.playerY = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.setColor(getColor());
        batch.draw(minimapTextureRegion, getX(), getY(), getWidth(), getHeight());

        batch.end();


        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);

        float dotX = getX() + (playerX / mapWidthTiles) * getWidth();
        float dotY = getY() + (playerY / mapHeightTiles) * getHeight();

        shapeRenderer.circle(dotX, dotY, 6);

        shapeRenderer.end();

        batch.begin();
    }
}
