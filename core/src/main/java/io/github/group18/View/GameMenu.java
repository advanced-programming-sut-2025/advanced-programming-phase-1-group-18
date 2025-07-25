package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.LightningEffect;
import io.github.group18.Main;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.Game;
import io.github.group18.Model.GameAssetManager;

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
    private Stage stage;  // stage for UI like InventoryView

    private LightningEffect lightningEffect;
    private InventoryView inventoryView;

    private InputMultiplexer inputMultiplexer;

    public GameMenu(GameController gameController, Game gameModel) {
        this.stage = new Stage(new ScreenViewport());
        this.cheatCodeStage = new Stage(new ScreenViewport());
        this.gameController = gameController;
        lightningEffect = new LightningEffect();
        initializeGame(gameModel);
    }

    private void initializeGame(Game gameModel) {
        this.gameModel = gameModel;
        gameView = new GameView(gameModel);
        inventoryView = new InventoryView(GameAssetManager.getGameAssetManager().getSkin());

        stage.addActor(inventoryView.getWindow());
        stage.addActor(inventoryView.getTooltipLabel());
        stage.addActor(inventoryView.getSkillTooltipLabel());
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel, gameController);

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);               // UI first
        inputMultiplexer.addProcessor(gameMenuInputAdapter); // then game input
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        inventoryView.update();
        gameModel.update(delta);
        lightningEffect.update(delta);
        gameView.render();
        lightningEffect.render(Main.getBatch());
        gameMenuInputAdapter.update(delta);

        handleNightSleepFade(delta);

        cheatCodeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        cheatCodeStage.draw();

        stage.act(delta);   // update UI stage
        stage.draw();       // draw UI stage
    }

    private void handleNightSleepFade(float delta) {
        if (!isSleeping) return;

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
                resetRenderStates();
                return;
            }
        }

        Batch batch = gameView.getBatch();

        Matrix4 originalProj = batch.getProjectionMatrix().cpy();
        Color originalColor = new Color(batch.getColor());
        int srcFunc = batch.getBlendSrcFunc();
        int dstFunc = batch.getBlendDstFunc();
        boolean blendingEnabled = Gdx.gl.glIsEnabled(GL20.GL_BLEND);

        batch.setProjectionMatrix(
            new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
        );
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        batch.begin();
        batch.setColor(0f, 0f, 0f, sleepAlpha);
        batch.draw(
            gameView.getPixel(),
            0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );
        batch.end();

        cheatCodeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        cheatCodeStage.draw();

        batch.setProjectionMatrix(originalProj);
        batch.setColor(originalColor);
        batch.setBlendFunction(srcFunc, dstFunc);
        if (!blendingEnabled) Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void resetRenderStates() {
        Batch batch = gameView.getBatch();
        batch.setColor(1f, 1f, 1f, 1f);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void startSleepTransition() {
        isSleeping = true;
        sleepAlpha = 0f;
        sleepTimer = 0f;
        advancingDay = false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }

    public Stage getCheatCodeStage() {
        return cheatCodeStage;
    }

    public void setCheatCodeStage(Stage cheatCodeStage) {
        this.cheatCodeStage = cheatCodeStage;
    }

    public LightningEffect getLightningEffect() {
        return lightningEffect;
    }

    public void setLightningEffect(LightningEffect lightningEffect) {
        this.lightningEffect = lightningEffect;
    }

    public GameView getGameView() {
        return gameView;
    }

    public InventoryView getInventoryView() {
        return inventoryView;
    }
}
