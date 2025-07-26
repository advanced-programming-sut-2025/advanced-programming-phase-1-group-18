package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.MarketController;
import io.github.group18.Controller.MarniesRanchController;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Result;
import io.github.group18.Model.NPC;
import io.github.group18.enums.NPCEnums;

public class NPCDialogView {

    private Stage stage;
    private Skin skin;
    private Table itemTable;
    private Window currentDialog;
    private InputProcessor inputProcessor;
    private NPC npc;

    public NPCDialogView(InputProcessor inputProcessor, NPC npc) {
        this.stage = new Stage(new ScreenViewport());
        this.inputProcessor = inputProcessor;
        this.npc = npc;
        skin = GameAssetManager.getGameAssetManager().getSkin();
        createDialogUI();
    }

    private void createDialogUI() {
        if (currentDialog != null) {
            currentDialog.remove();
        }

        Window npcWindow = new Window("NPC Dialog", skin);
        npcWindow.setModal(true);

        Label npctalk = new Label(GameMenuController.meetNPC(npc).getMessage(), skin);

        Table headerTable = new Table(skin);
        headerTable.add(npctalk).pad(20, 20, 20, 20).row();

        npcWindow.add(headerTable).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(inputProcessor);
                npcWindow.remove();
                Gdx.input.setInputProcessor(inputProcessor);
                if (npc.getName() == NPCEnums.SEBASTIAN) {
                    App.getCurrentGame().setSebastian_dialog(false);
                }
                if (npc.getName() == NPCEnums.ABIGAIL) {
                    App.getCurrentGame().setAbigail_dialog(false);
                }
                if (npc.getName() == NPCEnums.HARVEY) {
                    App.getCurrentGame().setHarvey_dialog(false);
                }
                if (npc.getName() == NPCEnums.LEAH) {
                    App.getCurrentGame().setLeah_dialog(false);
                }
                if (npc.getName() == NPCEnums.ROBIN) {
                    App.getCurrentGame().setRobin_dialog(false);
                }
            }
        });
        npcWindow.add(closeButton).pad(20, 20, 20, 20);

        npcWindow.pack();
        npcWindow.setPosition(
            Gdx.graphics.getWidth() / 2 - npcWindow.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - npcWindow.getHeight() / 2
        );

        stage.addActor(npcWindow);
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
            App.getCurrentGame().setSebastian_dialog(false);
        }
        if (npc.getName() == NPCEnums.ABIGAIL) {
            App.getCurrentGame().setAbigail_dialog(false);
        }
        if (npc.getName() == NPCEnums.HARVEY) {
            App.getCurrentGame().setHarvey_dialog(false);
        }
        if (npc.getName() == NPCEnums.LEAH) {
            App.getCurrentGame().setLeah_dialog(false);
        }
        if (npc.getName() == NPCEnums.ROBIN) {
            App.getCurrentGame().setRobin_dialog(false);
        }
    }

}
