package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.Item;
import io.github.group18.Network.Client.App.GameMessageHandler;
import io.github.group18.enums.ActionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static io.github.group18.Model.Game.getCurrentPlayer;

public class ActionMenu {
    private Stage stage;
    private Window window;
    private Skin skin;
    private GameMenuInputAdapter gameMenuInputAdapter;
    float scale;
    Table actionTable;
    ArrayList<ImageButton> buttons;
    private Map<Object, TextureRegion> textures;
    private int SLOT_SIZE = 64;
    private boolean active = false;
    private Player player;
    Table slotsTable = new Table();
    boolean isChoose = true;

    private CraftingItem craftingItem;
    private ArrayList<String> selectedItems = new ArrayList<>();


    public ActionMenu() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        player =getCurrentPlayer();
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.textures = new HashMap<>();
        createWindow();
        stage.addActor(window);

    }
    private void loadActionTable() {
        for (ActionEnum action : player.getActionQueue()) {
            textures.put(action, GameAssetManager.getGameAssetManager().getActionAtlas().findRegion(action.name()));
        }
    }
    private void createWindow() {
        window = new Window("Actions Menu", skin);
        textures = new HashMap<>();
        window.setSize(700, 400);
        window.setPosition(100, 100);
        window.setMovable(true);
        window.setResizable(false);
        Gdx.input.setInputProcessor(stage);

        player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        slotsTable.clear();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);

        ImageButton closeButton = new ImageButton(new TextureRegionDrawable(
            new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        window.getTitleTable().add(closeButton).center();

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });

        // ساخت اسلات‌ها
        refreshActionSlots(); // اولین بار

        // اضافه کردن جدول فقط یک‌بار
        window.add(slotsTable).row();

        // دکمه‌ی پایین
        TextButton toggleButton = new TextButton("More", skin);
        toggleButton.setWidth(1000);
        toggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                isChoose = !isChoose;
                setChoose(!isChoose);
                toggleButton.setText(isChoose ? "Choose" : "More");
                refreshActionSlots(); // فقط محتوای جدول تغییر کند
            }
        });
        window.add(toggleButton).row();
    }


    private void refreshActionSlots() {
        slotsTable.clear();

        LinkedList<ActionEnum> actions = player.getActionQueue();
        int count = 0;

        int limit = isChoose ? actions.size() : Math.min(3, actions.size());

        for (int i = 0; i < limit; i++) {
            final ActionEnum action = actions.get(i);
            Stack slotStack = new Stack();

            Texture slotTex = new Texture(Gdx.files.internal("game/tiles/slot.png"));
            Texture highlightTex = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
            Image slotBg = new Image(slotTex);

            slotBg.addListener(new ClickListener() {
                @Override public void enter(InputEvent e, float x, float y, int pointer, Actor fromActor) {
                    if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(highlightTex));
                }
                @Override public void exit(InputEvent e, float x, float y, int pointer, Actor toActor) {
                    if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(slotTex)));
                }
            });

            slotStack.add(slotBg);

            TextureRegion itemTex = GameAssetManager.getGameAssetManager().getActionAtlas().findRegion(action.name());
            if (itemTex != null) {
                ImageButton itemImage = new ImageButton(new TextureRegionDrawable(itemTex));
                slotStack.add(itemImage);
                itemImage.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (isChoose) {
                            player.moveToFront(action);
                            refreshActionSlots();
                        } else {
                            player.setAction(action);
                            GameMessageHandler.playerActionPop(player.getID(),action.name());
                            setActive(false);
                            Gdx.input.setInputProcessor(gameMenuInputAdapter);
                        }
                    }

                    @Override public void enter(InputEvent e, float x, float y, int pointer, Actor fromActor) {
                        if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(highlightTex));
                    }

                    @Override public void exit(InputEvent e, float x, float y, int pointer, Actor toActor) {
                        if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(slotTex)));
                    }
                });
            }

            slotsTable.add(slotStack);
            count++;
            if (count % 3 == 0) slotsTable.row();
        }
    }



    public void render() {
        if (active) {
//            if(actionTable.getZIndex()< window.getZIndex()){
//                actionTable.setZIndex(window.getZIndex()+1);
//            }
            loadActionTable();
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Window getWindow() {
        return window;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public GameMenuInputAdapter getGameMenuInputAdapter() {
        return gameMenuInputAdapter;
    }

    public void setGameMenuInputAdapter(GameMenuInputAdapter gameMenuInputAdapter) {
        this.gameMenuInputAdapter = gameMenuInputAdapter;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Table getActionTable() {
        return actionTable;
    }

    public void setActionTable(Table actionTable) {
        this.actionTable = actionTable;
    }

    public ArrayList<ImageButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<ImageButton> buttons) {
        this.buttons = buttons;
    }



    public Map<Object, TextureRegion> getTextures() {
        return textures;
    }

    public void setTextures(Map<Object, TextureRegion> textures) {
        this.textures = textures;
    }

    public CraftingItem getCraftingItem() {
        return craftingItem;
    }

    public void setCraftingItem(CraftingItem craftingItem) {
        this.craftingItem = craftingItem;
    }

    public int getSLOT_SIZE() {
        return SLOT_SIZE;
    }

    public void setSLOT_SIZE(int SLOT_SIZE) {
        this.SLOT_SIZE = SLOT_SIZE;
    }

    public ArrayList<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(ArrayList<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
