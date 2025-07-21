package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameController;

import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import io.github.group18.Controller.GameMenuController;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.Tool;

public class GameMenuInputAdapter extends InputAdapter {
    private final Game game;
    private final GameController gameController;
    private final Set<Integer> keysHeld = new HashSet<>();

    public GameMenuInputAdapter(Game game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
        game.getCurrentPlayer().setShowInventory(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        keysHeld.add(keycode);

        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            int selectedSlot = keycode - Input.Keys.NUM_1;
            game.getCurrentPlayer().getInventory().setSelectedSlot(selectedSlot);
            return true;
        }

        if (keycode == Input.Keys.ESCAPE) {
//            gameController.goToMain();
            return true;
        }

        if (keycode == Input.Keys.N) {
            GameMenuController.nextTurn(gameController.getGameMenu(), gameController.getGameMenu().getGameView());
            return true;
        }
        if (keycode == Input.Keys.H) {
            Stage stage = gameController.getGameMenu().getCheatCodeStage();
            Gdx.input.setInputProcessor(stage);
            CheatCodeDialog cheatDialog = new CheatCodeDialog(stage,
                GameAssetManager.getGameAssetManager().getSkin(), this);
            cheatDialog.show(stage);
            cheatDialog.setColor(Color.WHITE);
        }
        if (keycode == Input.Keys.I) {
            game.getCurrentPlayer().setShowInventory(!game.getCurrentPlayer().isShowInventory());
            return true;
        }
        if (keycode == Input.Keys.B) {
            App.getGameController().getGameMenu().getCraftingMenu().setActive(true);
            App.getGameController().getGameMenu().getCraftingMenu().setGameMenuInputAdapter(this);
            Gdx.input.setInputProcessor(App.getGameController().getGameMenu().getCraftingMenu().getStage());
        }
        if (keycode == Input.Keys.C) {
            Game.getCurrentPlayer().pickSelectedItem();
//            performAction(screenX, screenY);
            return true;
        }
//        if (keycode == Input.Keys.X) {
//            performAction(screenX, screenY);
//            return true;
//        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysHeld.remove(keycode);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        int current = game.getCurrentPlayer().getInventory().getSelectedSlot();
        int size = game.getCurrentPlayer().getInventory().getMaxQuantity();
        int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
        game.getCurrentPlayer().getInventory().setSelectedSlot(next);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            Game.getCurrentPlayer().pickSelectedItem();
//            performAction(screenX, screenY);
            return true;
        }
        if (button == Input.Buttons.RIGHT) {
            performAction(screenX, screenY);
            return true;
        }
        return false;
    }

    public void update(float delta) {
        Player player = game.getCurrentPlayer();
        float vx = 0, vy = 0;
        int dir = 0;

        if (keysHeld.contains(Input.Keys.W)) {
            vy += 1;
            dir = 3;
        }
        if (keysHeld.contains(Input.Keys.S)) {
            vy -= 1;
            dir = 1;
        }
        if (keysHeld.contains(Input.Keys.A)) {
            vx -= 1;
            dir = 4;
        }
        if (keysHeld.contains(Input.Keys.D)) {
            vx += 1;
            dir = 2;
        }

        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if (length > 0) {
            vx /= length;
            vy /= length;
            player.setMovingDirection(dir);
        } else {
            player.setMovingDirection(0);
        }

        float speed = player.getSpeed();
        player.setVelocity(vx * speed, vy * speed);
        player.update(delta, game.getMap(), gameController.getGameMenu().getGameView());
    }

    private void performAction(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }

        Item item = Game.getCurrentPlayer().getInventory().getItemBySlot(game.getCurrentPlayer().getInventory().getSelectedSlot());
        gameController.useItem(item, tileX, tileY, game);
    }


    public Game getGame() {
        return game;
    }

    public GameController getGameController() {
        return gameController;
    }

    public Set<Integer> getKeysHeld() {
        return keysHeld;
    }

}
