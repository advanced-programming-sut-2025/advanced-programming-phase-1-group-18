package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.App;
import io.github.group18.Model.Game;

public class GameMenu implements Screen {
    private GameView gameView;
    public Game gameModel;
    private GameMenuInputAdapter gameMenuInputAdapter;
    private GameController gameController;
    private boolean isSleeping = false;
    private float sleepAlpha = 0f;
    private float sleepTimer = 0f;
    private static final float SLEEP_DURATION = 2f; // seconds
    private static final float FADE_SPEED = 1.5f;   // speed of fading
    private boolean advancingDay = false;
    private Stage cheatCodeStage;


    public GameMenu(GameController gameController,Game gameModel) {
        this.cheatCodeStage = new Stage();
        this.gameController = gameController;
        initializeGame(gameModel);
    }

    private void initializeGame(Game gameModel) {
        this.gameModel = gameModel;
        gameView = new GameView(gameModel);
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel, gameController);
        Gdx.input.setInputProcessor(gameMenuInputAdapter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        gameModel.update(delta);
        gameView.render();
        gameMenuInputAdapter.update(delta);

        handleNightBrightness();

        handleNightSleepFade(delta);

        cheatCodeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        cheatCodeStage.draw();
    }

    private void handleNightSleepFade(float delta) {
        if (isSleeping) {
            sleepTimer += delta;
            if (!advancingDay && sleepAlpha < 1f) {
                sleepAlpha = Math.min(1f, sleepAlpha + delta * FADE_SPEED);
                if (sleepAlpha >= 1f) {
                    GameMenuController.startNewDay(gameController.getGameMenu(), false);
                    advancingDay = true;
                }
            } else if (advancingDay && sleepAlpha > 0f) {
                sleepAlpha = Math.max(0f, sleepAlpha - delta * FADE_SPEED);
                if (sleepAlpha <= 0f) {
                    isSleeping = false;
                    advancingDay = false;
                    sleepTimer = 0f;
                }
            }

            // Render black overlay
            gameView.getBatch().begin();
            gameView.getBatch().setColor(0f, 0f, 0f, sleepAlpha);
            gameView.getBatch().draw(gameView.getPixel(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            gameView.getBatch().setColor(1f, 1f, 1f, 1f);
            gameView.getBatch().end();
        }
    }

    private void handleNightBrightness() {
        int currentHour = gameModel.getCurrentDateTime().getHour();
        float brightness = 1f;

        if (currentHour >= 18 || currentHour <= 6) {
            float nightFactor;
            if (currentHour >= 18 && currentHour < 24) {
                nightFactor = (currentHour - 18) / 6f;
            } else {
                nightFactor = (6 - currentHour) / 6f;
            }

            brightness = 1f - (nightFactor * 0.5f);
        }


        if (brightness < 1f) {
            float overlayAlpha = 1f - brightness;

            gameView.getBatch().begin();
            gameView.getBatch().setColor(0f, 0f, 0f, overlayAlpha);
            gameView.getBatch().draw(gameView.getPixel(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            gameView.getBatch().setColor(1f, 1f, 1f, 1f);
            gameView.getBatch().end();
        }
    }

    public void startSleepTransition() {
        isSleeping = true;
        sleepAlpha = 0f;
        sleepTimer = 0f;
        advancingDay = false;
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

    public Stage getCheatCodeStage() {
        return cheatCodeStage;
    }

    public void setCheatCodeStage(Stage cheatCodeStage) {
        this.cheatCodeStage = cheatCodeStage;
    }

    // Other Screen methods
}
