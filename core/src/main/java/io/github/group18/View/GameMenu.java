package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.LightningEffect;
import io.github.group18.Main;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.App;
import io.github.group18.Model.Buff;
import io.github.group18.Model.Game;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.enums.CraftingRecipesEnums;
import io.github.group18.enums.SkillEnum;
import com.badlogic.gdx.InputMultiplexer;

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
    private CraftingMenu craftingMenu;
    private CookingMenu cookingMenu;
    private LightningEffect lightningEffect;
    private InventoryView inventoryView;
    private InputMultiplexer inputMultiplexer;
    private FishingMiniGame fishingMiniGame;
    private CraftingUI craftingUI;
    private Stage stage;
    private Buff buff;
    private boolean showBuff = false;


    public GameMenu(GameController gameController, Game gameModel) {
        this.stage = new Stage(new ScreenViewport());
        this.cheatCodeStage = new Stage();
        this.gameController = gameController;
        lightningEffect = new LightningEffect();
        this.craftingMenu = new CraftingMenu();
        this.fishingMiniGame = new FishingMiniGame();
        craftingMenu.setActive(false);
        this.cookingMenu = new CookingMenu();
        cookingMenu.setActive(false);
        this.craftingUI = new CraftingUI(new CraftingItem(CraftingRecipesEnums.Furnace.name()));
        craftingUI.setActive(false);
        initializeGame(gameModel);
    }

    private void initializeGame(Game gameModel) {
        this.gameModel = gameModel;
        gameView = new GameView(gameModel);
        gameMenuInputAdapter = new GameMenuInputAdapter(gameModel, gameController);
        Gdx.input.setInputProcessor(gameMenuInputAdapter);
        inventoryView = new InventoryView(GameAssetManager.getGameAssetManager().getSkin());

        stage.addActor(inventoryView.getWindow());
        stage.addActor(inventoryView.getTooltipLabel());
        stage.addActor(inventoryView.getSkillTooltipLabel());


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
        gameModel.update(delta);
        lightningEffect.update(delta);
        gameView.render();
        lightningEffect.render(Main.getBatch());
        gameMenuInputAdapter.update(delta,gameView.getBatch());

        handleNightSleepFade(delta);

        cheatCodeStage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        cheatCodeStage.draw();
        craftingMenu.render();
        cookingMenu.render();
        craftingUI.render();

        stage.act(delta);
        stage.draw();

        fishingMiniGame.update(delta);
        fishingMiniGame.render();
    }

    private void handleNightSleepFade(float delta) {
        if (!isSleeping) return;

        // --- Alpha Logic ---
        sleepTimer += delta;
        if (!advancingDay && sleepAlpha < 1f) {
            sleepAlpha = Math.min(1f, sleepAlpha + delta * FADE_SPEED);
            if (sleepAlpha >= 1f) {
                GameMenuController.startNewDay(gameController.getGameMenu(), false, gameView);
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

    public CraftingMenu getCraftingMenu() {
        return craftingMenu;
    }

    public void setCraftingMenu(CraftingMenu craftingMenu) {
        this.craftingMenu = craftingMenu;
    }
    // Other Screen methods


    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public Game getGameModel() {
        return gameModel;
    }

    public void setGameModel(Game gameModel) {
        this.gameModel = gameModel;
    }

    public GameMenuInputAdapter getGameMenuInputAdapter() {
        return gameMenuInputAdapter;
    }

    public void setGameMenuInputAdapter(GameMenuInputAdapter gameMenuInputAdapter) {
        this.gameMenuInputAdapter = gameMenuInputAdapter;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public float getSleepAlpha() {
        return sleepAlpha;
    }

    public void setSleepAlpha(float sleepAlpha) {
        this.sleepAlpha = sleepAlpha;
    }

    public float getSleepTimer() {
        return sleepTimer;
    }

    public void setSleepTimer(float sleepTimer) {
        this.sleepTimer = sleepTimer;
    }

    public boolean isAdvancingDay() {
        return advancingDay;
    }

    public void setAdvancingDay(boolean advancingDay) {
        this.advancingDay = advancingDay;
    }

    public InventoryView getInventoryView() {
        return inventoryView;
    }

    public CookingMenu getCookingMenu() {
        return cookingMenu;
    }

    public void setCookingMenu(CookingMenu cookingMenu) {
        this.cookingMenu = cookingMenu;
    }

    public void showBuffEffect(Buff buff) {
        Main.getBatch().begin();
        int scale = 3;
//        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        TextureRegionDrawable drawable = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
//            getCookingAtlas().findRegion(name));
//        drawable.setMinSize(drawable.getMinWidth()*scale, drawable.getMinHeight()*scale);
        // لود عکس
        TextureRegionDrawable buffTexture = new TextureRegionDrawable();
        try {
            buffTexture = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
                getSkillAtlas().findRegion(buff.getBuffSkillType().name()));
        }catch (NullPointerException e) {
            buffTexture = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
                getSkillAtlas().findRegion(SkillEnum.MiningSkill.name()));
        }
        buffTexture.setMinSize(buffTexture.getMinWidth()*scale, buffTexture.getMinHeight()*scale);
        Image buffImage = new Image(buffTexture);

        // تنظیم سایز و جایگاه دلخواه در صفحه (مثلا وسط)
        buffImage.setSize(100, 100);
        buffImage.setPosition(
            (Gdx.graphics.getWidth() - buffImage.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - buffImage.getHeight()) / 2f
        );

        // ابتدا شفافیت را صفر کن
        buffImage.getColor().a = 0f;

        // اضافه به stage
//        stage.addActor(buffImage);

        buffImage.draw(Main.getBatch(),buffImage.getColor().a);
//        Main.getBatch().draw(buffImage,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        // اکشن fade in -> مکث -> fade out -> حذف
        buffImage.addAction(Actions.sequence(
            Actions.fadeIn(0.5f),
            Actions.delay(1.5f),
            Actions.fadeOut(0.5f),
            Actions.run(() -> buffImage.remove())
        ));
        Main.getBatch().end();
    }    // Other Screen methods

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }

    public boolean isShowBuff() {
        return showBuff;
    }

    public void setShowBuff(boolean showBuff) {
        this.showBuff = showBuff;
    }

    public Stage getStage() {
        return stage;
    }

    public CraftingUI getCraftingUI() {
        return craftingUI;
    }

    public void setCraftingUI(CraftingUI craftingUI) {
        this.craftingUI = craftingUI;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
