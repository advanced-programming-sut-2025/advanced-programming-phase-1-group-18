package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.group18.Main;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.GameAssetManager;

import java.awt.*;

public class ClockController {
    private Label TimeLabel;
    private static BitmapFont font;

    public ClockController(DateTime dateTime) {
        this.TimeLabel = new Label("");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    public static void render(SpriteBatch batch, DateTime dateTime) {

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


    public static void dispose() {
        if (font != null) {
            font.dispose();
            font = null;
        }
    }
}
