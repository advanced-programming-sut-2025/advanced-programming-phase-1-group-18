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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.Item;
import io.github.group18.Network.Client.App.ActionMessageHandler;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Client.App.GameMessageHandler;
import io.github.group18.Network.Client.App.VoteMessageHandler;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.ActionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.lang.reflect.Type;


public class VoteMenu {
    private Stage stage;
    private Window window;
    private Skin skin;
    private GameMenuInputAdapter gameMenuInputAdapter;

    Label vtTrGame;
    Label vtRmvPlayer;
    SelectBox<String>players;
    TextButton vtYs;
    TextButton vtNo;
    TextButton vote;

    Table actionTable;

    private boolean active = false;
    //    private Player player;
    Table slotsTable = new Table();


    public VoteMenu() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        player =getCurrentPlayer();
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.vtNo = new TextButton("No", skin);
        this.vtYs = new TextButton("Yes", skin);
        this.vtTrGame = new Label("Vote Terminate Game", skin);
        this.vtRmvPlayer = new Label("Vote Remove Player", skin);
        this.vote = new TextButton("Vote", skin);
        this.players = new SelectBox<>(skin);
        createWindow();
        stage.addActor(window);

    }
    private void loadActionTable() {
        Message res = VoteMessageHandler.getPlayers();
        int playerNum = res.getIntFromBody("playersNum");
        ArrayList<String>players = new ArrayList<>();
        for (int i = 0; i < playerNum; i++) {
            players.add(res.getFromBody(String.valueOf(i)));
        }
        com.badlogic.gdx.utils.Array<String> array = new com.badlogic.gdx.utils.Array<>(players.toArray(new String[0]));
        this.players.setItems(array);
    }
    private void createWindow() {
        window = new Window("Vote Menu", skin);

        window.setSize(700, 400);
        window.setPosition(100, 100);
        window.setMovable(true);
        window.setResizable(false);
        Gdx.input.setInputProcessor(stage);

//        player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        slotsTable.clear();

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

        refreshActionSlots();

        window.add(slotsTable).row();

    }


    private void refreshActionSlots() {
        slotsTable.clear();
        int count = 0;
        vtYs.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               VoteMessageHandler.voteTerminateGame(ClientModel.getPlayer().getOwner().getUsername(),true);
               Gdx.input.setInputProcessor(gameMenuInputAdapter);
               setActive(false);
           }
        });
        vtNo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VoteMessageHandler.voteTerminateGame(ClientModel.getPlayer().getOwner().getUsername(),false);
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });


        vote.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                VoteMessageHandler.votePlayer(players.getSelected(), ClientModel.getPlayer().getOwner().getUsername());
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });

        slotsTable.add(vtTrGame);
        slotsTable.row();
        slotsTable.add(vtYs);
        slotsTable.add(vtNo);
        slotsTable.row();
        slotsTable.add(vtRmvPlayer);
        slotsTable.row();
        slotsTable.add(players);
        slotsTable.add(vote);

//        int limit = isChoose ? actions.size() : Math.min(3, actions.size());
//
//        for (int i = 0; i < limit; i++) {
//            final ActionEnum action = actions.get(i);
//            Stack slotStack = new Stack();
//
//            Texture slotTex = new Texture(Gdx.files.internal("game/tiles/slot.png"));
//            Texture highlightTex = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
//            Image slotBg = new Image(slotTex);
//
//            slotBg.addListener(new ClickListener() {
//                @Override public void enter(InputEvent e, float x, float y, int pointer, Actor fromActor) {
//                    if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(highlightTex));
//                }
//                @Override public void exit(InputEvent e, float x, float y, int pointer, Actor toActor) {
//                    if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(slotTex)));
//                }
//            });
//
//            slotStack.add(slotBg);
//
//            TextureRegion itemTex = GameAssetManager.getGameAssetManager().getActionAtlas().findRegion(action.name());
//            if (itemTex != null) {
//                ImageButton itemImage = new ImageButton(new TextureRegionDrawable(itemTex));
//                slotStack.add(itemImage);
//                itemImage.addListener(new ClickListener() {
//                    @Override
//                    public void clicked(InputEvent event, float x, float y) {
//                        System.out.println("Clicked");
//                        if (isChoose) {
//                            ActionMessageHandler.sendToFrontAction(App.getCurrentUser().getID(), action);
////                            player.moveToFront(action);
//                            refreshActionSlots();
//                        } else {
//                            ActionMessageHandler.setAction(App.getCurrentUser().getID(), action);
//                            ClientModel.getPlayer().setAction(action);
////                            player.setAction(action);
////                            GameMessageHandler.playerActionPop(App.getCurrentUser().getID(),action.name());
//                            setActive(false);
//                            Gdx.input.setInputProcessor(gameMenuInputAdapter);
//                        }
//                    }
//
//                    @Override public void enter(InputEvent e, float x, float y, int pointer, Actor fromActor) {
//                        if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(highlightTex));
//                    }
//
//                    @Override public void exit(InputEvent e, float x, float y, int pointer, Actor toActor) {
//                        if (pointer == -1) slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(slotTex)));
//                    }
//                });
//            }
//
//            slotsTable.add(slotStack);
//            count++;
//            if (count % 3 == 0) slotsTable.row();
//        }
    }



    public void render() {
        if (active) {

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

//    public float getScale() {
//        return scale;
//    }
//
//    public void setScale(float scale) {
//        this.scale = scale;
//    }
//
//    public Table getActionTable() {
//        return actionTable;
//    }
//
//    public void setActionTable(Table actionTable) {
//        this.actionTable = actionTable;
//    }
//
//    public ArrayList<ImageButton> getButtons() {
//        return buttons;
//    }
//
//    public void setButtons(ArrayList<ImageButton> buttons) {
//        this.buttons = buttons;
//    }
//
//
//
//    public Map<Object, TextureRegion> getTextures() {
//        return textures;
//    }
//
//    public void setTextures(Map<Object, TextureRegion> textures) {
//        this.textures = textures;
//    }
//
//    public CraftingItem getCraftingItem() {
//        return craftingItem;
//    }
//
//    public void setCraftingItem(CraftingItem craftingItem) {
//        this.craftingItem = craftingItem;
//    }
//
//    public int getSLOT_SIZE() {
//        return SLOT_SIZE;
//    }
//
//    public void setSLOT_SIZE(int SLOT_SIZE) {
//        this.SLOT_SIZE = SLOT_SIZE;
//    }
//
//    public ArrayList<String> getSelectedItems() {
//        return selectedItems;
//    }
//
//    public void setSelectedItems(ArrayList<String> selectedItems) {
//        this.selectedItems = selectedItems;
//    }
//
//    public boolean isChoose() {
//        return isChoose;
//    }
//
//    public void setChoose(boolean choose) {
//        isChoose = choose;
//    }
}
