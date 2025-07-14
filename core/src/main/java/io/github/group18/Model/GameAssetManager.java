package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    public static Texture menuBackground = new Texture(Gdx.files.internal("menu_bg.png"));

    Texture backgroundMapTexture = new Texture(Gdx.files.internal("map.png"));
    Texture map1Texture = new Texture(Gdx.files.internal("map1.png"));
    Texture map2Texture = new Texture(Gdx.files.internal("map2.png"));
    Texture map3Texture = new Texture(Gdx.files.internal("map3.png"));
    Texture map4Texture = new Texture(Gdx.files.internal("map4.png"));
    Texture mapDeafultTexture = new Texture(Gdx.files.internal("mapDeafult.png"));



    private GameAssetManager(){

    }

    public static GameAssetManager getGameAssetManager(){
        if (gameAssetManager == null){
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public static Texture getBackground() {
        return menuBackground;
    }

    public Texture getBackgroundMapTexture() {
        return backgroundMapTexture;
    }

    public void setBackgroundMapTexture(Texture backgroundMapTexture) {
        this.backgroundMapTexture = backgroundMapTexture;
    }

    public Texture getMap1Texture() {
        return map1Texture;
    }

    public void setMap1Texture(Texture map1Texture) {
        this.map1Texture = map1Texture;
    }

    public Texture getMap2Texture() {
        return map2Texture;
    }

    public void setMap2Texture(Texture map2Texture) {
        this.map2Texture = map2Texture;
    }

    public Texture getMap3Texture() {
        return map3Texture;
    }

    public void setMap3Texture(Texture map3Texture) {
        this.map3Texture = map3Texture;
    }

    public Texture getMap4Texture() {
        return map4Texture;
    }

    public void setMap4Texture(Texture map4Texture) {
        this.map4Texture = map4Texture;
    }

    public Texture getMapDeafultTexture() {
        return mapDeafultTexture;
    }

    public void setMapDeafultTexture(Texture mapDeafultTexture) {
        this.mapDeafultTexture = mapDeafultTexture;
    }
}
