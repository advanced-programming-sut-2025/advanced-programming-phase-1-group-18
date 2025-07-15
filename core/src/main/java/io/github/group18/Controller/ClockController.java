package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.group18.Model.App;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;

import java.awt.*;

public class ClockController {
    private Label TimeLabel;
    private static BitmapFont hour;
    private static BitmapFont day;
    private static BitmapFont gold;
    private TextureAtlas textureAtlas;
    private Sprite rawClock;
    private Sprite clockArrow;
    private Sprite Spring;
    private Sprite Summer;
    private Sprite Fall;
    private Sprite Winter;
    private Sprite Sunny;
    private Sprite Rain;
    private Sprite Snow;
    private Sprite Storm;
    int scale;

    public ClockController(DateTime dateTime) {
        this.TimeLabel = new Label("");
        scale = 4;
        hour = new BitmapFont();
        hour.setColor(Color.BLACK);
        hour.getData().setScale(2f);
        day = new BitmapFont();
        day.setColor(Color.BLACK);
        day.getData().setScale(2f);
        gold = new BitmapFont();
        gold.setColor(Color.BLACK);
        gold.getData().setScale(2f);

        textureAtlas = GameAssetManager.getGameAssetManager().getTextureAtlas();
        rawClock = textureAtlas.createSprite("Raw-Clock");
        clockArrow = textureAtlas.createSprite("Arrow");

        Spring = textureAtlas.createSprite("Spring");
        Summer = textureAtlas.createSprite("Summer");
        Fall = textureAtlas.createSprite("Fall");
        Winter = textureAtlas.createSprite("Winter");

        Sunny = textureAtlas.createSprite("Sunny");
        Rain = textureAtlas.createSprite("Rain");
        Snow = textureAtlas.createSprite("Snow");
        Storm = textureAtlas.createSprite("Storm");

        rawClock.setSize(rawClock.getWidth()*scale, rawClock.getHeight()*scale);
        clockArrow.setSize(clockArrow.getWidth()*scale,clockArrow.getHeight()*scale);
        Spring.setSize(Spring.getWidth()*scale, Spring.getHeight()*scale);
        Summer.setSize(Summer.getWidth()*scale, Summer.getHeight()*scale);
        Fall.setSize(Fall.getWidth()*scale, Fall.getHeight()*scale);
        Winter.setSize(Winter.getWidth()*scale, Winter.getHeight()*scale);
        Sunny.setSize(Sunny.getWidth()*scale, Sunny.getHeight()*scale);
        Rain.setSize(Rain.getWidth()*scale, Rain.getHeight()*scale);
        Snow.setSize(Snow.getWidth()*scale, Snow.getHeight()*scale);
        Storm.setSize(Storm.getWidth()*scale, Storm.getHeight()*scale);

        rawClock.setPosition(Gdx.graphics.getWidth()-rawClock.getWidth()-10
            ,Gdx.graphics.getHeight()-rawClock.getHeight()-10);
        clockArrow.setPosition(rawClock.getX()+88-clockArrow.getWidth()/2,rawClock.getY()+156);
//        clockArrow.setOriginBasedPosition(rawClock.getX()+88,rawClock.getY()+156);
        clockArrow.setOrigin(clockArrow.getWidth()/2,0);

        Sunny.setPosition(rawClock.getX()+29*scale,rawClock.getY()+35*scale);
        Rain.setPosition(rawClock.getX()+29*scale,rawClock.getY()+35*scale);
        Snow.setPosition(rawClock.getX()+29*scale,rawClock.getY()+35*scale);
        Storm.setPosition(rawClock.getX()+29*scale,rawClock.getY()+35*scale);

        Summer.setPosition(rawClock.getX()+53*scale,rawClock.getY()+35*scale);
        Fall.setPosition(rawClock.getX()+53*scale,rawClock.getY()+35*scale);
        Winter.setPosition(rawClock.getX()+53*scale,rawClock.getY()+35*scale);
        Spring.setPosition(rawClock.getX()+53*scale,rawClock.getY()+35*scale);


    }

    public void render(SpriteBatch batch, DateTime dateTime) {
        clockArrow.setRotation(-((dateTime.getHour() - 9)*12+180));
        rawClock.draw(batch);
        clockArrow.draw(batch);
        switch (dateTime.getSeason()){
            case "Spring":
                Spring.draw(batch);
                break;
            case "Summer":
                Summer.draw(batch);
                break;
            case "Fall":
                Fall.draw(batch);
                break;
            case "Winter":
                Winter.draw(batch);
                break;
        }
        switch (App.getCurrentGame().getCurrentWeather()){
            case RAIN -> Rain.draw(batch);
            case SNOW -> Snow.draw(batch);
            case SUNNY -> Sunny.draw(batch);
            case STORM -> Storm.draw(batch);
        }
        if (hour == null) {
            hour = new BitmapFont();
            hour.setColor(Color.BLACK);
            hour.getData().setScale(2f);
        }
        if (day == null) {
            day = new BitmapFont();
            day.setColor(Color.BLACK);
            day.getData().setScale(2f);
        }
        if (gold == null) {
            gold = new BitmapFont();
            gold.setColor(Color.BLACK);
            gold.getData().setScale(2f);
        }

        GameMenuController menuController = App.getGameMenuController();
        String dayOfWeek = menuController.dayOfWeek().isSuccessful()? menuController.dayOfWeek().getMessage():"Monday";
        hour.draw(batch,
            dateTime.getHour() + " o'clock",
            rawClock.getX()+27*scale,
            rawClock.getY()+23*scale+hour.getLineHeight());
        day.draw(batch, dayOfWeek+". "+dateTime.getDay(),
            rawClock.getX()+27*scale, rawClock.getY()+45*scale+hour.getLineHeight());
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        gold.draw(batch,String.valueOf(currentPlayer.getGold()),
            rawClock.getX()+17*scale, rawClock.getY()+3*scale+gold.getLineHeight());
    }


    public void dispose() {
        if (hour != null) {
            hour.dispose();
            hour = null;
        }
    }
}
