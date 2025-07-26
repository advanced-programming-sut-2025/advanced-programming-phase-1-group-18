package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.MarniesRanchController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.Price;
import io.github.group18.enums.NPCEnums;

public class NPCView {

    private Stage stage;
    private Skin skin;
    private Table itemTable;
    private Window currentDialog;
    private InputProcessor inputProcessor;
    private NPC npc;

    public NPCView(InputProcessor inputProcessor, NPC npc) {
        this.stage = new Stage(new ScreenViewport());
        this.inputProcessor = inputProcessor;
        this.npc = npc;
        skin = GameAssetManager.getGameAssetManager().getSkin();
        createNPCUI();
    }

    private void createNPCUI() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        Window npcWindow = new Window("NPC Menu", skin);
        npcWindow.setModal(true);
        npcWindow.setMovable(false);


        Label goldLabel = new Label("NPC: " + npc.getName().toString(), skin);


        Table headerTable = new Table(skin);
        headerTable.add(goldLabel).pad(20, 20, 20, 20).row();

        npcWindow.add(headerTable).row();

        TextButton giftNpc = new TextButton("Gift Item to " + npc.getName(), skin);
        giftNpc.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                giftNPCPage();
            }
        });
        npcWindow.add(giftNpc).pad(20, 20, 20, 20).row();

        TextButton questNpc = new TextButton("See Quests", skin);
        questNpc.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                seeQuestsPage();
            }
        });
        npcWindow.add(questNpc).pad(20, 20, 20, 20).row();

        TextButton questNpc1 = new TextButton("Do Quests", skin);
        questNpc1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                doQuestsPage();
            }
        });
        npcWindow.add(questNpc1).pad(20, 20, 20, 20).row();

        TextButton friendshipnpc = new TextButton("Friendship level", skin);
        friendshipnpc.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                friendshipLevel();
            }
        });
        npcWindow.add(friendshipnpc).pad(20, 20, 20, 20).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(inputProcessor);
                npcWindow.remove();
                Gdx.input.setInputProcessor(inputProcessor);
                if (npc.getName() == NPCEnums.SEBASTIAN) {
                    App.getCurrentGame().setSebastian_view(false);
                }
                if (npc.getName() == NPCEnums.ABIGAIL) {
                    App.getCurrentGame().setAbigail_view(false);
                }
                if (npc.getName() == NPCEnums.HARVEY) {
                    App.getCurrentGame().setHarvey_view(false);
                }
                if (npc.getName() == NPCEnums.LEAH) {
                    App.getCurrentGame().setLeah_view(false);
                }
                if (npc.getName() == NPCEnums.ROBIN) {
                    App.getCurrentGame().setRobin_view(false);
                }
            }
        });
        npcWindow.add(closeButton).pad(20, 20, 20, 20).row();

        npcWindow.pack();
        npcWindow.setPosition(
            Gdx.graphics.getWidth() / 2 - npcWindow.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - npcWindow.getHeight() / 2
        );

        stage.addActor(npcWindow);
    }

    public void giftNPCPage() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        currentDialog = new Window("Gift for " + npc.getName(), skin);
        currentDialog.setModal(true);

        Table itemDisplay = new Table(skin);
        Item item = App.getCurrentGame().getCurrentPlayer().getInventory().getItemBySlot(App.getCurrentGame().getCurrentPlayer().getInventory().getSelectedSlot());
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
                int stock = App.getCurrentGame().getCurrentPlayer().getInventory().getItemQuantity(item);
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

        TextButton buyButton = new TextButton("Gift", skin);
        buyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = GameMenuController.giftNPC(npc, item, quantity[0]);
                if (result.isSuccessful()) {
                    currentDialog.remove();
                    createNPCUI();
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

    public void seeQuestsPage() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        currentDialog = new Window("Quests" + npc.getName(), skin);
        currentDialog.setModal(true);

        Label questsLabel = new Label(GameMenuController.questsList(npc).getMessage(), skin);
        currentDialog.add(questsLabel).pad(20, 20, 20, 20).row();

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });

        Table buttonTable = new Table(skin);
        buttonTable.add(cancelButton);

        currentDialog.add(buttonTable).pad(20);

        currentDialog.pack();
        currentDialog.setPosition(
            Gdx.graphics.getWidth() / 2 - currentDialog.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - currentDialog.getHeight() / 2
        );

        stage.addActor(currentDialog);
    }

    public void doQuestsPage() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        currentDialog = new Window("Finish Quests" + npc.getName(), skin);
        currentDialog.setModal(true);

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
                int stock = 4;
                if (stock == -1 || quantity[0] < stock) {
                    quantity[0]++;
                    quantityLabel.setText(String.valueOf(quantity[0]));
                }
            }
        });

        Table quantityTable = new Table(skin);
        quantityTable.add(minusButton).width(100);
        quantityTable.add(quantityLabel).width(100).pad(20,20,20,20);
        quantityTable.add(plusButton).width(100);

        currentDialog.add(quantityTable).pad(20, 20, 20, 20).row();

        TextButton doButton = (TextButton) new TextButton("finish", skin).pad(20, 20, 20, 20);
        doButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String result = GameMenuController.questsFinish(npc,quantity[0]).getMessage();
                Dialog errorDialog = new Dialog(result, skin);
                errorDialog.button("OK");
                errorDialog.show(stage);
            }
        });
        currentDialog.add(doButton).pad(20, 20, 20, 20).row();

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });

        Table buttonTable = new Table(skin);
        buttonTable.add(cancelButton);

        currentDialog.add(buttonTable).pad(20);

        currentDialog.pack();
        currentDialog.setPosition(
            Gdx.graphics.getWidth() / 2 - currentDialog.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - currentDialog.getHeight() / 2
        );

        stage.addActor(currentDialog);
    }

    public void friendshipLevel() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        currentDialog = new Window("Friendship" + npc.getName(), skin);
        currentDialog.setModal(true);

        Label questsLabel = new Label(GameMenuController.friendshipList(npc).getMessage(), skin);
        currentDialog.add(questsLabel).pad(20, 20, 20, 20).row();

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentDialog.remove();
                currentDialog = null;
            }
        });

        Table buttonTable = new Table(skin);
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Table getItemTable() {
        return itemTable;
    }

    public void setItemTable(Table itemTable) {
        this.itemTable = itemTable;
    }

    public Window getCurrentDialog() {
        return currentDialog;
    }

    public void setCurrentDialog(Window currentDialog) {
        this.currentDialog = currentDialog;
    }

    public InputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(InputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
        Gdx.input.setInputProcessor(inputProcessor);
        if (npc.getName() == NPCEnums.SEBASTIAN) {
            App.getCurrentGame().setSebastian_view(false);
        }
        if (npc.getName() == NPCEnums.ABIGAIL) {
            App.getCurrentGame().setAbigail_view(false);
        }
        if (npc.getName() == NPCEnums.HARVEY) {
            App.getCurrentGame().setHarvey_view(false);
        }
        if (npc.getName() == NPCEnums.LEAH) {
            App.getCurrentGame().setLeah_view(false);
        }
        if (npc.getName() == NPCEnums.ROBIN) {
            App.getCurrentGame().setRobin_view(false);
        }
    }
}
