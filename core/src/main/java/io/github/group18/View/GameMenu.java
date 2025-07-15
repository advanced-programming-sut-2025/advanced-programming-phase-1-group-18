package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.LightningEffect;
import io.github.group18.Main;
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
    private LightningEffect lightningEffect;


    public GameMenu(GameController gameController, Game gameModel) {
        this.cheatCodeStage = new Stage();
        this.gameController = gameController;
        lightningEffect = new LightningEffect();
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



        gameModel.update(delta);
        lightningEffect.update(delta);
        gameView.render();
        lightningEffect.render(Main.getBatch());
        gameMenuInputAdapter.update(delta);

        handleNightSleepFade(delta);

        cheatCodeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        cheatCodeStage.draw();
    }

    private void handleNightSleepFade(float delta) {
        if (!isSleeping) return;

        // --- Alpha Logic ---
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
                resetRenderStates(); // Critical: Clean up after fade
                return; // Skip rendering when fade is done
            }
        }

        // --- Isolated Fade Rendering ---
        Batch batch = gameView.getBatch();

        // 1. Backup ALL relevant states
        Matrix4 originalProj = batch.getProjectionMatrix().cpy();
        Color originalColor = new Color(batch.getColor());
        int srcFunc = batch.getBlendSrcFunc();
        int dstFunc = batch.getBlendDstFunc();
        boolean blendingEnabled = Gdx.gl.glIsEnabled(GL20.GL_BLEND);

        // 2. Configure fade-specific settings
        batch.setProjectionMatrix(
            new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight())
        );
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // 3. Draw the fade
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
        // 4. RESTORE original states EXACTLY
        batch.setProjectionMatrix(originalProj);
        batch.setColor(originalColor);
        batch.setBlendFunction(srcFunc, dstFunc);
        if (!blendingEnabled) Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void resetRenderStates() {
        // Force-clean OpenGL/Batch states (paranoid mode)
        Batch batch = gameView.getBatch();
        batch.setColor(1f, 1f, 1f, 1f); // Reset color
        Gdx.gl.glDisable(GL20.GL_BLEND); // Force-disable blending
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA); // Default blend
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

    public LightningEffect getLightningEffect() {
        return lightningEffect;
    }

    public void setLightningEffect(LightningEffect lightningEffect) {
        this.lightningEffect = lightningEffect;
    }
    // Other Screen methods
}
