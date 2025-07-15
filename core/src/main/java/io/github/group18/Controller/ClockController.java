package io.github.group18.Controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.group18.Model.App;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;

public class ClockController {
    private BitmapFont hour;
    private BitmapFont day;
    private BitmapFont gold;
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
    private int scale;

    public ClockController() {
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
        clockArrow.setSize(clockArrow.getWidth()*scale, clockArrow.getHeight()*scale);
        Spring.setSize(Spring.getWidth()*scale, Spring.getHeight()*scale);
        Summer.setSize(Summer.getWidth()*scale, Summer.getHeight()*scale);
        Fall.setSize(Fall.getWidth()*scale, Fall.getHeight()*scale);
        Winter.setSize(Winter.getWidth()*scale, Winter.getHeight()*scale);
        Sunny.setSize(Sunny.getWidth()*scale, Sunny.getHeight()*scale);
        Rain.setSize(Rain.getWidth()*scale, Rain.getHeight()*scale);
        Snow.setSize(Snow.getWidth()*scale, Snow.getHeight()*scale);
        Storm.setSize(Storm.getWidth()*scale, Storm.getHeight()*scale);

        clockArrow.setOrigin(clockArrow.getWidth()/2, 0);
    }

    public void render(SpriteBatch batch, DateTime dateTime, OrthographicCamera camera) {
        // Calculate position relative to camera viewport
        float clockX = camera.position.x + (camera.viewportWidth/2) - rawClock.getWidth() - 10;
        float clockY = camera.position.y + (camera.viewportHeight/2) - rawClock.getHeight() - 10;

        // Update all sprite positions
        rawClock.setPosition(clockX, clockY);
        clockArrow.setPosition(clockX + 88 - clockArrow.getWidth()/2, clockY + 156);
        Sunny.setPosition(clockX + 29*scale, clockY + 35*scale);
        Rain.setPosition(clockX + 29*scale, clockY + 35*scale);
        Snow.setPosition(clockX + 29*scale, clockY + 35*scale);
        Storm.setPosition(clockX + 29*scale, clockY + 35*scale);
        Summer.setPosition(clockX + 53*scale, clockY + 35*scale);
        Fall.setPosition(clockX + 53*scale, clockY + 35*scale);
        Winter.setPosition(clockX + 53*scale, clockY + 35*scale);
        Spring.setPosition(clockX + 53*scale, clockY + 35*scale);

        // Draw clock
        clockArrow.setRotation(-((dateTime.getHour() - 9)*12 + 180));
        rawClock.draw(batch);
        clockArrow.draw(batch);

        // Draw season and weather
        switch (dateTime.getSeason()) {
            case "Spring": Spring.draw(batch); break;
            case "Summer": Summer.draw(batch); break;
            case "Fall": Fall.draw(batch); break;
            case "Winter": Winter.draw(batch); break;
        }
        switch (App.getCurrentGame().getCurrentWeather()) {
            case RAIN: Rain.draw(batch); break;
            case SNOW: Snow.draw(batch); break;
            case SUNNY: Sunny.draw(batch); break;
            case STORM: Storm.draw(batch); break;
        }

        // Draw text
        GameMenuController menuController = App.getGameMenuController();
        String dayOfWeek = menuController.dayOfWeek().isSuccessful() ?
            menuController.dayOfWeek().getMessage() : "Monday";

        hour.draw(batch, dateTime.getHour() + " o'clock",
            clockX + 27*scale, clockY + 23*scale + hour.getLineHeight());
        day.draw(batch, dayOfWeek + ". " + dateTime.getDay(),
            clockX + 27*scale, clockY + 45*scale + hour.getLineHeight());

        Player currentPlayer = App.getCurrentGame().getCurrentPlayer();
        gold.draw(batch, String.valueOf(currentPlayer.getGold()),
            clockX + 17*scale, clockY + 3*scale + gold.getLineHeight());
    }

    public void dispose() {
        if (hour != null) hour.dispose();
        if (day != null) day.dispose();
        if (gold != null) gold.dispose();
    }
}
