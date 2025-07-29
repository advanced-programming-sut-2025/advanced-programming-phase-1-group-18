package io.github.group18.View;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.LobbyController;

public class LobbyView implements Screen {
    LobbyController controller;
    Skin skin;
    GameMenuController gameMenuController;

    public LobbyView(LobbyController lobbyController, Skin skin, GameMenuController gameMenuController) {
        this.controller = lobbyController;
        this.skin = skin;
        this.gameMenuController = gameMenuController;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
