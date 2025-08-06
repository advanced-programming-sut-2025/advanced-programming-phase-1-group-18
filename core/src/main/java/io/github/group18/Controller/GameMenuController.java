package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.google.gson.Gson;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.*;
import io.github.group18.Model.Satl;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.*;
import io.github.group18.enums.*;
import io.github.group18.enums.Menu;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Field;
import java.security.cert.TrustAnchor;
import java.sql.Struct;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;

public class GameMenuController implements ShowCurrentMenu, MenuEnter {

    public void handleGameMenuButtons(GameMenuMenu view) {
        if (view != null) {
            if (view.getExitGame().isPressed()) {
                System.exit(0);
//                exitGame();
            } else if (view.getTerminateGame().isPressed()) {
//                voteTerminateGame(new Scanner(System.in));
            } else if (view.getLoadGame().isPressed()) {
                loadGame();
            } else if (view.getStartNewGame().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new LobbyView(new LobbyController(), view.getSkin()));
            }
        }
    }

    @Override
    public void menuEnter(String menuName, Player playerrr) {
        Player currentPlayer = playerrr;
        if (isFainted(playerrr)) {
            System.out.println("You are fainted!");
        }
        //from registermenu we can move to loginmenu
        menuName = menuName.toLowerCase();

        Map<String, int[]> shopHours = Map.of(
            "blacksmithmenu", new int[]{9, 16},
            "carpentersshopmenu", new int[]{9, 20},
            "fishshopmenu", new int[]{9, 17},
            "jojamartmenu", new int[]{9, 23},
            "marniesranchmenu", new int[]{9, 16},
            "pirresgeneralstoremenu", new int[]{9, 17},
            "thestardropsaloonmenu", new int[]{12, 24}
        );

        Map<String, int[]> shopPositions = Map.of(
            "blacksmithmenu", new int[]{ClientModel.getBlackSmithTopLeftx(), ClientModel.getBlackSmithTopLefty(), ClientModel.getBlackSmithWidth(), ClientModel.getBlackSmithHeight()},
            "carpentersshopmenu", new int[]{ClientModel.getCarpentersShopTopLeftx(), ClientModel.getCarpentersShopTopLefty(), ClientModel.getCarpentersShopWidth(), ClientModel.getCarpentersShopHeight()},
            "fishshopmenu", new int[]{ClientModel.getFishShopTopLeftx(), ClientModel.getFishShopTopLefty(), ClientModel.getFishShopWidth(), ClientModel.getFishShopHeight()},
            "jojamartmenu", new int[]{ClientModel.getJojoMartTopLeftx(), ClientModel.getJojoMartTopLefty(), ClientModel.getJojoMartWidth(), ClientModel.getJojoMartHeight()},
            "marniesranchmenu", new int[]{ClientModel.getMarniesRanchTopLeftx(), ClientModel.getMarniesRanchTopLefty(), ClientModel.getMarniesRanchWidth(), ClientModel.getMarniesRanchHeight()},
            "pirresgeneralstoremenu", new int[]{ClientModel.getPierresGeneralStoreTopLeftx(), ClientModel.getPierresGeneralStoreTopLefty(), ClientModel.getPierresGeneralStoreWidth(), ClientModel.getPierresGeneralStoreHeight()},
            "thestardropsaloonmenu", new int[]{ClientModel.getTheStardropSaloonTopLeftx(), ClientModel.getTheStardropSaloonTopLefty(), ClientModel.getTheStardropSaloonWidth(), ClientModel.getTheStardropSaloonHeight()}
        );

        if (shopHours.containsKey(menuName) && shopPositions.containsKey(menuName)) {
            int currentHour = App.getCurrentGame().getCurrentDateTime().getHour();
            int[] hours = shopHours.get(menuName);
            int[] position = shopPositions.get(menuName);

            if (currentHour >= hours[0] && currentHour <= hours[1]) {
                if ((currentPlayer.getX() >= position[0] && currentPlayer.getX() <= position[0] + position[2]) &&
                    (currentPlayer.getY() >= position[1] && currentPlayer.getY() <= position[1] + position[3])) {
                    switch (menuName) {
                        case "blacksmithmenu":
                            App.setCurrentMenu(Menu.BlacksmithMenu);
                            System.out.println("You are now in BlacksmithMenu!");
                            break;
                        case "carpentersshopmenu":
                            App.setCurrentMenu(Menu.CarpentersshopMenu);
                            System.out.println("You are now in CarpentersshopMenu!");
                            break;
                        case "fishshopmenu":
                            App.setCurrentMenu(Menu.FishshopMenu);
                            System.out.println("You are now in FishshopMenu!");
                            break;
                        case "jojamartmenu":
                            App.setCurrentMenu(Menu.JojamartMenu);
                            System.out.println("You are now in JojamartMenu!");
                            break;
                        case "marniesranchmenu":
                            App.setCurrentMenu(Menu.MarniesranchMenu);
                            System.out.println("You are now in MarniesranchMenu!");
                            break;
                        case "pirresgeneralstoremenu":
                            App.setCurrentMenu(Menu.PirresgeneralstoreMenu);
                            System.out.println("You are now in PirresgeneralstoreMenu!");
                            break;
                        case "thestardropsaloonmenu":
                            App.setCurrentMenu(Menu.ThestardropsaloonMenu);
                            System.out.println("You are now in ThestardropsaloonMenu!");
                            break;
                        default:
                            System.out.println("Invalid menu");
                            break;
                    }
                } else {
                    System.out.println("You have to be in the correct shop area to enter " + menuName);
                }
            } else {
                System.out.println(menuName + " is open between " + hours[0] + " and " + hours[1]);
            }
        } else {
            System.out.println("Invalid menu");
        }

    }


    public static void checkSkilRecipe(Player player) {
        Player currentPlayer = player;
        if (currentPlayer.getForagingSkill().getLevel() >= 150) {
            boolean cr1 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.CharcoalKiln) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.CharcoalKiln);
            }
        }
        //VegetableMedley
        if (currentPlayer.getForagingSkill().getLevel() >= 250) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.VegetableMedley) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.VegetableMedley);
                cookingrecipe.setPrice(120);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
        }
        //SurvivalBurger
        if (currentPlayer.getForagingSkill().getLevel() >= 350) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.SurvivalBurger) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.SurvivalBurger);
                cookingrecipe.setPrice(180);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
        }
        if (currentPlayer.getForagingSkill().getLevel() >= 450) {
            boolean cr1 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.MysticTreeSeed) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.MysticTreeSeed);
            }
        }
        //FarmersLunch
        if (currentPlayer.getFarmingSkill().getLevel() >= 150) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.FarmersLunch) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.FarmersLunch);
                cookingrecipe.setPrice(150);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
        }
        //FarmersLunch
        if (currentPlayer.getFarmingSkill().getLevel() >= 150) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.FarmersLunch) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.FarmersLunch);
                cookingrecipe.setPrice(150);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }

            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.Sprinkler) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.BeeHouse);
            }
            boolean cr3 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.BeeHouse) {
                    cr3 = true;
                    break;
                }
            }
            if (!cr3) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.BeeHouse);
            }
        }
        if (currentPlayer.getFarmingSkill().getLevel() >= 250) {
            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.QualitySprinkler) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.QualitySprinkler);
            }

            boolean cr3 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.DeluxeScarecrow) {
                    cr3 = true;
                    break;
                }
            }
            if (!cr3) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.DeluxeScarecrow);
            }

            boolean cr4 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.CheesePress) {
                    cr4 = true;
                    break;
                }
            }
            if (!cr4) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.CheesePress);
            }

            boolean cr5 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.PreservesJar) {
                    cr5 = true;
                    break;
                }
            }
            if (!cr5) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.PreservesJar);
            }
        }
        if (currentPlayer.getFarmingSkill().getLevel() >= 350) {
            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.IridiumSprinkler) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.IridiumSprinkler);
            }

            boolean cr3 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.Keg) {
                    cr3 = true;
                    break;
                }
            }
            if (!cr3) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.Keg);
            }

            boolean cr4 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.Loom) {
                    cr4 = true;
                    break;
                }
            }
            if (!cr4) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.Loom);
            }

            boolean cr5 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.OilMaker) {
                    cr5 = true;
                    break;
                }
            }
            if (!cr5) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.OilMaker);
            }
        }
        //DishOTheSea
        if (currentPlayer.getFishingSkill().getLevel() >= 250) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.DishOTheSea) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.DishOTheSea);
                cookingrecipe.setPrice(220);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
        }
        //SeaformPudding
        if (currentPlayer.getFishingSkill().getLevel() >= 350) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.SeaformPudding) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.SeaformPudding);
                cookingrecipe.setPrice(300);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
        }
        //MinersTreat
        if (currentPlayer.getMiningSkill().getLevel() >= 150) {
            boolean cr1 = false;
            for (Cookingrecipe cookingrecipe : currentPlayer.getCookingRecipes()) {
                if (cookingrecipe.getFood() == FoodCookingEnums.MinersTreat) {
                    cr1 = true;
                    break;
                }
            }
            if (!cr1) {
                Cookingrecipe cookingrecipe = new Cookingrecipe();
                cookingrecipe.setFood(FoodCookingEnums.MinersTreat);
                cookingrecipe.setPrice(200);
                currentPlayer.getCookingRecipes().add(cookingrecipe);
            }
            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.CherryBomb) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.CherryBomb);
            }
        }
        if (currentPlayer.getMiningSkill().getLevel() >= 250) {
            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.Bomb) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.Bomb);
            }
        }
        if (currentPlayer.getMiningSkill().getLevel() >= 350) {
            boolean cr2 = false;
            for (CraftingRecipesEnums recipe : currentPlayer.getCraftingRecipes()) {
                if (recipe == CraftingRecipesEnums.MegaBomb) {
                    cr2 = true;
                    break;
                }
            }
            if (!cr2) {
                currentPlayer.getCraftingRecipes().add(CraftingRecipesEnums.MegaBomb);
            }
        }
    }

    public Result whoAmI(Player player) {
        return new Result(true, "Player: " + player.getOwner().getUsername());
    }

    public static boolean isFainted(Player player) {
        if (player.getEnergy() == 0) {
            return true;
        }
        return false;
    }

    public void exit() {
        System.exit(0);
    }

    public static int getFieldValue(Class<?> gameClass, Object gameInstance, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = gameClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(gameInstance);
    }

    public static void setMapp(Game game) {
        ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
        int columns = ClientModel.mapHeight; // Define the number of columns

        for (int i = 0; i < ClientModel.mapWidth; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new Kashi()); // Initialize each cell with an instance of Kashi
            }
            Map.add(row);
        }
        game.setMap(Map);

        for (int i = 0; i < ClientModel.mapWidth; i++) {
            for (int j = 0; j < columns; j++) {
                Map.get(i).get(j).setWalkable(true);
                Map.get(i).get(j).setEnterance(false);
                Map.get(i).get(j).setShokhmZadeh(false);
            }
        }
    }

    public static Result gameNew(ArrayList<String> users, ArrayList<Integer> maps, User currentUser) throws NoSuchFieldException, IllegalAccessException {
//        App.setCurrentUser(currentUser);
        String[] usernames = users.toArray(new String[0]);

        if (usernames.length > 4) {
            return new Result(false, "Choose 4 usernames Max");
        }
//        for (String username : usernames) {
//            boolean check = false;
//            for (User user : App.getUsers_List()) {
//                if (username.equals(user.getUsername())) {
//                    check = true;
//                    break;
//                }
//            }
//            if (!check) {
//                return new Result(false, "Username does not exist");
//            }
//        }
//        String[] usrnms = new String[usernames.length];
//        int counter = 0;
//        for (String username : usernames) {
//            for (String usernm : usrnms) {
//                if (username.equals(usernm)) {
//                    return new Result(false, "Username selected twice");
//                }
//            }
//            usrnms[counter] = username;
//        }
//        for (String username : usernames) {
//            for (Game game : App.getGames()) {
//                for (Player player : game.getPlayers()) {
//                    if (player.getOwner().getUsername().equals(username)) {
//                        return new Result(false, "Username is already in a game");
//                    }
//                }
//            }
//        }
        Game NewGame = new Game(currentUser);
        NewGame.setPlayers(new ArrayList<>());

//        Player player1 = new Player();
//        player1.setOwner(currentUser);
//        player1.setEnergy(200);
//        NewGame.getPlayers().add(player1);

        for (String username : usernames) {
            Player player = new Player();
            User user1 = null;
            for (User user : App.getUsers_List()) {
                if (username.equals(user.getUsername())) {
                    user1 = user;
                    player.setOwner(user);
                    player.setEnergy(200);
                    NewGame.getPlayers().add(player);
                    break;
                }
            }
        }
        NewGame.setCurrentSeason(Seasons.Spring);
        NewGame.setCurrentDateTime(new DateTime(9, 1));
        Deque<WeatherEnum> weather = new ArrayDeque<>();
        weather.addLast(getRandomWeather(Seasons.Spring));
        NewGame.setCurrentWeather(getRandomWeather(Seasons.Spring));
        NewGame.setWeather(weather);

        for (int i = 0; i < NewGame.getPlayers().size(); i++) {
            NewGame.getPlayers().get(i).getOwner().setTimesPlayed(NewGame.getPlayers().get(i).getOwner().getTimesPlayed() + 1);
        }

        for (int i = 0; i < NewGame.getPlayers().size(); i++) {
            Player player;
//            if (i != 0) {
            player = NewGame.getPlayers().get(i);
//            } else {
//                player = player1;
//            }
            System.out.println("Choosing map for: " + player.getOwner().getUsername());

            int number = maps.get(i);

            if (number >= 1 && number <= 4) {

            } else {
                return new Result(false, "Invalid number");
            }

            int topLeftx = (int) getFieldValue(ClientModel.class, NewGame, "player" + (i + 1) + "TopLeftx");
            int topLefty = (int) getFieldValue(ClientModel.class, NewGame, "player" + (i + 1) + "TopLefty");

            player.setX(topLeftx + 5);
            player.setY(topLefty + 5);

            if (i == 0) {
                setMapp(NewGame);
                App.setCurrentGame(NewGame);
                App.setGames(new ArrayList<>());
                App.getGames().add(NewGame);
            }
            switch (number) {
                case 1:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap1(topLeftx, topLefty);
                    break;
                case 2:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap2(topLeftx, topLefty);
                    break;
                case 3:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap3(topLeftx, topLefty);
                    break;
                case 4:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap4(topLeftx, topLefty);
                    break;
                default:
                    System.out.println("Unexpected error occurred.");
                    break;
            }
            ClientConnectionThread clientConnectionThread = ServerModel.getConnectionByUserName(player.getOwner().getUsername());
            HashMap<String, Object> map = new HashMap<>();
            map.put("owner", player.getOwner());
            map.put("energy", player.getEnergy());
            map.put("x", player.getX());
            map.put("y", player.getY());
            Message msg = new Message(map, Message.Type.add_player_to_Clientmain, Message.Menu.game_menu);
//            System.out.println("we have sent a message to the client " + clientConnectionThread.getUser().getUsername() + " to fill the player object in clientmodel, here is x and y + " + player.getX() + " " + player.getY());
            clientConnectionThread.sendMessage(msg);
        }
        NewGame.initializeFriendships();
        return new Result(true, "Game Created");
    }

    public static WeatherEnum getRandomWeather(Seasons seasons) {
        // ۱. فهرست وضعیت‌های مجاز را می‌سازیم
        List<WeatherEnum> candidates = Stream.of(WeatherEnum.values())
            .filter(w -> w.isAllowedIn(seasons))
            .toList();

        // ۲. عدد تصادفی بین 0 و size-1
        int idx = ThreadLocalRandom.current().nextInt(candidates.size());

        // ۳. انتخاب و بازگشت
        return candidates.get(idx);
    }

    private static final Random random = new Random();

    public static int[][] getRandomPlaces(int amount, int farmWidth, int farmHeight) {
        int[][] strikePositions = new int[amount][2]; // آرایه برای ذخیره مختصات (x,y)

        for (int i = 0; i < amount; i++) {
            strikePositions[i][0] = random.nextInt(farmWidth);  // مقدار x تصادفی
            strikePositions[i][1] = random.nextInt(farmHeight); // مقدار y تصادفی
        }
        return strikePositions;

    }

    public Result exitGame(Player playerrr) {
        if (App.getCurrentGame() == null) {
            return new Result(false, "Game not started");
        }
        if (App.getCurrentGame().getCreator().getUsername().equals(playerrr.getOwner().getUsername())) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if (player.getOwner().getHighestGold() < player.getGold()) {
                    player.getOwner().setHighestGold(player.getGold());
                }
            }
//            for (User user : App.getUsers_List()) {
//                System.out.println(user.getUsername() + " " + user.getHighestGold());
//            }

            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter("users.json")) {
                gson.toJson(App.getUsers_List(), writer);
                System.out.println("Users saved to users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.GameMenu);
            System.exit(0);
            return new Result(true, "Game Exited");
        } else {
            return new Result(false, "You don't have access exit the game");
        }
    }

    public Result voteTerminateGame(Scanner scanner, Player playerrr) {
        int total_neg = 0;
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (!player.getOwner().getUsername().equals(playerrr.getOwner().getUsername())) {
                System.out.println(player.getOwner().getUsername() + " Enter your vote to terminate game(+/-): ");
                String vote = scanner.nextLine();
                if (vote.equals("-")) {
                    total_neg++;
                }
            }
        }
        if (total_neg > 0) {
            return new Result(false, "Looks like someone want to still play!");
        } else {
            //delete game
            for (Player player : App.getCurrentGame().getPlayers()) {
//                System.out.println(player.getOwner().getHighestGold() + " " + player.getGold());
                if (player.getOwner().getHighestGold() < player.getGold()) {
//                    System.out.println("upgraded gold");
                    player.getOwner().setHighestGold(player.getGold());
                }
//                System.out.println("new gold: " + player.getOwner().getHighestGold());
            }
            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter("users.json")) {
                gson.toJson(App.getUsers_List(), writer);
                System.out.println("Users saved to users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
//            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.GameMenu);
            System.exit(0);
            return new Result(true, "Game Exited");
        }
    }

    public Result loadGame() {
        return new Result(true, "");
    }

    public Result saveGame() {
        return new Result(true, "");
    }

    public Result removeGame() {
        return new Result(true, "");
    }

//    public static void nextTurn(GameMenu gameMenu, GameView gameView) {
//
//        App.getCurrentGame().setIndexPlayerinControl(App.getCurrentGame().getIndexPlayerinControl() + 1);
//        if (App.getCurrentGame().getIndexPlayerinControl() == App.getCurrentGame().getPlayers().size()) {
//            App.getCurrentGame().setIndexPlayerinControl(0);
//
//
//            if (App.getCurrentGame().getCurrentDateTime().getHour() == 23) {
//
//                startNewDay(gameMenu, true, gameView);
//
//            } else {
//                App.getCurrentGame().setCurrentDateTime(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour() + 1,
//                    App.getCurrentGame().getCurrentDateTime().getDay()));
//            }
//
//            for (Player player : App.getCurrentGame().getPlayers()) {
//                if (player.getFoodBuff().getBuffHours() != 0) {
//                    player.getFoodBuff().setBuffHours(player.getFoodBuff().getBuffHours() - 1);
//                }
//            }
//
//
//        }
//    }

    public static void getBackHome() {
        int player1TopLeftx = 0;
        int player1TopLefty = 0;
        int player1Width = 450;
        int player1Height = 200;

        int player2TopLeftx = 550;
        int player2TopLefty = 0;
        int player2Width = 449;
        int player2Height = 200;

        int player3TopLeftx = 0;
        int player3TopLefty = 360;
        int player3Width = 450;
        int player3Height = 200;

        int player4TopLeftx = 550;
        int player4TopLefty = 360;
        int player4Width = 449;
        int player4Height = 200;
        Cord player1 = getTopLeftCottage(player1TopLeftx, player1TopLefty, player1TopLeftx + player1Width, player1TopLefty + player1Height);
        Cord player2 = getTopLeftCottage(player2TopLeftx, player2TopLefty, player2TopLeftx + player2Width, player2TopLefty + player2Height);
        Cord player3 = getTopLeftCottage(player3TopLeftx, player3TopLefty, player2TopLeftx + player3Width, player3TopLefty + player3Height);
        Cord player4 = getTopLeftCottage(player4TopLeftx, player4TopLefty, player4TopLeftx + player4Width, player4TopLefty + player4Height);
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            if (i == 0) {
                if (App.getCurrentGame().getPlayers().get(0).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(0).setX(player1.getX() + 5);
                    App.getCurrentGame().getPlayers().get(0).setY(player1.getY() + 5);
                }
            } else if (i == 1) {
                if (App.getCurrentGame().getPlayers().get(1).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(1).setX(player2.getX() + 5);
                    App.getCurrentGame().getPlayers().get(1).setY(player2.getY() + 5);
                }
            } else if (i == 2) {
                if (App.getCurrentGame().getPlayers().get(2).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(2).setX(player3.getX() + 5);
                    App.getCurrentGame().getPlayers().get(2).setY(player3.getY() + 5);
                }
            } else if (i == 3) {
                if (App.getCurrentGame().getPlayers().get(3).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(3).setX(player4.getX() + 5);
                    App.getCurrentGame().getPlayers().get(3).setY(player4.getY() + 5);
                }
            }
        }

    }

    public static Cord getTopLeftCottage(int firstX, int firstY, int secondX, int secondY) {
        for (int x = firstX; x < secondX; x++) {
            for (int y = firstY; y < secondY; y++) {
                if (App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof Cottage) {
                    return new Cord(x, y);
                }
            }
        }
        return null;
    }

    public static void startNewDay(GameMenu gameMenu, boolean transaction, GameView gameView, Player playerrr) {
        if (transaction) {
            gameMenu.startSleepTransition();
            return;
        }

        //filling markets
        App.getCurrentGame().getBlackSmithMarket().fillStock();
        App.getCurrentGame().getCarpentersShopMarket().fillStock();
        App.getCurrentGame().getCarpentersShopMarket().fillStock();
        App.getCurrentGame().getFishShopMarket().fillStock();
        App.getCurrentGame().getJojoMartMarket().fillStock(App.getCurrentGame().getCurrentDateTime().getSeason());
        App.getCurrentGame().getMarniesRanchMarket().fillStock();
        App.getCurrentGame().getPierresGeneralStoreMarket().fillStock(App.getCurrentGame().getCurrentSeason());

        //going back to cottage
        getBackHome();

        //handle energy after gash
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getDaysAfterGash() >= 1) {
                player.setMaxEnergy(200);
                player.setDaysAfterGash(0);
            }
            if (player.getEnergy() != 200) {
                player.setDaysAfterGash(player.getDaysAfterGash() + 1);
            }
            player.setEnergy(player.getMaxEnergy());
        }

        // handle energy after marriage
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getDaysAfterJavabeRad() >= 6) {
                player.setMaxEnergy(200);
                player.setDaysAfterJavabeRad(0);
            }
            if (player.getMaxEnergyforMarriage() != 200) {
                player.setDaysAfterJavabeRad(player.getDaysAfterJavabeRad() + 1);
            }
            player.setEnergy(player.getMaxEnergyforMarriage());
        }

        //NPC gift player
        for (Friendshipali friendshipali : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
            if (friendshipali.getFriendshipLevel() / 200 >= 3) {
                FoodCooking foodCooking = new FoodCooking();
                foodCooking.setName(FoodCookingEnums.Pizza);
                foodCooking.setSellPrice(300);
                foodCooking.setEnergy(150);
                Random random = new Random();
                if (random.nextInt(0, 2) == 1) {
                    friendshipali.getPlayer().getInventory().addItem(foodCooking, 1);
                }
            }
        }
        for (Friendshipali friendshipali : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
            if (friendshipali.getFriendshipLevel() / 200 >= 3) {
                Mineral mineral = new Mineral();
                mineral.setType(ForagingMineralsEnums.IronOre);
                mineral.setPrice(10);
                Random random = new Random();
                if (random.nextInt(0, 2) == 1) {
                    friendshipali.getPlayer().getInventory().addItem(mineral, 1);
                }
            }
        }
        for (Friendshipali friendshipali : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
            if (friendshipali.getFriendshipLevel() / 200 >= 3) {
                Food food = new Food();
                food.setName("coffee");
                food.setPrice(200);
                Random random = new Random();
                if (random.nextInt(0, 2) == 1) {
                    friendshipali.getPlayer().getInventory().addItem(food, 1);
                }
            }
        }
        for (Friendshipali friendshipali : App.getCurrentGame().getNPCLEAH().getFriendships()) {
            if (friendshipali.getFriendshipLevel() / 200 >= 3) {
                FoodCooking foodCooking = new FoodCooking();
                foodCooking.setName(FoodCookingEnums.Salad);
                foodCooking.setEnergy(113);
                foodCooking.setSellPrice(110);
                Random random = new Random();
                if (random.nextInt(0, 2) == 1) {
                    friendshipali.getPlayer().getInventory().addItem(foodCooking, 1);
                }
            }
        }
        for (Friendshipali friendshipali : App.getCurrentGame().getNPCROBIN().getFriendships()) {
            if (friendshipali.getFriendshipLevel() / 200 >= 3) {
                Mineral mineral = new Mineral();
                mineral.setType(ForagingMineralsEnums.IronOre);
                mineral.setPrice(10);
                Random random = new Random();
                if (random.nextInt(0, 2) == 1) {
                    friendshipali.getPlayer().getInventory().addItem(mineral, 1);
                }
            }
        }

        // Sell Satl end of the day
        for (Item item : playerrr.getMyFarm().getSatl().getItems().keySet()) {
            //AllCrop+
            //Fertilizer+
            //Food+
            //FoodCooking+
            //Foraging Crop+
            //Foraging Seed+
            //Hay+
            //Market Products+
            //MilkPail+
            //Mineral+
            //Shear+
            //TreeSeed+
            //AllCrop+
            //AllCrop+
            if (item.getCorrectPrice() <= 0) {
                playerrr.setGold(playerrr.getGold() + 100 * playerrr.getMyFarm().getSatl().getItems().get(item));
            } else {
                playerrr.setGold(playerrr.getGold() + item.getCorrectPrice() * playerrr.getMyFarm().getSatl().getItems().get(item));
            }
            playerrr.getMyFarm().getSatl().removeItem(item, playerrr.getMyFarm().getSatl().getItems().get(item));

        }

        //grow plants
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (AllTree allTree : player.getMyFarm().getAllTrees()) {
                if (allTree.isFedThisDay() || App.getCurrentGame().getCurrentWeather() == WeatherEnum.RAIN) {
                    allTree.setDaysGrowCounter(allTree.getDaysGrowCounter() + 1);
                }
            }
            for (AllCrop allCrop : player.getMyFarm().getAllCrops()) {
                if (allCrop.isFedThisDay() || App.getCurrentGame().getCurrentWeather() == WeatherEnum.RAIN) {
                    allCrop.setDaysGrowCounter(allCrop.getDaysGrowCounter() + 1);
                }
            }
        }

        //set isfedtoday to false
        for (Player player : App.getCurrentGame().getPlayers()) {
            for (AllTree allTree : player.getMyFarm().getAllTrees()) {
                allTree.setFedThisDay(false);
            }
            for (AllCrop allCrop : player.getMyFarm().getAllCrops()) {
                allCrop.setFedThisDay(false);
            }
        }

        //fill the markets
        App.getCurrentGame().getPierresGeneralStoreMarket().setLargePackBougth(false);
        App.getCurrentGame().getPierresGeneralStoreMarket().setDeluxePackBought(false);
        App.getCurrentGame().getCarpentersShopMarket().setBarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setBigbarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setBigcoop(false);
        App.getCurrentGame().getCarpentersShopMarket().setCoop(false);
        App.getCurrentGame().getCarpentersShopMarket().setDeluxebarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setDeluxecoop(false);

        //handle kalag attack
        Random rand = new Random();
        for (Player player : App.getCurrentGame().getPlayers()) {
            int chanceOfKalag = player.getMyFarm().getAllTrees().size() + player.getMyFarm().getAllCrops().size();
            int threshold = (chanceOfKalag / 16) + 1;
            boolean shouldRemove = false;

            if (threshold >= 4) {
                shouldRemove = true; // 100%
            } else if (threshold >= 3) {
                shouldRemove = rand.nextInt(100) < 75; // 75%
            } else if (threshold >= 2) {
                shouldRemove = rand.nextInt(100) < 50; // 50%
            } else if (threshold >= 1) {
                shouldRemove = rand.nextInt(100) < 25; // 25%
            }

            if (shouldRemove) {
                gameView.startRedFlash();
                // Check what we have available to remove
                boolean hasTrees = player.getMyFarm().getAllTrees().size() > 0;
                boolean hasCrops = player.getMyFarm().getAllCrops().size() > 0;

                if (hasTrees && hasCrops) {
                    // Both available - random choice
                    if (rand.nextBoolean()) {
                        AllTree allTree = player.getMyFarm().getAllTrees().get(rand.nextInt(player.getMyFarm().getAllTrees().size()));
                        allTree.setDaysGrowCounter(0);
                        System.out.println("attacked tree");
                    } else {
                        AllCrop allCrop = player.getMyFarm().getAllCrops().get(rand.nextInt(player.getMyFarm().getAllCrops().size()));
                        if (allCrop.isOneTime()) {
                            player.getMyFarm().getAllCrops().remove(allCrop);
                            for (int j = 0; j < 560; j++) {
                                for (int i = 0; i < 1000; i++) {
                                    if (App.getCurrentGame().getMap().get(i).get(j).getInside() == allCrop) {
                                        App.getCurrentGame().getMap().get(i).get(j).setInside(null);
                                        player.getMyFarm().getAllCrops().remove(allCrop);
                                        System.out.println("deleted crop");
                                        break;
                                    }
                                }
                            }
                        } else {
                            allCrop.setDaysGrowCounter(0);
                            System.out.println("attacked crop");
                        }
                    }
                } else if (hasTrees) {
                    AllTree allTree = player.getMyFarm().getAllTrees().get(rand.nextInt(player.getMyFarm().getAllTrees().size()));
                    allTree.setDaysGrowCounter(0);
                    System.out.println("attacked tree");
                } else if (hasCrops) {
                    AllCrop allCrop = player.getMyFarm().getAllCrops().get(rand.nextInt(player.getMyFarm().getAllCrops().size()));
                    if (allCrop.isOneTime()) {
                        player.getMyFarm().getAllCrops().remove(allCrop);
                        for (int j = 0; j < 560; j++) {
                            for (int i = 0; i < 1000; i++) {
                                if (App.getCurrentGame().getMap().get(i).get(j).getInside() == allCrop) {
                                    App.getCurrentGame().getMap().get(i).get(j).setInside(null);
                                    player.getMyFarm().getAllCrops().remove(allCrop);
                                    System.out.println("deleted crop");
                                    break;
                                }
                            }
                        }
                    } else {
                        allCrop.setDaysGrowCounter(0);
                        System.out.println("attacked crop");
                    }
                }
            }
        }

        //Foraging
        for (int j = 0; j < 560; j++) {
            for (int i = 0; i < 1000; i++) {
                if ((i >= 0 && i <= 450 && j >= 0 && j <= 200) ||
                    (i >= 550 && i <= 1000 && j >= 0 && j <= 200) ||
                    (i >= 0 && i <= 450 && j >= 360 && j <= 560) ||
                    (i >= 550 && i <= 1000 && j >= 360 && j <= 560)) {
                    if (App.getCurrentGame().getMap().get(i).get(j).getInside() == null) {
                        boolean foragingChance = rand.nextInt(100) == 86;

                        //foraging crop
                        //foraging tree
                        //foraging mineral
                        int whichForaging = rand.nextInt(0, 4);
                        if (foragingChance && whichForaging == 0) {
                            ForagingCropsEnums[] foragingCropsTypes = ForagingCropsEnums.values();
                            ForagingCrop foragingCrop = new ForagingCrop();
                            foragingCrop.setType(foragingCropsTypes[random.nextInt(foragingCropsTypes.length)]);
                            App.getCurrentGame().getMap().get(i).get(j).setInside(foragingCrop);
                        } else {
                            if (foragingChance && whichForaging == 1) {
                                ForagingTreesEnums[] foragingTreesTypes = ForagingTreesEnums.values();
                                ForagingTree foragingTree = new ForagingTree();
                                foragingTree.setType(foragingTreesTypes[random.nextInt(foragingTreesTypes.length)]);
                                App.getCurrentGame().getMap().get(i).get(j).setInside(foragingTree);
                            } else {
                                if (foragingChance && whichForaging == 2) {
                                    ForagingMineralsEnums[] foragingMineralsTypes = ForagingMineralsEnums.values();
                                    Mineral mineral = new Mineral();
                                    mineral.setType(foragingMineralsTypes[random.nextInt(foragingMineralsTypes.length)]);
                                    App.getCurrentGame().getMap().get(i).get(j).setInside(mineral);
                                }
                            }
                        }
                    }
                }
            }
        }

        //handling new day datetime
        App.getCurrentGame().setCurrentDateTime(new DateTime(9, App.getCurrentGame().getCurrentDateTime().getDay() + 1));

        //update friendships
        App.getCurrentGame().endOfDayUpdate();

        //handle weather
        App.getCurrentGame().setCurrentWeather(App.getCurrentGame().getWeather().pollFirst());
        Deque<WeatherEnum> weather = new ArrayDeque<>();
        if (App.getCurrentGame().getCurrentDateTime().getDay() % 28 == 0) {
            String season2 = App.getCurrentGame().getCurrentDateTime().getSeason();
            switch (season2) {
                case "Spring":
                    weather.addLast(getRandomWeather(Seasons.Summer));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Summer":
                    weather.addLast(getRandomWeather(Seasons.Fall));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Fall":
                    weather.addLast(getRandomWeather(Seasons.Winter));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Winter":
                    weather.addLast(getRandomWeather(Seasons.Spring));
                    App.getCurrentGame().setWeather(weather);
                    break;
                default:
                    break;
            }
        } else {

            weather.addLast(getRandomWeather(App.getCurrentGame().getCurrentSeason()));
            App.getCurrentGame().setWeather(weather);
        }

        //Strike thor if needed
        if (App.getCurrentGame().getCurrentWeather() == WeatherEnum.STORM) {
            //Thor thor = new Thor();
            int farmWide = 50;
            int farmHeight = 50;
            int[][] strikePosition = new int[3][2];
            strikePosition = getRandomPlaces(3, farmWide, farmHeight);
            //ArrayList<ArrayList<Kashi>> map = App.getCurrentGame().getMap();
            Kashi[] kashis = new Kashi[3];
            for (int i = 0; i < 3; i++) {
                kashis[i] = new Kashi();

                if (App.getCurrentGame().getMap().get(strikePosition[i][0]).get(strikePosition[i][1]).getInside() instanceof AllTree ||
                    App.getCurrentGame().getMap().get(strikePosition[i][0]).get(strikePosition[i][1]).getInside() instanceof ForagingTree) {

                    Mineral coal = new Mineral();
                    coal.setType(ForagingMineralsEnums.Coal);
                    App.getCurrentGame().getMap().get(strikePosition[i][0]).get(strikePosition[i][1]).setInside(coal);
                }
            }
            //Server-TODO
//            App.getGameController().getGameMenu().getLightningEffect().start();
////                    thor.setKhordeh(kashiList);
        }

        //update animal navazesh
        for (Player player : App.getCurrentGame().getPlayers()) {
            ArrayList<Animal> animals = player.getMyBoughtAnimals();
            for (Animal animal : animals) {
                // Is Navazeshed or no
                if (!animal.isTaghzieh()) {
                    if (animal.getFriendship() - 20 < 0) {
                        animal.setFriendship(0);
                    } else {
                        animal.setFriendship(animal.getFriendship() - 20);
                    }
                }
                // Is Taghzied or no
                if (!animal.isNavazesh()) {
                    if ((animal.getFriendship() / 200) - 10 < 0) {
                        animal.setFriendship(0);
                    } else {
                        animal.setFriendship((animal.getFriendship()) / 200 - 10);
                    }
                }
                //
                if (animal.isOutside()) {
                    animal.setFriendship(animal.getFriendship() - 20);
                }
                animal.setTaghzieh(false);
                animal.setNavazesh(false);
            }
        }
    }

    public Result time(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentDateTime().getHour()));
    }

    public Result date(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getDay();
        return new Result(true, stringBuilder);
    }

    public Result dateTime(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getDay() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getHour();
        return new Result(true, stringBuilder);

    }

    public static Result dayOfWeek(Player playerrr, int day) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        int dayOfWeek = day % 7;
        return switch (dayOfWeek) {
            case 0 -> new Result(true, "Sun");
            case 1 -> new Result(true, "Mon");
            case 2 -> new Result(true, "Tue");
            case 3 -> new Result(true, "Wed");
            case 4 -> new Result(true, "Thu");
            case 5 -> new Result(true, "Fri");
            case 6 -> new Result(true, "Sat");
            default -> null;
        };
    }

    public Result cheatAdvanceTime(int hour, GameMenu gameMenu, GameView gameView, Player playerrr) {
        if (hour < 0) {
            return new Result(false, "cheatCode: Invalid hour");
        }
        int newHour = hour + App.getCurrentGame().getCurrentDateTime().getHour();
        int hourOfDay = newHour % 24;
        int newDay = (newHour / 24) + App.getCurrentGame().getCurrentDateTime().getDay();
        App.getCurrentGame().setCurrentDateTime(new DateTime(hourOfDay, newDay));
//        Deque<WeatherEnum> weather = new ArrayDeque<>();
//        weather.addLast(getRandomWeather(App.getCurrentGame().getCurrentSeason()));
//        App.getCurrentGame().setCurrentWeather(weather.pollFirst());
//        App.getCurrentGame().setWeather(weather);
//        if (App.getCurrentGame().getCurrentDateTime().getDay() % 28 == 0) {
//            String season2 = App.getCurrentGame().getCurrentDateTime().getSeason();
//            switch (season2) {
//                case "Spring":
//                    weather.addLast(getRandomWeather(Seasons.Summer));
//                    App.getCurrentGame().setWeather(weather);
//                    break;
//                case "Summer":
//                    weather.addLast(getRandomWeather(Seasons.Fall));
//                    App.getCurrentGame().setWeather(weather);
//                    break;
//                case "Fall":
//                    weather.addLast(getRandomWeather(Seasons.Winter));
//                    App.getCurrentGame().setWeather(weather);
//                    break;
//                case "Winter":
//                    weather.addLast(getRandomWeather(Seasons.Spring));
//                    App.getCurrentGame().setWeather(weather);
//                    break;
//                default:
//                    break;
//            }
//        } else {
//            weather.addLast(getRandomWeather(App.getCurrentGame().getCurrentSeason()));
//            App.getCurrentGame().setWeather(weather);
//        }
        for (int i = 0; i < hour / 24; i++) {
            startNewDay(gameMenu, true, gameView, playerrr);
        }
//        if (hour / 24 >= 1) {
//            App.getCurrentGame().getCurrentDateTime().setDay(App.getCurrentGame().getCurrentDateTime().getDay() - 1);
//        }
        return new Result(true, "cheatCode: Hour changed! New Hour: " + hourOfDay + " New Day: " + newDay);
    }

    public Result cheatAdvanceDate(int day, GameMenu gameMenu, GameView gameView, Player playerrr) {
        if (day < 0) {
            return new Result(false, "cheatCode: Invalid day");
        }
        int newDay = day + App.getCurrentGame().getCurrentDateTime().getDay();
        App.getCurrentGame().setCurrentDateTime(new DateTime(day, newDay));
        Deque<WeatherEnum> weather = new ArrayDeque<>();
        weather.addLast(getRandomWeather(App.getCurrentGame().getCurrentSeason()));
        App.getCurrentGame().setCurrentWeather(weather.pollFirst());
        App.getCurrentGame().setWeather(weather);
        if (App.getCurrentGame().getCurrentDateTime().getDay() % 28 == 0) {
            String season2 = App.getCurrentGame().getCurrentDateTime().getSeason();
            switch (season2) {
                case "Spring":
                    weather.addLast(getRandomWeather(Seasons.Summer));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Summer":
                    weather.addLast(getRandomWeather(Seasons.Fall));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Fall":
                    weather.addLast(getRandomWeather(Seasons.Winter));
                    App.getCurrentGame().setWeather(weather);
                    break;
                case "Winter":
                    weather.addLast(getRandomWeather(Seasons.Spring));
                    App.getCurrentGame().setWeather(weather);
                    break;
                default:
                    break;
            }
        } else {
            weather.addLast(getRandomWeather(App.getCurrentGame().getCurrentSeason()));
            App.getCurrentGame().setWeather(weather);
        }
        for (int i = 0; i < day; i++) {
            startNewDay(gameMenu, true, gameView, playerrr);
        }
        //App.getCurrentGame().getCurrentDateTime().setDay(App.getCurrentGame().getCurrentDateTime().getDay() - 1);
        return new Result(true, "cheatCode: Day changed! New Day: " + newDay);
    }

    public Result season(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, App.getCurrentGame().getCurrentDateTime().getSeason());
    }

    public Result cheatThor(int x, int y, Player playerrr) {

        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }

        if (App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof AllTree || App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof ForagingTree) {
            Mineral coal = new Mineral();
            coal.setType(ForagingMineralsEnums.Coal);
            App.getCurrentGame().getMap().get(x).get(y).setInside(coal);
        }
        //Server-TODO
//        App.getGameController().getGameMenu().getLightningEffect().start();
        return new Result(true, "cheatCode: Thor changed! Thor strike at (" + x + "," + y + ")");
    }

    public Result weather(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, "Weather : " + App.getCurrentGame().getCurrentWeather().toString());
    }

    public Result weatherForecast(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, "Tomorrow Weather : " + App.getCurrentGame().getWeather().getFirst().toString());

    }

    public Result cheatWeather(String Type, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Deque<WeatherEnum> weather = new ArrayDeque<>();
        switch (Type.toLowerCase()) {
            case "sunny":
                weather.addFirst(WeatherEnum.SUNNY);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Sunny");
            case "rain":
                weather.addFirst(WeatherEnum.RAIN);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Rain");
            case "storm":
                weather.addFirst(WeatherEnum.STORM);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Storm");
            case "snow":
                weather.addFirst(WeatherEnum.SNOW);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Snow");
            default:
                return new Result(false, "cheatCode: Invalid Weather");
        }
    }

    public Result greenHouseBiuld(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        if (playerrr.getWood() > 500
            && playerrr.getGold() > 1000) {
            playerrr.getMyFarm().getMyGreenHouse().setStatus(true);
        }
        return new Result(true, "Green House Build for " + playerrr.getOwner().getUsername() + "successfully " + playerrr
            .getMyFarm().getMyGreenHouse().isStatus() + " " + playerrr
            .getMyFarm().getMyGreenHouse().hashCode());
    }

    public void walk(int x, int y, Player playerrr) {
        HashMap<String, Object> body1 = new HashMap<>();
        body1.put("username", playerrr.getOwner().getUsername());
        body1.put("x", String.valueOf(x));
        body1.put("y", String.valueOf(y));
        ClientModel.getPlayer().setX(x);
        ClientModel.getPlayer().setY(y);
        Message send1 = new Message(body1, Message.Type.player_pos_update, Message.Menu.game);
        ClientModel.getServerConnectionThread().sendMessage(send1);
    }

    public void printMap(int x, int y, int Xsize, int Ysize) {
        ArrayList<ArrayList<Kashi>> Map = App.getCurrentGame().getMap();
        for (int j = y; j < Xsize + y; j++) {
            for (int i = x; i < Ysize + x; i++) {
                // Color reset code
                String reset = "\u001B[0m";

                if ((i == 450 && j <= 200) ||
                    (i == 550 && j <= 200) ||
                    (i == 450 && j >= 360) ||
                    (i == 550 && j >= 360) ||
                    (i == 475 && j >= 90 && j <= 110) ||
                    (i == 475 && j >= 450 && j <= 470) ||
                    (i == 525 && j >= 90 && j <= 110) ||
                    (i == 525 && j >= 450 && j <= 470)) {
                    System.out.print("\u001B[38;5;46m|" + reset);
                } else {
                    if ((j == 360 && i <= 450) ||
                        (j == 360 && i >= 550) ||
                        (j == 200 && i >= 550) ||
                        (j == 200 && i <= 450) ||
                        (j == 90 && i >= 475 && i <= 525) ||
                        (j == 110 && i >= 475 && i <= 525) ||
                        (j == 450 && i >= 475 && i <= 525) ||
                        (j == 470 && i >= 475 && i <= 525)) {
                        System.out.print("\u001B[38;5;46m=" + reset);
                    } else {
                        boolean found = false;
                        for (Player player : App.getCurrentGame().getPlayers()) {
                            if (player.getX() == i && player.getY() == j) {
                                found = true;
                                System.out.print("\u001B[38;5;196m@" + reset);
                                break;
                            }
                        }


                        if (!found) {
                            if (Map.get(i).get(j).getEnterance()) {
                                System.out.print("\u001B[38;5;196mE" + reset);
                            } else if (Map.get(i).get(j).getInside() instanceof Satl) {
                                System.out.print("\u001B[38;5;46mS" + reset); // Bright Green
                            } else
                                // All Tree - Bright Green
                                if (Map.get(i).get(j).getInside() instanceof AllTree) {
                                    System.out.print("\u001B[38;5;46mT" + reset); // Bright Green
                                }
                                // Buildings (Barns) - Cyan
                                else if (Map.get(i).get(j).getInside() instanceof BigBarn ||
                                    Map.get(i).get(j).getInside() instanceof Tavileh ||
                                    Map.get(i).get(j).getInside() instanceof DeluxeBarn) {
                                    System.out.print("\u001B[36mV" + reset); // Cyan
                                }
                                // Barn Animals - Light Cyan
                                else if (Map.get(i).get(j).getInside() instanceof TavilehAnimal) {
                                    System.out.print("\u001B[96mv" + reset); // Light Cyan
                                }
                                // Coops - Yellow
                                else if (Map.get(i).get(j).getInside() instanceof Cage ||
                                    Map.get(i).get(j).getInside() instanceof BigCoop ||
                                    Map.get(i).get(j).getInside() instanceof DeluxeCoop) {
                                    System.out.print("\u001B[33mC" + reset); // Yellow
                                }
                                // Coop Animals - Light Yellow
                                else if (Map.get(i).get(j).getInside() instanceof CageAnimal) {
                                    System.out.print("\u001B[93mc" + reset); // Light Yellow
                                }
                                // Stone - Gray
                                else if (Map.get(i).get(j).getInside() instanceof Stone) {
                                    System.out.print("\u001B[37ms" + reset); // Gray
                                }
                                // Refrigerator - White
                                else if (Map.get(i).get(j).getInside() instanceof Refrigerator) {
                                    System.out.print("\u001B[97mR" + reset); // White
                                }
                                // Quarry - Orange
                                else if (Map.get(i).get(j).getInside() instanceof Quarry) {
                                    System.out.print("\u001B[38;5;208mQ" + reset); // Orange
                                }
                                // Markets - Bright Blue
                                else if (Map.get(i).get(j).getInside() instanceof TheStardropSaloonMarket ||
                                    Map.get(i).get(j).getInside() instanceof BlackSmithMarket ||
                                    Map.get(i).get(j).getInside() instanceof JojoMartMarket ||
                                    Map.get(i).get(j).getInside() instanceof MarniesRanchMarket ||
                                    Map.get(i).get(j).getInside() instanceof PierresGeneralStoreMarket ||
                                    Map.get(i).get(j).getInside() instanceof CarpentersShopMarket ||
                                    Map.get(i).get(j).getInside() instanceof FishShopMarket) {
                                    System.out.print("\u001B[38;5;27mM" + reset); // Bright Blue
                                }
                                // Matarsak - Purple
                                else if (Map.get(i).get(j).getInside() instanceof Matarsak) {
                                    System.out.print("\u001B[35mm" + reset); // Purple
                                }
                                // NPCs - Magenta
                                else if (Map.get(i).get(j).getInside() instanceof NPC) {
                                    System.out.print("\u001B[95mN" + reset); // Light Magenta
                                }
                                // Lake - Bright Blue
                                else if (Map.get(i).get(j).getInside() instanceof Lake) {
                                    System.out.print("\u001B[38;5;33mL" + reset); // Bright Blue
                                }
                                // GreenHouse - Green
                                else if (Map.get(i).get(j).getInside() instanceof GreenHouse) {
                                    System.out.print("\u001B[32mG" + reset); // Green
                                }
                                // Cottage - Brown
                                else if (Map.get(i).get(j).getInside() instanceof Cottage) {
                                    System.out.print("\u001B[38;5;130mK" + reset); // Brown
                                }
                                // ForagingTree - Light Green
                                else if (Map.get(i).get(j).getInside() instanceof ForagingTree) {
                                    System.out.print("\u001B[38;5;112mF" + reset); // Light Green
                                }
                                // ForagingCrop - Light Yellow
                                else if (Map.get(i).get(j).getInside() instanceof ForagingCrop || Map.get(i).get(j).getInside() instanceof AllCrop) {
                                    System.out.print("\u001B[38;5;228mP" + reset); // Light Yellow
                                }
                                // ForagingSeed - Light Cyan
                                else if (Map.get(i).get(j).getInside() instanceof ForagingSeed) {
                                    System.out.print("\u001B[38;5;159mS" + reset); // Light Cyan
                                } else if (Map.get(i).get(j).getInside() instanceof Mineral) {
                                    System.out.print("\u001B[38;5;130mM" + reset); // Brown
                                }
                                // Default (empty/ground) - Dark Gray
                                else {
                                    System.out.print("\u001B[38;5;240m#" + reset); // Dark Gray
                                }
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    public void helpReadingMap() {
        System.out.println("Show elements in Map:");
        System.out.println("=============================");
        System.out.println("All Tree -> T");
        System.out.println("Animals -> A");
        System.out.println("Cage-> C");
        System.out.println("CageAnimals-> c");
        System.out.println("Cottage-> K");
        System.out.println("GreenHouse-> G");
        System.out.println("Lake-> L");
        System.out.println("Market-> M");
        System.out.println("Matarsak-> m");
        System.out.println("NPCs -> N");
        System.out.println("Quarry-> Q");
        System.out.println("Refrigerator-> R");
        System.out.println("Stone-> S");
        System.out.println("Tavileh-> V");
        System.out.println("TavilehAnimals-> v");
        System.out.println("SimpleLand-> #");
        System.out.print("\n");
        System.out.println("end Elements");
        System.out.println("=============================");

    }

    public void energyShow(Player playerrr) {
        System.out.println("Energy: " +
            playerrr.getEnergy());
    }

    public Result energySet(int value, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        if (value < 0) {
            return new Result(false, "CheatCode: Invalid energy");
        }
        playerrr.setEnergy(value);
        return new Result(true, "CheatCode: Energy set to " + value);
    }

    public void energyUnlimited(Player playerrr) {
        if (isFainted(playerrr)) {
            System.out.println("You are fainted!");
        }
        playerrr.setMaxEnergy(Integer.MAX_VALUE);
        playerrr.setEnergy(Integer.MAX_VALUE);
        System.out.println("**Energy unlimited**");

    }

//    public void inventoryShow() {
//        if (isFainted()) {
//            System.out.println("You are fainted!");
//        }
//        Map<Item, Integer> items = App.getCurrentGame().getPlayers()
//            .get(App.getCurrentGame().getIndexPlayerinControl())
//            .getInventory()
//            .getItems();
//        if (items.isEmpty()) {
//            System.out.println("**Inventory is Empty**");
//        } else {
//            for (Item item : items.keySet()) {
//                System.out.println("- " + item.getCorrectName() + " (Quantity: " + items.get(item) + ")");
//            }
//        }
//    }

//    public Result inventoryTrash(String name, int number) {
//        if (isFainted()) {
//            return new Result(false, "You are fainted!");
//        }
//        //find Item by name
//        Item item = findItemByName(name);
//        if (item == null) {
//            return new Result(false, "Item not found");
//        } else {
//            Map<Item, Integer> itemmmm = playerrr.getInventory().getItems();
//            TrashCan trashcan = null;
//            for (Item itemm : itemmmm.keySet()) {
//                if (itemm instanceof TrashCan) {
//                    trashcan = (TrashCan) item;
//                    break;
//                }
//            }
//            if (trashcan != null && trashcan.getJens().equals("initial")) {
//
//            }
//            if (trashcan != null && trashcan.getJens().equals("copper")) {
//                int nowgold = playerrr.getGold();
//                int price = (int) (item.getCorrectPrice() * 0.15);
//                playerrr.setGold(nowgold + price);
//            }
//            if (trashcan != null && trashcan.getJens().equals("iron")) {
//                int nowgold = playerrr.getGold();
//                int price = (int) (item.getCorrectPrice() * 0.3);
//                playerrr.setGold(nowgold + price);
//            }
//            if (trashcan != null && trashcan.getJens().equals("gold")) {
//                int nowgold = playerrr.getGold();
//                int price = (int) (item.getCorrectPrice() * 0.45);
//                playerrr.setGold(nowgold + price);
//            }
//            if (trashcan != null && trashcan.getJens().equals("iridium")) {
//                int nowgold = playerrr.getGold();
//                int price = (int) (item.getCorrectPrice() * 0.6);
//                playerrr.setGold(nowgold + price);
//            }
//            playerrr.getInventory().removeItem(item, number);
//            return new Result(true, number + "of Item " + item.getClass().getSimpleName() + " removed");
//        }
//    }

//    public Item findItemByName(String name) {
//        Map<Item, Integer> items = App.getCurrentGame().getPlayers()
//            .get(App.getCurrentGame().getIndexPlayerinControl())
//            .getInventory()
//            .getItems();
//
//        for (Item item : items.keySet()) {
//            if (item.getCorrectName().equalsIgnoreCase(name)) { // مقایسه نام کلاس آیتم با ورودی
//                return item;
//            }
//        }
//
//        return null;
//    }

//    public Result toolEquip(String toolName) {
//        if (isFainted()) {
//            return new Result(false, "You are fainted!");
//        }
//        Item item = findItemByName(toolName);
//        if (item == null) {
//            return new Result(false, "Item not found");
//        } else if (!(item instanceof Tool)) {
//            return new Result(false, "Your entered item is not a tool!");
//        } else {
//            Tool tool = (Tool) item;
//            playerrr.setInMyHandTool(tool);
//            return new Result(true, "now your entered tool is in your hand!");
//        }
//    }

    public Result toolsShowCurrent(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Tool tool = playerrr.getInMyHandTool();
        if (tool == null) {
            return new Result(false, "!You don't have any tools in your hand!");
        } else {
            String name = tool.getCorrectName();
            return new Result(true, "You have " + name + " tool in your hand!");
        }
    }

//    public Result toolShowAvailable() {
//        if (isFainted()) {
//            return new Result(false, "You are fainted!");
//        }
//        Map<Item, Integer> items = playerrr.getInventory().getItems();
//        StringBuilder toolsList = new StringBuilder("Tools in Inventory:\n");
//        for (Item item : items.keySet()) {
//            if (item instanceof Tool) {
//                toolsList.append("- ").append(item.getCorrectName()).append("\n");
//            }
//        }
//        return new Result(true, toolsList.toString());
//    }

    public Result toolsUpgrade(String jens, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        if (!jens.equals("initial") && !jens.equals("copper") && !jens.equals("iron") && !jens.equals("gold") && !jens.equals("iridium")) {
            return new Result(false, "Your entered jens is not Okay!");
        }
        Tool tool = playerrr.getInMyHandTool();
        if (tool == null) {
            return new Result(false, "Item not found");
        } else if (!(tool instanceof Tool)) {
            return new Result(false, "You can not upgrade this type of item!");
        }

        if (jens.equals("copper")) {
            if (tool instanceof Axe) {
                if (playerrr.getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 2000);
                    ((Axe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (playerrr.getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 2000);
                    ((Pickaxe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (playerrr.getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 2000);
                    ((Hoe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (playerrr.getGold() < 1000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 1000);
                    ((TrashCan) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("iron")) {
            if (tool instanceof Axe) {
                if (playerrr.getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 5000);
                    ((Axe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (playerrr.getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 5000);
                    ((Pickaxe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (playerrr.getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 5000);
                    ((Hoe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (playerrr.getGold() < 2500) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 2500);
                    ((TrashCan) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("gold")) {
            if (tool instanceof Axe) {
                if (playerrr.getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 10000);
                    ((Axe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (playerrr.getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 10000);
                    ((Pickaxe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (playerrr.getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 10000);
                    ((Hoe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (playerrr.getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 5000);
                    ((TrashCan) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("iridium")) {
            if (tool instanceof Axe) {
                if (playerrr.getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 25000);
                    ((Axe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (playerrr.getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 25000);
                    ((Pickaxe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (playerrr.getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 25000);
                    ((Hoe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (playerrr.getGold() < 12500) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    playerrr.setGold(playerrr.getGold() - 12500);
                    ((TrashCan) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
        }
        return new Result(true, "!");
    }

//    public static Result toolsUse(Tool tool, Point point, Game game, GameController gameController) {
//
//        if (isFainted()) {
//            return new Result(false, "You are fainted!");
//        }
//
//        if (tool instanceof Axe) {
//            System.out.println(((Axe) tool).use(direction));
//            return new Result(true, "You used your tool");
//        } else if (tool instanceof Pickaxe) {
//            System.out.println(((Pickaxe) tool).use(direction));
//            return new Result(true, "You used your tool");
//        } else if (tool instanceof WateringCan) {
//            System.out.println(((WateringCan) tool).use(direction));
//            return new Result(true, "You used your tool");
//        } else if (tool instanceof Hoe) {
//            System.out.println(((Hoe) tool).use(direction));
//            return new Result(true, "You used your tool");
//
//        } else if (tool instanceof MilkPail) {
//            System.out.println(((MilkPail) tool).use(direction));
//            return new Result(true, "You used your tool");
//
//        } else if (tool instanceof Shear) {
//            System.out.println(((Shear) tool).use(direction));
//            return new Result(true, "You used your tool");
//
//        } else if (tool instanceof TrashCan) {
//            System.out.println();
//            return new Result(true, "You used your tool");
//
//        } else if (tool instanceof Scythe) {
//            System.out.println(((Scythe) tool).use(direction));
//            return new Result(true, "You used your tool");
//
//        } else {
//            return new Result(false, "Invalid situation");
//        }
//    }

    public Result craftInfo(String craftName, GameController gameController, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        ForagingSeedsEnums foragingSeedsEnums;
        StringBuilder craftInfo = new StringBuilder();
        craftName.replace(" ", "");
        BitmapFont font = new BitmapFont();
        SpriteBatch batch = new SpriteBatch();
        for (AllCropsEnums crop : AllCropsEnums.values()) {
            if (crop.name().replace(" ", "").equalsIgnoreCase(craftName.replace(" ", ""))) {
                StringBuilder stages = new StringBuilder();
                foragingSeedsEnums = crop.getSeedSet();
                AllCrop crop1 = new AllCrop();
                crop1.initilizeCrop(foragingSeedsEnums);
                for (int i = 0; i < crop1.getStages().size(); i++) {
                    stages.append(crop1.getStages().get(i));
                    stages.append("-");
                }
                stages.deleteCharAt(stages.length() - 1);
                //crop1.initilizeCrop(foragingSeedsEnums);
                craftInfo.append("Name: ").append(craftName).append("\n");
                craftInfo.append("Source: ").append(foragingSeedsEnums.name()).append("\n");
                craftInfo.append("Stages: ").append(stages).append("\n");
                craftInfo.append("Total Harvest Time: ").append(crop1.getTotalHarvestTime()).append("\n");
                craftInfo.append("One Time: ").append(crop1.isOneTime() ? "TRUE" : "FALSE").append("\n");
                craftInfo.append("Regrowth Time: ").append(crop1.getRegrowthTime()).append("\n");
                craftInfo.append("Base Sell Price: ").append(crop1.getBaseSellPrice()).append("\n");
                craftInfo.append("Is Edible: ").append(crop1.isEdible() ? "TRUE" : "FALSE").append("\n");
                //                craftInfo.append("Base Health: ").append(crop1.getBaseHealth()).append("\n");
                //                craftInfo.append("Season: ").append(crop1.getSeason()).append("\n");
                craftInfo.append("Can Become Giant: ").append(crop1.isCanBecomeGiant() ? "TRUE" : "FALSE");
                //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
                Game game = null;
                popUpMessage(gameController, craftInfo.toString(), game);
                batch.begin();
                font.draw(batch, craftInfo.toString(), 100, 100);
                batch.end();
                return new Result(true, craftInfo.toString());
            }
        }
        batch.begin();
        font.draw(batch, "craft not found", 100, 100);
        batch.end();
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        popUpMessage(gameController, "craft not found", game);
        return new Result(false, "craft not found");
    }

    private void popUpMessage(GameController gameController, String message, Game game) {
        gameController.getGameMenu().getGameView().setPopupMessage(message);
        gameController.getGameMenu().getGameView().setShowPopup(true);
        gameController.getGameMenu().getGameView().setPopupRect(new Rectangle(Gdx.graphics.getWidth() / 2 - 150,
            Gdx.graphics.getHeight() / 2 - 75,
            300, 150));
    }

    private static final Map<MixedSeedsEnums, AllCropsEnums> SEED_TO_CROP_MAP = Map.ofEntries(
        // Fall crops
        Map.entry(MixedSeedsEnums.Artichoke, AllCropsEnums.Artichoke),
        Map.entry(MixedSeedsEnums.Corn, AllCropsEnums.Corn),
        Map.entry(MixedSeedsEnums.Eggplant, AllCropsEnums.EggPlant), // Note case difference
        Map.entry(MixedSeedsEnums.Pumpkin, AllCropsEnums.Pumpkin),
        Map.entry(MixedSeedsEnums.Sunflower, AllCropsEnums.SunFlower), // Note case difference
        Map.entry(MixedSeedsEnums.FairyRose, AllCropsEnums.FairyRose),

        // Spring crops
        Map.entry(MixedSeedsEnums.CauliFlower, AllCropsEnums.CauliFlower),
        Map.entry(MixedSeedsEnums.Parsnip, AllCropsEnums.Parsnip),
        Map.entry(MixedSeedsEnums.Potato, AllCropsEnums.Potato),
        Map.entry(MixedSeedsEnums.BlueJazz, AllCropsEnums.BlueJazz),
        Map.entry(MixedSeedsEnums.Tulip, AllCropsEnums.Tulip),

        // Summer crops
        Map.entry(MixedSeedsEnums.HotPepper, AllCropsEnums.HotPepper),
        Map.entry(MixedSeedsEnums.Radish, AllCropsEnums.Radish),
        Map.entry(MixedSeedsEnums.Wheat, AllCropsEnums.Wheat),
        Map.entry(MixedSeedsEnums.Poppy, AllCropsEnums.Poppy),
        Map.entry(MixedSeedsEnums.SummerSpangle, AllCropsEnums.SummerSpangle),

        // Winter crop
        Map.entry(MixedSeedsEnums.PowderMelon, AllCropsEnums.PowderMelon)
    );

    public static Result plant(MixedSeed mixedSeed, Kashi kashi, Player playerrr) {

        Player currentplayer = playerrr;

        if (!kashi.isShokhmZadeh()) {
            return new Result(false, "You have to shokhm it first");
        }

        try {

            playerrr
                .getInventory().removeItem(mixedSeed, 1);

            Seasons currentSeason = App.getCurrentGame().getCurrentSeason();

            List<MixedSeedsEnums> seasonalCrops = Arrays.stream(MixedSeedsEnums.values())
                .filter(crop -> crop.isAllowedIn(currentSeason))
                .collect(Collectors.toList());

            if (seasonalCrops.isEmpty()) {
                return new Result(false, "No crops available for planting in " + currentSeason);
            }


            Random random = new Random();
            MixedSeedsEnums selectedSeed = seasonalCrops.get(random.nextInt(seasonalCrops.size()));

            AllCropsEnums cropEnum = SEED_TO_CROP_MAP.get(selectedSeed);

            if (cropEnum == null) {
                return new Result(false, "No corresponding crop found for " + selectedSeed.name());
            }

            AllCrop allCrop = new AllCrop();
            allCrop.initilizeCrop(selectedSeed);

            Player currentPlayer = playerrr;
            kashi.setInside(allCrop);

            currentPlayer.getMyFarm().getAllCrops().add(allCrop);
            return new Result(true, "Planted " + cropEnum.name() + " successfully");
        } catch (Exception e) {
            return new Result(false, "can't plant");
        }

    }

    public static Result plant(ForagingSeed foragingSeed, Kashi kashi, Player playerrr) {

        Player currentplayer = playerrr;

        if (!kashi.isShokhmZadeh()) {
            return new Result(false, "You have to shokhm it first");
        }

        //ForagingSeed
        try {
            AllCrop allCrop1 = new AllCrop();
            ForagingSeedsEnums foragingSeedsEnums = foragingSeed.getType();
            playerrr.getInventory().removeItem(foragingSeed, 1);

            allCrop1.setSourceForagingSeedEnum(foragingSeedsEnums);
            allCrop1.initilizeCrop(foragingSeedsEnums);

            Player currentPlayer = playerrr;
            Kashi kashi1 = kashi;
            kashi1.setInside(allCrop1);

            currentPlayer.getMyFarm().getAllCrops().add(allCrop1);
            return new Result(true, "Plant successfully placed");

        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return new Result(false, "can't plant");
    }

    public static Result plant(TreeSeed treeSeed, Kashi kashi, Player playerrr) {
        Player currentplayer = playerrr;

        if (!kashi.isShokhmZadeh()) {
            return new Result(false, "You have to shokhm it first");
        }

        //TreeSeed

        AllTree allTree = new AllTree();
        try {
            TreeSeedEnums allTreesEnums = treeSeed.getType();
            playerrr.getInventory().removeItem(treeSeed, 1);


            allTree.setSource(allTreesEnums);
            allTree.initilizeCrop(allTreesEnums);

            Player currentPlayer = playerrr;
            Kashi kashi2 = kashi;
            kashi2.setInside(allTree);

            currentPlayer.getMyFarm().getAllTrees().add(allTree);


            return new Result(true, "Tree successfully placed");
        } catch (Exception ee) {
            return new Result(false, "what happened");
        }
    }

    public Result showPlant(int x, int y, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Kashi targetKashi = new Kashi();
        try {
            targetKashi = App.getCurrentGame().getMap().get(x).get(y);
        } catch (Exception e) {
            return new Result(false, "invalid x or y");
        }

        if (targetKashi.getInside() instanceof AllCrop) {
            AllCrop allCrop = (AllCrop) targetKashi.getInside();

            StringBuilder resultBuilder = new StringBuilder();

            resultBuilder.append("=== Crop Details ===\n");
            resultBuilder.append(String.format("%-20s: %s%n", "Type", allCrop.getType()));

            if (allCrop.getSourceForagingSeedEnum() != null) {
                resultBuilder.append(String.format("%-20s: %s%n", "Source (Foraging Seed)", allCrop.getSourceForagingSeedEnum()));
            } else {
                resultBuilder.append(String.format("%-20s: %s%n", "Source (Mixed Seed)", allCrop.getSourceMixedSeedEnum()));
            }

            resultBuilder.append(String.format("%-20s: %d days%n", "Growth Counter", allCrop.getDaysGrowCounter()));
            resultBuilder.append(String.format("%-20s: %s%n", "Growth Stages", allCrop.getStages()));
            resultBuilder.append(String.format("%-20s: %d days%n", "Total Harvest Time", allCrop.getTotalHarvestTime()));
            resultBuilder.append(String.format("%-20s: %s%n", "One-Time Harvest", allCrop.isOneTime()));

            if (allCrop.getRegrowthTime() != -1) {
                resultBuilder.append(String.format("%-20s: %d days%n", "Regrowth Time", allCrop.getRegrowthTime()));
            }

            resultBuilder.append(String.format("%-20s: %s%n", "Can Become Giant", allCrop.isCanBecomeGiant()));
            resultBuilder.append(String.format("%-20s: %s%n", "Fed Today", allCrop.isFedThisDay()));
            resultBuilder.append(String.format("%-20s: %s%n", "Edible", allCrop.isEdible()));
            resultBuilder.append(String.format("%-20s: %s%n", "Energy", allCrop.getEnergy()));
            resultBuilder.append(String.format("%-20s: %s%n", "Base sell Price", allCrop.getBaseSellPrice()));
            resultBuilder.append("====================\n");

            return new Result(true, resultBuilder.toString());
        } else {
            if (targetKashi.getInside() instanceof ForagingCrop) {
                ForagingCrop foragingCrop = (ForagingCrop) targetKashi.getInside();

                StringBuilder resultBuilder = new StringBuilder();

                resultBuilder.append("=== Crop Details ===\n");
                resultBuilder.append(String.format("%-20s: %s%n", "Type", foragingCrop.getType()));
                resultBuilder.append(String.format("%-20s: %s%n", "Energy", foragingCrop.getEnergy()));
                resultBuilder.append(String.format("%-20s: %s%n", "Base sell Price", foragingCrop.getBaseSellPrice()));
                resultBuilder.append("====================\n");

                return new Result(true, resultBuilder.toString());
            } else {
                if (targetKashi.getInside() instanceof AllTree) {
                    AllTree allTree = (AllTree) targetKashi.getInside();

                    StringBuilder resultBuilder = new StringBuilder();

                    resultBuilder.append("=== Crop Details ===\n");
                    resultBuilder.append(String.format("%-20s: %s%n", "Type", allTree.getType()));

                    resultBuilder.append(String.format("%-20s: %s%n", "Source (Mixed Seed)", allTree.getSource()));

                    resultBuilder.append(String.format("%-20s: %d days%n", "Growth Counter", allTree.getDaysGrowCounter()));
                    resultBuilder.append(String.format("%-20s: %s%n", "Growth Stages", allTree.getStages()));
                    resultBuilder.append(String.format("%-20s: %d days%n", "Total Harvest Time", allTree.getTotalHarvestTime()));
                    resultBuilder.append(String.format("%-20s: %s%n", "One-Time Harvest", allTree.getFruitHarvestCycle()));


                    resultBuilder.append(String.format("%-20s: %s%n", "Can Become Giant", allTree.isCanBecomeGiant()));
                    resultBuilder.append(String.format("%-20s: %s%n", "Fed Today", allTree.isFedThisDay()));
                    resultBuilder.append(String.format("%-20s: %s%n", "Edible", allTree.isEdible()));
                    resultBuilder.append(String.format("%-20s: %s%n", "Energy", allTree.getEnergy()));
                    resultBuilder.append(String.format("%-20s: %s%n", "Base sell Price", allTree.getBaseSellPrice()));
                    resultBuilder.append("====================\n");

                    return new Result(true, resultBuilder.toString());
                } else {
                    if (targetKashi.getInside() instanceof AllTree) {
                        ForagingCrop foragingCrop = (ForagingCrop) targetKashi.getInside();

                        StringBuilder resultBuilder = new StringBuilder();

                        resultBuilder.append("=== Crop Details ===\n");
                        resultBuilder.append(String.format("%-20s: %s%n", "Type", foragingCrop.getType()));
                        resultBuilder.append(String.format("%-20s: %s%n", "Energy", foragingCrop.getEnergy()));
                        resultBuilder.append(String.format("%-20s: %s%n", "Base sell Price", foragingCrop.getBaseSellPrice()));
                        resultBuilder.append("====================\n");

                        return new Result(true, resultBuilder.toString());
                    } else {
                        return new Result(false, "No plant on this kashi");
                    }
                }
            }
        }

    }

    public static Result fertilize(Fertilizer fertilizer, Kashi kashi, Player playerrr) {

        Player currentPlayer = playerrr;

        if (kashi.getInside() instanceof AllTree || kashi.getInside() instanceof AllCrop) {
            if (kashi.getInside() instanceof AllTree) {
                AllTree allTree = (AllTree) kashi.getInside();
                allTree.setDaysGrowCounter(allTree.getTotalHarvestTime());
            } else {
                AllCrop allCrop = (AllCrop) kashi.getInside();
                allCrop.setDaysGrowCounter(allCrop.getTotalHarvestTime());
            }
            playerrr.getInventory().removeItem(fertilizer, 1);
            return new Result(true, "Successfully Fertilized");
        } else {
            return new Result(false, "Stand near a plant");
        }

    }

    public Result howMuchWater(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        for (Item item : playerrr.getInventory().getItems().keySet()) {
            if (item instanceof WateringCan) {
                return new Result(true, "Capacity: " + (((WateringCan) item).getCapacity()));
            }
        }
        return new Result(false, "No WateringCan found");
    }

    public Result craftingShowRecipes(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder resultBuilder = new StringBuilder();
        ArrayList<CraftingRecipesEnums> craftingRecipes = playerrr
            .getCraftingRecipes();

        for (CraftingRecipesEnums craftingRecipe : craftingRecipes) {
            resultBuilder.append(craftingRecipe.name()).append(":\n");

            for (Map.Entry<String, Integer> entry : craftingRecipe.getIngredients().entrySet()) {
                resultBuilder.append("  - ")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
            }

            resultBuilder.append("\n");
        }
        //resultBuilder.deleteCharAt(resultBuilder.length() - 1);
        if (resultBuilder.isEmpty()) {
            resultBuilder.append("No crafting recipes found");
        }
        return new Result(true, resultBuilder.toString());
    }

    public Result craftingCraft(String itemName, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = playerrr;

        // 1. بررسی انرژی و دسترسی‌های اولیه
        if (currentPlayer.getEnergy() < 2) {
            return new Result(false, "Not enough energy to craft " + itemName);
        }

        if (!CraftingRecipesEnums.containsCraft(itemName)) {
            return new Result(false, "No crafting recipe found for " + itemName);
        }

        // 2. پیدا کردن دستور ساخت
        CraftingRecipesEnums recipe = findRecipe(currentPlayer, itemName);
        if (recipe == null) {
            return new Result(false, "Craft recipe not found");
        }

        // 3. بررسی مواد اولیه
        if (!hasRequiredMaterials(currentPlayer, recipe)) {
            return new Result(false, "Not enough resources to craft " + itemName);
        }

        // 4. انجام عملیات ساخت
        return executeCrafting(currentPlayer, itemName, recipe, playerrr);
    }

    private CraftingRecipesEnums findRecipe(Player player, String itemName) {
        return player.getCraftingRecipes().stream()
            .filter(recipe -> recipe.name().equalsIgnoreCase(itemName))
            .findFirst()
            .orElse(null);
    }

    private boolean hasRequiredMaterials(Player player, CraftingRecipesEnums recipe) {
        Map<String, Integer> requiredItems = recipe.getIngredients();

        for (Map.Entry<String, Integer> entry : requiredItems.entrySet()) {
            String itemName = entry.getKey().replaceAll(" ", "");
            int requiredCount = entry.getValue();

            if (itemName.equalsIgnoreCase("wood")) {
                if (player.getWood() < requiredCount) {
                    return false;
                }
            } else {
                int totalCount = 0;
                for (Map.Entry<Item, Pair<Integer, Integer>> inventoryEntry : player.getInventory().getItems().entrySet()) {
                    if (inventoryEntry.getKey().getCorrectName().equalsIgnoreCase(itemName)) {
                        totalCount += inventoryEntry.getValue().first;
                    }
                }
                if (totalCount < requiredCount) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countPlayerItems(Player player, String itemName) {
        return player.getInventory().getItems().entrySet().stream()
            .filter(entry -> entry.getKey().getCorrectName().equalsIgnoreCase(itemName))
            .mapToInt(entry -> entry.getValue().first) // گرفتن تعداد از Pair
            .sum();
    }


    private Result executeCrafting(Player player, String itemName, CraftingRecipesEnums recipe, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        // مصرف مواد اولیه
        consumeMaterials(player, recipe, playerrr);

        // افزودن آیتم ساخته شده
        addCraftedItem(player, itemName, playerrr);

        // کاهش انرژی
        player.setEnergy(player.getEnergy() - 2);

        return new Result(true, itemName + " crafted successfully");
    }

    private void consumeMaterials(Player player, CraftingRecipesEnums recipe, Player playerrr) {
        if (isFainted(playerrr)) {
            System.out.println("You are fainted!");
        }
        for (Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            String itemName = entry.getKey().replaceAll(" ", "");
            int requiredCount = entry.getValue();

            if (itemName.equalsIgnoreCase("wood")) {
                player.setWood(player.getWood() - requiredCount);
            } else {
                int remaining = requiredCount;
                Iterator<Map.Entry<Item, Pair<Integer, Integer>>> iterator = player.getInventory().getItems().entrySet().iterator();

                while (iterator.hasNext() && remaining > 0) {
                    Map.Entry<Item, Pair<Integer, Integer>> inventoryEntry = iterator.next();
                    if (inventoryEntry.getKey().getCorrectName().equalsIgnoreCase(itemName)) {
                        int available = inventoryEntry.getValue().first;

                        if (available <= remaining) {
                            remaining -= available;
                            iterator.remove();
                        } else {
                            inventoryEntry.setValue(new Pair<>(available - remaining, inventoryEntry.getValue().second));
                            remaining = 0;
                        }
                    }
                }
            }
        }
    }

    private void addCraftedItem(Player player, String itemName, Player playerrr) {
        if (isFainted(playerrr)) {
            System.out.println("You are fainted!");
        }
        player.getInventory().getItems().entrySet().stream()
            .filter(entry -> entry.getKey() instanceof CraftingItem &&
                entry.getKey().getClass().getSimpleName().equalsIgnoreCase(itemName))
            .findFirst()
            .ifPresentOrElse(
                entry -> entry.setValue(new Pair<>(entry.getValue().first + 1, entry.getValue().second)),
                () -> {
                    CraftingItem newItem = new CraftingItem(itemName);
                    newItem.setName(itemName);
                    player.getInventory().addItem(newItem, 1);
                }
            );
    }

//    public Result placeItem(String name, String direction) {
//        if (isFainted()) {
//            return new Result(false, "You are fainted!");
//        }
//        name = name.replaceAll(" ", "").toLowerCase();
//        Player player = playerrr;
//        Cord tileCord = new Cord((int) player.getX(), (int) player.getY());
//        int dir_x = -1;
//        int dir_y = -1;
//        switch (direction.toLowerCase()) {
//            case "n": {
//                dir_x = 0;
//                dir_y = -1;
//                break;
//            }
//            case "ne": {
//                dir_x = 1;
//                dir_y = -1;
//                break;
//            }
//            case "e": {
//                dir_x = 1;
//                dir_y = 0;
//                break;
//            }
//            case "se": {
//                dir_x = 1;
//                dir_y = 1;
//                break;
//            }
//            case "s": {
//                dir_x = 0;
//                dir_y = 1;
//                break;
//            }
//            case "sw": {
//                dir_x = -1;
//                dir_y = 1;
//                break;
//            }
//            case "w": {
//                dir_x = -1;
//                dir_y = 0;
//                break;
//            }
//            case "nw": {
//                dir_x = -1;
//                dir_y = -1;
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        Iterator<Map.Entry<Item, Integer>> iterator = player.getInventory().getItems().entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Item, Integer> entry = iterator.next();
//            Item item = entry.getKey();
//            int count = entry.getValue();
//
//            if (item.getCorrectName().equalsIgnoreCase(name)) {
//                if (count > 1) {
//                    player.getInventory().getItems().put(item, count - 1); // یکی کم کن
//                } else {
//                    iterator.remove(); // اگر فقط یکی بود، کل آیتم رو حذف کن
//                }
//
//                tileCord.setX(dir_x + tileCord.getX());
//                tileCord.setY(dir_y + tileCord.getY());
//                if (App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).getInside() == null) {
//                    App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).setItemInside(item);
//                } else {
//                    return new Result(false, "tile isn't empty for item");
//                }
//                return new Result(true, "craft " + name + " placed successfully");
//            }
//        }
//        return new Result(false, "item " + name + " not found");
//    }

    public Result cheatAddItem(String name, int count, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        name = name.replace(" ", "");
        Player player = playerrr;
        Map<Item, Pair<Integer, Integer>> inventory = player.getInventory().getItems();

        boolean addItem = false;
        boolean canAdd = !(player.getInventory().isFull()); // این متد باید بررسی کند ظرفیت اینونتوری کافی هست یا نه

        if (!canAdd) {
            return new Result(false, "cheat code: Not enough space in inventory to add " + count + " of " + name);
        }
        if (name.equalsIgnoreCase("mixedseeds")) {
            MixedSeed MixedSeed = new MixedSeed();
            player.getInventory().addItem(MixedSeed, count);
            addItem = true;
        } else if (ForagingSeedsEnums.isContain(name)) {
            ForagingSeed foragingSeed = new ForagingSeed();
            foragingSeed.setType(ForagingSeedsEnums.getEnum(name));
            player.getInventory().addItem(foragingSeed, count);
            addItem = true;
        } else if (TreeSeedEnums.isContain(name)) {
            TreeSeed treeSeed = new TreeSeed();
            treeSeed.setType(TreeSeedEnums.getEnum(name));
            player.getInventory().addItem(treeSeed, count);
            addItem = true;
        } else if (AllCropsEnums.isContain(name)) {
            AllCrop allCrop = new AllCrop();
            allCrop.setType(AllCropsEnums.getEnum(name));
            player.getInventory().addItem(allCrop, count);
            addItem = true;
        } else if (ForagingMineralsEnums.isContain(name)) {
            Mineral mineral = new Mineral();
            mineral.setType(ForagingMineralsEnums.getEnum(name));
            player.getInventory().addItem(mineral, count);
            addItem = true;
        } else if (AllFishesEnum.isValidFish(name)) {
            Fish fish = new Fish(name);
            fish.setName(AllFishesEnum.getEnum(name).toString());
            player.getInventory().addItem(fish, count);
            addItem = true;
        } else if (ArtisanGoodsEnums.isContain(name)) {
            ArtisanGoods artisanGoods = new ArtisanGoods(name);
            artisanGoods.setName(ArtisanGoodsEnums.getEnum(name).toString());
            player.getInventory().addItem(artisanGoods, count);
            addItem = true;
        } else if (ForagingCropsEnums.isContain(name)) {
            ForagingCrop foragingCrop = new ForagingCrop();
            foragingCrop.setType(ForagingCropsEnums.getEnum(name));
            player.getInventory().addItem(foragingCrop, count);
            addItem = true;
        } else if (CraftingRecipesEnums.containsCraft(name)) {
            CraftingItem craftingItem = new CraftingItem(name);
            craftingItem.setName(name);
            playerrr.
                getCraftingRecipes().add(craftingItem.getCraftingItem());
            player.getInventory().addItem(craftingItem, count);
            addItem = true;
        } else if (FoodCookingEnums.isContain(name)) {
            Cookingrecipe cookingrecipe = new Cookingrecipe();
            cookingrecipe.setFood(FoodCookingEnums.valueOf(name));
            playerrr.getCookingRecipes().add(cookingrecipe);
            addItem = true;
        } else if (PoleJensEnums.isContain(name)) {
            String jens = name.replaceAll("pole", "");
            FishingPole fishingPole = new FishingPole();
            fishingPole.setJens(jens);
            player.getInventory().addItem(fishingPole, count);
            addItem = true;
        } else {
            switch (name) {
                case "stone":
                    StoneItem stoneItem = new StoneItem();
                    stoneItem.setPrice(20);
                    player.getInventory().addItem(stoneItem, count);
                    addItem = true;
                    break;
                case "pizza":
                    Food food = new Food();
                    food.setName("pizza");
                    player.getInventory().addItem(food, count);
                    addItem = true;
                    break;
                case "wood":
                    player.setWood(player.getWood() + count);
                    System.out.println("New Wood: " + player.getWood());
                    addItem = true;
                    break;
                case "pumpkinpie":
                    FoodCooking pumpkinPie = new FoodCooking();
                    pumpkinPie.setSellPrice(385);
                    pumpkinPie.setEnergy(225);
                    pumpkinPie.setName(FoodCookingEnums.PumpkinPie);
                    player.getInventory().addItem(pumpkinPie, count);
                    addItem = true;
                    break;
                case "flower":
                    player.getInventory().addItem(new Flower(), count);
                    addItem = true;
                    break;
                case "BasicRetainingSoil":
                    Fertilizer fertilizer2 = new Fertilizer();
                    fertilizer2.setName("Basic Retaining Soil");
                    fertilizer2.setPrice(100);
                    player.getInventory().addItem(fertilizer2, count);
            }

        }

//        for (Item item : AllGameItems.getAllItems()) {
//            if (item.getClass().getSimpleName().equalsIgnoreCase(name)) {
//                foundItem = item;
//                break;
//            }
//        }


        // بررسی امکان اضافه کردن آیتم به اینونتوری
        // اگر آیتم در اینونتوری هست، تعدادش را زیاد می‌کنیم
//        boolean added = false;
//        for (Map.Entry<Item, Integer> entry : inventory.entrySet()) {
//            if (entry.getKey().getClass().getSimpleName().equalsIgnoreCase(name)) {
//                inventory.put(entry.getKey(), entry.getValue() + count);
//                added = true;
//                break;
//            }
//        }

        // اگر آیتم جدید بود، اضافه‌اش می‌کنیم
//        if (!added) {
//            // یک نمونه جدید از آیتم بساز (clone یا new) چون باید کلید جدید برای هش‌مپ ساخته شود
//            try {
//                Item newItem = foundItem.getClass().getDeclaredConstructor().newInstance();
//                inventory.put(newItem, count);
//            } catch (Exception e) {
//                return new Result(false, "Error: Could not instantiate item '" + name + "'.");
//            }
//        }

        if (addItem) {

            return new Result(true, "Successfully added " + count + " of " + name + " to inventory.");
        } else {
            return new Result(false, "Failed to add " + count + " of " + name + " to inventory.");
        }
    }

    public Result cookingRefrigerator(String pickOrPut, String itemname, Player playerrr) throws ClassNotFoundException {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Refrigerator refrigerator = playerrr
            .getMyFarm().getMyCottage().getMyRefrigerator();
        Stack<Integer> freeIndexes = new Stack<>();
        for (int i = 9 - 1; i >= 0; i--) {
            freeIndexes.push(i);
        }
        refrigerator.setFreeIndexes(freeIndexes);
        if (pickOrPut.equalsIgnoreCase("put")) {
            if (refrigerator.getTotalItemCount() >= 9) {
                return new Result(false, "Refrigerator is full");
            }
            boolean found = false;
            for (Item item : playerrr.getInventory().getItems().keySet()) {
                if (item.getCorrectName().equals(itemname.toLowerCase().replace(" ", ""))) {
                    found = true;
                    refrigerator.addItem(item, 1);
                    playerrr.getInventory().removeItem(item, 1);
                    return new Result(true, "Successful");
                }
            }
            if (!found) {
                return new Result(false, "You don't have that item");
            }
        }
        if (pickOrPut.equalsIgnoreCase("pick")) {
            boolean found = false;
            if (refrigerator == null) {
                return new Result(false, "You don't have that item");
            }
            for (Item item : refrigerator.getItems().keySet()) {
                if (item.getCorrectName().equals(itemname.toLowerCase().replace(" ", ""))) {
                    found = true;
                    if (playerrr.getInventory().isFull()) {
                        return new Result(false, "Inventory is full");
                    }
                    playerrr.getInventory().addItem(item, 1);
                    refrigerator.removeItem(item, 1);
                    return new Result(true, "Successful");
                }
            }
            if (!found) {
                return new Result(false, "You don't have that item");
            }
        }
        return new Result(false, "");
    }

    public Result cookingShowRecipes(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        if (playerrr.getCookingRecipes().isEmpty()) {
            return new Result(false, "You are cooked(0 cooking recipes)");
        } else {
            StringBuilder result = new StringBuilder();
            for (Cookingrecipe cookingrecipe : playerrr.getCookingRecipes()) {
                result.append(cookingrecipe.getFood()).append(" recipe");
            }
            return new Result(true, result.toString());
        }
    }

    public Result cookingPrepare(String recipeName, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        if (playerrr.getEnergy() < 3) {
            return new Result(false, "Not enough energy to cook");
        }
        Set<String> FOOD_ENUMS = new HashSet<>();
        for (FoodCookingEnums food : FoodCookingEnums.values()) {
            FOOD_ENUMS.add(food.name().toLowerCase());
        }
        if (isValidFood(recipeName.toLowerCase(), FOOD_ENUMS)) {
            FoodCookingEnums foodCookingEnums;
            try {
                foodCookingEnums = FoodCookingEnums.valueOf(recipeName);
            } catch (IllegalArgumentException e) {
                return new Result(false, "invalid food");
            }
            Cookingrecipe targetCookingRecipe = null;
            for (Cookingrecipe cookingrecipe : playerrr.getCookingRecipes()) {
                if (cookingrecipe.getFood() == foodCookingEnums) {
                    targetCookingRecipe = cookingrecipe;
                }
            }
            if (targetCookingRecipe == null) {
                return new Result(false, "You don't have the recipe");
            }
            HashMap<String, Integer> ingredients = foodCookingEnums.getIngredients();

            for (String food : ingredients.keySet()) {
                boolean found = false;
                for (Item item : playerrr.getInventory().getItems().keySet()) {
                    if (item.getCorrectName().equals(food.toLowerCase().replace(" ", ""))) {
                        if (playerrr.getInventory().getItemQuantity(item) >= ingredients.get(food)) {
                            found = true;
                        }
                    }
                }
                if (!found) {
                    //System.out.println(food + " nigga");
                    return new Result(false, "You don't have the items needed");
                }
            }
            for (String food : ingredients.keySet()) {
                for (Item item : playerrr.getInventory().getItems().keySet()) {
                    if (item.getCorrectName().equals(food.toLowerCase().replace(" ", ""))) {
                        playerrr.getInventory().removeItem(item, ingredients.get(food));
                    }
                }
            }

            FoodCooking foodCooking = targetCookingRecipe.letmecook(foodCookingEnums);
            playerrr.getInventory().addItem(foodCooking, 1);
            playerrr.setEnergy(playerrr.getEnergy() - 3);
            return new Result(true, "Successfully made " + recipeName);
        } else {
            return new Result(false, "Invalid food");
        }
    }

    public static boolean isValidFood(String input, Set<String> FOOD_ENUMS) {
        String processedInput = input.replace(" ", "");
        return FOOD_ENUMS.stream()
            .anyMatch(food -> food.equalsIgnoreCase(processedInput));
    }

    public Result eat(String foodName, Player playerrr) {

        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Set<String> FOOD_ENUMS = new HashSet<>();
        FoodCookingEnums foodCookingEnums = null;
        for (FoodCookingEnums food : FoodCookingEnums.values()) {
            FOOD_ENUMS.add(food.name().toLowerCase());
            if (food.name().equalsIgnoreCase(foodName)) {
                foodCookingEnums = FoodCookingEnums.valueOf(foodName);
            }
        }

        //        try {
        //        } catch (Exception e) {
//            return new Result(false, "Invalid food");
//        }
        if (isValidFood(foodName, FOOD_ENUMS)) {
            for (Item item : playerrr.getInventory().getItems().keySet()) {
                if (item instanceof FoodCooking) {
                    FoodCooking foodCooking = (FoodCooking) item;
                    if (foodCooking.getNamee() == foodCookingEnums) {
                        playerrr.setEnergy(Math.min(playerrr.getMaxEnergy(), playerrr.getEnergy() + foodCooking.getEnergy()));
                        playerrr.getInventory().removeItem(item, 1);
                        playerrr.setFoodBuff(foodCooking.getBuff());
                        showBuffEffect(foodCooking.getBuff());
                        //Server-TODO
//                        App.getGameController().getGameMenu().setBuff(foodCooking.getBuff());
//                        App.getGameController().getGameMenu().
                        return new Result(true, "You ate " + foodName);
                    }
                }
            }
        } else if (AllCropsEnums.isContain(foodName)) {
            AllCropsEnums allCropsEnums = AllCropsEnums.valueOf(foodName);
            ForagingSeedsEnums cropSeed = allCropsEnums.getSeedSet();
            AllCrop crop = new AllCrop();
            crop.initilizeCrop(cropSeed);
            if (crop.isEdible()) {
                for (Item item : playerrr.getInventory().getItems().keySet()) {
                    if (item instanceof AllCrop) {
                        if (crop.getCorrectName().equalsIgnoreCase(foodName)) {
                            playerrr.setEnergy(Math.min(playerrr.getMaxEnergy(), playerrr.getEnergy() + crop.getEnergy()));
                            playerrr.getInventory().removeItem(item, 1);
                            return new Result(true, "You ate " + foodName);
                        }
                    }
                }
            }
        } else if (ArtisanGoodsEnums.isContain(foodName)) {
            ArtisanGoods artisanGoods = new ArtisanGoods(foodName);
            if (artisanGoods.getEnergyUsage() != 0) {
                for (Item item : playerrr.getInventory().getItems().keySet()) {
                    if (item instanceof ArtisanGoods) {
                        if (artisanGoods.getCorrectName().equalsIgnoreCase(foodName)) {
                            playerrr.
                                setEnergy(Math.min(playerrr.getMaxEnergy(), playerrr.getEnergy() + artisanGoods.getEnergyUsage()));
                            playerrr.getInventory().removeItem(item, 1);
                            return new Result(true, "You ate " + foodName);
                        }
                    }
                }
            }
        } else {
            return new Result(false, "Invalid food");
        }
        return new Result(false, "You don't have that food");
    }


    public Result pet(String name, Player playerrr) {
        Animal animal = findAnimalByName(name, playerrr);
        if (animal == null) {
            return new Result(false, "You can not pet that animal!");
        }
        if (!animal.isOutside()) {
            return new Result(false, "You can not pet that animal!(it is on its cage/Tavileh)!");
        }
        if (animal.getXofAnimal() == -1 && animal.getYofAnimal() == -1) {
            return new Result(false, "it is inside!");
        } else {
            int x = (int) playerrr.getX();
            int y = (int) playerrr.getY();
            int xOfAnimal = animal.getXofAnimal();
            int yOfAnimal = animal.getYofAnimal();
            boolean isOkay = AreYouNearForNavazesh(x, y, xOfAnimal, yOfAnimal);
            if (isOkay) {
                animal.setNavazesh(true);
                animal.setFriendship(animal.getFriendship() + 15);
                return new Result(true, "you navazeshed it successfully!");
            } else {
                return new Result(false, "You should be more near to " + animal.getName());
            }
        }
    }

    public boolean AreYouNearForNavazesh(int x, int y, int animalx, int animaly) {
        if ((x - 1 == animalx && y == animaly) || (x == animalx && y - 1 == animaly) || (x + 1 == animalx && y == animaly) || (x == animalx && y + 1 == animaly)) {
            return true;
        }
        return false;
    }

    public Result cheatSetFriendship(String animalName, int amount, Player playerrr) {
        Animal animal = findAnimalByName(animalName, playerrr);
        if (animal == null) {
            return new Result(false, "Your entered name does not exists between your animals!");
        } else if (amount > 1000) {
            return new Result(false, "The amount of friendship level can not be more than 1000!");
        } else {
            animal.setFriendship(amount);
            return new Result(true, "Your friendship between you and " + animalName + " set to " + amount + " successfully!");
        }

    }

    public Result animals(Player playerrr) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
        for (Animal animal : animals) {
            // name
            sb.append("Animal name:  ").append(animal.getName()).append("\n");
            // navazesh
            sb.append("Is Navazesh today: ").append(animal.isNavazesh() ? "Yes" : "No").append("\n");
            // fed
            sb.append("Is Fed today:  ").append(animal.isTaghzieh() ? "Yes" : "No").append("\n");
            // friendship level
            sb.append("Friendship level: ").append(animal.getFriendship()).append("\n");
            sb.append("----------------------------\n");
        }
        return new Result(true, sb.toString());
    }

    public Result shepherdInAnimals(String animalName, Player playerrr) {
        ArrayList<ArrayList<Kashi>> map = App.getCurrentGame().getMap();
        //errors
        boolean founded = MarniesRanchController.IsAnimalNameUnique(animalName, playerrr);
        if (founded) {
            return new Result(false, "your animal name doesn't exist!");
        }
        // find that animal
        Animal animal = findAnimalByName(animalName, playerrr);
        if (!(animal.isOutside())) {
            return new Result(false, "Your entered animal is inside!");
        } else {

            animal.setXofAnimal(-1);
            animal.setYofAnimal(-1);
            String where = animal.getWhereDoILive();
            if (animal instanceof CageAnimal) {
                switch (where) {
                    case "coop":
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyCage().getCageAnimals();
                        cageAnimals.add((CageAnimal) animal);
                        break;
                    case "bigcoop":
                        ArrayList<CageAnimal> bigCoopAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                        bigCoopAnimals.add((CageAnimal) animal);
                        break;
                    case "deluxecoop":
                        ArrayList<CageAnimal> deluxeCoopAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                        deluxeCoopAnimals.add((CageAnimal) animal);
                        break;
                    default:
                        break;
                }
            }
            if (animal instanceof TavilehAnimal) {
                switch (where) {
                    case "barn":
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyTavileh().getTavilehAnimals();
                        tavilehAnimals.add((TavilehAnimal) animal);
                        break;
                    case "bigbarn":
                        ArrayList<TavilehAnimal> bigBarnAnimals = playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals();
                        bigBarnAnimals.add((TavilehAnimal) animal);
                        break;
                    case "deluxebarn":
                        ArrayList<TavilehAnimal> deluxeBarnAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                        deluxeBarnAnimals.add((TavilehAnimal) animal);
                        break;
                    default:
                        break;
                }
            }
            return new Result(true, "Your animal successfully moved to his Cage/Tavileh!");
        }
    }

    public Result shepherdOutAnimals(String animalName, int x, int y, Player playerrr) {

        ArrayList<ArrayList<Kashi>> map = App.getCurrentGame().getMap();
        //errors
        boolean founded = MarniesRanchController.IsAnimalNameUnique(animalName, playerrr);
        if (founded) {
            return new Result(false, "your animal name doesn't exist!");
        }
        // find that animal
        Animal animal = findAnimalByName(animalName, playerrr);
        //
        if (animal.isOutside()) {
            return new Result(false, "Your entered animal is outside!");
        }
        if (!map.get(x).get(y).getWalkable()) {
            return new Result(false, "your can not move animal to there!");
        }
        //weather
        if (App.getCurrentGame().getCurrentWeather() == WeatherEnum.RAIN || App.getCurrentGame().getCurrentWeather() == WeatherEnum.SNOW || App.getCurrentGame().getCurrentWeather() == WeatherEnum.STORM) {
            return new Result(false, "Weather is not okay for moving " + animalName + " to outside!");
        } else {
            animal.setXofAnimal(x);
            animal.setYofAnimal(y);
            animal.setOutside(true);
            String where = animal.getWhereDoILive();
            switch (where) {
                case "coop":
                    ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyCage().getCageAnimals();
                    cageAnimals.remove(animal);
                    break;
                case "bigcoop":
                    ArrayList<CageAnimal> bigCoopAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                    bigCoopAnimals.remove(animal);
                    break;
                case "deluxecoop":
                    ArrayList<CageAnimal> deluxeCoopAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                    deluxeCoopAnimals.remove(animal);
                    break;
                case "barn":
                    ArrayList<TavilehAnimal> barnAnimals = playerrr.getMyFarm().getMyTavileh().getTavilehAnimals();
                    barnAnimals.remove(animal);
                    break;
                case "bigbarn":
                    ArrayList<TavilehAnimal> bigBarnAnimals = playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals();
                    bigBarnAnimals.remove(animal);
                    break;
                case "deluxebarn":
                    ArrayList<TavilehAnimal> deluxeBarnAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                    deluxeBarnAnimals.remove(animal);
                    break;
                default:
                    break;
            }
            return new Result(true, animalName + " successfully moved there!");
        }


    }

    public Result shepherdAnimals(String animalName, int x, int y) {
        return new Result(true, "");
    }

    public Result feedHay(String animalName, Player playerrr) {
        Animal animal = findAnimalByName(animalName, playerrr);
        if (animal == null) {
            return new Result(false, "Your entered animal doesn't exist!");
        } else if (animal.isTaghzieh()) {
            return new Result(false, "Your entered animal has been fed today!");
        }
        animal.setTaghzieh(true);
        return new Result(true, "Your animal was fed successfully!");
    }

    public void produces() {

    }

    public Result collectProduce(String name) {
        return new Result(true, "");
    }

    public Result sellAnimal(String name, Player playerrr) {
        Animal animal = findAnimalByName(name, playerrr);
        if (animal == null) {
            return new Result(false, "Your entered animal was not found between your own animals! so you can not sell!");
        }
        if (animal.isOutside()) {
            return new Result(false, "You can not sell this animal because it is outside of coops/barns!");
        }
        int priceOfSelling = (int) (animal.getPrice() * ((animal.getFriendship() * 1000) + 0.3));
        int goldsOfPlayer = playerrr.getGold();
        playerrr.setGold(goldsOfPlayer + priceOfSelling);
        playerrr.getMyBoughtAnimals().remove(animal);
        String where = animal.getWhereDoILive();
        switch (where) {
            case "coop":
                ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyCage().getCageAnimals();
                cageAnimals.remove(animal);
                break;
            case "bigcoop":
                ArrayList<CageAnimal> bigCoopAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                bigCoopAnimals.remove(animal);
                break;
            case "deluxecoop":
                ArrayList<CageAnimal> deluxeCoopAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                deluxeCoopAnimals.remove(animal);
                break;
            case "barn":
                ArrayList<TavilehAnimal> barnAnimals = playerrr.getMyFarm().getMyTavileh().getTavilehAnimals();
                barnAnimals.remove(animal);
                break;
            case "bigbarn":
                ArrayList<TavilehAnimal> bigBarnAnimals = playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals();
                bigBarnAnimals.remove(animal);
                break;
            case "deluxebarn":
                ArrayList<TavilehAnimal> deluxeBarnAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                deluxeBarnAnimals.remove(animal);
                break;
            default:
                break;
        }
        return new Result(true, "You sell " + animal.getName() + " Successfully and " + priceOfSelling + " golds added to your golds!");
    }

    public Result fishing(String fishingPole, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player player = playerrr;
        Map<Item, Pair<Integer, Integer>> inventory = player.getInventory().getItems();
        for (Item item : inventory.keySet()) {
            if (item instanceof FishingPole) {
                String type = ((FishingPole) item).getJens();
                if (type.equalsIgnoreCase(fishingPole)) {
                    return new Result(true, ((FishingPole) item).use());
                }
            }
        }
        return new Result(false, "Fishing pole not found");
    }

    public static boolean equalsIgnoreCase(Set<String> set1, Set<String> set2) {
        if (set1.size() != set2.size()) {
            return false;
        }

        Set<String> lowerSet1 = new HashSet<>();
        for (String s : set1) {
            lowerSet1.add(s.toLowerCase());
        }

        Set<String> lowerSet2 = new HashSet<>();
        for (String s : set2) {
            lowerSet2.add(s.toLowerCase());
        }

        return lowerSet1.equals(lowerSet2);
    }

    public Result artisanUse(String craftName, ArrayList<String> itemName, CraftingItem craftingIte, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        CraftingItem craftingItem = craftingIte;
        Player player = playerrr;
        craftName = craftName.replace("_", "");
        Inventory inventory = player.getInventory();
        Map<Item, Pair<Integer, Integer>> inventoryItems = inventory.getItems();
        boolean isEnoughIngredient = false;
        String artisanName = "";
        ArrayList<ArtisanGoodsEnums> artisanGoodsPossible = new ArrayList<>();
        for (ArtisanGoodsEnums goods : ArtisanGoodsEnums.values()) {
            if (goods.getProducer().name().equalsIgnoreCase(craftName)) {
                artisanGoodsPossible.add(goods);
            }
        }
        for (ArtisanGoodsEnums goods : artisanGoodsPossible) {
            Set<String> mapKeys = new HashSet<>(goods.getIngredients().keySet());
            Set<String> listItems = new HashSet<>(itemName);
            if (equalsIgnoreCase(mapKeys, listItems)) {
                isEnoughIngredient = true;
                artisanName = goods.name();
                break;
            }
        }
        if (isEnoughIngredient) {
            ArtisanGoods artisanGood = new ArtisanGoods(artisanName);
            artisanGood.startProcessing(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour(),
                App.getCurrentGame().getCurrentDateTime().getDay()));
            player.getArtisansInProduce().add(artisanGood);
            craftingItem.setInsideArtisan(artisanGood);
            craftingItem.setProcessing(true);
            return new Result(true, "artisan: " + artisanName + " start to produce");
        }
        return new Result(false, "ingredients and crafting does not match");
    }

    public Result artisanGet(String artisanName, CraftingItem craftingItem, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player player = playerrr;
        Inventory inventory = player.getInventory();
        ArrayList<ArtisanGoods> artisanGoods = new ArrayList<>();
        artisanGoods = player.getArtisansInProduce();
        if (craftingItem.isReady()) {
            inventory.addItem(craftingItem.getInsideArtisan(), inventory.getItemQuantity(craftingItem.getInsideArtisan()) + 1);
            player.getArtisansInProduce().remove(craftingItem.getInsideArtisan());
            return new Result(true, "artisan: " + artisanName + " added to artisans");
        }
        return new Result(false, "artisan not found");
    }

    public static Result sell(Item item, int count, Player playerrr) {
        Player currentPlayer = playerrr;

        try {
            if ((App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY()).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() - 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY()).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() - 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() - 1).getInside() instanceof Satl)) {

            } else {
                return new Result(false, "Please stand near a Satl" + currentPlayer.getX() + " " + currentPlayer.getY());
            }
        } catch (Exception e) {
            return new Result(false, "Don't stand near the borders");
        }

        int quantity = count;

        boolean found = false;
        if (quantity <= playerrr.getInventory().getItemQuantity(item)) {
            playerrr.getMyFarm().getSatl().addItem(item, quantity);
            playerrr.getInventory().removeItem(item, quantity);
            return new Result(true, "Sold" + quantity + " of " + item);
        } else {
            return new Result(false, "Not enough items in your inventory");
        }
    }

    public Result friendships(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        Player currentPlayer = playerrr;
        Game currentGame = App.getCurrentGame();
        for (Friendship friendship : currentGame.getFriendships()) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if ((!player.equals(currentPlayer)) && friendship.isBetween(currentPlayer, player)) {
                    result.append("Friendship between ")
                        .append(currentPlayer.getOwner().getUsername()) // فرض: Player کلاس متد getUsername داره
                        .append(" and ")
                        .append(player.getOwner().getUsername())
                        .append("\nLevel: ").append(friendship.getLevel())
                        .append("\nXP: ").append(friendship.getXp())
                        .append("\nLast Interaction: Day ").append(friendship.getLastInteractionDate().getDay())
                        .append(", Hour ").append(friendship.getLastInteractionDate().getHour());
                }
            }

        }
        return new Result(true, result.toString());
    }

    public static Result talk(String username, String message, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = playerrr;
        Game currentGame = App.getCurrentGame();
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(username);
        int dx = (int) Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = (int) Math.abs(currentPlayer.getY() - targetPlayer.getY());

        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);

        for (Friendship friendships : currentGame.getFriendships()) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if ((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)) {
                    friendship = friendships;
                }
            }

        }
        assert friendship != null;
        friendship.interact(20);


        String addressedMessage = currentPlayer.getOwner().getUsername() + " : " + message + "(" +
            App.getCurrentGame().getCurrentDateTime().getDay() + " : " + App.getCurrentGame().getCurrentDateTime().getHour()
            + ")";
        friendship.getTalkHistory().add(addressedMessage);
        return new Result(true, "message sent successfully");
    }

    public static Result talkHistory(String username, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = playerrr;
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(username);
        Game currentGame = App.getCurrentGame();
        StringBuilder result = new StringBuilder();
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);
        for (Friendship friendships : currentGame.getFriendships()) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if ((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)) {
                    friendship = friendships;
                }
            }
        }
        assert friendship != null;
        for (String message : friendship.getTalkHistory()) {
            result.append(message).append("\n");
        }
        friendship.getTalkHistory().clear();

        return new Result(true, result.toString());
    }

    public static Result gift(String userName, int amount, String item, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }

        item = item.replaceAll(" ", "");
        Item gift = null;
        Gift newGift = null;
        Player currentPlayer = playerrr;
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Pair<Integer, Integer>> itemInventory = inventory.getItems();
        Game currentGame = App.getCurrentGame();

        Player targetPlayer = currentGame.getPlayerByUsername(userName);
        if (targetPlayer == null) {
            return new Result(false, "Player not found");
        }

        int dx = (int) Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = (int) Math.abs(currentPlayer.getY() - targetPlayer.getY());
        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }

        Friendship friendship = getFriendship(targetPlayer, currentPlayer);
        for (Friendship friendships : currentGame.getFriendships()) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if ((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)) {
                    friendship = friendships;
                }
            }
        }
        if (friendship == null || friendship.getLevel() == 0) {
            return new Result(false, "Your friendship level is not enough to gift");
        }

        boolean foundItem = false;

        for (Map.Entry<Item, Pair<Integer, Integer>> entry : itemInventory.entrySet()) {
            Item currentItem = entry.getKey();
            Pair<Integer, Integer> itemData = entry.getValue(); // (quantity, slot)

            if (currentItem.getCorrectName().equalsIgnoreCase(item)) {
                int currentQuantity = itemData.first;

                if (currentQuantity < amount) {
                    return new Result(false, "You don't have enough items in your inventory");
                }

                if (targetPlayer.getInventory().getMaxQuantity() - targetPlayer.getInventory().getItems().size() <= 0) {
                    return new Result(false, "Target player does not have enough space in inventory");
                }


                if (currentQuantity - amount == 0) {
                    inventory.removeItem(currentItem, amount);
                } else {
                    inventory.getItems().put(currentItem, new Pair<>(currentQuantity - amount, itemData.second));
                }

                foundItem = true;
                gift = currentItem;
                newGift = new Gift(currentItem, amount, currentPlayer, targetPlayer);
                break;
            }
        }

        if (!foundItem) {
            return new Result(false, "No such item in your inventory");
        }


        Inventory targetInventory = targetPlayer.getInventory();
        if (targetInventory.containsItem(gift)) {
            Pair<Integer, Integer> existing = targetInventory.existing(gift);
            targetInventory.getItems().put(gift, new Pair<>(existing.first + amount, existing.second));
        } else {
            targetInventory.addItem(gift, amount);
        }

        currentGame.getGifts().add(newGift);

        return new Result(true, "Gift sent successfully");
    }


    public static Friendship getFriendship(Player targetPlayer, Player currentPlayer) {
        Game currentGame = App.getCurrentGame();
        for (Friendship friendships : currentGame.getFriendships()) {
            if (friendships.isBetween(currentPlayer, targetPlayer)) {
                return friendships;
            }
        }
        return null;
    }

    public Result giftList(Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        result.append("Gift List: \n");
        Player currentPlayer = playerrr;
        for (Gift gift : App.getCurrentGame().getGifts()) {
            if (gift.getReceiver().equals(currentPlayer)) {
                result.append("Gift id: ").append(gift.getId()).append("\n");
                result.append("Gift Sender: ").append(gift.getSender().getOwner().getUsername()).append("\n");
                result.append("Gift name: ").append(gift.getName()).append("\n");
                result.append("Gift amount: ").append(gift.getAmount()).append("\n");
                result.append("Gift is rated: ").append(gift.isRated()).append("\n");
            }
        }
        return new Result(true, result.toString());
    }

    public Result giftRate(int giftNumber, int rate, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = playerrr;
        for (Gift gift : App.getCurrentGame().getGifts()) {
            if (gift.getId() == giftNumber) {
                if (gift.getReceiver().equals(currentPlayer)) {
                    if (!gift.isRated()) {
                        if (rate > 0 && rate < 6) {
                            gift.setRated(true);
                            gift.setRate(rate);
                            Friendship friendship = null;
                            Player targetPlayer = gift.getSender();
                            friendship = getFriendship(targetPlayer, currentPlayer);
//                            for (Friendship friendships :App.getCurrentGame().getFriendships()){
//                                for(Player player : App.getCurrentGame().getPlayers()){
//                                    if(player.equals(currentPlayer) && friendships.isBetween(currentPlayer, player)){
//                                        friendship = friendships;
//                                    }
//                                }
//                            }
                            assert friendship != null;
                            friendship.interact(((rate - 3) * 30) + 15);
                            return new Result(true, "Gift rated successfully");
                        }
                    }
                }
            }
        }
        return new Result(false, "Gift can't rated");
    }

    public Result giftHistory(String name, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        result.append("Gift History of ").append(name).append(": \n");
        Player currentPlayer = playerrr;
        Player targetPlayer = null;
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (player.getOwner().getUsername().equals(name)) {
                targetPlayer = player;
            }
        }
        if (targetPlayer == null) {
            return new Result(false, "Player not found");
        }
        result.append("As sender:\n");
        for (Gift gift : App.getCurrentGame().getGifts()) {
            if (gift.getSender().equals(currentPlayer)) {
                result.append("\tGift id      : ").append(gift.getId()).append("\n");
                result.append("\tGift name    : ").append(gift.getName()).append("\n");
                result.append("\tGift amount  : ").append(gift.getAmount()).append("\n");
                result.append("\tGift is rated: ").append(gift.isRated()).append("\n");
                result.append("\tGift receiver: ").append(gift.getReceiver().getOwner().getUsername()).append("\n----\n");
            }
        }
        result.append("As receiver:\n");
        for (Gift gift : App.getCurrentGame().getGifts()) {
            if (gift.getReceiver().equals(currentPlayer)) {
                result.append("\tGift id      : ").append(gift.getId()).append("\n");
                result.append("\tGift name    : ").append(gift.getName()).append("\n");
                result.append("\tGift amount  : ").append(gift.getAmount()).append("\n");
                result.append("\tGift is rated: ").append(gift.isRated()).append("\n");
                result.append("\tGift sender  : ").append(gift.getSender().getOwner().getUsername()).append("\n----\n");
            }
        }
        return new Result(true, result.toString());
    }

    public Result hug(String userName, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = playerrr;
        if (App.getCurrentGame().getPlayerByUsername(userName) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(userName);
        int dx = (int) Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = (int) Math.abs(currentPlayer.getY() - targetPlayer.getY());
        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to hug");
        }
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);
        assert friendship != null;
        if (friendship.getLevel() < 2) {
            return new Result(false, "your friendship should be at least 2");
        }
        friendship.interact(60);
        return new Result(true, "You hug " + userName + " successfully");
    }

    public static Result flower(String username, Player playerrr) {
        if (isFainted(playerrr)) {
            return new Result(false, "You are fainted!");
        }

        Player currentPlayer = playerrr;
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Pair<Integer, Integer>> itemInventory = inventory.getItems();
        Game currentGame = App.getCurrentGame();

        Player targetPlayer = currentGame.getPlayerByUsername(username);
        if (targetPlayer == null) {
            return new Result(false, "Player not found");
        }

        int dx = (int) Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = (int) Math.abs(currentPlayer.getY() - targetPlayer.getY());
        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }

        Friendship friendship = getFriendship(targetPlayer, currentPlayer);
        if (friendship == null || friendship.getLevel() != 2) {
            return new Result(false, "You can send flower only in friendship level 2");
        }


        Item flowerItem = null;
        for (Item item : itemInventory.keySet()) {
            if (item.getCorrectName().equalsIgnoreCase("flower")) {
                flowerItem = item;
                break;
            }
        }

        if (flowerItem == null) {
            return new Result(false, "You don't have a flower in your inventory");
        }

        Pair<Integer, Integer> flowerData = itemInventory.get(flowerItem);
        int currentQty = flowerData.first;

        if (currentQty < 1) {
            return new Result(false, "You don't have enough flowers");
        }

        if (targetPlayer.getInventory().getMaxQuantity() - targetPlayer.getInventory().getItems().size() <= 0) {
            return new Result(false, "Target player does not have enough space in inventory");
        }


        if (currentQty == 1) {
            inventory.removeItem(flowerItem, 1);
        } else {
            inventory.getItems().put(flowerItem, new Pair<>(currentQty - 1, flowerData.second));
        }


        Flower flowerForTarget = new Flower();

        Inventory targetInventory = targetPlayer.getInventory();
        if (targetInventory.containsItem(flowerForTarget)) {
            Pair<Integer, Integer> existing = targetInventory.existing(flowerForTarget);
            targetInventory.getItems().put(flowerForTarget, new Pair<>(existing.first + 1, existing.second));
        } else {
            targetInventory.addItem(flowerForTarget, 1);
        }


        if (friendship.getLevel() < 3) {
            friendship.setLevel(3);
        }

        return new Result(true, "Flower sent successfully");
    }


    public static Result askMarriage(String userName, String ringName, Player playerrr) {
        Player currentPlayer = playerrr;
        Player destinationPlayer = App.getCurrentGame().getPlayerByUsername(userName);

        if (destinationPlayer == null) {
            return new Result(false, "Player not found");
        }
        int dx = (int) Math.abs(currentPlayer.getX() - destinationPlayer.getX());
        int dy = (int) Math.abs(currentPlayer.getY() - destinationPlayer.getY());

        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "Be more near than her/him");
        }

        if (Objects.equals(destinationPlayer.getOwner().getGender(), currentPlayer.getOwner().getGender())) {

            return new Result(false, "Your genders should be different!");
        }
        if (currentPlayer.getOwner().getGender().equalsIgnoreCase("female") && currentPlayer.getOwner().getGender().equalsIgnoreCase("male")) {
            return new Result(false, "Wait! You are female and he is male!!!!");
        }

        Friendship friendship = getFriendship(destinationPlayer, currentPlayer);
        Game currentGame = App.getCurrentGame();
        for (Friendship friendships : currentGame.getFriendships()) {
            for (Player player : App.getCurrentGame().getPlayers()) {
                if ((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)) {
                    friendship = friendships;
                }
            }
        }
        if (friendship == null || friendship.getLevel() < 3) {
            return new Result(false, "Your friendship level is not enough(3) to ask Marriage");
        }

        Item ring = null;
        for (Item item : currentPlayer.getInventory().getItems().keySet()) {
            if (item.getCorrectName().equalsIgnoreCase(ringName.replace(" ", ""))) {
                ring = item;
                break;
            }
        }

        if (ring == null) {
            return new Result(false, "You don't have such a ring!");
        }


        MarriageProposal proposal = new MarriageProposal(currentPlayer, destinationPlayer, ring);
        App.getCurrentGame().addMarriageProposal(proposal);
        return new Result(true, "Marriage proposal sent to " + userName + " with a " + ring.getCorrectName() + ".");
    }

    public static Result response(String acceptOrReject, String userName, Player playerrr) {
        Player currentPlayer = playerrr;
        List<MarriageProposal> proposals = App.getCurrentGame().getMarriageProposals();

        MarriageProposal targetProposal = null;
        for (MarriageProposal proposal : proposals) {

            if (proposal.getReceiver().equals(currentPlayer) &&
                proposal.getProposer().getOwner().getUsername().equals(userName)) {
                targetProposal = proposal;
                break;
            }
        }

        if (targetProposal == null) {
            return new Result(false, "No marriage proposal found from user: " + userName);
        }

        if (acceptOrReject.equalsIgnoreCase("-accept")) {

            playerrr.getInventory().addItem(targetProposal.getRing(), 1);
            targetProposal.getProposer().getInventory().removeItem(targetProposal.getRing(), 1);
            proposals.remove(targetProposal);
            Friendship friendship = getFriendship(targetProposal.getReceiver(), targetProposal.getProposer());
            if (friendship != null) {
                friendship.setLevel(4);
            }
            targetProposal.getReceiver().setPartner(targetProposal.getProposer());
            targetProposal.getProposer().setPartner(targetProposal.getReceiver());
            return new Result(true, "You have accepted the marriage proposal from " + userName);
        } else if (acceptOrReject.equalsIgnoreCase("-reject")) {
            Friendship friendship = getFriendship(targetProposal.getReceiver(), targetProposal.getProposer());
            if (friendship != null) {
                friendship.setLevel(0);
            }
            targetProposal.getProposer().setMaxEnergy((int) (0.75 * targetProposal.getProposer().getMaxEnergy()));
            proposals.remove(targetProposal);
            targetProposal.getProposer().setDaysAfterJavabeRad(0);
            return new Result(true, "You have rejected the marriage proposal from " + userName);
        } else {
            return new Result(false, "Invalid response. Use 'accept' or 'reject'.");
        }
    }

    public void showMyMarriageProposals(Player playerrr) {
        Player currentPlayer = playerrr;
        List<MarriageProposal> proposals = App.getCurrentGame().getMarriageProposals();
        boolean hasProposal = false;
        for (MarriageProposal proposal : proposals) {
            if (proposal.getReceiver().equals(currentPlayer)) {
                System.out.println("Proposal from: " + proposal.getProposer().getUsername());
                System.out.println("Ring: " + proposal.getRing().getCorrectName());
                System.out.println("----------------------");
                hasProposal = true;
            }
        }
        if (!hasProposal) {
            System.out.println("You have no marriage proposals.");
        }
    }

    public Animal findAnimalByName(String animalName, Player playerrr) {
        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public void startTrade() {

    }

    private boolean validNPCPlace(NPCEnums npcEnums, Player playerrr) {
        Player currentPlayer = playerrr;

        return (App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() + 1).getInside() instanceof NPC &&
            ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX()).get((int) currentPlayer.getY() - 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY()).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY()).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY()).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY()).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() + 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() + 1).get((int) currentPlayer.getY() - 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() + 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get((int) currentPlayer.getX() - 1).get((int) currentPlayer.getY() - 1).getInside()).getName() == npcEnums);


    }

    public static Result meetNPC(NPC npc, Player playerrr) {

        Player currentPlayer = playerrr;

        int friendshipLevel = -1;
        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLevel = friendship.getFriendshipLevel();
            }
        }
        switch (npc.getName()) {
            case SEBASTIAN:
                switch (Math.min(friendshipLevel, 799) / 200) {
                    case 0:
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Oh, hey. I don’t think we’ve met. I’m Sebastian. The weather today is "
                            + App.getCurrentGame().getCurrentWeather() + " in " + App.getCurrentGame().getCurrentSeason() + ". What brings you here?");
                    case 1:
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hi, I'm Sebastian. Ugh, another " + App.getCurrentGame().getCurrentWeather() + " day... Season: "
                            + App.getCurrentGame().getCurrentSeason() + " Day: " + App.getCurrentGame().getCurrentDateTime().getDay() + " Hour: "
                            + App.getCurrentGame().getCurrentDateTime().getHour());
                    case 2:
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hey " + currentPlayer.getOwner().getUsername() + ". It's " + App.getCurrentGame().getCurrentSeason()
                            + ", so of course the weather's " + App.getCurrentGame().getCurrentWeather() + ". Whatever.");
                    case 3:
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Oh, hey " + currentPlayer.getOwner().getUsername() + ". Honestly? I don’t even mind this "
                            + App.getCurrentGame().getCurrentWeather() + " weather when you're around.");
                }
                break;

            case ABIGAIL:
                switch (Math.min(friendshipLevel, 799) / 200) {
                    case 0:
                        if (!App.getCurrentGame().getNPCABIGAIL().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCABIGAIL().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Whoa, a stranger! I'm Abigail. It's " + App.getCurrentGame().getCurrentSeason()
                            + ", and the weather's " + App.getCurrentGame().getCurrentWeather() + ". Wanna explore the mines later?");
                    case 1:
                        if (!App.getCurrentGame().getNPCABIGAIL().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCABIGAIL().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hi " + currentPlayer.getOwner().getUsername() + "! A " + App.getCurrentGame().getCurrentWeather()
                            + " day in " + App.getCurrentGame().getCurrentSeason() + "? Perfect for an adventure!");
                    case 2:
                        if (!App.getCurrentGame().getNPCABIGAIL().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCABIGAIL().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, currentPlayer.getOwner().getUsername() + "!! Let’s do something crazy today! The "
                            + App.getCurrentGame().getCurrentWeather() + " weather won’t stop us!");
                    case 3:
                        if (!App.getCurrentGame().getNPCABIGAIL().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCABIGAIL().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "You know, " + currentPlayer.getOwner().getUsername() + ", you're one of my favorite people. "
                            + App.getCurrentGame().getCurrentWeather() + " days feel way better when you're around.");
                }
                break;

            case HARVEY:
                switch (Math.min(friendshipLevel, 799) / 200) {
                    case 0:
                        if (!App.getCurrentGame().getNPCHARVEY().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCHARVEY().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Ahem, hello. I'm Dr. Harvey. It's " + App.getCurrentGame().getCurrentSeason()
                            + ", and the weather is " + App.getCurrentGame().getCurrentWeather() + ". Please take care of yourself.");
                    case 1:
                        if (!App.getCurrentGame().getNPCHARVEY().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCHARVEY().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Oh, " + currentPlayer.getOwner().getUsername() + "! The " + App.getCurrentGame().getCurrentWeather()
                            + " weather might affect your health. Be careful!");
                    case 2:
                        if (!App.getCurrentGame().getNPCHARVEY().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCHARVEY().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, currentPlayer.getOwner().getUsername() + ", I always feel better seeing you, even on this "
                            + App.getCurrentGame().getCurrentWeather() + " day.");
                    case 3:
                        if (!App.getCurrentGame().getNPCHARVEY().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCHARVEY().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Ah, " + currentPlayer.getOwner().getUsername() + ". Even the worst " + App.getCurrentGame().getCurrentWeather()
                            + " can't ruin my day when I see you.");
                }
                break;

            case LEAH:
                switch (Math.min(friendshipLevel, 799) / 200) {
                    case 0:
                        if (!App.getCurrentGame().getNPCLEAH().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCLEAH().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hi there. I’m Leah. The " + App.getCurrentGame().getCurrentSeason()
                            + " air is so inspiring today. The " + App.getCurrentGame().getCurrentWeather() + " just adds to the mood.");
                    case 1:
                        if (!App.getCurrentGame().getNPCLEAH().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCLEAH().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Oh, " + currentPlayer.getOwner().getUsername() + "! The way the "
                            + App.getCurrentGame().getCurrentWeather() + " looks in " + App.getCurrentGame().getCurrentSeason()
                            + " is beautiful, don’t you think?");
                    case 2:
                        if (!App.getCurrentGame().getNPCLEAH().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCLEAH().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, currentPlayer.getOwner().getUsername() + ", you’re like sunshine even on this "
                            + App.getCurrentGame().getCurrentWeather() + " day.");
                    case 3:
                        if (!App.getCurrentGame().getNPCLEAH().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCLEAH().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "You always inspire me, " + currentPlayer.getOwner().getUsername() + ". Even when the weather is "
                            + App.getCurrentGame().getCurrentWeather() + ", I feel like creating something new.");
                }
                break;

            case ROBIN:
                switch (Math.min(friendshipLevel, 799) / 200) {
                    case 0:
                        if (!App.getCurrentGame().getNPCROBIN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCROBIN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hi, dear! I'm Robin. It's " + App.getCurrentGame().getCurrentSeason()
                            + ", and the weather's " + App.getCurrentGame().getCurrentWeather() + "—great day for carpentry!");
                    case 1:
                        if (!App.getCurrentGame().getNPCROBIN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCROBIN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "Hey, " + currentPlayer.getOwner().getUsername() + "! How’s the "
                            + App.getCurrentGame().getCurrentWeather() + " treating you this " + App.getCurrentGame().getCurrentSeason() + "?");
                    case 2:
                        if (!App.getCurrentGame().getNPCROBIN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCROBIN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, currentPlayer.getOwner().getUsername() + ", sweetie! Even with this "
                            + App.getCurrentGame().getCurrentWeather() + ", you always brighten my day!");
                    case 3:
                        if (!App.getCurrentGame().getNPCROBIN().isTalkedWithToday()) {
                            App.getCurrentGame().getNPCROBIN().setTalkedWithToday(true);
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 20);
                                    break;
                                }
                            }
                        }
                        return new Result(true, "You make everything better, " + currentPlayer.getOwner().getUsername() + ", even a "
                            + App.getCurrentGame().getCurrentWeather() + " day like this.");
                }
                break;

            default:
                return new Result(false, "Invalid NPC name.");
        }
        return null;
    }

    public static Result giftNPC(NPC npcName, Item item, int count, Player playerrr) {
        Player currentPlayer = playerrr;
        switch (npcName.getName()) {
            case SEBASTIAN:
//                //pashm-->??????
//                //pumkinpie-->FoodCooking
//                //pizza--?FoodCooking
                if (!(item instanceof Tool)) {
                    if (item instanceof FoodCooking foodCooking && (foodCooking.getNamee() == FoodCookingEnums.PumpkinPie || foodCooking.getNamee() == FoodCookingEnums.Pizza)) {
                        currentPlayer.getInventory().removeItem(item, count);
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
                        }
                        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                return new Result(true, "Gifted successfully");
                            }
                        }
                    } else {
                        if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC got " + item.getCorrectName());
                        } else {
                            return new Result(false, "NPC doesn't like " + item.getCorrectName());
                        }
                    }
                } else {
                    return new Result(false, "You can't gift tools");
                }
                break;

            case ABIGAIL:
//                //Stone-->stone
//                //kani ahan-->mineral
//                //AllCrops-->Coffee
                if (!(item instanceof Tool)) {
                    if (item instanceof StoneItem || (item instanceof Mineral mineral && mineral.getType() == ForagingMineralsEnums.IronOre) ||
                        (item instanceof AllCrop allCrop && allCrop.getType() == AllCropsEnums.CoffeeBean)) {
                        currentPlayer.getInventory().removeItem(item, count);
                        if (!App.getCurrentGame().getNPCABIGAIL().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCABIGAIL().setGiftedToday(true);
                        }
                        for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                return new Result(true, "Gifted successfully");
                            }
                        }
                    } else {
                        if (!App.getCurrentGame().getNPCABIGAIL().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCABIGAIL().setGiftedToday(true);
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC got " + item.getCorrectName());
                        } else {
                            return new Result(false, "NPC doesn't like " + item.getCorrectName());
                        }
                    }
                } else {
                    return new Result(false, "You can't gift tools");
                }
                break;

            case HARVEY:
//                //AllCrops-->Coffee
//                //torshi-->???????
//                //sharab-->wine
                if (!(item instanceof Tool)) {
                    if ((item instanceof ArtisanGoods artisanGoods && artisanGoods.getArtisanGoods() == ArtisanGoodsEnums.Wine) ||
                        (item instanceof AllCrop allCrop && allCrop.getType() == AllCropsEnums.CoffeeBean)) {
                        currentPlayer.getInventory().removeItem(item, count);
                        if (!App.getCurrentGame().getNPCHARVEY().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCHARVEY().setGiftedToday(true);
                        }
                        for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                return new Result(true, "Gifted successfully");
                            }
                        }
                    } else {
                        if (!App.getCurrentGame().getNPCHARVEY().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCHARVEY().setGiftedToday(true);
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC got " + item.getCorrectName());
                        } else {
                            return new Result(false, "NPC doesn't like " + item.getCorrectName());
                        }
                    }
                } else {
                    return new Result(false, "You can't gift tools");
                }
                break;

            case LEAH:
//                //salad-->Foodcooking
//                //grape -->ForagingCrop,AllCrop
//                //sharab-->wine
//                break;
                if (!(item instanceof Tool)) {
                    if ((item instanceof FoodCooking foodCooking && foodCooking.getNamee() == FoodCookingEnums.Salad) ||
                        (item instanceof ForagingCrop foragingCrop && foragingCrop.getType() == ForagingCropsEnums.Grape) ||
                        (item instanceof AllCrop allCrop && allCrop.getType() == AllCropsEnums.Grape) ||
                        (item instanceof ArtisanGoods artisanGoods && artisanGoods.getArtisanGoods() == ArtisanGoodsEnums.Wine)) {
                        currentPlayer.getInventory().removeItem(item, count);
                        if (!App.getCurrentGame().getNPCLEAH().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCLEAH().setGiftedToday(true);
                        }
                        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                return new Result(true, "Gifted successfully");
                            }
                        }
                    } else {
                        if (!App.getCurrentGame().getNPCLEAH().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCLEAH().setGiftedToday(true);
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC got " + item.getCorrectName());
                        } else {
                            return new Result(false, "NPC doesn't like " + item.getCorrectName());
                        }
                    }
                } else {
                    return new Result(false, "You can't gift tools");
                }
                break;
            case ROBIN:
                //spaghetti-->FoodCooking
                //choob-->???????
                //shemsh ahan-->Artisan goods
                if (!(item instanceof Tool)) {
                    if ((item instanceof FoodCooking foodCooking && foodCooking.getNamee() == FoodCookingEnums.Spaghetti) ||
                        (item instanceof ArtisanGoods artisanGoods && artisanGoods.getArtisanGoods() == ArtisanGoodsEnums.IronBar)) {
                        currentPlayer.getInventory().removeItem(item, count);
                        if (!App.getCurrentGame().getNPCROBIN().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
                        }
                        for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                return new Result(true, "Gifted successfully");
                            }
                        }
                    } else {
                        if (!App.getCurrentGame().getNPCROBIN().isGiftedToday()) {
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
                                    break;
                                }
                            }
                            currentPlayer.getInventory().removeItem(item, count);
                            App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC got " + item.getCorrectName());
                        } else {
                            currentPlayer.getInventory().removeItem(item, count);
                            return new Result(false, "NPC doesn't like " + item.getCorrectName());
                        }
                    }
                } else {
                    return new Result(false, "You can't gift tools");
                }
                break;
            default:
                //System.out.println(npcName);
                return new Result(false, "Invalid NPC name.");
        }
        return null;
    }

    public static Result friendshipList(NPC npc, Player playerrr) {
        StringBuilder result = new StringBuilder();
        Player currentPlayer = playerrr;
        for (Friendshipali friendship : npc.getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append(npc.getName());
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        return new Result(true, result.toString());
    }

    public static Result questsList(NPC npc, Player playerrr) {
        StringBuilder result = new StringBuilder();
        Player currentPlayer = playerrr;
        int friendshipLVL = -1;

        for (Friendshipali friendship : npc.getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append(npc.getName() + "\n");
        int counter = 1;
        for (Item item : npc.getQuests().keySet()) {
            NPCItem npcItem = npc.getQuests().get(item);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                result.append(counter++).append("- ").append("Your quest is to get ").append(npcItem.getQuantity()).append(" of ").append(item.getCorrectName()).append("\n");
            }
        }

        return new Result(true, result.toString());
    }

    public static Result questsFinish(NPC npc, int index, Player playerrr) {
        index--;
        Player currentPlayer = playerrr;
        if (npc.getName() == NPCEnums.SEBASTIAN) {
            int friendshipLVL = -1;
            for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                if (friendship.getPlayer() == currentPlayer) {
                    friendshipLVL = friendship.getFriendshipLevel() / 200;
                    break;
                }
            }
            boolean flag = false;
            for (Item object : App.getCurrentGame().getNPCSEBASTIAN().getQuests().keySet()) {
                NPCItem npcItem = App.getCurrentGame().getNPCSEBASTIAN().getQuests().get(object);
                if (friendshipLVL >= npcItem.getRequiredLevel() && npcItem.getRequiredLevel() == index) {
                    flag = true;
                    boolean found = false;
                    for (Item item : currentPlayer.getInventory().getItems().keySet()) {
                        if (object.getCorrectName().toLowerCase().replace(" ", "").equals(item.getCorrectName())) {
                            if (currentPlayer.getInventory().getItemQuantity(item) < npcItem.getQuantity()) {
                                return new Result(false, "You don't have enough resources");
                            } else {
                                found = true;
                                currentPlayer.getInventory().removeItem(item, npcItem.getQuantity());
                                switch (object.getCorrectName()) {
                                    case "ironore":
                                        Mineral diamond = new Mineral();
                                        diamond.setType(ForagingMineralsEnums.Diamond);
                                        diamond.setPrice(750);
                                        if (friendshipLVL == 2) {
                                            currentPlayer.getInventory().addItem(diamond, 4);
                                        } else {
                                            currentPlayer.getInventory().addItem(diamond, 2);
                                        }
                                        App.getCurrentGame().getNPCSEBASTIAN().getQuests().remove(object);
                                        return new Result(true, "SEBASTIAN: Thanks for giving me " + object.getCorrectName());
                                    case "pumpkinpie":
                                        if (friendshipLVL == 2) {
                                            currentPlayer.setGold(currentPlayer.getGold() + 10000);
                                        } else {
                                            currentPlayer.setGold(currentPlayer.getGold() + 5000);
                                        }
                                        App.getCurrentGame().getNPCSEBASTIAN().getQuests().remove(object);
                                        return new Result(true, "SEBASTIAN: Thanks for giving me " + object.getCorrectName());
                                    case "stone":
                                        Mineral quartz = new Mineral();
                                        quartz.setType(ForagingMineralsEnums.Quartz);
                                        quartz.setPrice(25);
                                        if (friendshipLVL == 2) {
                                            currentPlayer.getInventory().addItem(quartz, 100);
                                        } else {
                                            currentPlayer.getInventory().addItem(quartz, 50);
                                        }
                                        App.getCurrentGame().getNPCSEBASTIAN().getQuests().remove(object);
                                        return new Result(true, "SEBASTIAN: Thanks for giving me " + object.getCorrectName());
                                }
                            }
                        }
                    }
                    if (!found) {
                        return new Result(false, "You don't have enough resources");
                    }
                }
            }
            if (!flag) {
                return new Result(false, "You can't do the selected quest right now");
            }
        } else {
            if (npc.getName() == NPCEnums.ABIGAIL) {
                int friendshipLVL = -1;
                for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                    if (friendship.getPlayer() == currentPlayer) {
                        friendshipLVL = friendship.getFriendshipLevel() / 200;
                        break;
                    }
                }
                boolean flag = false;
                for (Item object : App.getCurrentGame().getNPCABIGAIL().getQuests().keySet()) {
                    NPCItem npcItem = App.getCurrentGame().getNPCABIGAIL().getQuests().get(object);
                    if (friendshipLVL >= npcItem.getRequiredLevel() && npcItem.getRequiredLevel() == index) {
                        flag = true;

                        boolean found = false;

                        for (Item item : currentPlayer.getInventory().getItems().keySet()) {

                            if (item.getCorrectName().toLowerCase().replace(" ", "").equals(object.getCorrectName())) {
                                if (currentPlayer.getInventory().getItemQuantity(item) < npcItem.getQuantity()) {
                                    return new Result(false, "You don't have enough resources");
                                } else {
                                    found = true;
                                    currentPlayer.getInventory().removeItem(item, npcItem.getQuantity());
                                    switch (object.getCorrectName()) {
                                        case "goldbar":
                                            for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                                                if (friendship.getPlayer() == currentPlayer) {
                                                    if (friendshipLVL == 2) {
                                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 400);
                                                    } else {
                                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                                    }
                                                    break;
                                                }
                                            }
                                            App.getCurrentGame().getNPCABIGAIL().getQuests().remove(object);
                                            return new Result(true, "ABIGAIL: Thanks for giving me " + object.getCorrectName());
                                        case "pumpkin":
                                            if (friendshipLVL == 2) {
                                                currentPlayer.setGold(currentPlayer.getGold() + 1000);
                                            } else {
                                                currentPlayer.setGold(currentPlayer.getGold() + 500);
                                            }
                                            App.getCurrentGame().getNPCABIGAIL().getQuests().remove(object);
                                            return new Result(true, "ABIGAIL: Thanks for giving me " + object.getCorrectName());
                                        case "wheat":
                                            CraftingItem abpash = new CraftingItem((CraftingRecipesEnums.IridiumSprinkler).toString());
                                            currentPlayer.getInventory().addItem(abpash, 1);
                                            App.getCurrentGame().getNPCABIGAIL().getQuests().remove(object);
                                            return new Result(true, "ABIGAIL: Thanks for giving me " + object.getCorrectName());
                                    }
                                }
                            }
                        }
                        if (!found) {
                            return new Result(false, "You don't have enough resources");
                        }
                    }
                }
                if (!flag) {
                    return new Result(false, "You can't do the selected quest right now");
                }
            } else {
                if (npc.getName() == NPCEnums.HARVEY) {
                    int friendshipLVL = -1;
                    for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                        if (friendship.getPlayer() == currentPlayer) {
                            friendshipLVL = friendship.getFriendshipLevel() / 200;
                            break;
                        }
                    }
                    boolean flag = false;
                    for (Item object : App.getCurrentGame().getNPCHARVEY().getQuests().keySet()) {
                        NPCItem npcItem = App.getCurrentGame().getNPCHARVEY().getQuests().get(object);
                        if (friendshipLVL >= npcItem.getRequiredLevel() && npcItem.getRequiredLevel() == index) {
                            flag = true;

                            boolean found = false;

                            for (Item item : currentPlayer.getInventory().getItems().keySet()) {

                                if ((object.getCorrectName().equalsIgnoreCase("allcrop") && item instanceof AllCrop) || item.getCorrectName().toLowerCase().replace(" ", "").equals(object.getCorrectName())) {
                                    if (currentPlayer.getInventory().getItemQuantity(item) < npcItem.getQuantity()) {
                                        return new Result(false, "You don't have enough resources");
                                    } else {
                                        found = true;
                                        currentPlayer.getInventory().removeItem(item, npcItem.getQuantity());
                                        switch (object.getCorrectName()) {
                                            case "allcrop":
                                                App.getCurrentGame().getNPCHARVEY().getQuests().remove(object);
                                                if (friendshipLVL == 2) {
                                                    currentPlayer.setGold(currentPlayer.getGold() + 1500);
                                                } else {
                                                    currentPlayer.setGold(currentPlayer.getGold() + 750);
                                                    System.out.println("added 750 gold");
                                                }
                                                return new Result(true, "HARVEY: Thanks for giving me " + object.getCorrectName());
                                            case "salmon":
                                                for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                                                    if (friendship.getPlayer() == currentPlayer) {
                                                        if (friendshipLVL == 2) {
                                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 400);
                                                        } else {
                                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
                                                        }
                                                        break;
                                                    }
                                                }
                                                App.getCurrentGame().getNPCHARVEY().getQuests().remove(object);
                                                return new Result(true, "HARVEY: Thanks for giving me " + object.getCorrectName());
                                            case "wine":
                                                FoodCooking foodCooking = new FoodCooking();
                                                foodCooking.setName(FoodCookingEnums.Salad);
                                                foodCooking.setEnergy(113);
                                                foodCooking.setSellPrice(110);
                                                if (friendshipLVL == 2) {
                                                    currentPlayer.getInventory().addItem(foodCooking, 10);
                                                } else {
                                                    currentPlayer.getInventory().addItem(foodCooking, 5);
                                                }
                                                App.getCurrentGame().getNPCHARVEY().getQuests().remove(object);
                                                return new Result(true, "HARVEY: Thanks for giving me " + object.getCorrectName());
                                        }
                                    }
                                }
                            }
                            if (!found) {
                                System.out.println(object.getCorrectName() + " " + npcItem.getQuantity());
                                return new Result(false, "You don't have enough resources");
                            }
                        }
                    }
                    if (!flag) {
                        return new Result(false, "You can't do the selected quest right now");
                    }
                } else {
                    if (npc.getName() == NPCEnums.LEAH) {
                        int friendshipLVL = -1;
                        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                            if (friendship.getPlayer() == currentPlayer) {
                                friendshipLVL = friendship.getFriendshipLevel() / 200;
                                break;
                            }
                        }
                        boolean flag = false;
                        for (Item object : App.getCurrentGame().getNPCLEAH().getQuests().keySet()) {
                            NPCItem npcItem = App.getCurrentGame().getNPCLEAH().getQuests().get(object);
                            if (friendshipLVL >= npcItem.getRequiredLevel() && npcItem.getRequiredLevel() == index) {
                                flag = true;

                                boolean found = false;

                                for (Item item : currentPlayer.getInventory().getItems().keySet()) {

                                    if (object.getCorrectName().equalsIgnoreCase("wood") || item.getCorrectName().toLowerCase().replace(" ", "").equals(object.getCorrectName())) {
                                        if ((currentPlayer.getInventory().getItemQuantity(item) < npcItem.getQuantity()) && !(object.getCorrectName().equalsIgnoreCase("wood") && currentPlayer.getWood() >= npcItem.getQuantity())) {
                                            return new Result(false, "You don't have enough resources");
                                        } else {
                                            found = true;
                                            currentPlayer.getInventory().removeItem(item, npcItem.getQuantity());
                                            switch (object.getCorrectName()) {
                                                case "wood":
                                                    if (npcItem.getRequiredLevel() == 0) {
                                                        App.getCurrentGame().getNPCLEAH().getQuests().remove(object);
                                                        if (friendshipLVL == 2) {
                                                            currentPlayer.setGold(currentPlayer.getGold() + 1000);
                                                        } else {
                                                            currentPlayer.setGold(currentPlayer.getGold() + 500);
                                                            System.out.println("added 500 gold");
                                                        }
                                                        return new Result(true, "LEAH: Thanks for giving me " + object.getCorrectName());
                                                    } else {
                                                        CraftingItem matarsak = new CraftingItem(CraftingRecipesEnums.DeluxeScarecrow.toString());
                                                        currentPlayer.getInventory().addItem(matarsak, 3);
                                                        App.getCurrentGame().getNPCLEAH().getQuests().remove(object);
                                                        return new Result(true, "LEAH: Thanks for giving me " + object.getCorrectName());
                                                    }
                                                case "salmon":
                                                    Cookingrecipe cookingrecipe = new Cookingrecipe();
                                                    cookingrecipe.setFood(FoodCookingEnums.SalmonDinner);
                                                    cookingrecipe.setPrice(100);
                                                    if (friendshipLVL == 2) {
                                                        cookingrecipe.setPrice(600);
                                                    } else {
                                                        cookingrecipe.setPrice(300);
                                                    }
                                                    currentPlayer.getCookingRecipes().add(cookingrecipe);
                                                    App.getCurrentGame().getNPCLEAH().getQuests().remove(object);
                                                    return new Result(true, "LEAH: Thanks for giving me " + object.getCorrectName());
                                            }
                                        }
                                    }
                                }
                                if (!found) {
                                    return new Result(false, "You don't have enough resources");
                                }
                            }
                        }
                        if (!flag) {
                            return new Result(false, "You can't do the selected quest right now");
                        }
                    } else {
                        if (npc.getName() == NPCEnums.ROBIN) {
                            int friendshipLVL = -1;
                            for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                                if (friendship.getPlayer() == currentPlayer) {
                                    friendshipLVL = friendship.getFriendshipLevel() / 200;
                                    break;
                                }
                            }
                            boolean flag = false;
                            for (Item object : App.getCurrentGame().getNPCROBIN().getQuests().keySet()) {
                                NPCItem npcItem = App.getCurrentGame().getNPCROBIN().getQuests().get(object);
                                if (friendshipLVL >= npcItem.getRequiredLevel() && npcItem.getRequiredLevel() == index) {
                                    flag = true;

                                    boolean found = false;

                                    for (Item item : currentPlayer.getInventory().getItems().keySet()) {

                                        if (object.getCorrectName().equalsIgnoreCase("wood") || item.getCorrectName().toLowerCase().replace(" ", "").equals(object.getCorrectName())) {
                                            if (currentPlayer.getInventory().getItemQuantity(item) < npcItem.getQuantity() && !(object.getCorrectName().equalsIgnoreCase("wood") && currentPlayer.getWood() >= npcItem.getQuantity())) {
                                                return new Result(false, "You don't have enough resources");
                                            } else {
                                                found = true;
                                                currentPlayer.getInventory().removeItem(item, npcItem.getQuantity());
                                                switch (object.getCorrectName()) {
                                                    case "wood":
                                                        if (npcItem.getRequiredLevel() == 0) {
                                                            App.getCurrentGame().getNPCROBIN().getQuests().remove(object);
                                                            if (friendshipLVL == 2) {
                                                                currentPlayer.setGold(currentPlayer.getGold() + 2000);
                                                            } else {
                                                                currentPlayer.setGold(currentPlayer.getGold() + 1000);
                                                                System.out.println("added 1000 gold");
                                                            }
                                                            return new Result(true, "ROBIN: Thanks for giving me " + object.getCorrectName());
                                                        } else {
                                                            App.getCurrentGame().getNPCROBIN().getQuests().remove(object);
                                                            if (friendshipLVL == 2) {
                                                                currentPlayer.setGold(currentPlayer.getGold() + 50000);
                                                            } else {
                                                                currentPlayer.setGold(currentPlayer.getGold() + 25000);
                                                            }
                                                            return new Result(true, "ROBIN: Thanks for giving me " + object.getCorrectName());
                                                        }
                                                    case "ironbar":
                                                        CraftingItem zanboor = new CraftingItem(CraftingRecipesEnums.BeeHouse.toString());
                                                        currentPlayer.getInventory().addItem(zanboor, 3);
                                                        App.getCurrentGame().getNPCROBIN().getQuests().remove(object);
                                                        return new Result(true, "ROBIN: Thanks for giving me " + object.getCorrectName());
                                                }
                                            }
                                        }
                                    }
                                    if (!found) {
                                        return new Result(false, "You don't have enough resources");
                                    }
                                }
                            }
                            if (!flag) {
                                return new Result(false, "You can't do the selected quest right now");
                            }
                        } else {
                            return new Result(false, "No NPC found nearby");
                        }
                    }
                }
            }
        }
        return null;
    }

    public void showBuffEffect(Buff buff) {
        // مسیر عکس را بسته به نوع buff مشخص کن
        Main.getBatch().begin();
        int scale = 3;
//        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
//        TextureRegionDrawable drawable = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
//            getCookingAtlas().findRegion(name));
//        drawable.setMinSize(drawable.getMinWidth()*scale, drawable.getMinHeight()*scale);
        // لود عکس
        TextureRegionDrawable buffTexture = new TextureRegionDrawable(GameAssetManager.getGameAssetManager().
            getSkillAtlas().findRegion(buff.getBuffSkillType().name()));
        buffTexture.setMinSize(buffTexture.getMinWidth() * scale, buffTexture.getMinHeight() * scale);
        Image buffImage = new Image(buffTexture);

        // تنظیم سایز و جایگاه دلخواه در صفحه (مثلا وسط)
        buffImage.setSize(100, 100);
        buffImage.setPosition(
            (Gdx.graphics.getWidth() - buffImage.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - buffImage.getHeight()) / 2f
        );

        // ابتدا شفافیت را صفر کن
        buffImage.getColor().a = 0f;

        // اضافه به stage
//        stage.addActor(buffImage);

        buffImage.draw(Main.getBatch(), buffImage.getColor().a);
//        Main.getBatch().draw(buffImage,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

        // اکشن fade in -> مکث -> fade out -> حذف
        buffImage.addAction(Actions.sequence(
            Actions.fadeIn(0.5f),
            Actions.delay(1.5f),
            Actions.fadeOut(0.5f),
            Actions.run(() -> buffImage.remove())
        ));
        Main.getBatch().end();
    }


    public void cheatAdd(int count, Player playerrr) {
        playerrr.setGold(playerrr.getGold() + count);
        System.out.println("new Balance: " + playerrr.getGold());
    }

}
