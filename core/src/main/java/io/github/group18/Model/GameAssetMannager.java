package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetMannager {
    private static GameAssetMannager gameAssetMannager;
    private static Skin skin = new Skin(Gdx.files.internal("Skin/pixthulhu-ui.json"));


    public static GameAssetMannager getGameAssetMannager() {
        if(gameAssetMannager == null) {
            gameAssetMannager = new GameAssetMannager();
        }
        return gameAssetMannager;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void setGameAssetMannager(GameAssetMannager gameAssetMannager) {
        this.gameAssetMannager = gameAssetMannager;
    }
}
