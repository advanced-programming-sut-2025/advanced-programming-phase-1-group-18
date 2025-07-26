package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.enums.CraftingRecipesEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class CraftingMenu {

    private Stage stage;
    private Window window;
    private Skin skin;
    private boolean active;
    private GameMenuInputAdapter gameMenuInputAdapter;
    float scale;
    Table craftingItem;
    ArrayList<ImageButton> buttons;
    Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
    Label notify;

    public CraftingMenu() {
        scale= 1.5F;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();
        window = new Window("Crafting Menu", skin);
        window.setSize(1000, 780);
        window.setPosition((float) Gdx.graphics.getWidth() /2-window.getWidth()/2,
            Gdx.graphics.getHeight()/2-window.getHeight()/2);

        Table table = new Table();
        Sprite bgMenu = new Sprite(GameAssetManager.menuBackground);
        Image bgMenuImage = new Image(GameAssetManager.getGameAssetManager().getBgCraftingMenu());
        bgMenu.setSize(window.getWidth(), window.getHeight());
        table.add(bgMenuImage).expand().fill();
        ImageButton closeButton = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getExitTexture())));
        notify = new Label("nono", skin);
        window.getTitleTable().setHeight(window.getTitleTable().getHeight()*5);
        window.getTitleTable()
            .add(closeButton)
            .size(50, 50)
            .padRight(10)
            .padTop(30)
            .right();
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(gameMenuInputAdapter);
                setActive(false);
            }
        });
        ImageButton.ImageButtonStyle Bee_House = createStyle("BeeHouse");
        ImageButton.ImageButtonStyle Bomb = createStyle("Bomb");
        ImageButton.ImageButtonStyle Charcoal_Kiln = createStyle("CharcoalKiln");
        ImageButton.ImageButtonStyle Cheese_Press = createStyle("CheesePress");
        ImageButton.ImageButtonStyle Cherry_Bomb = createStyle("CherryBomb");
        ImageButton.ImageButtonStyle Dehydrator = createStyle("Dehydrator");
        ImageButton.ImageButtonStyle Deluxe_Scarecrow = createStyle("DeluxeScarecrow");
        ImageButton.ImageButtonStyle Fish_Smoker = createStyle("FishSmoker");
        ImageButton.ImageButtonStyle Furnace = createStyle("Furnace");
        ImageButton.ImageButtonStyle Grass_Starter = createStyle("GrassStarter");
        ImageButton.ImageButtonStyle Iridium_Sprinkler = createStyle("IridiumSprinkler");
        ImageButton.ImageButtonStyle Keg = createStyle("Keg");
        ImageButton.ImageButtonStyle Loom = createStyle("Loom");
        ImageButton.ImageButtonStyle Mayonnaise_Machine = createStyle("MayonnaiseMachine");
        ImageButton.ImageButtonStyle Mega_Bomb = createStyle("MegaBomb");
        ImageButton.ImageButtonStyle Mystic_Tree_Seed = createStyle("MysticTreeSeed");
        ImageButton.ImageButtonStyle Oil_Maker = createStyle("OilMaker");
        ImageButton.ImageButtonStyle Preserves_Jar = createStyle("PreservesJar");
        ImageButton.ImageButtonStyle Quality_Sprinkler = createStyle("QualitySprinkler");
        ImageButton.ImageButtonStyle Scarecrow = createStyle("Scarecrow");
        ImageButton.ImageButtonStyle Sprinkler = createStyle("Sprinkler");

        buttons = new ArrayList<>();
        ImageButton BeeHouse = new ImageButton(Bee_House);
        ImageButton BombButton = new ImageButton(Bomb);
        ImageButton CharcoalKilnButton = new ImageButton(Charcoal_Kiln);
        ImageButton CheesePressButton = new ImageButton(Cheese_Press);
        ImageButton CherryBombButton = new ImageButton(Cherry_Bomb);
        ImageButton DehydratorButton = new ImageButton(Dehydrator);
        ImageButton DeluxeScarecrowButton = new ImageButton(Deluxe_Scarecrow);
        ImageButton FishSmokerButton = new ImageButton(Fish_Smoker);
        ImageButton FurnaceButton = new ImageButton(Furnace);
        ImageButton GrassStarterButton = new ImageButton(Grass_Starter);
        ImageButton IridiumSprinklerButton = new ImageButton(Iridium_Sprinkler);
        ImageButton KegButton = new ImageButton(Keg);
        ImageButton LoomButton = new ImageButton(Loom);
        ImageButton MayonnaiseMachineButton = new ImageButton(Mayonnaise_Machine);
        ImageButton MegaBombButton = new ImageButton(Mega_Bomb);
        ImageButton MysticTreeSeedButton = new ImageButton(Mystic_Tree_Seed);
        ImageButton OilMakerButton = new ImageButton(Oil_Maker);
        ImageButton PreservesJarButton = new ImageButton(Preserves_Jar);
        ImageButton QualitySprinklerButton = new ImageButton(Quality_Sprinkler);
        ImageButton ScarecrowButton = new ImageButton(Scarecrow);
        ImageButton SprinklerButton = new ImageButton(Sprinkler);

        buttons.add(BeeHouse);
        buttons.add(BombButton);
        buttons.add(CherryBombButton);
        buttons.add(MegaBombButton);
        buttons.add(CharcoalKilnButton);
        buttons.add(CheesePressButton);
        buttons.add(DehydratorButton);
        buttons.add(DeluxeScarecrowButton);
        buttons.add(FishSmokerButton);
        buttons.add(FurnaceButton);
        buttons.add(GrassStarterButton);
        buttons.add(IridiumSprinklerButton);
        buttons.add(KegButton);
        buttons.add(LoomButton);
        buttons.add(MayonnaiseMachineButton);
        buttons.add(MysticTreeSeedButton);
        buttons.add(OilMakerButton);
        buttons.add(PreservesJarButton);
        buttons.add(QualitySprinklerButton);
        buttons.add(ScarecrowButton);
        buttons.add(SprinklerButton);
        for (ImageButton button : buttons) {
            scale = 0.5F;
            button.setSize(button.getWidth()*scale, button.getHeight()*scale);
            button.setColor(1,1,1,0.5F);
        }

        addToolButtonListener(BeeHouse, "BeeHouse", skin, stage);
        addToolButtonListener(BombButton, "Bomb", skin, stage);
        addToolButtonListener(CharcoalKilnButton, "CharcoalKiln", skin, stage);
        addToolButtonListener(CheesePressButton, "CheesePress", skin, stage);
        addToolButtonListener(CherryBombButton, "CherryBomb", skin, stage);
        addToolButtonListener(DehydratorButton, "Dehydrator", skin, stage);
        addToolButtonListener(DeluxeScarecrowButton, "DeluxeScarecrow", skin, stage);
        addToolButtonListener(FishSmokerButton, "FishSmoker", skin, stage);
        addToolButtonListener(FurnaceButton, "Furnace", skin, stage);
        addToolButtonListener(GrassStarterButton, "GrassStarter", skin, stage);
        addToolButtonListener(IridiumSprinklerButton, "IridiumSprinkler", skin, stage);
        addToolButtonListener(KegButton, "Keg", skin, stage);
        addToolButtonListener(LoomButton, "Loom", skin, stage);
        addToolButtonListener(MayonnaiseMachineButton, "MayonnaiseMachine", skin, stage);
        addToolButtonListener(MegaBombButton, "MegaBomb", skin, stage);
        addToolButtonListener(MysticTreeSeedButton, "MysticTreeSeed", skin, stage);
        addToolButtonListener(OilMakerButton, "OilMaker", skin, stage);
        addToolButtonListener(PreservesJarButton, "PreservesJar", skin, stage);
        addToolButtonListener(QualitySprinklerButton, "QualitySprinkler", skin, stage);
        addToolButtonListener(ScarecrowButton, "Scarecrow", skin, stage);
        addToolButtonListener(SprinklerButton, "Sprinkler", skin, stage);


        craftingItem = new Table();
        craftingItem.setFillParent(true);
        craftingItem.add(BeeHouse).padTop(0).padLeft(0).left();
        craftingItem.add(BombButton).padTop(0).padLeft(5).left();
        craftingItem.add(CherryBombButton).padTop(0).padLeft(5).left();
        craftingItem.add(MegaBombButton).padTop(0).padLeft(5).left();
        craftingItem.add(CharcoalKilnButton).padTop(0).padLeft(5).left();
        craftingItem.add(CheesePressButton).padTop(0).padLeft(5).left();
        craftingItem.add(DehydratorButton).padTop(0).padLeft(5).left();
        craftingItem.add(DeluxeScarecrowButton).padTop(0).padLeft(5).left();
        craftingItem.add(FishSmokerButton).padTop(0).padLeft(5).left();
        craftingItem.add(FurnaceButton).padTop(0).padLeft(5).left();
        craftingItem.add(GrassStarterButton).padTop(0).padLeft(5).left().row();
        craftingItem.add(IridiumSprinklerButton).padTop(100).padLeft(5).left();
        craftingItem.add(KegButton).padTop(100).padLeft(5).left();
        craftingItem.add(LoomButton).padTop(100).padLeft(5).left();
        craftingItem.add(MayonnaiseMachineButton).padTop(100).padLeft(5).left();
        craftingItem.add(MysticTreeSeedButton).padTop(100).padLeft(5).left();
        craftingItem.add(OilMakerButton).padTop(100).padLeft(5).left();
        craftingItem.add(PreservesJarButton).padTop(100).padLeft(5).left();
        craftingItem.add(QualitySprinklerButton).padTop(100).padLeft(5).left();
        craftingItem.add(ScarecrowButton).padTop(100).padLeft(5).left();
        craftingItem.add(SprinklerButton).padTop(100).padLeft(5).left().row();


        window.add(table).expand().fill();
        window.setModal(false);
        window.setMovable(false);
        craftingItem.setZIndex(window.getZIndex()+1);


        // اضافه کردن پنجره به stage
        stage.addActor(window);
        stage.addActor(craftingItem);
//        stage.addActor(closeButton);
    }

    public void render() {
        if (active) {
            adjustButtons();
            if(craftingItem.getZIndex()< window.getZIndex()){
                craftingItem.setZIndex(window.getZIndex()+1);
            }
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
        }
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public void adjustButtons() {
        ArrayList<CraftingRecipesEnums> recipesEnums =App.getCurrentGame().
            getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCraftingRecipes();
        for (CraftingRecipesEnums recipesEnum : recipesEnums) {
            switch (recipesEnum) {
                case BeeHouse -> buttons.get(0).setColor(Color.WHITE);
                case Bomb -> buttons.get(1).setColor(Color.WHITE);
                case CherryBomb -> buttons.get(2).setColor(Color.WHITE);
                case MegaBomb -> buttons.get(3).setColor(Color.WHITE);
                case CharcoalKiln -> buttons.get(4).setColor(Color.WHITE);
                case CheesePress -> buttons.get(5).setColor(Color.WHITE);
                case Dehydrator -> buttons.get(6).setColor(Color.WHITE);
                case DeluxeScarecrow -> buttons.get(7).setColor(Color.WHITE);
                case FishSmoker -> buttons.get(8).setColor(Color.WHITE);
                case Furnace -> buttons.get(9).setColor(Color.WHITE);
                case GrassStarter -> buttons.get(10).setColor(Color.WHITE);
                case IridiumSprinkler -> buttons.get(11).setColor(Color.WHITE);
                case Keg -> buttons.get(12).setColor(Color.WHITE);
                case Loom -> buttons.get(13).setColor(Color.WHITE);
                case MayonnaiseMachine -> buttons.get(14).setColor(Color.WHITE);
                case MysticTreeSeed -> buttons.get(15).setColor(Color.WHITE);
                case OilMaker -> buttons.get(16).setColor(Color.WHITE);
                case PreservesJar -> buttons.get(17).setColor(Color.WHITE);
                case QualitySprinkler -> buttons.get(18).setColor(Color.WHITE);
                case Scarecrow -> buttons.get(19).setColor(Color.WHITE);
                case Sprinkler -> buttons.get(20).setColor(Color.WHITE);
            }
        }
    }

    public void addToolButtonListener( ImageButton button,  String toolName,  Skin skin,  Stage stage) {
        CraftingRecipesEnums info = CraftingRecipesEnums.valueOf(toolName);
        Dialog infoDialog = new Dialog("craft info\n", skin);
        Dialog resultDialog = new Dialog("Result\n", skin);
        resultDialog.button("close");
        StringBuilder infoText = new StringBuilder();
        HashMap<String, Integer> ingredient = info.getIngredients();
        infoText.append(" »").append(toolName).append("\n");
        infoText.append("  Ingredient:    \n");
        for (String key : ingredient.keySet()) {
            infoText.append("  ").append(key).append(" : ").append(ingredient.get(key)).append("  \n");
        }
        Label content = new Label(infoText.toString(), GameAssetManager.getGameAssetManager().getSkinNormal());
        content.setColor(Color.BLACK);
        content.setFontScale(2f);
        infoDialog.text(content);
        infoDialog.setModal(false);
        infoDialog.getContentTable().setBackground(skin.getDrawable("white"));
        infoDialog.getContentTable().setColor(1f, 0.8f, 0.2f, 1f);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoDialog.hide();
                StringBuilder resultText = new StringBuilder();
                Result result = App.getGameMenuController().craftingCraft(toolName);
                if(result.isSuccessful()){
                    resultText.append(result.getMessage());
                    if (info.isPlaceable()){
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl())
                            .setPlacingItem(true);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl())
                            .setCraftingInHand(new CraftingItem(info.name()));
                    }
                }else{
                    resultText.append("Error: "+result.getMessage());
                }
                resultDialog.getContentTable().clear();
                resultDialog.text(resultText.toString());
                resultDialog.show(stage);
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(pointer == -1) {
                    infoDialog.show(stage);
                    infoDialog.setPosition(button.getX()+100, button.getY()+100);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(pointer == -1) { // یعنی فقط hover بوده
                    infoDialog.hide();
                }
            }
        });
    }
    public void showToolInfoDialog(String toolName, CraftingRecipesEnums toolInfo, Skin skin, Stage stage) {
        // ساخت Pixmap و بکگراند خردلی (یک بار بهتر است global ساخته شود)
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1f, 0.8f, 0.2f, 1f); // رنگ خردلی
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        Drawable mustardBg = new TextureRegionDrawable(new TextureRegion(texture));
        pixmap.dispose();

        Dialog infoDialog = new Dialog("craft info", skin);
        infoDialog.getContentTable().setBackground(mustardBg);


        infoDialog.text(toolInfo.name()+"\n"+toolInfo.getIngredients()+"\n"+toolInfo.getPrice());

        infoDialog.button("close");

        infoDialog.show(stage);
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

    public GameMenuInputAdapter getGameMenuInputAdapter() {
        return gameMenuInputAdapter;
    }

    public void setGameMenuInputAdapter(GameMenuInputAdapter gameMenuInputAdapter) {
        this.gameMenuInputAdapter = gameMenuInputAdapter;
    }

    public ImageButton.ImageButtonStyle createStyle(String name) {
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        TextureRegionDrawable drawable = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().getCraftingAtlas().findRegion(name));
        drawable.setMinSize(drawable.getMinWidth()*scale, drawable.getMinHeight()*scale);
        style.up =drawable;
        return style;
    }
}
