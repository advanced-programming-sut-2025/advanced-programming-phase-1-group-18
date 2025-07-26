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
import io.github.group18.Model.Items.*;
import io.github.group18.enums.CraftingRecipesEnums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CraftingUI {
    private Stage stage;
    private Window window;
    private Skin skin;
    private GameMenuInputAdapter gameMenuInputAdapter;
    float scale;
    Table craftingItemTable;
    ArrayList<ImageButton> buttons;
    private Map<Object, TextureRegion> textures;
    private CraftingItem craftingItem;
    private int SLOT_SIZE = 64;
    private boolean active = false;
    private ArrayList<String> selectedItems = new ArrayList<>();

    public CraftingUI(CraftingItem craftingItem) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.craftingItem = craftingItem;
        this.textures = new HashMap<>();

//        window = new Window(craftingItem.getCraftingItem().name()+"\n", skin);
//        window.setSize(1000, 780);
//        window.setPosition(
//            (float) Gdx.graphics.getWidth() /2 - window.getWidth()/2,
//            Gdx.graphics.getHeight()/2 - window.getHeight()/2
//        );
//
//        ImageButton closeButton = new ImageButton(new TextureRegionDrawable(
//            new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())
//        ));
//        window.getTitleTable().setHeight(window.getTitleTable().getHeight()*5);
//        window.getTitleTable().add(closeButton).size(50, 50).padRight(10).padTop(30).right();
//
//        closeButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                Gdx.input.setInputProcessor(gameMenuInputAdapter);
//                setActive(false);
//            }
//        });
        if(craftingItem.isProcessing()){
            createPrepareWindow();
        }else if(craftingItem.isReady()){
            createReadyWindow();
        }else {
            createWindow();
        }
        stage.addActor(window);

//        stage.addActor(inventoryWindow);
    }
    private void loadInventoryItems() {
        for (Item item : App.getCurrentGame()
            .getCurrentPlayer().getInventory().getItems().keySet()) {
            if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
            } else {
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal("Tools/Gold_Pan.png"))));
            }
        }
    }
    private void createInventoryWindow() {
//        if (!active) return;

        window = new Window(craftingItem.getCraftingItem().name()+"\n", skin);
        window.setSize(1000, 780);
        window.setPosition(
            (float) Gdx.graphics.getWidth() /2 - window.getWidth()/2,
            Gdx.graphics.getHeight()/2 - window.getHeight()/2
        );

        ImageButton closeButton = new ImageButton(new TextureRegionDrawable(
            new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())
        ));
        window.getTitleTable().setHeight(window.getTitleTable().getHeight()*5);
        window.getTitleTable().add(closeButton).size(50, 50).padRight(10).padTop(30).right();

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });

        loadInventoryItems();

        Table slotsTable = new Table();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);

        buildInventorySlots(slotsTable);
        Dialog errorDialog = new Dialog("Error", skin);
        errorDialog.center();
        errorDialog.button("close");
        TextButton startButton = new TextButton("Start", skin);
        startButton.setWidth(1000);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                errorDialog.getContentTable().clear();
                Result result = App.getGameMenuController().artisanUse(craftingItem.getCraftingItem().name(),selectedItems,
                    craftingItem);
                if(!result.isSuccessful()){
                    selectedItems.clear();
                }
                errorDialog.text(result.getMessage());
                errorDialog.show(window.getStage());
            }
        });
//        slotsTable.row();
//        slotsTable.add(startButton);
        window.add(slotsTable).expand().fill();
        window.row();
        window.add(startButton);
    }
    private void buildInventorySlots(Table slotsTable) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Inventory inventory = player.getInventory();
        Map<Item, Pair<Integer, Integer>> items = inventory.getItems();

        int count = 0;
        for (int i = 0; i < inventory.getMaxQuantity(); i++) {
            Stack slotStack = new Stack();
            Image slotBg = new Image(new Texture(Gdx.files.internal("game/tiles/slot.png")));
            Texture highlightTexture = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
            slotBg.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                }
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if(pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(highlightTexture)));
                    }
                }
                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if(pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(
                            new Texture(Gdx.files.internal("game/tiles/slot.png")))));
                    }
                }

            });
            slotBg.setTouchable(Touchable.enabled);
            slotStack.add(slotBg);
            for (Map.Entry<Item, Pair<Integer, Integer>> entry : items.entrySet()) {
                Item item = entry.getKey();
                int index = entry.getValue().second;
                int quantity = entry.getValue().first;

                if (index == i) {
                    TextureRegion itemTex = textures.get(item);
                    if (itemTex != null) {
                        ImageButton itemImage = new ImageButton(new TextureRegionDrawable(itemTex));
                        slotStack.add(itemImage);
                        Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("You can't move it to refrigerator");
                        errorDialog.center();
                        errorDialog.button("close");

                        Dialog confirmDialog = new Dialog("Confirm", skin) {
                            @Override
                            protected void result(Object object) {
                                boolean confirmed = (boolean) object;
                                if (confirmed) {
                                    System.out.println("Confirmed for item: " + item.getCorrectName());
                                    if (!selectedItems.contains(item.getCorrectName())) {
                                        selectedItems.add(item.getCorrectName());
                                    }
                                } else {
                                    System.out.println("Cancelled");
                                }
                            }
                        };
                        confirmDialog.text("Are you sure you use this item?");
                        confirmDialog.button("yes", true);
                        confirmDialog.button("no", false);
                        itemImage.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                confirmDialog.show(window.getStage());
                            }
                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                if(pointer == -1) {
                                    slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(highlightTexture)));
                                }
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                if(pointer == -1) {
                                    slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(
                                        new Texture(Gdx.files.internal("game/tiles/slot.png")))));
                                }
                            }
                        });

                        // Quantity label
                        Label qtyLabel = new Label(String.valueOf(quantity), skin);
                        qtyLabel.setFontScale(0.8f);
                        qtyLabel.setAlignment(Align.bottomRight);
                        qtyLabel.setTouchable(Touchable.disabled);
                        slotStack.add(qtyLabel);
                    }
                }
            }

            slotsTable.add(slotStack);
            count++;
            if (count % 10 == 0) slotsTable.row();
        }
    }
    private void createWindow() {
        window = new Window("Artisan", skin);
        textures = new HashMap<Object, TextureRegion>();
        window.setSize(700, 400);
        window.setPosition(100, 100);
        window.setMovable(true);
        window.setResizable(false);
        //Gdx.input.setInputProcessor(window.getStage());
        Gdx.input.setInputProcessor(stage);
        loadInventoryItems();
        Table slotsTable = new Table();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);
        ImageButton closeButton = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        window.getTitleTable()
            .add(closeButton)
            .center();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = player.getInventory();
        Map<Item, Pair<Integer, Integer>> items = inventory.getItems();

        int count = 0;

        for (int i = 0; i < inventory.getMaxQuantity(); i++) {
            Stack slotStack = new Stack();

            // Slot background
            Image slotBg = new Image(new Texture(Gdx.files.internal("game/tiles/slot.png")));
            Texture highlightTexture = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
            slotBg.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                }
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if(pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(highlightTexture)));
                    }
                }
                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if(pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(
                            new Texture(Gdx.files.internal("game/tiles/slot.png")))));
                    }
                }

            });
            slotBg.setTouchable(Touchable.enabled);
            slotStack.add(slotBg);

            // If item exists in this slot index, add it
            for (Map.Entry<Item, Pair<Integer, Integer>> entry : items.entrySet()) {
                Item item = entry.getKey();
                int index = entry.getValue().second;
                int quantity = entry.getValue().first;

                if (index == i) {
                    System.out.println(i);
                    TextureRegion itemTex = textures.get(item);
                    if (itemTex != null) {
                        ImageButton itemImage = new ImageButton(new TextureRegionDrawable(itemTex));
                        slotStack.add(itemImage);
                        Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("You can't move it to refrigerator");
                        errorDialog.center();
                        errorDialog.button("close");

                        Dialog confirmDialog = new Dialog("Confirm", skin) {
                            @Override
                            protected void result(Object object) {
                                boolean confirmed = (boolean) object;
                                if (confirmed) {
                                    System.out.println("Confirmed for item: " + item.getCorrectName());
                                    if (!selectedItems.contains(item.getCorrectName())) {
                                        selectedItems.add(item.getCorrectName());
                                    }
                                } else {
                                    System.out.println("Cancelled");
                                }
                            }
                        };
                        confirmDialog.text("Are you sure you use this item?");
                        confirmDialog.button("yes", true);
                        confirmDialog.button("no", false);
                        itemImage.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                confirmDialog.show(window.getStage());
                            }
                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                if(pointer == -1) {
                                    slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(highlightTexture)));
                                }
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                if(pointer == -1) {
                                    slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(
                                        new Texture(Gdx.files.internal("game/tiles/slot.png")))));
                                }
                            }
                        });

                        // Quantity label
                        Label qtyLabel = new Label(String.valueOf(quantity), skin);
                        qtyLabel.setFontScale(0.8f);
                        qtyLabel.setAlignment(Align.bottomRight);
                        qtyLabel.setTouchable(Touchable.disabled);
                        slotStack.add(qtyLabel);
                    }
                }
            }

            slotsTable.add(slotStack);

            count++;
            if (count % 10 == 0) slotsTable.row(); // هر 10 تا یک ردیف جدید
        }

        window.add(slotsTable);
        Dialog errorDialog = new Dialog("Result", skin);
        errorDialog.center();
        errorDialog.button("close");
        TextButton startButton = new TextButton("Start", skin);
        startButton.setWidth(1000);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                errorDialog.getContentTable().clear();
                Result result = App.getGameMenuController().artisanUse(craftingItem.getCraftingItem().name(),
                    selectedItems,craftingItem);
                if(!result.isSuccessful()){
                    selectedItems.clear();
                }
                craftingItem.setProcessing(true);
                errorDialog.text(result.getMessage());
                errorDialog.show(window.getStage());
            }
        });
//        slotsTable.row();
//        slotsTable.add(startButton);
        window.row();
        window.add(startButton);
    }
    private void createPrepareWindow() {
        window = new Window("Artisan", skin);
        textures = new HashMap<Object, TextureRegion>();
        window.setSize(700, 400);
        window.setPosition(100, 100);
        window.setMovable(true);
        window.setResizable(false);
        //Gdx.input.setInputProcessor(window.getStage());
        Gdx.input.setInputProcessor(stage);

        loadInventoryItems();
        Table slotsTable = new Table();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);
        ImageButton closeButton = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        window.getTitleTable()
            .add(closeButton)
            .center();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        window.add(slotsTable);
        Dialog errorDialog = new Dialog("Result", skin);
        errorDialog.center();
        errorDialog.button("close");
        TextButton startButton = new TextButton("Stop Producing", skin);
        startButton.setWidth(1000);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                craftingItem.setProcessing(false);
                craftingItem.setReady(false);
                craftingItem.setInsideArtisan(null);
                errorDialog.getContentTable().clear();
                errorDialog.text("Preparing Stopped by YOU");
                errorDialog.show(window.getStage());
                active =false;
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
            }
        });
//        slotsTable.row();
//        slotsTable.add(startButton);
        window.row();
        window.add(startButton);
        TextButton cheatReady = new TextButton("Cheat Ready", skin);
        cheatReady.setWidth(1000);
        cheatReady.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                craftingItem.setProcessing(false);
                craftingItem.setReady(true);
                errorDialog.getContentTable().clear();
                errorDialog.text("Cheat Ready");
                errorDialog.show(window.getStage());
                active = false;
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
            }
        });
//        slotsTable.row();
//        slotsTable.add(startButton);
        window.row();
        window.add(startButton);
        window.row();
        window.add(cheatReady);
        window.row();
    }
    private void createReadyWindow() {
        window = new Window("Artisan", skin);
        textures = new HashMap<Object, TextureRegion>();
        window.setSize(700, 400);
        window.setPosition(100, 100);
        window.setMovable(true);
        window.setResizable(false);
       // Gdx.input.setInputProcessor(window.getStage());
        Gdx.input.setInputProcessor(stage);

        loadInventoryItems();
        Table slotsTable = new Table();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);
        ImageButton closeButton = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        window.getTitleTable()
            .add(closeButton)
            .center();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        window.add(slotsTable);
        Dialog errorDialog = new Dialog("Result", skin);
        errorDialog.center();
        errorDialog.button("close");
        TextButton startButton = new TextButton("collect", skin);
        startButton.setWidth(1000);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(App.getGameMenuController().artisanGet(craftingItem.getInsideArtisan().getCorrectName(),craftingItem));
                craftingItem.setProcessing(false);
                craftingItem.setReady(false);
                errorDialog.getContentTable().clear();
                errorDialog.text("Artisan Collected");
                errorDialog.show(window.getStage());
                craftingItem.setInsideArtisan(null);
                active =false;
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
            }
        });

        window.row();
        Label artisanLabel = new Label("Artisan: " + craftingItem.getInsideArtisan().getArtisanGoods().name()+
            " Is ready", skin);
        window.add(artisanLabel);
        window.row();
        window.add(startButton);
    }

    public void render() {
        if (active) {
//            if(craftingItemTable.getZIndex()< window.getZIndex()){
//                craftingItemTable.setZIndex(window.getZIndex()+1);
//            }
            loadInventoryItems();
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

    public Table getCraftingItemTable() {
        return craftingItemTable;
    }

    public void setCraftingItemTable(Table craftingItemTable) {
        this.craftingItemTable = craftingItemTable;
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
}
