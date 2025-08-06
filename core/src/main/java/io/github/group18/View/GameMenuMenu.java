package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.enums.GameMenuCommands;
import io.github.group18.enums.LoginMenuCommands;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import io.github.group18.Controller.TradeMenuController;
import io.github.group18.enums.MarketMenuEnums;

public class GameMenuMenu extends AppMenu implements Screen {

    private GameMenuController controller = new GameMenuController();
    private final TradeMenuController tradeController = new TradeMenuController();

    private Stage stage;
    private final TextButton startNewGame;
    private final TextButton loadGame;
    private final TextButton exitGame;
    private final TextButton terminateGame;
    private final Label MenuTitle;
    private final Table menuTable;
    private Table savedGameTable;
    private Skin skin;
    private Texture background;
    private int gameWidth = Gdx.graphics.getWidth();
    private int gameHeight = Gdx.graphics.getHeight();

    public GameMenuMenu(GameMenuController controller, Skin skin) {
        this.background = GameAssetManager.getBackground();
        this.skin = skin;
        this.controller = controller;
        this.startNewGame = new TextButton("Start New Game", skin);
        this.loadGame = new TextButton("Load Game", skin);
        this.exitGame = new TextButton("Exit", skin);
        this.terminateGame = new TextButton("Terminate", skin);
        this.MenuTitle = new Label("Game Menu", skin, "title");
        this.menuTable = new Table();
        this.savedGameTable = new Table();

    }


    public void check(String input, Scanner scanner, GameController gameController) {
        if (controller == null) {
            controller = new GameMenuController();
        }
        if (GameMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (GameMenuCommands.GameNew.getMather(input) != null) {
        } else if (GameMenuCommands.ExitGame.getMather(input) != null) {
            System.out.println(controller.exitGame(ClientModel.getPlayer()));
        } else if (GameMenuCommands.VoteTerminateGame.getMather(input) != null) {
            System.out.println(controller.voteTerminateGame(scanner, ClientModel.getPlayer()));
        } else if (GameMenuCommands.ShowPlant.getMather(input) != null) {
            System.out.println(controller.showPlant(Integer.parseInt(GameMenuCommands.ShowPlant.getMather(input).group(1)), Integer.parseInt(GameMenuCommands.ShowPlant.getMather(input).group(2)), ClientModel.getPlayer()));
        } else if (GameMenuCommands.HowMuchWater.getMather(input) != null) {
            System.out.println(controller.howMuchWater(ClientModel.getPlayer()));
        } else if (GameMenuCommands.TIME.getMather(input) != null) {
            System.out.println(controller.time(ClientModel.getPlayer()));
        } else if (GameMenuCommands.DATE.getMather(input) != null) {
            System.out.println(controller.date(ClientModel.getPlayer()));
        } else if (GameMenuCommands.DATETIME.getMather(input) != null) {
            System.out.println(controller.dateTime(ClientModel.getPlayer()));
        } else if (GameMenuCommands.CHEAT_ADVANCE_DATE.getMather(input) != null) {
            System.out.println(controller.cheatAdvanceDate(Integer.parseInt(GameMenuCommands.CHEAT_ADVANCE_DATE.
                getMather(input).group(1).trim()), gameController.getGameMenu(), gameController.getGameMenu().getGameView(), ClientModel.getPlayer()));
        } else if (GameMenuCommands.CHEAT_ADVANCE_TIME.getMather(input) != null) {
            System.out.println(controller.cheatAdvanceTime(Integer.parseInt(GameMenuCommands.CHEAT_ADVANCE_TIME.getMather(input).group(1)), gameController.getGameMenu(), gameController.getGameMenu().getGameView(), ClientModel.getPlayer()));
        } else if (GameMenuCommands.SEASON.getMather(input) != null) {
            System.out.println(controller.season(ClientModel.getPlayer()));
        } else if (GameMenuCommands.CHEAT_THOR.getMather(input) != null) {
            System.out.println(controller.cheatThor(Integer.parseInt(GameMenuCommands.CHEAT_THOR.getMather(input).group(1)),
                Integer.parseInt(GameMenuCommands.CHEAT_THOR.getMather(input).group(2)), ClientModel.getPlayer()));
        } else if (GameMenuCommands.WEATHER.getMather(input) != null) {
            System.out.println(controller.weather(ClientModel.getPlayer()));
        } else if (GameMenuCommands.WEATHER_FORECAST.getMather(input) != null) {
            System.out.println(controller.weatherForecast(ClientModel.getPlayer()));
        } else if (GameMenuCommands.CHEAT_WEATHER_SET.getMather(input) != null) {
            System.out.println(controller.cheatWeather(GameMenuCommands.CHEAT_WEATHER_SET.getMather(input).group(1), ClientModel.getPlayer()));
        } else if (GameMenuCommands.GREENHOUSE_BUILD.getMather(input) != null) {
            System.out.println(controller.greenHouseBiuld(ClientModel.getPlayer()));
        } else if (GameMenuCommands.PrintMap.getMather(input) != null) {
            controller.printMap(Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(1)),
                Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(2)),
                Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(3)),
                Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(4)));
        } else if (GameMenuCommands.HelpReadingMap.getMather(input) != null) {
            controller.helpReadingMap();
        } else if (GameMenuCommands.EnergyShow.getMather(input) != null) {
            controller.energyShow(ClientModel.getPlayer());
        } else if (GameMenuCommands.EnergySet.getMather(input) != null) {
            System.out.println(controller.energySet(Integer.parseInt(GameMenuCommands.EnergySet.getMather(input).group(1)), ClientModel.getPlayer()));
        } else if (GameMenuCommands.EnergyUnlimited.getMather(input) != null) {
            controller.energyUnlimited(ClientModel.getPlayer());
        } else if (GameMenuCommands.Walk.getMather(input) != null) {
            controller.walk(Integer.parseInt(GameMenuCommands.Walk.getMather(input).group(1)),
                Integer.parseInt(GameMenuCommands.Walk.getMather(input).group(2)), ClientModel.getPlayer());
        }
    }
//        else if (GameMenuCommands.TOOLEQUIP.getMather(input) != null) {
//            String name = GameMenuCommands.TOOLEQUIP.getMather(input).group(1);
////            System.out.println(controller.toolEquip(name));
//        } else if (GameMenuCommands.TOOLSSHOWCURRENT.getMather(input) != null) {
//            System.out.println(controller.toolsShowCurrent());
//        } else if (GameMenuCommands.TOOLSSHOWAVAILABLE.getMather(input) != null) {
////            System.out.println(controller.toolShowAvailable());
//        } else if (GameMenuCommands.TOOLSUSE.getMather(input) != null) {
//            String direction = GameMenuCommands.TOOLSUSE.getMather(input).group(1);
////            System.out.println(controller.toolsUse(direction));
//        } else if (GameMenuCommands.COOKINGREFRIGERATOR.getMather(input) != null) {
//            String action = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(1);
//            String item = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(2);
//            try {
////                System.out.println(controller.cookingRefrigerator(action, item));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (GameMenuCommands.COOKINGSHOWRECIPES.getMather(input) != null) {
//            System.out.println(controller.cookingShowRecipes());
//        } else if (GameMenuCommands.COOKINGPREPARE.getMather(input) != null) {
//            System.out.println(controller.cookingPrepare(GameMenuCommands.COOKINGPREPARE.getMather(input).group(1)));
//        } else if (GameMenuCommands.EAT.getMather(input) != null) {
//            System.out.println(controller.eat(GameMenuCommands.EAT.getMather(input).group(1)));
//        } else if (GameMenuCommands.SELL.getMather(input) != null) {
////            System.out.println(controller.sell(GameMenuCommands.SELL.getMather(input).group(1), GameMenuCommands.SELL.getMather(input).group(2)));
//        } else if (GameMenuCommands.MEETNPC.getMather(input) != null) {
////            System.out.println(controller.meetNPC(GameMenuCommands.MEETNPC.getMather(input).group(1)));
//        } else if (GameMenuCommands.GIFTNPC.getMather(input) != null) {
////            System.out.println(controller.giftNPC(GameMenuCommands.GIFTNPC.getMather(input).group(1), GameMenuCommands.GIFTNPC.getMather(input).group(2)));
//        } else if (GameMenuCommands.FRIENDSHIPNPCLIST.getMather(input) != null) {
////            System.out.println(controller.friendshipList());
//        } else if (GameMenuCommands.QUESTSLIST.getMather(input) != null) {
////            System.out.println(controller.questsList());
//        } else if (GameMenuCommands.QUESTSFINISH.getMather(input) != null) {
////            System.out.println(controller.questsFinish(Integer.parseInt(GameMenuCommands.QUESTSFINISH.getMather(input).group(1))));
//        } else if (GameMenuCommands.FriendShip.getMather(input) != null) {
//            System.out.println(controller.friendships());
//        } else if (GameMenuCommands.Talk.getMather(input) != null) {
//            System.out.println(controller.talk(GameMenuCommands.Talk.getMather(input).group(1),
//                GameMenuCommands.Talk.getMather(input).group(2)));
//        } else if (GameMenuCommands.TalkHistory.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.TalkHistory.getMather(input);
//            System.out.println(controller.talkHistory(matcher.group(1)));
//        } else if (GameMenuCommands.Gift.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.Gift.getMather(input);
////            System.out.println(controller.gift(
////                matcher.group(1),
////                Integer.parseInt(matcher.group(3)),
////                matcher.group(2)));
//        } else if (GameMenuCommands.GiftList.getMather(input) != null) {
//            System.out.println(controller.giftList());
//        } else if (GameMenuCommands.GiftRate.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.GiftRate.getMather(input);
//            System.out.println(controller.giftRate(
//                Integer.parseInt(matcher.group(1)),
//                Integer.parseInt(matcher.group(2))));
//        } else if (GameMenuCommands.GiftHistory.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.GiftHistory.getMather(input);
//            System.out.println(controller.giftHistory(matcher.group(1)));
//        } else if (GameMenuCommands.Hug.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.Hug.getMather(input);
//            System.out.println(controller.hug(matcher.group(1)));
//        } else if (GameMenuCommands.Flower.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.Flower.getMather(input);
////            System.out.println(controller.flower(matcher.group(1)));
//        } else if (GameMenuCommands.CRAFT_INFO.getMather(input) != null) {
//            System.out.println(controller.craftInfo(GameMenuCommands.CRAFT_INFO.getMather(input).group(1), gameController));
//        } else if (GameMenuCommands.SHOW_RECIPES.getMather(input) != null) {
//            System.out.println(controller.craftingShowRecipes());
//        } else if (GameMenuCommands.CRAFT_ITEM.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.CRAFT_ITEM.getMather(input);
//            System.out.println(controller.craftingCraft(matcher.group(1).trim()));
//        } else if (GameMenuCommands.PLACE_ITEM.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.PLACE_ITEM.getMather(input);
//            String name = matcher.group(1).trim();
//            String destination = matcher.group(2).trim();
////            System.out.println(controller.placeItem(name, destination));
//        } else if (GameMenuCommands.CHEAT_ADD_ITEM.getMather(input) != null) {
//            Matcher matcher = GameMenuCommands.CHEAT_ADD_ITEM.getMather(input);
//            String name = matcher.group(1).trim();
//            int count = Integer.parseInt(matcher.group(2));
//            System.out.println(controller.cheatAddItem(name, count));
//        } else if (GameMenuCommands.Fishing.getMather(input) != null) {
////            System.out.println(controller.fishing(GameMenuCommands.Fishing.getMather(input).group(1)));
//        } else if (GameMenuCommands.ArtisanUse.getMather(input) != null) {
//            String[] parts = input.split("\\s+");
//            if (parts.length >= 3 && parts[0].equals("artisan") && parts[1].equals("use")) {
//                String artisanName = parts[2];
//                ArrayList<String> items = new ArrayList<>();
//                for (int i = 3; i < parts.length; i++) {
//                    items.add(parts[i]);
//                }
////                System.out.println(controller.artisanUse(artisanName, items));
//            }
//        } else if (LoginMenuCommands.MenuEnter.getMather(input) != null) {
//            String menuName = LoginMenuCommands.MenuEnter.getMather(input).group(1);
//            controller.menuEnter(menuName);
//        } else if (GameMenuCommands.ArtisanGet.getMather(input) != null) {
////            System.out.println(controller.artisanGet(GameMenuCommands.ArtisanGet.getMather(input).group(1)));
//
//        } else if (GameMenuCommands.WHOAMI.getMather(input) != null) {
//            System.out.println(controller.whoAmI());
//        } else if (GameMenuCommands.ASKMARRIAGE.getMather(input) != null) {
//            String username = GameMenuCommands.ASKMARRIAGE.getMather(input).group(1);
//            String ring = GameMenuCommands.ASKMARRIAGE.getMather(input).group(2);
//            System.out.println(controller.askMarriage(username, ring));
//        } else if (GameMenuCommands.RESPOND.getMather(input) != null) {
//            String acceptOrReject = GameMenuCommands.RESPOND.getMather(input).group(1);
//            String username = GameMenuCommands.RESPOND.getMather(input).group(2);
//            System.out.println(controller.response(acceptOrReject, username));
//        } else if (GameMenuCommands.SHOWMYMARRIAGEPROPOSALS.getMather(input) != null) {
//            controller.showMyMarriageProposals();
//        } else if (GameMenuCommands.STARTTRADE.getMather(input) != null) {
//            tradeController.startTrade();
//        } else if (GameMenuCommands.TRADING.getMather(input) != null) {
//            String username = GameMenuCommands.TRADING.getMather(input).group(1);
//            String type = GameMenuCommands.TRADING.getMather(input).group(2);
//            String item = GameMenuCommands.TRADING.getMather(input).group(3);
//            int amount = Integer.parseInt(GameMenuCommands.TRADING.getMather(input).group(4));
//            String price = (GameMenuCommands.TRADING.getMather(input).group(5));
//            String targetItem = GameMenuCommands.TRADING.getMather(input).group(6);
//            String targetAmount = GameMenuCommands.TRADING.getMather(input).group(7);
//            if (price == null && targetItem != null && targetAmount != null) {
//                int finalAmount = Integer.parseInt(targetAmount);
////                System.out.println(tradeController.tradeWithItem(username, type, item, amount, targetItem, finalAmount));
//            }
//            if (targetItem == null && price != null) {
//                int finalPrice = Integer.parseInt(price);
////                System.out.println(tradeController.tradeWithMoney(username, type, item, amount, finalPrice));
//            } else {
//                System.out.println("You can not put both!");
//            }
//        } else if (GameMenuCommands.TRADERESPONSE.getMather(input) != null) {
//            int id = Integer.parseInt(GameMenuCommands.TRADERESPONSE.getMather(input).group(2));
//            String acceptOrReject = GameMenuCommands.TRADERESPONSE.getMather(input).group(1);

    /// /            System.out.println(tradeController.tradeResponse(acceptOrReject, id));
//        } else if (GameMenuCommands.TRADEHISTORY.getMather(input) != null) {
//            tradeController.tradeHistory();
//        }
//        // new Trade
//        else if (GameMenuCommands.TRADELIST.getMather(input) != null) {
//            tradeController.tradeList();
//        }
//        //animalssssss ..............
//        else if (GameMenuCommands.SHEPHERDOUT.getMather(input) != null) {
//            String nameOfAnimal = GameMenuCommands.SHEPHERDOUT.getMather(input).group(1);
//            int xOfAnimalPlacing = Integer.parseInt(GameMenuCommands.SHEPHERDOUT.getMather(input).group(2));
//            int yOfAnimalPlacing = Integer.parseInt(GameMenuCommands.SHEPHERDOUT.getMather(input).group(3));
//            System.out.println(controller.shepherdOutAnimals(nameOfAnimal, xOfAnimalPlacing, yOfAnimalPlacing));
//        } else if (GameMenuCommands.SHEPHERDIN.getMather(input) != null) {
//            String nameofAnimal = GameMenuCommands.SHEPHERDIN.getMather(input).group(1);
//            System.out.println(controller.shepherdInAnimals(nameofAnimal));
//        } else if (GameMenuCommands.FEEDHAY.getMather(input) != null) {
//            String nameOfAnimal = GameMenuCommands.FEEDHAY.getMather(input).group(1);
//            System.out.println(controller.feedHay(nameOfAnimal));
//        } else if (GameMenuCommands.ANIMALSINFORMATIONS.getMather(input) != null) {
//            System.out.println(controller.animals());
//        } else if (GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input) != null) {
//            String name = GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input).group(1);
//            int amount = Integer.parseInt(GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input).group(2));
//            System.out.println(controller.cheatSetFriendship(name, amount));
//        } else if (GameMenuCommands.PET.getMather(input) != null) {
//            String name = GameMenuCommands.PET.getMather(input).group(1);
//            System.out.println(controller.pet(name));
//        } else if (GameMenuCommands.SELL.getMather(input) != null) {
//            String name = GameMenuCommands.SELL.getMather(input).group(1);
//            System.out.println(controller.sellAnimal(name));
//        } else if (GameMenuCommands.CHEATADD.getMather(input) != null) {
//            controller.cheatAdd(Integer.parseInt(MarketMenuEnums.CHEATADD.getMather(input).group(1)));
//        } else if (GameMenuCommands.SHOW_RECIPES.getMather(input) != null) {
//            controller.showMyMarriageProposals();
//        } else {
//            System.out.println("Invalid command");
//        }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


// Setup main menu table
        menuTable.setFillParent(true);
        menuTable.center();

// Add game title to menu
        menuTable.add(MenuTitle).colspan(2).padBottom(100);
        menuTable.row();

// Add "Start New Game" button
        menuTable.add(startNewGame).colspan(2).padBottom(10).width(500);
        menuTable.row();

// Create outer table for saved games with background and padding
        Table outerSavedGame = new Table(skin);
        outerSavedGame.setBackground(skin.getDrawable("white"));
        outerSavedGame.pad(5);

// Add "saved Games" label to inner table
        Label savedGameLabel = new Label("Saved Game: \n NOTHING", skin);
        savedGameLabel.setColor(Color.BLACK);
        savedGameTable.add(savedGameLabel).colspan(2).padBottom(10).center();
        savedGameTable.row();

// Add savedGameTable to outerSavedGame table
        outerSavedGame.add(savedGameTable);

// Add outerSavedGame to menu table
        menuTable.add(outerSavedGame).colspan(2).padBottom(10).width(500);
        menuTable.row();

// Add load, exit, and terminate buttons
        menuTable.add(loadGame).colspan(2).padBottom(10).width(500);
        menuTable.row();

        menuTable.add(exitGame).colspan(2).padBottom(10).width(500);
        menuTable.row();

        menuTable.add(terminateGame).colspan(2).padBottom(10).width(500);

// Finally add menuTable to stage
        stage.addActor(menuTable);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getMain().getBatch().begin();
        Main.getMain().getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getMain().getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleGameMenuButtons(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public GameMenuController getController() {
        return controller;
    }

    public void setController(GameMenuController controller) {
        this.controller = controller;
    }

    public TradeMenuController getTradeController() {
        return tradeController;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getStartNewGame() {
        return startNewGame;
    }

    public TextButton getLoadGame() {
        return loadGame;
    }

    public TextButton getExitGame() {
        return exitGame;
    }

    public TextButton getTerminateGame() {
        return terminateGame;
    }

    public Label getMenuTitle() {
        return MenuTitle;
    }

    public Table getMenuTable() {
        return menuTable;
    }

    public Table getSavedGameTable() {
        return savedGameTable;
    }

    public void setSavedGameTable(Table savedGameTable) {
        this.savedGameTable = savedGameTable;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    @Override
    public void check(Scanner scanner) {

    }
}
