package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.group18.Main;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.GameAssetManager;

import java.awt.*;

public class ClockController {
    private Label TimeLabel;
    private static BitmapFont font;
    private TextureAtlas textureAtlas;
    private Sprite rawClock;
    private Sprite clockArrow;

    public ClockController(DateTime dateTime) {
        this.TimeLabel = new Label("");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
        textureAtlas = GameAssetManager.getGameAssetManager().getTextureAtlas();
        rawClock = textureAtlas.createSprite("Raw-Clock");
        clockArrow = textureAtlas.createSprite("Arrow");
        rawClock.setSize(rawClock.getWidth()*4, rawClock.getHeight()*4);
        clockArrow.setSize(clockArrow.getWidth()*4,clockArrow.getHeight()*4);
        rawClock.setPosition(Gdx.graphics.getWidth()-rawClock.getWidth()-10
            ,Gdx.graphics.getHeight()-rawClock.getHeight()-10);
        clockArrow.setPosition(rawClock.getX()+88-clockArrow.getWidth()/2,rawClock.getY()+156);
//        clockArrow.setOriginBasedPosition(rawClock.getX()+88,rawClock.getY()+156);
        clockArrow.setOrigin(clockArrow.getWidth()/2,0);

    }

    public void render(SpriteBatch batch, DateTime dateTime) {
        clockArrow.setRotation(-((dateTime.getHour() - 9)*12+180));
        rawClock.draw(batch);
        clockArrow.draw(batch);
        if (font == null) {
            font = new BitmapFont();
            font.setColor(Color.WHITE);
            font.getData().setScale(2f);
        }

        font.draw(batch,
            dateTime.getHour() + " : " + dateTime.getSeason(),
            (float) Gdx.graphics.getWidth() / 2,
            (float) Gdx.graphics.getHeight() / 2);
    }


    public void dispose() {
        if (font != null) {
            font.dispose();
            font = null;
        }
    }
}
