package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.group18.Main;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.GameAssetManager;

import java.awt.*;

public class ClockController {
    private DateTime dateTime;
    private Label TimeLabel;
    private BitmapFont font;

    public ClockController(DateTime dateTime) {
        this.dateTime = dateTime;
        this.TimeLabel = new Label("");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2f);
    }

    public void update() {
//        Main.getBatch().begin();
        font.draw(Main.getBatch(),dateTime.getHour()+" : "+dateTime.getSeason(),
            (float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2);
//        Main.getBatch().end();
    }
}
