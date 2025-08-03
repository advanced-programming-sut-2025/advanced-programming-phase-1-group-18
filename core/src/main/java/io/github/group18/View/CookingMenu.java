package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.FoodCooking;
import io.github.group18.enums.CraftingRecipesEnums;
import io.github.group18.enums.FoodCookingEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class CookingMenu {

    private Stage stage;
    private Window window;
    private Skin skin;
    private boolean active;
    private GameMenuInputAdapter gameMenuInputAdapter;
    float scale;
    Table craftingItem;
    ArrayList<ImageButton> buttons;
    //Server-TODO
//    Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
    Label notify;

    public CookingMenu() {
        scale= 1.75F;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();
        window = new Window("Cooking Menu\n", skin);
        window.setSize(1000, 780);
        window.setPosition((float) Gdx.graphics.getWidth() /2-window.getWidth()/2,
            Gdx.graphics.getHeight()/2-window.getHeight()/2);

        Table table = new Table();
        Sprite bgMenu = new Sprite(GameAssetManager.menuBackground);
        Image bgMenuImage = new Image(GameAssetManager.getGameAssetManager().getBgCookingMenu());
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
        ImageButton.ImageButtonStyle BakedFish = createStyle("BakedFish");
        ImageButton.ImageButtonStyle Bread = createStyle("Bread");
        ImageButton.ImageButtonStyle Cookie = createStyle("Cookie");
        ImageButton.ImageButtonStyle DishOTheSea = createStyle("DishOTheSea");
        ImageButton.ImageButtonStyle FarmersLunch = createStyle("FarmersLunch");
        ImageButton.ImageButtonStyle Friedegg = createStyle("Friedegg");
        ImageButton.ImageButtonStyle FruitSalad = createStyle("FruitSalad");
        ImageButton.ImageButtonStyle HashBrowns = createStyle("HashBrowns");
        ImageButton.ImageButtonStyle MakiRoll = createStyle("MakiRoll");
        ImageButton.ImageButtonStyle MinersTreat = createStyle("MinersTreat");
        ImageButton.ImageButtonStyle Omelet = createStyle("Omelet");
        ImageButton.ImageButtonStyle Pancakes = createStyle("Pancakes");
        ImageButton.ImageButtonStyle Pizza = createStyle("Pizza");
        ImageButton.ImageButtonStyle PumpkinPie = createStyle("PumpkinPie");
        ImageButton.ImageButtonStyle RedPlate = createStyle("RedPlate");
        ImageButton.ImageButtonStyle Salad = createStyle("Salad");
        ImageButton.ImageButtonStyle SalmonDinner = createStyle("SalmonDinner");
        ImageButton.ImageButtonStyle SeaformPudding = createStyle("SeaformPudding");
        ImageButton.ImageButtonStyle Spaghetti = createStyle("Spaghetti");
        ImageButton.ImageButtonStyle SurvivalBurger = createStyle("SurvivalBurger");
        ImageButton.ImageButtonStyle Tortilla = createStyle("Tortilla");
        ImageButton.ImageButtonStyle TripleShotEspresso = createStyle("TripleShotEspresso");
        ImageButton.ImageButtonStyle VegetableMedley = createStyle("VegetableMedley");

        buttons = new ArrayList<>();
        ImageButton BakedFishButton = new ImageButton(BakedFish);
        ImageButton BreadButton = new ImageButton(Bread);
        ImageButton CookieButton = new ImageButton(Cookie);
        ImageButton DishOTheSeaButton = new ImageButton(DishOTheSea);
        ImageButton FarmersLunchButton = new ImageButton(FarmersLunch);
        ImageButton FriedeggButton = new ImageButton(Friedegg);
        ImageButton FruitSaladButton = new ImageButton(FruitSalad);
        ImageButton HashBrownsButton = new ImageButton(HashBrowns);
        ImageButton MakiRollButton = new ImageButton(MakiRoll);
        ImageButton MinersTreatButton = new ImageButton(MinersTreat);
        ImageButton OmeletButton = new ImageButton(Omelet);
        ImageButton PancakesButton = new ImageButton(Pancakes);
        ImageButton PizzaButton = new ImageButton(Pizza);
        ImageButton PumpkinPieButton = new ImageButton(PumpkinPie);
        ImageButton RedPlateButton = new ImageButton(RedPlate);
        ImageButton SaladButton = new ImageButton(Salad);
        ImageButton SalmonDinnerButton = new ImageButton(SalmonDinner);
        ImageButton SeaformPuddingButton = new ImageButton(SeaformPudding);
        ImageButton SpaghettiButton = new ImageButton(Spaghetti);
        ImageButton SurvivalBurgerButton = new ImageButton(SurvivalBurger);
        ImageButton TortillaButton = new ImageButton(Tortilla);
        ImageButton TripleShotEspressoButton = new ImageButton(TripleShotEspresso);
        ImageButton VegetableMedleyButton = new ImageButton(VegetableMedley);

        buttons.add(BakedFishButton);
        buttons.add(BreadButton);
        buttons.add(CookieButton);
        buttons.add(DishOTheSeaButton);
        buttons.add(FarmersLunchButton);
        buttons.add(FriedeggButton);
        buttons.add(FruitSaladButton);
        buttons.add(HashBrownsButton);
        buttons.add(MakiRollButton);
        buttons.add(MinersTreatButton);
        buttons.add(OmeletButton);
        buttons.add(PancakesButton);
        buttons.add(PizzaButton);
        buttons.add(PumpkinPieButton);
        buttons.add(RedPlateButton);
        buttons.add(SaladButton);
        buttons.add(SalmonDinnerButton);
        buttons.add(SeaformPuddingButton);
        buttons.add(SpaghettiButton);
        buttons.add(SurvivalBurgerButton);
        buttons.add(TortillaButton);
        buttons.add(TripleShotEspressoButton);
        buttons.add(VegetableMedleyButton);
        for (ImageButton button : buttons) {
            scale = 0.5F;
            button.setSize(button.getWidth()*scale, button.getHeight()*scale);
            button.setColor(1,1,1,0.5F);
        }

        addToolButtonListener(BakedFishButton, "BakedFish", skin, stage);
        addToolButtonListener(BreadButton, "Bread", skin, stage);
        addToolButtonListener(CookieButton, "Cookie", skin, stage);
        addToolButtonListener(DishOTheSeaButton, "DishOTheSea", skin, stage);
        addToolButtonListener(FarmersLunchButton, "FarmersLunch", skin, stage);
        addToolButtonListener(FriedeggButton, "Friedegg", skin, stage);
        addToolButtonListener(FruitSaladButton, "FruitSalad", skin, stage);
        addToolButtonListener(HashBrownsButton, "HashBrowns", skin, stage);
        addToolButtonListener(MakiRollButton, "MakiRoll", skin, stage);
        addToolButtonListener(MinersTreatButton, "MinersTreat", skin, stage);
        addToolButtonListener(OmeletButton, "Omelet", skin, stage);
        addToolButtonListener(PancakesButton, "Pancakes", skin, stage);
        addToolButtonListener(PizzaButton, "Pizza", skin, stage);
        addToolButtonListener(PumpkinPieButton, "PumpkinPie", skin, stage);
        addToolButtonListener(RedPlateButton, "RedPlate", skin, stage);
        addToolButtonListener(SaladButton, "Salad", skin, stage);
        addToolButtonListener(SalmonDinnerButton, "SalmonDinner", skin, stage);
        addToolButtonListener(SeaformPuddingButton, "SeaformPudding", skin, stage);
        addToolButtonListener(SpaghettiButton, "Spaghetti", skin, stage);
        addToolButtonListener(SurvivalBurgerButton, "SurvivalBurger", skin, stage);
        addToolButtonListener(TortillaButton, "Tortilla", skin, stage);
        addToolButtonListener(TripleShotEspressoButton, "TripleShotEspresso", skin, stage);
        addToolButtonListener(VegetableMedleyButton, "VegetableMedley", skin, stage);


        craftingItem = new Table();
        craftingItem.setFillParent(true);
        craftingItem.add(BakedFishButton).padTop(0).padLeft(0).left();
        craftingItem.add(BreadButton).padTop(0).padLeft(5).left();
        craftingItem.add(CookieButton).padTop(0).padLeft(5).left();
        craftingItem.add(DishOTheSeaButton).padTop(0).padLeft(5).left();
        craftingItem.add(FarmersLunchButton).padTop(0).padLeft(5).left();
        craftingItem.add(FriedeggButton).padTop(0).padLeft(5).left();
        craftingItem.add(FruitSaladButton).padTop(0).padLeft(5).left();
        craftingItem.add(HashBrownsButton).padTop(0).padLeft(5).left();
        craftingItem.add(MakiRollButton).padTop(0).padLeft(5).left();
        craftingItem.add(MinersTreatButton).padTop(0).padLeft(5).left().row();
        craftingItem.add(OmeletButton).padTop(100).padLeft(5).left();
        craftingItem.add(PancakesButton).padTop(100).padLeft(5).left();
        craftingItem.add(PizzaButton).padTop(100).padLeft(5).left();
        craftingItem.add(PumpkinPieButton).padTop(100).padLeft(5).left();
        craftingItem.add(RedPlateButton).padTop(100).padLeft(5).left();
        craftingItem.add(SaladButton).padTop(100).padLeft(5).left();
        craftingItem.add(SalmonDinnerButton).padTop(100).padLeft(5).left();
        craftingItem.add(SeaformPuddingButton).padTop(100).padLeft(5).left();
        craftingItem.add(SpaghettiButton).padTop(100).padLeft(5).left();
        craftingItem.add(SurvivalBurgerButton).padTop(100).padLeft(5).left().row();
        craftingItem.add(TortillaButton).padTop(100).padLeft(5).left();
        craftingItem.add(TripleShotEspressoButton).padTop(100).padLeft(5).left();
        craftingItem.add(VegetableMedleyButton).padTop(100).padLeft(5).left().row();
        ImageButton inventoryIcon = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getInventoryIconTexture())));
        craftingItem.add(inventoryIcon).padTop(100).padLeft(5).left();
        inventoryIcon.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               InventoryUI inventoryUI = new InventoryUI(skin);
               stage.addActor(inventoryUI.getInventoryWindow());
               inventoryUI.getInventoryWindow().setVisible(true);
               Gdx.input.setInputProcessor(inventoryUI.getInventoryWindow().getStage());
           }
        });
        ImageButton refrigeratorIcon = new ImageButton(new TextureRegionDrawable
            (new TextureRegion(GameAssetManager.getGameAssetManager().getRefrigeratorIconTexture())));
        craftingItem.add(refrigeratorIcon).padTop(100).padLeft(5).left();
        refrigeratorIcon.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               RefrigeratorUI refrigeratorUI = new RefrigeratorUI(skin);
               stage.addActor(refrigeratorUI.getInventoryWindow());
               refrigeratorUI.getInventoryWindow().setVisible(true);
               Gdx.input.setInputProcessor(refrigeratorUI.getInventoryWindow().getStage());
           }
        });

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
        //Server-TODO
//        ArrayList<Cookingrecipe> recipesEnums =App.getCurrentGame().
//            getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingRecipes();
//        for (Cookingrecipe recipesEnum : recipesEnums) {
//            FoodCookingEnums recipes = recipesEnum.getFood();
//            switch (recipes) {
//                case BakedFish -> buttons.get(0).setColor(Color.WHITE);
//                case Bread -> buttons.get(1).setColor(Color.WHITE);
//                case Cookie -> buttons.get(2).setColor(Color.WHITE);
//                case DishOTheSea -> buttons.get(3).setColor(Color.WHITE);
//                case FarmersLunch -> buttons.get(4).setColor(Color.WHITE);
//                case Friedegg -> buttons.get(5).setColor(Color.WHITE);
//                case FruitSalad -> buttons.get(6).setColor(Color.WHITE);
//                case HashBrowns -> buttons.get(7).setColor(Color.WHITE);
//                case MakiRoll -> buttons.get(8).setColor(Color.WHITE);
//                case MinersTreat -> buttons.get(9).setColor(Color.WHITE);
//                case Omelet -> buttons.get(10).setColor(Color.WHITE);
//                case Pancakes -> buttons.get(11).setColor(Color.WHITE);
//                case Pizza -> buttons.get(12).setColor(Color.WHITE);
//                case PumpkinPie -> buttons.get(13).setColor(Color.WHITE);
//                case RedPlate -> buttons.get(14).setColor(Color.WHITE);
//                case Salad -> buttons.get(15).setColor(Color.WHITE);
//                case SalmonDinner -> buttons.get(16).setColor(Color.WHITE);
//                case SeaformPudding -> buttons.get(17).setColor(Color.WHITE);
//                case Spaghetti -> buttons.get(18).setColor(Color.WHITE);
//                case SurvivalBurger -> buttons.get(19).setColor(Color.WHITE);
//                case Tortilla -> buttons.get(20).setColor(Color.WHITE);
//                case TripleShotEspresso -> buttons.get(21).setColor(Color.WHITE);
//            }
//        }
    }
    public void addToolButtonListener(ImageButton button, String foodName, Skin skin, Stage stage) {
        FoodCookingEnums info = FoodCookingEnums.valueOf(foodName);
        Dialog infoDialog = new Dialog("food info\n", skin);
        Dialog resultDialog = new Dialog("Result\n", skin);
        resultDialog.button("close");
        StringBuilder infoText = new StringBuilder();
        HashMap<String, Integer> ingredient = info.getIngredients();
        infoText.append(" »").append(foodName).append("\n");
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
        Dialog eatDialog = new Dialog("Eat Food", skin) {
            @Override
            public void result(Object object) {
                boolean confirmed = (boolean) object;
                if (confirmed) {
                    //Server-TODO
//                    System.out.println(App.getGameMenuController().eat(foodName));
                    Gdx.input.setInputProcessor(gameMenuInputAdapter);
                    setActive(false);
                    //Server-TODO
//                    App.getCurrentGame().getCurrentPlayer().eat();
                } else {
                    System.out.println("Cancelled");
                }
            }
        };
        eatDialog.button("yes", true);
        eatDialog.button("no", false);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoDialog.hide();
                StringBuilder resultText = new StringBuilder();
                //Server-TODO
//                Result result = App.getGameMenuController().cookingPrepare(foodName);
//                if(result.isSuccessful()){
//                    eatDialog.getContentTable().clear();
//                    resultText.append(result.getMessage());
//                    resultText.append("\nDo you want to eat?");
//                    eatDialog.text(resultText.toString());
//                    eatDialog.show(stage);
//                }else{
//                    resultText.append("Error: "+result.getMessage());
//                    resultDialog.getContentTable().clear();
//                    resultDialog.text(resultText.toString());
//                    resultDialog.show(stage);
//                }
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
        TextureRegionDrawable drawable = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
            getCookingAtlas().findRegion(name));
        drawable.setMinSize(drawable.getMinWidth()*scale, drawable.getMinHeight()*scale);
        style.up =drawable;
        return style;
    }
}
