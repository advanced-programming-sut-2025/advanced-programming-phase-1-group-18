package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;

import java.util.HashMap;
import java.util.Map;

public class RefrigeratorUI {
    private Skin skin;
    private Window inventoryWindow;
    private Map<Object, TextureRegion> textures;
    private int SLOT_SIZE = 64;
    private boolean active = true;

    public RefrigeratorUI(Skin skin) {
        this.skin = skin;
        createInventoryWindow();
    }
    private void loadInventoryItems() {
        for (Item item : App.getCurrentGame()
            .getCurrentPlayer().getMyFarm().getMyCottage().getMyRefrigerator().getItems().keySet()) {
            if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
            } else {
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal("Tools/Gold_Pan.png"))));
            }
        }
    }
    private void createInventoryWindow() {
        if (!active) {
            return;
        }
        inventoryWindow = new Window("Refrigerator", skin);
        textures = new HashMap<Object, TextureRegion>();
        inventoryWindow.setSize(700, 400);
        inventoryWindow.setPosition(100, 100);
        inventoryWindow.setMovable(true);
        inventoryWindow.setResizable(false);
        Gdx.input.setInputProcessor(inventoryWindow.getStage());
        loadInventoryItems();
        Table slotsTable = new Table();
        slotsTable.defaults().size(SLOT_SIZE).pad(0);
        ImageButton closeButton = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        inventoryWindow.getTitleTable()
            .add(closeButton)
            .size(50, 50)
            .padRight(10)
            .padTop(30)
            .right();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                inventoryWindow.setVisible(false);
                setActive(false);
            }
        });
        Player player = App.getCurrentGame().getCurrentPlayer();
//        Inventory inventory = player.getInventory();
        Refrigerator refrigerator = player.getMyFarm().getMyCottage().getMyRefrigerator();
        Map<Item, Pair<Integer, Integer>> items = refrigerator.getItems();

        int count = 0;

        for (int i = 0; i < refrigerator.getMaxQuantity(); i++) {
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
                    if(pointer == -1) { // یعنی فقط hover بوده
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
                    TextureRegion itemTex = textures.get(item);
                    if (itemTex != null) {
                        ImageButton itemImage = new ImageButton(new TextureRegionDrawable(itemTex));
                        slotStack.add(itemImage);
                        Dialog confirmDialog = new Dialog("Confirm", skin) {
                            @Override
                            protected void result(Object object) {
                                boolean confirmed = (boolean) object;
                                if (confirmed) {
                                    System.out.println("Confirmed for item: " + item.getCorrectName());
                                    try {
                                        System.out.println(App.getGameMenuController()
                                            .cookingRefrigerator("pick", item.getCorrectName()));
                                        loadInventoryItems();
                                        inventoryWindow.setVisible(false);
                                    } catch (ClassNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else {
                                    System.out.println("Cancelled");
                                }
                            }
                        };
                        confirmDialog.text("are you sure you want to move to inventory?");
                        confirmDialog.button("yes", true);
                        confirmDialog.button("no", false);
                        itemImage.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                confirmDialog.show(inventoryWindow.getStage());
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

        inventoryWindow.add(slotsTable);
    }

    public Window getInventoryWindow() {
        return inventoryWindow;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
