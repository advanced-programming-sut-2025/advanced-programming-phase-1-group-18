package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.*;

import java.util.HashMap;
import java.util.Map;

public class InventoryUI {
    private Skin skin;
    private Window inventoryWindow;
    private Map<Object, TextureRegion> textures;
    private int SLOT_SIZE = 64;
    private boolean active = true;

    public InventoryUI(Skin skin) {
        this.skin = skin;
        createInventoryWindow();
    }

    private void loadInventoryItems() {
        for (Item item : App.getCurrentGame()
            .getCurrentPlayer().getInventory().getItems().keySet()) {
            if (item instanceof CraftingItem) {
                textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getCraftingAtlas().
                    findRegion(((CraftingItem) item).getCraftingItem().name())));
            } else if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                try {
                    textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
                } catch (Exception e) {
                    textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem()));
                }
            } else {
                textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem()));
            }
        }
    }
    private void createInventoryWindow() {
        if (!active) {
            return;
        }
        inventoryWindow = new Window("Inventory", skin);
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
                                    try {
                                        System.out.println(App.getGameMenuController().cookingRefrigerator("put", item.getCorrectName()));
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
                        confirmDialog.text("are you sure you want to move to refrigerator?");
                        confirmDialog.button("yes", true);
                        confirmDialog.button("no", false);
                        itemImage.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                if(item instanceof Crop || item instanceof AnimalProduct || item instanceof Food
                                || item instanceof Fish || item instanceof FoodCooking){
                                    confirmDialog.show(inventoryWindow.getStage());
                                }else {
                                    errorDialog.show(inventoryWindow.getStage());
                                }
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
