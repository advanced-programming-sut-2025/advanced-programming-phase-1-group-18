package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Controller.FishShopController;
import io.github.group18.Controller.MarketController;
import io.github.group18.Controller.MarniesRanchController;
import io.github.group18.Model.Items.Fish;
import io.github.group18.Model.Items.FishingPole;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.Price;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.AnimalShopWindow;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreUI {
    private Stage stage;
    private Skin skin;
    private boolean showAllItems = true;
    //    private MarketController<T> marketController;
    private String marketName;
    private Table itemTable;
    private Window currentDialog;
    private InputProcessor inputProcessor;

    public StoreUI(String market, InputProcessor inputProcessor) {
        try {
            this.marketName = market;
            this.stage = new Stage(new ScreenViewport());
//            System.out.println("fish3.51");
            this.skin = GameAssetManager.getGameAssetManager().getSkin();
//            System.out.println("fish3.52");
            this.inputProcessor = inputProcessor;
//            System.out.println("fish3.53");
            createMainStoreUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createMainStoreUI() {
//        System.out.println("fish3.531");
        stage.clear();
//        System.out.println("fish3.532");
        Window storeWindow = new Window("Store Menu", skin);
        storeWindow.setModal(true);
        storeWindow.setMovable(false);
//        System.out.println("fish3.533");
        HashMap<String, Object> goldbody = new HashMap<>();
        goldbody.put("username", ClientModel.getPlayer().getOwner().getUsername());
        Message send = new Message(goldbody, Message.Type.get_gold1, Message.Menu.game);
        Message response = null;
//        System.out.println("fish3.534");
        while (response == null || response.getType() != Message.Type.get_gold1) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        }
//        System.out.println("fish3.535");
        int playergold = response.getIntFromBody("gold");
//        System.out.println("fish3.54");

        TextButton toggleButton = new TextButton(showAllItems ? "Show Available" : "Show All", skin);
        toggleButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showAllItems = !showAllItems;
                toggleButton.setText(showAllItems ? "Show Available" : "Show All");
                refreshItemList(playergold);
            }
        });


        Label goldLabel = new Label("Gold: " + playergold, skin);


        Table headerTable = new Table(skin);
        headerTable.add(toggleButton).pad(20, 20, 20, 20);

        headerTable.add(goldLabel).pad(20, 20, 20, 20).row();

//        if (marketController.isAnimalStore()) {
//            TextButton animalShopButton = new TextButton("Animal Shop", skin);
//            animalShopButton.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    AnimalShopWindow animalShopWindow = new AnimalShopWindow(skin, stage, (animalType, customName) -> {
//                        System.out.println("User selected: " + animalType + " with name: " + customName);
//Server-TODO
//                        Result result = MarniesRanchController.buyAnimal(animalType.toLowerCase(), customName);
//                        Dialog resultDialog = new Dialog(result.isSuccessful() ? "Success" : "Error", skin);
//                        resultDialog.text(result.getMessage());
//                        resultDialog.button("OK");
//                        resultDialog.show(stage);
//
//                        if (result.isSuccessful()) {
//                            refreshItemList();
//                        }
//                    });
//
//                    stage.addActor(animalShopWindow);
//                }
//            });
//
//            headerTable.add(animalShopButton).pad(20, 20, 20, 20).row();
//        }
        storeWindow.add(headerTable).row();
//        System.out.println("fish3.55");
        itemTable = new Table(skin);
        refreshItemList(playergold);

        ScrollPane scrollPane = new ScrollPane(itemTable, skin);
        scrollPane.setFadeScrollBars(false);
        storeWindow.add(scrollPane).width(800).height(600).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(inputProcessor);
                storeWindow.remove();
                ClientModel.setWindowOpen(false);
            }
        });
        storeWindow.add(closeButton).pad(20, 20, 20, 20);

        storeWindow.pack();
        storeWindow.setPosition(
            Gdx.graphics.getWidth() / 2 - storeWindow.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - storeWindow.getHeight() / 2
        );
//        System.out.println("fish3.56");
        stage.addActor(storeWindow);
//        System.out.println("fish3.57");
    }

    private void refreshItemList(int gold) {
        itemTable.clear();

        HashMap<String, Object> storeitembody = new HashMap<>();
        storeitembody.put("store", marketName);
        Message send = new Message(storeitembody, Message.Type.get_store_stock, Message.Menu.game);
        Message response = null;
        while (response == null || response.getType() != Message.Type.get_store_stock) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        }

        Gson gson = new Gson();
        Object stockOBJ = response.getBody().get("stock");
        String stockArraylist = gson.toJson(stockOBJ);

        Type userListType = new TypeToken<ArrayList<StockEntry>>() {
        }.getType();
        ArrayList<StockEntry> stockentrys = gson.fromJson(stockArraylist, userListType);

        HashMap<FishingPole, Integer> stockMap = new HashMap<>();
        for (StockEntry entry : stockentrys) {
            stockMap.put(entry.pole, entry.quantity);
        }

        HashMap<FishingPole, Integer> stock = showAllItems ?
            stockMap : getAvailableItems(stockMap);

        for (Map.Entry<FishingPole, Integer> entry : stock.entrySet()) {
            FishingPole item = entry.getKey();
            int quantity = entry.getValue();

            boolean isAvailable = quantity > 0 || quantity == -1;

            Table row = new Table(skin);

            Texture texture;
            if (item instanceof PictureModel) {
                try {
                    texture = new Texture(Gdx.files.internal(((PictureModel) item).getPath()));
                } catch (Exception e) {
                    texture = GameAssetManager.getGameAssetManager().getDefaultInventoryItem();
                }
            } else {
                texture = GameAssetManager.getGameAssetManager().getDefaultInventoryItem();
            }

            Image image = new Image(texture);
            image.setScaling(Scaling.fit);
            row.add(image).size(32, 32).padRight(10);

            image.setColor(isAvailable ? Color.WHITE : Color.GRAY);

            if (item instanceof Name) {
                Label label = new Label(((Name) item).getCorrectName(), skin);
                if (!isAvailable) {
                    label.setColor(Color.GRAY);
                }
                row.add(label).width(300).pad(20, 20, 20, 20);
            } else {
                Label label = new Label("unknown", skin);
                if (!isAvailable) {
                    label.setColor(Color.GRAY);
                }
                row.add(label).width(300).pad(20, 20, 20, 20);
            }

            if (item instanceof Price) {
                Label label = new Label(String.valueOf(((Price) item).getCorrectPrice()) + "g", skin);
                if (!isAvailable) {
                    label.setColor(Color.GRAY);
                }
                row.add(label).width(80).pad(20, 20, 20, 20);
            } else {
                Label label = new Label("-unknown" + "g", skin);
                if (!isAvailable) {
                    label.setColor(Color.GRAY);
                }
                row.add(label).width(80).pad(20, 20, 20, 20);
            }

            if (quantity > 0) {
                row.setColor(Color.WHITE);
                row.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        showPurchaseDialog(item, gold, stock);
                    }
                });
            } else {
//                System.out.println("is this gray?");
                row.setColor(Color.GRAY);
            }
            row.pad(20, 20, 20, 20);
            itemTable.add(row).fillX();
            itemTable.row();
        }
    }

    private HashMap<FishingPole, Integer> getAvailableItems(HashMap<FishingPole, Integer> stock) {
        HashMap<FishingPole, Integer> available = new HashMap<>();
        for (Map.Entry<FishingPole, Integer> entry : stock.entrySet()) {
            if (entry.getValue() > 0) {
                available.put(entry.getKey(), entry.getValue());
            }
        }
        return available;
    }

    private void showPurchaseDialog(FishingPole item, int gold, HashMap<FishingPole, Integer> stock) {
        if (currentDialog != null) {
            currentDialog.remove();
        }
        String name;
        int price;
        if (item instanceof Name) {
            name = ((Name) item).getCorrectName();
        } else {
            name = "unknown";
        }
        if (item instanceof Price) {
            price = ((Price) item).getCorrectPrice();
        } else {
            price = -69;
        }
        currentDialog = new Window("Buy " + name, skin);
        currentDialog.setModal(true);

        Table itemDisplay = new Table(skin);
        if (item instanceof PictureModel) {
            Texture texture;
            try {
                texture = new Texture(Gdx.files.internal(((PictureModel) item).getPath()));
            } catch (Exception e) {
                texture = GameAssetManager.getGameAssetManager().getDefaultInventoryItem();
            }
            Image image = new Image(texture);
            image.setScaling(Scaling.fit);
            itemDisplay.add(image).size(128, 128).padRight(20);
        }

        itemDisplay.add(new Label(name, skin)).row();
        itemDisplay.add(new Label("Price: " + price + "g", skin)).pad(10, 0, 0, 0).row();

        itemDisplay.add(new Label("Your gold: " + gold + "g", skin)).pad(10, 0, 0, 0).row();

        currentDialog.add(itemDisplay).pad(20, 20, 20, 20).row();

        final int[] quantity = {1};
        Label quantityLabel = new Label(String.valueOf(quantity[0]), skin);

        TextButton minusButton = (TextButton) new TextButton("-", skin).pad(20, 20, 20, 20);
        minusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (quantity[0] > 1) {
                    quantity[0]--;
                    quantityLabel.setText(String.valueOf(quantity[0]));
                }
            }
        });

        TextButton plusButton = (TextButton) new TextButton("+", skin).pad(20, 20, 20, 20);
        plusButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int stock = 100;
                if (stock == -1 || quantity[0] < stock) {
                    quantity[0]++;
                    quantityLabel.setText(String.valueOf(quantity[0]));
                }
            }
        });

        Table quantityTable = new Table(skin);
        quantityTable.add(minusButton).width(100);
        quantityTable.add(quantityLabel).width(100).pad(20, 20, 20, 20);
        quantityTable.add(plusButton).width(100);

        currentDialog.add(quantityTable).pad(20, 20, 20, 20).row();

        TextButton buyButton = new TextButton("Buy", skin);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = FishShopController.purchase1(name, String.valueOf(quantity[0]), ClientModel.getPlayer(), stock);
                if (result.isSuccessful()) {
                    currentDialog.remove();
                    refreshItemList(gold);
                    createMainStoreUI();
                } else {
                    Dialog errorDialog = new Dialog("Error", skin);
                    errorDialog.text(result.getMessage());
                    errorDialog.button("OK");
                    errorDialog.show(stage);
                }
            }
        });

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });

        Table buttonTable = new Table(skin);
        buttonTable.add(buyButton).padRight(20);
        buttonTable.add(cancelButton);

        currentDialog.add(buttonTable).pad(20);

        currentDialog.pack();
        currentDialog.setPosition(
            Gdx.graphics.getWidth() / 2 - currentDialog.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - currentDialog.getHeight() / 2
        );

        stage.addActor(currentDialog);
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public Stage getStage() {
        return stage;
    }
}
