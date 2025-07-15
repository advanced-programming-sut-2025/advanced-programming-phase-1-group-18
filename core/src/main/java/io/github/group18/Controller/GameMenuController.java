package io.github.group18.Controller;

import com.google.gson.Gson;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.*;
import io.github.group18.Model.Satl;
import io.github.group18.View.GameMenu;
import io.github.group18.View.GameMenuMenu;
import io.github.group18.View.StartNewGame;
import io.github.group18.enums.*;

import java.io.FileWriter;
import java.io.IOException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;

public class GameMenuController implements ShowCurrentMenu, MenuEnter {

    public void handleGameMenuButtons(GameMenuMenu view) {
        if (view != null) {
            if (view.getExitGame().isPressed()) {
                exitGame();
            } else if (view.getTerminateGame().isPressed()) {
                voteTerminateGame(new Scanner(System.in));
            } else if (view.getLoadGame().isPressed()) {
                loadGame();
            } else if (view.getStartNewGame().isPressed()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartNewGame(new StartNewGameController(), view.getSkin(), this));

            }
        }
    }

    public static void checkSkilRecipe() {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    public Result whoAmI() {
        int Playerindex = App.getCurrentGame().getIndexPlayerinControl();
        return new Result(true, "Player: " + App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getOwner().getUsername() + " " + Playerindex);
    }

    public boolean isFainted() {
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() == 0) {
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

    public void setMapp(Game game) {
        ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
        int columns = 560; // Define the number of columns

        for (int i = 0; i < 1000; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new Kashi()); // Initialize each cell with an instance of Kashi
            }
            Map.add(row);
        }
        game.setMap(Map);

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < columns; j++) {
                Map.get(i).get(j).setWalkable(true);
                Map.get(i).get(j).setEnterance(false);
                Map.get(i).get(j).setShokhmZadeh(false);
            }
        }
    }

    public Result gameNew(ArrayList<String> users, ArrayList<Integer> maps) throws NoSuchFieldException, IllegalAccessException {
//        String[] parts = command.split("\\s+");
//        if (parts.length < 3) {
//            return new Result(false, "Invalid command format");
//        }
//        String[] usernames = Arrays.copyOfRange(parts, 3, parts.length);
//        if (command.split("\\s+").length - 3 >= 0)
//            System.arraycopy(command.split("\\s+"), 3, usernames, 0, command.split("\\s+").length - 3);
        String[] usernames = users.toArray(new String[0]);

        if (usernames.length > 3) {
            return new Result(false, "Choose 3 usernames Max");
        }
        for (String username : usernames) {
            boolean check = false;
            if (username.equals(App.getCurrentUser().getUsername())) {
                return new Result(false, "Can't choose yourself");
            }
            for (User user : App.getUsers_List()) {
                if (username.equals(user.getUsername())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return new Result(false, "Username does not exist");
            }
        }
        String[] usrnms = new String[usernames.length];
        int counter = 0;
        for (String username : usernames) {
            for (String usernm : usrnms) {
                if (username.equals(usernm)) {
                    return new Result(false, "Username selected twice");
                }
            }
            usrnms[counter] = username;
        }
        for (String username : usernames) {
            for (Game game : App.getGames()) {
                for (Player player : game.getPlayers()) {
                    if (player.getOwner().getUsername().equals(username)) {
                        return new Result(false, "Username is already in a game");
                    }
                }
            }
        }
        Game NewGame = new Game();
        NewGame.setPlayers(new ArrayList<>());
        NewGame.setCreator(App.getCurrentUser());
        NewGame.setIndexPlayerinControl(0);

        Player player1 = new Player();
        player1.setOwner(App.getCurrentUser());
        player1.setEnergy(200);
        NewGame.getPlayers().add(player1);
        //System.out.println("");
        for (String username : usernames) {
            Player player = new Player();
            for (User user : App.getUsers_List()) {
                if (username.equals(user.getUsername())) {
//                    System.out.println("nigga");
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
            //System.out.println(NewGame.getPlayers().get(i).getOwner().getUsername() + " " + NewGame.getPlayers().get(i).getOwner().getTimesPlayed());
        }

        for (int i = 0; i < NewGame.getPlayers().size(); i++) {
            Player player;
            if (i != 0) {
                player = NewGame.getPlayers().get(i);
            } else {
                player = player1;
            }
            System.out.println("Choosing map for: " + player.getOwner().getUsername());

//            int number = -1;
//            number = scanner.nextInt();
//            scanner.nextLine();
            int number = maps.get(i);

            if (number >= 1 && number <= 4) {

            } else {
                return new Result(false, "Invalid number");
            }

            int topLeftx = (int) getFieldValue(Game.class, NewGame, "player" + (i + 1) + "TopLeftx");
            int topLefty = (int) getFieldValue(Game.class, NewGame, "player" + (i + 1) + "TopLefty");

            player.setX(topLeftx);
            player.setY(topLefty);

            if (i == 0) {
                setMapp(NewGame);
                App.setCurrentGame(NewGame);
                App.setGames(new ArrayList<>());
                App.getGames().add(NewGame);
            }
            switch (number - 1) {
                case 0:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap1(topLeftx, topLefty);
                    break;
                case 1:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap2(topLeftx, topLefty);
                    break;
                case 2:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap3(topLeftx, topLefty);
                    break;
                case 3:
                    player.setMyFarm(new Farm());
                    player.getMyFarm().createMap4(topLeftx, topLefty);
                    break;
                default:
                    System.out.println("Unexpected error occurred.");
                    break;
            }
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

    public Result exitGame() {
//        System.exit(0);
        if (App.getCurrentGame() == null) {
            return new Result(false, "Game not started");
        }
        if (App.getCurrentGame().getCreator().getUsername().equals(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getOwner().getUsername())) {
            //save game
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
            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.GameMenu);
            System.exit(0);
            return new Result(true, "Game Exited");
        } else {
            return new Result(false, "You don't have access exit the game");
        }
    }

    public Result voteTerminateGame(Scanner scanner) {
        int total_neg = 0;
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (!player.getOwner().getUsername().equals(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getOwner().getUsername())) {
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
            App.setCurrentGame(null);
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

    public static void nextTurn(GameMenu gameMenu) {

        App.getCurrentGame().setIndexPlayerinControl(App.getCurrentGame().getIndexPlayerinControl() + 1);
        if (App.getCurrentGame().getIndexPlayerinControl() == App.getCurrentGame().getPlayers().size()) {
            App.getCurrentGame().setIndexPlayerinControl(0);


            if (App.getCurrentGame().getCurrentDateTime().getHour() == 23) {

                startNewDay(gameMenu, true);

            } else {
                App.getCurrentGame().setCurrentDateTime(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour() + 1,
                    App.getCurrentGame().getCurrentDateTime().getDay()));
            }

            for (Player player : App.getCurrentGame().getPlayers()) {
                if (player.getFoodBuff().getBuffHours() != 0) {
                    player.getFoodBuff().setBuffHours(player.getFoodBuff().getBuffHours() - 1);
                }
            }


        }
    }

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
                    App.getCurrentGame().getPlayers().get(0).setX(player1.getX());
                    App.getCurrentGame().getPlayers().get(0).setY(player1.getY());
                }
            } else if (i == 1) {
                if (App.getCurrentGame().getPlayers().get(1).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(1).setX(player2.getX());
                    App.getCurrentGame().getPlayers().get(1).setY(player2.getY());
                }
            } else if (i == 2) {
                if (App.getCurrentGame().getPlayers().get(2).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(2).setX(player3.getX());
                    App.getCurrentGame().getPlayers().get(2).setY(player3.getY());
                }
            } else if (i == 3) {
                if (App.getCurrentGame().getPlayers().get(3).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(3).setX(player4.getX());
                    App.getCurrentGame().getPlayers().get(3).setY(player4.getY());
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

    public static void startNewDay(GameMenu gameMenu, boolean transaction) {
        if (transaction) {
            gameMenu.startSleepTransition();
            return;
        }
        //restock markets
        App.getCurrentGame().getBlackSmithMarket().fillStock();
        App.getCurrentGame().getCarpentersShopMarket().fillStock();
        App.getCurrentGame().getCarpentersShopMarket().fillStock();
        App.getCurrentGame().getFishShopMarket().fillStock();
        //System.out.println("nigga");
        App.getCurrentGame().getJojoMartMarket().fillStock(App.getCurrentGame().getCurrentDateTime().getSeason());
        //System.out.println("nigga1");
        App.getCurrentGame().getMarniesRanchMarket().fillStock();
        App.getCurrentGame().getPierresGeneralStoreMarket().fillStock(App.getCurrentGame().getCurrentSeason());

        getBackHome();

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
// for marriage
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
        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().getItems().keySet()) {
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
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() + item.getCorrectPrice() * App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().getItems().get(item));
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().removeItem(item, App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().getItems().get(item));


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

        App.getCurrentGame().getPierresGeneralStoreMarket().setLargePackBougth(false);
        App.getCurrentGame().getPierresGeneralStoreMarket().setDeluxePackBought(false);
        App.getCurrentGame().getCarpentersShopMarket().setBarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setBigbarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setBigcoop(false);
        App.getCurrentGame().getCarpentersShopMarket().setCoop(false);
        App.getCurrentGame().getCarpentersShopMarket().setDeluxebarn(false);
        App.getCurrentGame().getCarpentersShopMarket().setDeluxecoop(false);

        Random rand = new Random();
        for (Player player : App.getCurrentGame().getPlayers()) {
            int chanceOfKalag = player.getMyFarm().getAllTrees().size() + player.getMyFarm().getAllCrops().size();
            int threshold = (chanceOfKalag / 16) + 1;
            boolean shouldRemove = false;

// Determine if we should attempt removal based on threshold
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

        App.getCurrentGame().setCurrentDateTime(new DateTime(9, App.getCurrentGame().getCurrentDateTime().getDay() + 1));

        App.getCurrentGame().endOfDayUpdate();


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
//                    thor.setKhordeh(kashiList);
        }

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

    public Result time() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentDateTime().getHour()));
    }

    public Result date() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getDay();
        return new Result(true, stringBuilder);
    }

    public Result dateTime() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getDay() +
            "-" +
            App.getCurrentGame().getCurrentDateTime().getHour();
        return new Result(true, stringBuilder);

    }

    public Result dayOfWeek() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        int dayOfWeek = App.getCurrentGame().getCurrentDateTime().getDay() % 7;
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

    public Result cheatAdvanceTime(int hour, GameMenu gameMenu) {
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
            startNewDay(gameMenu,true);
        }
//        if (hour / 24 >= 1) {
//            App.getCurrentGame().getCurrentDateTime().setDay(App.getCurrentGame().getCurrentDateTime().getDay() - 1);
//        }
        return new Result(true, "cheatCode: Hour changed! New Hour: " + hourOfDay + " New Day: " + newDay);
    }

    public Result cheatAdvanceDate(int day, GameMenu gameMenu) {
        if (day < 0) {
            return new Result(false, "cheatCode: Invalid day");
        }
        int newDay = day + App.getCurrentGame().getCurrentDateTime().getDay();
        //App.getCurrentGame().setCurrentDateTime(new DateTime(day, newDay));
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
        for (int i = 0; i < day; i++) {
            startNewDay(gameMenu,true);
        }
        //App.getCurrentGame().getCurrentDateTime().setDay(App.getCurrentGame().getCurrentDateTime().getDay() - 1);
        return new Result(true, "cheatCode: Day changed! New Day: " + newDay);
    }

    public Result season() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, App.getCurrentGame().getCurrentDateTime().getSeason());
    }

    public Result cheatThor(int x, int y) {

        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }

        if (App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof AllTree || App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof ForagingTree) {
            Mineral coal = new Mineral();
            coal.setType(ForagingMineralsEnums.Coal);
            App.getCurrentGame().getMap().get(x).get(y).setInside(coal);
        }

        return new Result(true, "cheatCode: Thor changed! Thor strike at (" + x + "," + y + ")");
    }

    public Result weather() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, "Weather : " + App.getCurrentGame().getCurrentWeather().toString());
    }

    public Result weatherForecast() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        return new Result(true, "Tomorrow Weather : " + App.getCurrentGame().getWeather().getFirst().toString());

    }

    public Result cheatWeather(String Type) {
        if (isFainted()) {
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

    public Result greenHouseBiuld() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() > 500
            && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() > 1000) {
            App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl())
                .getMyFarm().getMyGreenHouse().setStatus(true);

        }
        return new Result(true, "Green House Build successfully");
    }

    public Result walk(int x, int y) {
        int index = App.getCurrentGame().getIndexPlayerinControl();
        switch (index) {

            case 0:
                if (x < 0 || y < 0 ||
                    (x > App.getCurrentGame().getPlayer2TopLeftx() &&
                        y < App.getCurrentGame().getPlayer2TopLefty() + App.getCurrentGame().getPlayer2Height()) ||
                    (x < App.getCurrentGame().getPlayer3TopLeftx() + App.getCurrentGame().getPlayer3Width() &&
                        y > App.getCurrentGame().getPlayer3TopLefty()) ||
                    (x > App.getCurrentGame().getPlayer4TopLeftx() &&
                        y > App.getCurrentGame().getPlayer4TopLefty() + App.getCurrentGame().getPlayer4Height())) {
                    return new Result(false, "Your destination is out of bounds");
                } else {
                    ArrayList<ArrayList<Kashi>> listMap = App.getCurrentGame().getMap();
                    Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

                    AStarPathfinder.Natigeh result = AStarPathfinder.findPath(
                        listMap,
                        player.getX(),
                        player.getY(),
                        x, y);

                    if (result != null) {
                        System.out.println("Way found!");
                        for (int[] node : result.path) {
                            System.out.println("  (" + node[0] + ", " + node[1] + ")");
                        }

                        int estimatedEnergy = AStarPathfinder.calculatePower(result.tileCount, result.turnCount);
                        System.out.printf("You need %d parts of energy to go there.\n", estimatedEnergy);
                        System.out.println("Do you wanna go there or not? yes if you want and no if you don't want");

                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("yes")) {
                                int tilesWalked = 0;
                                int turnsMade = 0;
                                int energyBefore = player.getEnergy();
                                String lastDirection = null;

                                for (int[] step : result.path) {
                                    int[] current = new int[]{player.getX(), player.getY()};
                                    String direction = AStarPathfinder.getDirection(current, step);
                                    if (lastDirection != null && !lastDirection.equals(direction)) {
                                        turnsMade++;
                                    }

                                    int energyNeededSoFar = AStarPathfinder.calculatePower(tilesWalked + 1, turnsMade);
                                    if (energyNeededSoFar > energyBefore) {
                                        player.setEnergy(0);
                                        player.setMaxEnergy((int) (0.75 * player.getMaxEnergy()));
                                        player.setDaysAfterGash(0);
                                        return new Result(true, "You ran out of energy and stopped at (" + player.getX() + ", " + player.getY() + ")");
                                    }

                                    player.setX(step[0]);
                                    player.setY(step[1]);
                                    lastDirection = direction;
                                    tilesWalked++;
                                }

                                int finalEnergy = energyBefore - AStarPathfinder.calculatePower(tilesWalked, turnsMade);
                                player.setEnergy(finalEnergy);

                                return new Result(true, "You went to your destination successfully!");
                            } else if (input.equalsIgnoreCase("no")) {
                                return new Result(true, "Transportation canceled!");
                            } else {
                                return new Result(false, "Enter yes or no");
                            }
                        }
                    } else {
                        return new Result(false, "Way not found!");
                    }
                }

            case 1:
                if (x < 0 || y < 0 ||
                    (x < App.getCurrentGame().getPlayer1TopLeftx() + App.getCurrentGame().getPlayer1Width() &&
                        y < App.getCurrentGame().getPlayer1TopLefty() + App.getCurrentGame().getPlayer1Height()) ||
                    (x < App.getCurrentGame().getPlayer3TopLeftx() + App.getCurrentGame().getPlayer3Width() &&
                        y > App.getCurrentGame().getPlayer3TopLefty()) ||
                    (x > App.getCurrentGame().getPlayer4TopLeftx() &&
                        y > App.getCurrentGame().getPlayer4TopLefty() + App.getCurrentGame().getPlayer4Height())) {
                    return new Result(false, "Your destination is out of bounds");
                } else {
                    ArrayList<ArrayList<Kashi>> listMap = App.getCurrentGame().getMap();
                    Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

                    AStarPathfinder.Natigeh result = AStarPathfinder.findPath(
                        listMap,
                        player.getX(),
                        player.getY(),
                        x, y);

                    if (result != null) {
                        System.out.println("Way found!");
                        for (int[] node : result.path) {
                            System.out.println("  (" + node[0] + ", " + node[1] + ")");
                        }

                        int estimatedEnergy = AStarPathfinder.calculatePower(result.tileCount, result.turnCount);
                        System.out.printf("You need %d parts of energy to go there.\n", estimatedEnergy);
                        System.out.println("Do you wanna go there or not? yes if you want and no if you don't want");

                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("yes")) {
                                int tilesWalked = 0;
                                int turnsMade = 0;
                                int energyBefore = player.getEnergy();
                                String lastDirection = null;

                                for (int[] step : result.path) {
                                    int[] current = new int[]{player.getX(), player.getY()};
                                    String direction = AStarPathfinder.getDirection(current, step);
                                    if (lastDirection != null && !lastDirection.equals(direction)) {
                                        turnsMade++;
                                    }

                                    int currentRequiredEnergy = AStarPathfinder.calculatePower(tilesWalked + 1, turnsMade);
                                    if (currentRequiredEnergy > energyBefore) {
                                        player.setEnergy(0);
                                        player.setMaxEnergy((int) (0.75 * player.getMaxEnergy()));
                                        player.setDaysAfterGash(0);
                                        return new Result(true, "You ran out of energy and stopped at (" + player.getX() + ", " + player.getY() + ")");
                                    }

                                    player.setX(step[0]);
                                    player.setY(step[1]);
                                    lastDirection = direction;
                                    tilesWalked++;
                                }

                                // مصرف انرژی نهایی
                                player.setEnergy(energyBefore - AStarPathfinder.calculatePower(tilesWalked, turnsMade));

                                return new Result(true, "You went to your destination successfully!");
                            } else if (input.equalsIgnoreCase("no")) {
                                return new Result(true, "Transportation canceled!");
                            } else {
                                return new Result(false, "Enter yes or no");
                            }
                        }
                    } else {
                        System.out.println("Way not found!");
                        return new Result(false, "There is no valid path to your destination.");
                    }
                }

                //}
            case 2:
                if (x < 0 || y < 0 ||
                    (x < App.getCurrentGame().getPlayer1TopLeftx() + App.getCurrentGame().getPlayer1Width() &&
                        y < App.getCurrentGame().getPlayer1TopLefty() + App.getCurrentGame().getPlayer1Height()) ||
                    (x > App.getCurrentGame().getPlayer2TopLeftx() &&
                        y < App.getCurrentGame().getPlayer2TopLefty() + App.getCurrentGame().getPlayer2Height()) ||
                    (x > App.getCurrentGame().getPlayer4TopLeftx() &&
                        y > App.getCurrentGame().getPlayer4TopLefty() + App.getCurrentGame().getPlayer4Height())) {
                    return new Result(false, "Your destination is out of bounds");
                } else {
                    ArrayList<ArrayList<Kashi>> listMap = App.getCurrentGame().getMap();
                    Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
                    AStarPathfinder.Natigeh result = AStarPathfinder.findPath(
                        listMap,
                        player.getX(),
                        player.getY(),
                        x, y);

                    if (result != null) {
                        System.out.println("Way found!");
                        for (int[] node : result.path) {
                            System.out.println("  (" + node[0] + ", " + node[1] + ")");
                        }

                        int estimatedEnergy = AStarPathfinder.calculatePower(result.tileCount, result.turnCount);
                        System.out.printf("You need  %d parts of energy to go there.\n", estimatedEnergy);
                        System.out.println("Do you wanna go there or not? yes if you want and no if you don't want");

                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("yes")) {
                                int tilesWalked = 0;
                                int turnsMade = 0;
                                int energyBefore = player.getEnergy();
                                String lastDirection = null;

                                for (int[] step : result.path) {
                                    int[] current = new int[]{player.getX(), player.getY()};
                                    String direction = AStarPathfinder.getDirection(current, step);
                                    if (lastDirection != null && !lastDirection.equals(direction)) {
                                        turnsMade++;
                                    }

                                    int currentRequiredEnergy = AStarPathfinder.calculatePower(tilesWalked + 1, turnsMade);
                                    if (currentRequiredEnergy > energyBefore) {
                                        player.setEnergy(0);
                                        player.setMaxEnergy((int) (0.75 * player.getMaxEnergy()));
                                        player.setDaysAfterGash(0);
                                        return new Result(true, "You ran out of energy and stopped at (" + player.getX() + ", " + player.getY() + ")");
                                    }

                                    player.setX(step[0]);
                                    player.setY(step[1]);
                                    lastDirection = direction;
                                    tilesWalked++;
                                }

                                // کم کردن نهایی انرژی بعد از طی کامل مسیر
                                player.setEnergy(energyBefore - AStarPathfinder.calculatePower(tilesWalked, turnsMade));

                                return new Result(true, "You went to your destination successfully!");
                            } else if (input.equalsIgnoreCase("no")) {
                                return new Result(true, "Transportation canceled!");
                            } else {
                                return new Result(false, "Enter yes or no");
                            }
                        }
                    } else {
                        System.out.println("Way not found!");
                        return new Result(false, "There is no valid path to your destination.");
                    }
                }
                //}
            /*case 3:
                if (x < 0 || y < 0 || (x < App.getCurrentGame().getPlayer1TopLeftx() + App.getCurrentGame().getPlayer1Width() && y < App.getCurrentGame().getPlayer1TopLefty() + App.getCurrentGame().getPlayer1Height()) || (x > App.getCurrentGame().getPlayer2TopLeftx() && y < App.getCurrentGame().getPlayer2TopLefty() + App.getCurrentGame().getPlayer2Height()) || (x < App.getCurrentGame().getPlayer3TopLeftx() + App.getCurrentGame().getPlayer3Width() && y > App.getCurrentGame().getPlayer3TopLefty())) {
                    return new Result(false, "You destination is out of bounds");
                } else {
                    ArrayList<ArrayList<Kashi>> listMap = App.getCurrentGame().getMap();
                    AStarPathfinder.Natigeh result = AStarPathfinder.findPath(listMap,
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getX(),
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getY(),
                            x, y);

                    if (result != null) {
                        System.out.println("Way found!");
                        for (int[] node : result.path) {
                            System.out.println("  (" + node[0] + ", " + node[1] + ")");
                        }

                        int requiredEnergy = AStarPathfinder.calculatePower(result.tileCount, result.turnCount);
                        System.out.printf("You need %d parts of energy to go there.\n", requiredEnergy);
                        System.out.println("Do you wanna go there or not? yes if you want and no if you don't want");

                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("yes")) {
                                int shomareh = App.getCurrentGame().getIndexPlayerinControl();
                                Player player = App.getCurrentGame().getPlayers().get(shomareh);
                                for (int[] step : result.path) {
                                    if (player.getEnergy() <= 0) {
                                        return new Result(true, "You ran out of energy and stopped at (" + player.getX() + ", " + player.getY() + ")");
                                    }
                                    System.out.println(player.getEnergy());
                                    player.setEnergy(player.getEnergy() - 1);
                                    player.setX(step[0]);
                                    player.setY(step[1]);
                                    //set max energy for tomorrow
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setMaxEnergy(150);
                                }
                                player.setEnergy(player.getEnergy() - requiredEnergy);
                                player.setX(x);
                                player.setY(y);
                                return new Result(true, "You went to your destination successfully!");
                            } else if (input.equalsIgnoreCase("no")) {
                                return new Result(true, "Transportation canceled!");
                            } else {
                                return new Result(false, "Enter yes or no");
                            }
                        }
                    } else {
                        System.out.println("Way not found!");
                        break;
                    }
                }
                //}
//               return new Result(true, "The walk section was done currectly!");
*/
            case 3:
                if (x < 0 || y < 0 ||
                    (x < App.getCurrentGame().getPlayer1TopLeftx() + App.getCurrentGame().getPlayer1Width() &&
                        y < App.getCurrentGame().getPlayer1TopLefty() + App.getCurrentGame().getPlayer1Height()) ||
                    (x > App.getCurrentGame().getPlayer2TopLeftx() &&
                        y < App.getCurrentGame().getPlayer2TopLefty() + App.getCurrentGame().getPlayer2Height()) ||
                    (x < App.getCurrentGame().getPlayer3TopLeftx() + App.getCurrentGame().getPlayer3Width() &&
                        y > App.getCurrentGame().getPlayer3TopLefty())) {
                    return new Result(false, "Your destination is out of bounds");
                } else {
                    ArrayList<ArrayList<Kashi>> listMap = App.getCurrentGame().getMap();
                    Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
                    AStarPathfinder.Natigeh result = AStarPathfinder.findPath(
                        listMap,
                        player.getX(),
                        player.getY(),
                        x, y);

                    if (result != null) {
                        System.out.println("Way found!");
                        for (int[] node : result.path) {
                            System.out.println("  (" + node[0] + ", " + node[1] + ")");
                        }

                        int estimatedEnergy = AStarPathfinder.calculatePower(result.tileCount, result.turnCount);
                        System.out.printf("You need %d parts of energy to go there.\n", estimatedEnergy);
                        System.out.println("Do you wanna go there or not? yes if you want and no if you don't want");

                        Scanner sc = new Scanner(System.in);
                        while (true) {
                            String input = sc.nextLine();
                            if (input.equalsIgnoreCase("yes")) {
                                int tilesWalked = 0;
                                int turnsMade = 0;
                                int energyBefore = player.getEnergy();
                                String lastDirection = null;

                                for (int[] step : result.path) {
                                    int[] current = new int[]{player.getX(), player.getY()};
                                    String direction = AStarPathfinder.getDirection(current, step);
                                    if (lastDirection != null && !lastDirection.equals(direction)) {
                                        turnsMade++;
                                    }

                                    int currentRequiredEnergy = AStarPathfinder.calculatePower(tilesWalked + 1, turnsMade);
                                    if (currentRequiredEnergy > energyBefore) {
                                        player.setEnergy(0);
                                        player.setMaxEnergy((int) (0.75 * player.getMaxEnergy()));
                                        player.setDaysAfterGash(0);
                                        return new Result(true, "You ran out of energy and stopped at (" + player.getX() + ", " + player.getY() + ")");
                                    }
                                    player.setX(step[0]);
                                    player.setY(step[1]);
                                    lastDirection = direction;
                                    tilesWalked++;
                                }

                                // Energy consumption done after full traversal
                                player.setEnergy(energyBefore - AStarPathfinder.calculatePower(tilesWalked, turnsMade));

                                return new Result(true, "You went to your destination successfully!");
                            } else if (input.equalsIgnoreCase("no")) {
                                return new Result(true, "Transportation canceled!");
                            } else {
                                return new Result(false, "Enter yes or no");
                            }
                        }
                    } else {
                        System.out.println("Way not found!");
                        return new Result(false, "There is no valid path to your destination.");
                    }
                }
        }
        return null;
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

    public void energyShow() {
        System.out.println("Energy: " +
            App.getCurrentGame().getPlayers().get(App.getCurrentGame()
                .getIndexPlayerinControl()).getEnergy());
    }

    public Result energySet(int value) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (value < 0) {
            return new Result(false, "CheatCode: Invalid energy");
        }
        App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(value);
        return new Result(true, "CheatCode: Energy set to " + value);
    }

    public void energyUnlimited() {
        if (isFainted()) {
            System.out.println("You are fainted!");
        }
        App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl()).setMaxEnergy(Integer.MAX_VALUE);
        App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(Integer.MAX_VALUE);
        System.out.println("**Energy unlimited**");

    }

    public void inventoryShow() {
        if (isFainted()) {
            System.out.println("You are fainted!");
        }
        Map<Item, Integer> items = App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl())
            .getInventory()
            .getItems();
        if (items.isEmpty()) {
            System.out.println("**Inventory is Empty**");
        } else {
            for (Item item : items.keySet()) {
                System.out.println("- " + item.getCorrectName() + " (Quantity: " + items.get(item) + ")");
            }
        }
    }

    public Result inventoryTrash(String name, int number) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        //find Item by name
        Item item = findItemByName(name);
        if (item == null) {
            return new Result(false, "Item not found");
        } else {
            Map<Item, Integer> itemmmm = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems();
            TrashCan trashcan = null;
            for (Item itemm : itemmmm.keySet()) {
                if (itemm instanceof TrashCan) {
                    trashcan = (TrashCan) item;
                    break;
                }
            }
            if (trashcan != null && trashcan.getJens().equals("initial")) {

            }
            if (trashcan != null && trashcan.getJens().equals("copper")) {
                int nowgold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold();
                int price = (int) (item.getCorrectPrice() * 0.15);
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(nowgold + price);
            }
            if (trashcan != null && trashcan.getJens().equals("iron")) {
                int nowgold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold();
                int price = (int) (item.getCorrectPrice() * 0.3);
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(nowgold + price);
            }
            if (trashcan != null && trashcan.getJens().equals("gold")) {
                int nowgold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold();
                int price = (int) (item.getCorrectPrice() * 0.45);
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(nowgold + price);
            }
            if (trashcan != null && trashcan.getJens().equals("iridium")) {
                int nowgold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold();
                int price = (int) (item.getCorrectPrice() * 0.6);
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(nowgold + price);
            }
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, number);
            return new Result(true, number + "of Item " + item.getClass().getSimpleName() + " removed");
        }
    }

    public Item findItemByName(String name) {
        Map<Item, Integer> items = App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl())
            .getInventory()
            .getItems();

        for (Item item : items.keySet()) {
            if (item.getCorrectName().equalsIgnoreCase(name)) { // مقایسه نام کلاس آیتم با ورودی
                return item;
            }
        }

        return null;
    }

    public Result toolEquip(String toolName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Item item = findItemByName(toolName);
        if (item == null) {
            return new Result(false, "Item not found");
        } else if (!(item instanceof Tool)) {
            return new Result(false, "Your entered item is not a tool!");
        } else {
            Tool tool = (Tool) item;
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setInMyHandTool(tool);
            return new Result(true, "now your entered tool is in your hand!");
        }
    }

    public Result toolsShowCurrent() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Tool tool = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
        if (tool == null) {
            return new Result(false, "!You don't have any tools in your hand!");
        } else {
            String name = tool.getCorrectName();
            return new Result(true, "You have " + name + " tool in your hand!");
        }
    }

    public Result toolShowAvailable() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Map<Item, Integer> items = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems();
        StringBuilder toolsList = new StringBuilder("Tools in Inventory:\n");
        for (Item item : items.keySet()) {
            if (item instanceof Tool) {
                toolsList.append("- ").append(item.getCorrectName()).append("\n");
            }
        }
        return new Result(true, toolsList.toString());
    }

    public Result toolsUpgrade(String jens) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (!jens.equals("initial") && !jens.equals("copper") && !jens.equals("iron") && !jens.equals("gold") && !jens.equals("iridium")) {
            return new Result(false, "Your entered jens is not Okay!");
        }
        Tool tool = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
        if (tool == null) {
            return new Result(false, "Item not found");
        } else if (!(tool instanceof Tool)) {
            return new Result(false, "You can not upgrade this type of item!");
        }

        if (jens.equals("copper")) {
            if (tool instanceof Axe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 2000);
                    ((Axe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 2000);
                    ((Pickaxe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 2000);
                    ((Hoe) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 1000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1000);
                    ((TrashCan) tool).update("copper");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("iron")) {
            if (tool instanceof Axe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 5000);
                    ((Axe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 5000);
                    ((Pickaxe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 2000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 5000);
                    ((Hoe) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 2500) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 2500);
                    ((TrashCan) tool).update("iron");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("gold")) {
            if (tool instanceof Axe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 10000);
                    ((Axe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 10000);
                    ((Pickaxe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 10000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 10000);
                    ((Hoe) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 5000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 5000);
                    ((TrashCan) tool).update("gold");
                    return new Result(true, "tool updated!");
                }
            }
        }
        if (jens.equals("iridium")) {
            if (tool instanceof Axe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 25000);
                    ((Axe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Pickaxe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 25000);
                    ((Pickaxe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof Hoe) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 25000) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 25000);
                    ((Hoe) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
            if (tool instanceof TrashCan) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 12500) {
                    return new Result(false, "Your golds are not enough!");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 12500);
                    ((TrashCan) tool).update("iridium");
                    return new Result(true, "tool updated!");
                }
            }
        }
        return new Result(true, "!");
    }

    public Result toolsUse(String direction) {

        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Tool tool = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
        if (tool instanceof Axe) {
            System.out.println(((Axe) tool).use(direction));
            return new Result(true, "You used your tool");
        } else if (tool instanceof Pickaxe) {
            System.out.println(((Pickaxe) tool).use(direction));
            return new Result(true, "You used your tool");
        } else if (tool instanceof WateringCan) {
            System.out.println(((WateringCan) tool).use(direction));
            return new Result(true, "You used your tool");
        } else if (tool instanceof Hoe) {
            System.out.println(((Hoe) tool).use(direction));
            return new Result(true, "You used your tool");

        } else if (tool instanceof MilkPail) {
            System.out.println(((MilkPail) tool).use(direction));
            return new Result(true, "You used your tool");

        } else if (tool instanceof Shear) {
            System.out.println(((Shear) tool).use(direction));
            return new Result(true, "You used your tool");

        } else if (tool instanceof TrashCan) {
            System.out.println();
            return new Result(true, "You used your tool");

        } else if (tool instanceof Scythe) {
            System.out.println(((Scythe) tool).use(direction));
            return new Result(true, "You used your tool");

        } else {
            return new Result(false, "Invalid situation");
        }
    }

    public Result craftInfo(String craftName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        ForagingSeedsEnums foragingSeedsEnums;
        StringBuilder craftInfo = new StringBuilder();
        craftName.replace(" ", "");
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
                return new Result(true, craftInfo.toString());
            }
        }
        return new Result(false, "craft not found");
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

    public Result plant(String source, String direction) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        int dir_x = -1;
        int dir_y = -1;

        switch (direction.toLowerCase()) {
            case "n": {
                dir_x = 0;
                dir_y = -1;
                break;
            }
            case "ne": {
                dir_x = 1;
                dir_y = -1;
                break;
            }
            case "e": {
                dir_x = 1;
                dir_y = 0;
                break;
            }
            case "se": {
                dir_x = 1;
                dir_y = 1;
                break;
            }
            case "s": {
                dir_x = 0;
                dir_y = 1;
                break;
            }
            case "sw": {
                dir_x = -1;
                dir_y = 1;
                break;
            }
            case "w": {
                dir_x = -1;
                dir_y = 0;
                break;
            }
            case "nw": {
                dir_x = -1;
                dir_y = -1;
                break;
            }
            default: {
                return new Result(false, "Please select a valid direction\nn,ne,e,se,s,sw,w,nw");
            }
        }
        Player currentplayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (App.getCurrentGame().getMap().get(currentplayer.getX() + dir_x).get(currentplayer.getY() + dir_y).isShokhmZadeh() == false) {
            return new Result(false, "You have to shokhm it first");
        }
        if (source.equalsIgnoreCase("mixedseeds") || source.equalsIgnoreCase("mixedseed")) {
            try {
                // 1. Validate the player has the mixed seed in inventory
                AllCrop allCrop = new AllCrop();
                boolean valid = false;

                // Normalize the input seed name (remove spaces, handle case)
                String normalizedSeedName = source.trim().replace(" ", "").toLowerCase();
                //normalizedSeedName = mixedseeds

                // Check inventory for the mixed seed
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof MixedSeed) {
                        valid = true;
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl())
                            .getInventory().removeItem(item, 1);
                        break;
                    }
                }

                if (!valid) {
                    return new Result(false, "You don't have mixed seed in your inventory");
                }

                // 2. Get current season
                Seasons currentSeason = App.getCurrentGame().getCurrentSeason();

                // 3. Get all mixed seeds that are allowed in current season
                List<MixedSeedsEnums> seasonalCrops = Arrays.stream(MixedSeedsEnums.values())
                    .filter(crop -> crop.isAllowedIn(currentSeason))
                    .collect(Collectors.toList());

                if (seasonalCrops.isEmpty()) {
                    return new Result(false, "No crops available for planting in " + currentSeason);
                }

                // 4. Randomly select one of the seasonal crops
                Random random = new Random();
                MixedSeedsEnums selectedSeed = seasonalCrops.get(random.nextInt(seasonalCrops.size()));

                // 5. Find the corresponding AllCropsEnums using our mapping
                AllCropsEnums cropEnum = SEED_TO_CROP_MAP.get(selectedSeed);

                if (cropEnum == null) {
                    return new Result(false, "No corresponding crop found for " + selectedSeed.name());
                }

                // 6. Initialize and place the crop
                allCrop.initilizeCrop(selectedSeed);

                Player currentPlayer = App.getCurrentGame().getPlayers()
                    .get(App.getCurrentGame().getIndexPlayerinControl());
                Kashi kashi = App.getCurrentGame().getMap()
                    .get(currentPlayer.getX() + dir_x)
                    .get(currentPlayer.getY() + dir_y);
                kashi.setInside(allCrop);

                currentPlayer.getMyFarm().getAllCrops().add(allCrop);
                return new Result(true, "Planted " + cropEnum.name() + " successfully");
            } catch (Exception e) {
            }
        } else {
            //ForagingSeed
            try {
                if (Arrays.stream(ForagingSeedsEnums.values())
                    .anyMatch(er -> er.name().equalsIgnoreCase(source.replace(" ", "")))) {
                    AllCrop allCrop1 = new AllCrop();
                    try {
                        ForagingSeedsEnums foragingSeedsEnums = Arrays.stream(ForagingSeedsEnums.values())
                            .filter(ef -> ef.name().equalsIgnoreCase(source.replace(" ", "")))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("No enum constant for: " + source));
                        boolean valid2 = false;
                        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                            //System.out.println(item.getCorrectName() + "niffa");
                            if (item instanceof ForagingSeed) {
                                ForagingSeed foragingSeed = (ForagingSeed) item;
                                if (foragingSeed.getCorrectName().equals(source.toLowerCase().replace(" ", ""))) {
                                    valid2 = true;
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                                    break;
                                }
                            }
                        }
                        if (!valid2) {
                            return new Result(false, "You don't have " + source + " in your inventory");
                        }

                        allCrop1.setSourceForagingSeedEnum(foragingSeedsEnums);
                        allCrop1.initilizeCrop(foragingSeedsEnums);

                        Player currentPlayer = App.getCurrentGame().getPlayers()
                            .get(App.getCurrentGame().getIndexPlayerinControl());
                        Kashi kashi1 = App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y);
                        kashi1.setInside(allCrop1);

                        currentPlayer.getMyFarm().getAllCrops().add(allCrop1);
                        return new Result(true, "Plant successfully placed");

                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }
                } else {
                    //AllTree
                    if (Arrays.stream(TreeSeedEnums.values())
                        .anyMatch(rt -> rt.name().equalsIgnoreCase(source.replace(" ", "")))) {
                        AllTree allTree = new AllTree();
                        try {
                            TreeSeedEnums allTreesEnums = Arrays.stream(TreeSeedEnums.values())
                                .filter(fv -> fv.name().equalsIgnoreCase(source.replace(" ", "")))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("No enum constant for: " + source));
                            boolean valid1 = false;
                            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                if (item instanceof TreeSeed) {
                                    TreeSeed treeSeed = (TreeSeed) item;
                                    if (treeSeed.getCorrectName().equals(source.toLowerCase().replace(" ", ""))) {
                                        valid1 = true;
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                                        break;
                                    }
                                }
                            }
                            if (!valid1) {
                                return new Result(false, "You don't have " + source + " in your inventory");
                            }

                            allTree.setSource(allTreesEnums);
                            allTree.initilizeCrop(allTreesEnums);

                            Player currentPlayer = App.getCurrentGame().getPlayers()
                                .get(App.getCurrentGame().getIndexPlayerinControl());
                            Kashi kashi2 = App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y);
                            kashi2.setInside(allTree);

                            currentPlayer.getMyFarm().getAllTrees().add(allTree);


                            return new Result(true, "Tree successfully placed");
                        } catch (Exception ee) {
                            return new Result(false, "what happened");
                        }
                    } else {
                        return new Result(false, "invalid source");
                    }
                }
            } catch (
                Exception eee) {
            }
        }
        return new Result(false, "can't plant");
    }

    public Result showPlant(int x, int y) {
        if (isFainted()) {
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

    public Result fertilize(String fertilizer, String direction) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        int dir_x = -1;
        int dir_y = -1;

        switch (direction.toLowerCase()) {
            case "n": {
                dir_x = 0;
                dir_y = -1;
                break;
            }
            case "ne": {
                dir_x = 1;
                dir_y = -1;
                break;
            }
            case "e": {
                dir_x = 1;
                dir_y = 0;
                break;
            }
            case "se": {
                dir_x = 1;
                dir_y = 1;
                break;
            }
            case "s": {
                dir_x = 0;
                dir_y = 1;
                break;
            }
            case "sw": {
                dir_x = -1;
                dir_y = 1;
                break;
            }
            case "w": {
                dir_x = -1;
                dir_y = 0;
                break;
            }
            case "nw": {
                dir_x = -1;
                dir_y = -1;
                break;
            }
            default: {
                return new Result(false, "Please select a valid direction\nn,ne,e,se,s,sw,w,nw");
            }
        }
        boolean found = false;
        switch (fertilizer.toLowerCase()) {
            case "deluxe retaining soil":
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof Fertilizer && item.getCorrectName().equalsIgnoreCase("deluxeretainingsoil")) {
                        found = true;
                        if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree || App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllCrop) {
                            if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree) {
                                AllTree allTree = (AllTree) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allTree.setDaysGrowCounter(allTree.getTotalHarvestTime());
                            } else {
                                AllCrop allCrop = (AllCrop) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allCrop.setDaysGrowCounter(allCrop.getTotalHarvestTime());
                            }
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                            return new Result(true, "Successfully Fertilized");
                        } else {
                            return new Result(false, "Stand near a plant");
                        }
                    }
                }
                break;
            case "basic retaining soil":
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof Fertilizer && item.getCorrectName().equalsIgnoreCase("basicretainingsoil")) {
                        found = true;
                        if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree || App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllCrop) {
                            if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree) {
                                AllTree allTree = (AllTree) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allTree.setDaysGrowCounter(allTree.getTotalHarvestTime());
                            } else {
                                AllCrop allCrop = (AllCrop) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allCrop.setDaysGrowCounter(allCrop.getTotalHarvestTime());
                            }
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                            return new Result(true, "Successfully Fertilized");
                        } else {
                            return new Result(false, "Stand near a plant");
                        }
                    }
                }
                break;
            case "quality retaining soil":
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof Fertilizer && item.getCorrectName().equalsIgnoreCase("qualityretainingsoil")) {
                        found = true;
                        if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree || App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllCrop) {
                            if (App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside() instanceof AllTree) {
                                AllTree allTree = (AllTree) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allTree.setDaysGrowCounter(allTree.getTotalHarvestTime());
                            } else {
                                AllCrop allCrop = (AllCrop) App.getCurrentGame().getMap().get(currentPlayer.getX() + dir_x).get(currentPlayer.getY() + dir_y).getInside();
                                allCrop.setDaysGrowCounter(allCrop.getTotalHarvestTime());
                            }
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                            return new Result(true, "Successfully Fertilized");
                        } else {
                            return new Result(false, "Stand near a plant");
                        }
                    }
                }
                break;
            default:
                return new Result(false, "Please select a valid fertilizer");
        }
        return null;
    }

    public Result howMuchWater() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
            if (item instanceof WateringCan) {
                return new Result(true, "Capacity: " + (((WateringCan) item).getCapacity()));
            }
        }
        return new Result(false, "No WateringCan found");
    }

    public Result craftingShowRecipes() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        //todo if player not in home return error
        StringBuilder resultBuilder = new StringBuilder();
        ArrayList<CraftingRecipesEnums> craftingRecipes = App.getCurrentGame().getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl())
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

    public Result craftingCraft(String itemName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = getCurrentPlayer();

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
        return executeCrafting(currentPlayer, itemName, recipe);
    }

    private Player getCurrentPlayer() {
        Game currentGame = App.getCurrentGame();
        return currentGame.getPlayers().get(currentGame.getIndexPlayerinControl());
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
                for (Map.Entry<Item, Integer> inventoryEntry : player.getInventory().getItems().entrySet()) {
                    if (inventoryEntry.getKey().getCorrectName().equalsIgnoreCase(itemName)) {
                        totalCount += inventoryEntry.getValue();
                    }
                }
                if (totalCount < requiredCount) {
                    return false;
                }
            }
        }
        return true;
    }
//
//    private int countPlayerItems(Player player, String itemName) {
//        return player.getInventory().getItems().entrySet().stream()
//                .filter(entry -> entry.getKey().getCorrectName().equalsIgnoreCase(itemName))
//                .mapToInt(Map.Entry::getValue)
//                .sum();
//    }

    private Result executeCrafting(Player player, String itemName, CraftingRecipesEnums recipe) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        // مصرف مواد اولیه
        consumeMaterials(player, recipe);

        // افزودن آیتم ساخته شده
        addCraftedItem(player, itemName);

        // کاهش انرژی
        player.setEnergy(player.getEnergy() - 2);

        return new Result(true, itemName + " crafted successfully");
    }

    private void consumeMaterials(Player player, CraftingRecipesEnums recipe) {
        if (isFainted()) {
            System.out.println("You are fainted!");
        }
        for (Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            String itemName = entry.getKey().replaceAll(" ", "");
            int requiredCount = entry.getValue();

            if (itemName.equalsIgnoreCase("wood")) {
                player.setWood(player.getWood() - requiredCount);
            } else {
                int remaining = requiredCount;
                Iterator<Map.Entry<Item, Integer>> iterator = player.getInventory().getItems().entrySet().iterator();

                while (iterator.hasNext() && remaining > 0) {
                    Map.Entry<Item, Integer> inventoryEntry = iterator.next();
                    if (inventoryEntry.getKey().getCorrectName().equalsIgnoreCase(itemName)) {
                        int available = inventoryEntry.getValue();

                        if (available <= remaining) {
                            remaining -= available;
                            iterator.remove();
                        } else {
                            inventoryEntry.setValue(available - remaining);
                            remaining = 0;
                        }
                    }
                }
            }
        }
    }

    private void addCraftedItem(Player player, String itemName) {
        if (isFainted()) {
            System.out.println("You are fainted!");
        }
        player.getInventory().getItems().entrySet().stream()
            .filter(entry -> entry.getKey() instanceof CraftingItem &&
                entry.getKey().getClass().getSimpleName().equalsIgnoreCase(itemName))
            .findFirst()
            .ifPresentOrElse(
                entry -> entry.setValue(entry.getValue() + 1),
                () -> {
                    CraftingItem newItem = new CraftingItem(itemName);
                    newItem.setName(itemName);
                    player.getInventory().getItems().put(newItem, 1);
                }
            );
    }

    public Result placeItem(String name, String direction) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        name = name.replaceAll(" ", "").toLowerCase();
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord(player.getX(), player.getY());
        int dir_x = -1;
        int dir_y = -1;
        switch (direction.toLowerCase()) {
            case "n": {
                dir_x = 0;
                dir_y = -1;
                break;
            }
            case "ne": {
                dir_x = 1;
                dir_y = -1;
                break;
            }
            case "e": {
                dir_x = 1;
                dir_y = 0;
                break;
            }
            case "se": {
                dir_x = 1;
                dir_y = 1;
                break;
            }
            case "s": {
                dir_x = 0;
                dir_y = 1;
                break;
            }
            case "sw": {
                dir_x = -1;
                dir_y = 1;
                break;
            }
            case "w": {
                dir_x = -1;
                dir_y = 0;
                break;
            }
            case "nw": {
                dir_x = -1;
                dir_y = -1;
                break;
            }
            default: {
                break;
            }
        }
        Iterator<Map.Entry<Item, Integer>> iterator = player.getInventory().getItems().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Item, Integer> entry = iterator.next();
            Item item = entry.getKey();
            int count = entry.getValue();

            if (item.getCorrectName().equalsIgnoreCase(name)) {
                if (count > 1) {
                    player.getInventory().getItems().put(item, count - 1); // یکی کم کن
                } else {
                    iterator.remove(); // اگر فقط یکی بود، کل آیتم رو حذف کن
                }
                //todo place in map ObjectInside?
                tileCord.setX(dir_x + tileCord.getX());
                tileCord.setY(dir_y + tileCord.getY());
                if (App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).getInside() == null) {
                    App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY()).setItemInside(item);
                } else {
                    return new Result(false, "tile isn't empty for item");
                }
                return new Result(true, "craft " + name + " placed successfully");
            }
        }
        return new Result(false, "item " + name + " not found");
    }

    public Result cheatAddItem(String name, int count) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        name = name.replace(" ", "");
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Map<Item, Integer> inventory = player.getInventory().getItems();

        boolean addItem = false;
        boolean canAdd = (player.getInventory().getMaxQuantity() - player.getInventory().getTotalItemCount()) > 0; // این متد باید بررسی کند ظرفیت اینونتوری کافی هست یا نه

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
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).
                getCraftingRecipes().add(craftingItem.getCraftingItem());
            player.getInventory().addItem(craftingItem, count);
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
                    addItem = true;
                    break;
                case "pumpkinpie":
                    FoodCooking pumpkinPie = new FoodCooking();
                    pumpkinPie.setSellPrice(385);
                    pumpkinPie.setEnergy(225);
                    pumpkinPie.setName(FoodCookingEnums.PumpkinPie);
                    player.getInventory().addItem(pumpkinPie, count);
                    addItem = true;
                case "flower":
                    player.getInventory().addItem(new Flower(), count);
                    addItem = true;
                    break;
            }

        }

        //todo if name is Item
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

    public Result cookingRefrigerator(String pickOrPut, String itemname) throws ClassNotFoundException {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (pickOrPut.equalsIgnoreCase("put")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().getTotalItemCount() >= 9) {
                return new Result(false, "Refrigerator is full");
            }
            boolean found = false;
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item.getCorrectName().equals(itemname.toLowerCase().replace(" ", ""))) {
                    found = true;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().addItem(item, 1);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                    return new Result(true, "Successful");
                }
            }
            if (!found) {
                return new Result(false, "You don't have that item");
            }
        }
        if (pickOrPut.equalsIgnoreCase("pick")) {
            boolean found = false;
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator() == null) {
                return new Result(false, "You don't have that item");
            }
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().getItems().keySet()) {
                if (item.getCorrectName().equals(itemname.toLowerCase().replace(" ", ""))) {
                    found = true;
                    if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().isFull()) {
                        return new Result(false, "Inventory is full");
                    }
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(item, 1);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().removeItem(item, 1);
                    return new Result(true, "Successful");
                }
            }
            if (!found) {
                return new Result(false, "You don't have that item");
            }
        }
        return new Result(false, "");
    }

    public Result cookingShowRecipes() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingRecipes().isEmpty()) {
            return new Result(false, "You are cooked(0 cooking recipes)");
        } else {
            StringBuilder result = new StringBuilder();
            for (Cookingrecipe cookingrecipe : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingRecipes()) {
                result.append(cookingrecipe.getFood()).append(" recipe");
            }
            return new Result(true, result.toString());
        }
    }

    public Result cookingPrepare(String recipeName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() < 3) {
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
            for (Cookingrecipe cookingrecipe : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingRecipes()) {
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
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item.getCorrectName().equals(food.toLowerCase().replace(" ", ""))) {
                        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item) >= ingredients.get(food)) {
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
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item.getCorrectName().equals(food.toLowerCase().replace(" ", ""))) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, ingredients.get(food));
                    }
                }
            }

            FoodCooking foodCooking = targetCookingRecipe.letmecook(foodCookingEnums);
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(foodCooking, 1);
            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() - 3);
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

    public Result eat(String foodName) {

        if (isFainted()) {
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
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item instanceof FoodCooking) {
                    FoodCooking foodCooking = (FoodCooking) item;
                    if (foodCooking.getNamee() == foodCookingEnums) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(Math.min(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMaxEnergy(), App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() + foodCooking.getEnergy()));
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setFoodBuff(foodCooking.getBuff());
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
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof AllCrop) {
                        if (crop.getCorrectName().equalsIgnoreCase(foodName)) {
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(Math.min(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMaxEnergy(), App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() + crop.getEnergy()));
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                            return new Result(true, "You ate " + foodName);
                        }
                    }
                }
            }
        } else if (ArtisanGoodsEnums.isContain(foodName)) {
            ArtisanGoods artisanGoods = new ArtisanGoods(foodName);
            if (artisanGoods.getEnergyUsage() != 0) {
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof ArtisanGoods) {
                        if (artisanGoods.getCorrectName().equalsIgnoreCase(foodName)) {
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(Math.min(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMaxEnergy(), App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() + artisanGoods.getEnergyUsage()));
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
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

    public Result buyAnimal(String animal, String name) {
        return new Result(true, "");
    }

    public Result pet(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) {
            return new Result(false, "You can not pet that animal!");
        }
        if (!animal.isOutside()) {
            return new Result(false, "You can not pet that animal!(it is on its cage/Tavileh)!");
        }
        if (animal.getXofAnimal() == -1 && animal.getYofAnimal() == -1) {
            return new Result(false, "it is inside!");
        } else {
            int x = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getX();
            int y = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getY();
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

    public Result cheatSetFriendship(String animalName, int amount) {
        Animal animal = findAnimalByName(animalName);
        if (animal == null) {
            return new Result(false, "Your entered name does not exists between your animals!");
        } else if (amount > 1000) {
            return new Result(false, "The amount of friendship level can not be more than 1000!");
        } else {
            animal.setFriendship(amount);
            return new Result(true, "Your friendship between you and " + animalName + " set to " + amount + " successfully!");
        }

    }

    public Result animals() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
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

    public Result shepherdInAnimals(String animalName) {
        ArrayList<ArrayList<Kashi>> map = App.getCurrentGame().getMap();
        //errors
        boolean founded = MarniesRanchController.IsAnimalNameUnique(animalName);
        if (founded) {
            return new Result(false, "your animal name doesn't exist!");
        }
        // find that animal
        Animal animal = findAnimalByName(animalName);
        if (!(animal.isOutside())) {
            return new Result(false, "Your entered animal is inside!");
        } else {

            animal.setXofAnimal(-1);
            animal.setYofAnimal(-1);
            String where = animal.getWhereDoILive();
            if (animal instanceof CageAnimal) {
                switch (where) {
                    case "coop":
                        ArrayList<CageAnimal> cageAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getCageAnimals();
                        cageAnimals.add((CageAnimal) animal);
                        break;
                    case "bigcoop":
                        ArrayList<CageAnimal> bigCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
                        bigCoopAnimals.add((CageAnimal) animal);
                        break;
                    case "deluxecoop":
                        ArrayList<CageAnimal> deluxeCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
                        deluxeCoopAnimals.add((CageAnimal) animal);
                        break;
                    default:
                        break;
                }
            }
            if (animal instanceof TavilehAnimal) {
                switch (where) {
                    case "barn":
                        ArrayList<TavilehAnimal> tavilehAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
                        tavilehAnimals.add((TavilehAnimal) animal);
                        break;
                    case "bigbarn":
                        ArrayList<TavilehAnimal> bigBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals();
                        bigBarnAnimals.add((TavilehAnimal) animal);
                        break;
                    case "deluxebarn":
                        ArrayList<TavilehAnimal> deluxeBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                        deluxeBarnAnimals.add((TavilehAnimal) animal);
                        break;
                    default:
                        break;
                }
            }
            return new Result(true, "Your animal successfully moved to his Cage/Tavileh!");
        }
    }

    public Result shepherdOutAnimals(String animalName, int x, int y) {

        ArrayList<ArrayList<Kashi>> map = App.getCurrentGame().getMap();
        //errors
        boolean founded = MarniesRanchController.IsAnimalNameUnique(animalName);
        if (founded) {
            return new Result(false, "your animal name doesn't exist!");
        }
        // find that animal
        Animal animal = findAnimalByName(animalName);
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
                    ArrayList<CageAnimal> cageAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getCageAnimals();
                    cageAnimals.remove(animal);
                    break;
                case "bigcoop":
                    ArrayList<CageAnimal> bigCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
                    bigCoopAnimals.remove(animal);
                    break;
                case "deluxecoop":
                    ArrayList<CageAnimal> deluxeCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
                    deluxeCoopAnimals.remove(animal);
                    break;
                case "barn":
                    ArrayList<TavilehAnimal> barnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
                    barnAnimals.remove(animal);
                    break;
                case "bigbarn":
                    ArrayList<TavilehAnimal> bigBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals();
                    bigBarnAnimals.remove(animal);
                    break;
                case "deluxebarn":
                    ArrayList<TavilehAnimal> deluxeBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
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

    public Result feedHay(String animalName) {
        Animal animal = findAnimalByName(animalName);
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

    public Result sellAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) {
            return new Result(false, "Your entered animal was not found between your own animals! so you can not sell!");
        }
        if (animal.isOutside()) {
            return new Result(false, "You can not sell this animal because it is outside of coops/barns!");
        }
        int priceOfSelling = (int) (animal.getPrice() * ((animal.getFriendship() * 1000) + 0.3));
        int goldsOfPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold();
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(goldsOfPlayer + priceOfSelling);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals().remove(animal);
        String where = animal.getWhereDoILive();
        switch (where) {
            case "coop":
                ArrayList<CageAnimal> cageAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getCageAnimals();
                cageAnimals.remove(animal);
                break;
            case "bigcoop":
                ArrayList<CageAnimal> bigCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
                bigCoopAnimals.remove(animal);
                break;
            case "deluxecoop":
                ArrayList<CageAnimal> deluxeCoopAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
                deluxeCoopAnimals.remove(animal);
                break;
            case "barn":
                ArrayList<TavilehAnimal> barnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
                barnAnimals.remove(animal);
                break;
            case "bigbarn":
                ArrayList<TavilehAnimal> bigBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals();
                bigBarnAnimals.remove(animal);
                break;
            case "deluxebarn":
                ArrayList<TavilehAnimal> deluxeBarnAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                deluxeBarnAnimals.remove(animal);
                break;
            default:
                break;
        }
        return new Result(true, "You sell " + animal.getName() + " Successfully and " + priceOfSelling + " golds added to your golds!");
    }

    public Result fishing(String fishingPole) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Map<Item, Integer> inventory = player.getInventory().getItems();
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

    public Result artisanUse(String craftName, ArrayList<String> itemName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        craftName = craftName.replace("_", "");
        Inventory inventory = player.getInventory();
        Map<Item, Integer> inventoryItems = inventory.getItems();
        boolean isEnoughIngredient = false;
        boolean foundInInventory = false;
        String artisanName = "";
        ArrayList<ArtisanGoodsEnums> artisanGoodsPossible = new ArrayList<>();
        for (Item item : inventoryItems.keySet()) {
            if (item instanceof CraftingItem) {
                if (((CraftingItem) item).getCraftingItem().name().equalsIgnoreCase(craftName)) {
                    for (ArtisanGoodsEnums goods : ArtisanGoodsEnums.values()) {
                        if (goods.getProducer().name().equals(craftName)) {
                            artisanGoodsPossible.add(goods);
                            foundInInventory = true;
                        }
                    }
                }
            }
        }
        if (!foundInInventory) {
            return new Result(false, "craft not found in inventory");
        }
        for (ArtisanGoodsEnums goods : artisanGoodsPossible) {
            Set<String> mapKeys = new HashSet<>(goods.getIngredients().keySet());
            Set<String> listItems = new HashSet<>(itemName);
            if (mapKeys.equals(listItems)) {
                isEnoughIngredient = true;
                artisanName = goods.name();
                break;
            }
//            for(String ingredient: itemName){
//                for(String ingredient1 : goods.getIngredients().keySet()){
//                    if(goods.getIngredients().equals(ingredient)){
//                        itemCount++;
//                    }
//                    isEnoughIngredient = goods.getProducer().getIngredients().size() == itemCount || itemName.size() == itemCount;
//                    if(isEnoughIngredient){
//                        artisanName = goods.getProducer().name();
//                    }
//                }
//            }
//            if(isEnoughIngredient){
//                break;
//            }
//            itemCount=0;
        }
        if (isEnoughIngredient) {
            ArtisanGoods artisanGood = new ArtisanGoods(artisanName);
            artisanGood.startProcessing(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour(), App.getCurrentGame().getCurrentDateTime().getDay()));
            player.getArtisansInProduce().add(artisanGood);
            return new Result(true, "artisan: " + artisanName + " start to produce");
        }
        return new Result(false, "ingredients and crafting does not match");
    }

    public Result artisanGet(String artisanName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = player.getInventory();
        ArrayList<ArtisanGoods> artisanGoods = new ArrayList<>();
        artisanGoods = player.getArtisansInProduce();
        for (ArtisanGoods goods : artisanGoods) {
            if (goods.getArtisanGoods().name().equalsIgnoreCase(artisanName)) {
                if (goods.isProcessingDone(App.getCurrentGame().getCurrentDateTime())) {
                    inventory.addItem(goods, inventory.getItemQuantity(goods) + 1);
                    player.getArtisansInProduce().remove(goods);
                    return new Result(true, "artisan: " + artisanName + " added to artisans");
                } else {
                    return new Result(false, "artisan isn't ready.");
                }
            }
        }
        return new Result(false, "artisan not found");
    }

    public Result sell(String productName, String count) {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        try {
            if ((App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY()).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() - 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY()).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() - 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() + 1).getInside() instanceof Satl) ||
                (App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() - 1).getInside() instanceof Satl)) {

            } else {
                return new Result(false, "Please stand near a Satl");
            }
        } catch (Exception e) {
            return new Result(false, "Don't stand near the borders");
        }
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        boolean found = false;
        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
            if (item.getCorrectName().equalsIgnoreCase(productName.replace(" ", ""))) {
                found = true;
                if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                    return new Result(true, "Sold" + quantity + " of " + productName);
                } else {
                    return new Result(false, "Not enough items in your inventory");
                }
            }
        }
        if (!found) {
            return new Result(false, "You don't have that item");
        }

        return null;
    }

    public Result friendships() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    public Result talk(String username, String message) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Game currentGame = App.getCurrentGame();
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(username);
        int dx = Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = Math.abs(currentPlayer.getY() - targetPlayer.getY());

        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);

//        for (Friendship friendships :currentGame.getFriendships()){
//            for(Player player : App.getCurrentGame().getPlayers()){
//                if((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)){
//                    friendship = friendships;
//                }
//            }
//
//        }
        assert friendship != null;
        friendship.interact(20);

        // ذخیره پیام
        String addressedMessage = currentPlayer.getOwner().getUsername() + " : " + message + "(" +
            App.getCurrentGame().getCurrentDateTime().getDay() + " : " + App.getCurrentGame().getCurrentDateTime().getHour()
            + ")";
        friendship.getTalkHistory().add(addressedMessage);
        return new Result(true, "messege sent successfully");
    }

    public Result talkHistory(String username) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(username);
        Game currentGame = App.getCurrentGame();
        StringBuilder result = new StringBuilder();
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);
//        for (Friendship friendships :currentGame.getFriendships()){
//            for(Player player : App.getCurrentGame().getPlayers()){
//                if((!player.equals(currentPlayer)) && friendships.isBetween(currentPlayer, player)){
//                    friendship = friendships;
//                }
//            }
//        }
        assert friendship != null;
        for (String message : friendship.getTalkHistory()) {
            result.append(message).append("\n");
        }
        return new Result(true, result.toString());
    }

    public Result gift(String userName, int amount, String item) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        item = item.replaceAll(" ", "");
        Item gift = null;
        Gift newGift = null;
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Integer> itemInventory = inventory.getItems();
        Game currentGame = App.getCurrentGame();
        if (App.getCurrentGame().getPlayerByUsername(userName) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(userName);
        int dx = Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = Math.abs(currentPlayer.getY() - targetPlayer.getY());
        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);
        assert friendship != null;
        if (friendship.getLevel() == 0) {
            return new Result(false, "your friendship level is not enough to gift");
        }
        boolean found5 = false;
        for (Item items : itemInventory.keySet()) {
            if (items.getCorrectName().equalsIgnoreCase(item)) {
                if (itemInventory.get(items) >= amount) {
                    if (targetPlayer.getInventory().getMaxQuantity() - targetPlayer.getInventory().getTotalItemCount() < amount) {
                        return new Result(false, "target player does not have enough space in inventory");
                    }
                    itemInventory.put(items, itemInventory.get(items) - amount);
                    found5 = true;
                    gift = items;
                    newGift = new Gift(items, amount, currentPlayer, targetPlayer);
                } else {
                    return new Result(false, "You don't have enough items in your inventory");
                }
            }
        }
        if (!found5) {
            return new Result(false, "No such item in your inventory");
        } else {
            boolean found6 = false;
            App.getCurrentGame().getGifts().add(newGift);
            for (Item items : targetPlayer.getInventory().getItems().keySet()) {
                if (items.getCorrectName().equals(item)) {
                    found6 = true;
                    targetPlayer.getInventory().getItems().put(items, targetPlayer.getInventory().getItems().get(items) + amount);
                }
            }
            if (!found6) {
                targetPlayer.getInventory().getItems().put(gift, amount);
            }
            //todo if gift is flower increase leve
            return new Result(true, "gift sent successfully");
        }
    }

    private static Friendship getFriendship(Player targetPlayer, Player currentPlayer) {
        Game currentGame = App.getCurrentGame();
        for (Friendship friendships : currentGame.getFriendships()) {
            if (friendships.isBetween(currentPlayer, targetPlayer)) {
                return friendships;
            }
        }
        return null;
    }

    public Result giftList() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        result.append("Gift List: \n");
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    public Result giftRate(int giftNumber, int rate) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    public Result giftHistory(String name) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        result.append("Gift History of ").append(name).append(": \n");
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    public Result hug(String userName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (App.getCurrentGame().getPlayerByUsername(userName) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(userName);
        int dx = Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = Math.abs(currentPlayer.getY() - targetPlayer.getY());
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

    public Result flower(String username) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Item gift = null;
        Gift newGift = null;
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Integer> itemInventory = inventory.getItems();
        Game currentGame = App.getCurrentGame();
        if (App.getCurrentGame().getPlayerByUsername(username) == null) {
            return new Result(false, "Player not found");
        }
        Player targetPlayer = App.getCurrentGame().getPlayerByUsername(username);
        int dx = Math.abs(currentPlayer.getX() - targetPlayer.getX());
        int dy = Math.abs(currentPlayer.getY() - targetPlayer.getY());

        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "You are not near to talk");
        }
        Friendship friendship = null;
        friendship = getFriendship(targetPlayer, currentPlayer);
        assert friendship != null;
        if (friendship.getLevel() != 2) {
            return new Result(false, "You can send flower in level 2");
        }
        boolean found5 = false;
        for (Item items : itemInventory.keySet()) {
            if (items.getCorrectName().equalsIgnoreCase("flower")) {
                if (itemInventory.get(items) >= 1) {
                    if (targetPlayer.getInventory().getMaxQuantity() - targetPlayer.getInventory().getTotalItemCount() < 1) {
                        return new Result(false, "target player does not have enough space in inventory");
                    }
                    if (itemInventory.get(items) - 1 == 0) {
                        itemInventory.remove(items);
                    } else {
                        itemInventory.put(items, itemInventory.get(items) - 1);
                    }
                    found5 = true;
                } else {
                    return new Result(false, "You don't have flower in your inventory");
                }
            }
        }
        if (!found5) {
            return new Result(false, "No such flower is in your inventory");
        } else {
            boolean found6 = false;
            targetPlayer.getInventory().addItem(new Flower(), 1);

            if (friendship.getLevel() < 3) {
                friendship.setLevel(3);
            }
            return new Result(true, "flower sent successfully");
        }
    }

    public Result askMarriage(String userName, String ringName) {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Player destinationPlayer = App.getCurrentGame().getPlayerByUsername(userName);

        if (destinationPlayer == null) {
            return new Result(false, "Player not found");
        }
        int dx = Math.abs(currentPlayer.getX() - destinationPlayer.getX());
        int dy = Math.abs(currentPlayer.getY() - destinationPlayer.getY());

        if (!(dx <= 1 && dy <= 1)) {
            return new Result(false, "Be more near than her/him");
        }

        if (Objects.equals(destinationPlayer.getOwner().getGender(), currentPlayer.getOwner().getGender())) {

            return new Result(false, "Your genders should be different!");
        }
        if (currentPlayer.getOwner().getGender().equalsIgnoreCase("female") && currentPlayer.getOwner().getGender().equalsIgnoreCase("male")) {
            return new Result(false, "Wait! You are female and he is male!!!!");
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

    public Result response(String acceptOrReject, String userName) {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(targetProposal.getRing(), 1);
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

    public void showMyMarriageProposals() {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
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

    /// / find animal by name
    public Animal findAnimalByName(String animalName) {
        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public void startTrade() {

    }

    private boolean validNPCPlace(NPCEnums npcEnums) {
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        return (App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() + 1).getInside() instanceof NPC &&
            ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY() - 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY()).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY()).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY()).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY()).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() + 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() + 1).get(currentPlayer.getY() - 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() + 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() + 1).getInside()).getName() == npcEnums) ||

            (App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() - 1).getInside() instanceof NPC &&
                ((NPC) App.getCurrentGame().getMap().get(currentPlayer.getX() - 1).get(currentPlayer.getY() - 1).getInside()).getName() == npcEnums);


    }

    public Result meetNPC(String npcName) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        int friendshipLevel = -1;
        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLevel = friendship.getFriendshipLevel();
            }
        }
        switch (npcName.toUpperCase()) {
            case "SEBASTIAN":
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

            case "ABIGAIL":
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

            case "HARVEY":
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

            case "LEAH":
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

            case "ROBIN":
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

    public Result giftNPC(String npcName, String item) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        switch (npcName.toUpperCase()) {
            case "SEBASTIAN":
//                try {
//                    Class<?> clazz = Class.forName(item);
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (clazz.isInstance(itm) && !(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                } catch (Exception e) {
//                    if (item.replace(" ", "").equalsIgnoreCase("pumpkinpie")) {
//                        boolean found = false;
//                        for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                            if (itm instanceof FoodCooking && ((FoodCooking) itm).getName() == FoodCookingEnums.pumpkinpie) {
//                                found = true;
//                                currentPlayer.getInventory().removeItem(itm, 1);
//                                if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                    App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                    for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                        if (friendship.getPlayer() == currentPlayer) {
//                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                            return new Result(true, "Gifted successfully");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (!found) {
//                            return new Result(false, "No item found with that name");
//                        }
//                    } else {
//                        if (item.replace(" ", "").equalsIgnoreCase("pizza")) {
//                            boolean found = false;
//                            for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                                if (itm instanceof FoodCooking && ((FoodCooking) itm).getName() == FoodCookingEnums.pizza) {
//                                    found = true;
//                                    currentPlayer.getInventory().removeItem(itm, 1);
//                                    if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                        App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                        for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                            if (friendship.getPlayer() == currentPlayer) {
//                                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                                return new Result(true, "Gifted successfully");
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if (!found) {
//                                return new Result(false, "No item found with that name");
//                            }
//                        } else {
//                            return new Result(false, "This item in not effective");
//                        }
//                    }
//                }
//                //pashm-->??????
//                //pumkinpie-->FoodCooking
//                //pizza--?FoodCooking
                boolean found = false;
                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
                    if (itm.getCorrectName().equalsIgnoreCase(item.replace(" ", ""))) {
                        found = true;
                        if (!(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
                            if (item.toLowerCase().replace(" ", "").equalsIgnoreCase("wool") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("pumpkinpie") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("pizza")) {
                                currentPlayer.getInventory().removeItem(itm, 1);
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
                                    return new Result(false, "NPC got " + item);
                                } else {
                                    return new Result(false, "NPC doesn't like " + item);
                                }
                            }
                        } else {
                            return new Result(false, "You can't gift tools");
                        }
                    }
                }
                if (!found) {
                    return new Result(false, "You don't have that item in your inventory");
                }
                break;

            case "ABIGAIL":
//                try {
//                    Class<?> clazz = Class.forName(item);
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (clazz.isInstance(itm) && !(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                } catch (Exception e) {
//                    if (item.replace(" ", "").equalsIgnoreCase("stone")) {
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (itm instanceof Stone && ((FoodCooking) itm).getName() == FoodCookingEnums.pumpkinpie) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                    } else {
//                        if (item.replace(" ", "").equalsIgnoreCase("iron")) {
//                            boolean found = false;
//                            for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                                if (itm instanceof Mineral
//                                        && ((Mineral) itm).getType() == ForagingMineralsEnums.Iron) {
//                                    found = true;
//                                    currentPlayer.getInventory().removeItem(itm, 1);
//                                    if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                        App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                        for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                            if (friendship.getPlayer() == currentPlayer) {
//                                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                                return new Result(true, "Gifted successfully");
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if (!found) {
//                                return new Result(false, "No item found with that name");
//                            }
//                        } else {
//                            if (item.replace(" ", "").equalsIgnoreCase("coffeebean")) {
//                                boolean found = false;
//                                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                                    if (itm instanceof AllCrop
//                                            && ((AllCrop) itm).getType() == AllCropsEnums.CoffeeBean) {
//                                        found = true;
//                                        currentPlayer.getInventory().removeItem(itm, 1);
//                                        if (!App.getCurrentGame().getNPCSEBASTIAN().isGiftedToday()) {
//                                            App.getCurrentGame().getNPCSEBASTIAN().setGiftedToday(true);
//                                            for (Friendship friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
//                                                if (friendship.getPlayer() == currentPlayer) {
//                                                    friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                                    return new Result(true, "Gifted successfully");
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                                if (!found) {
//                                    return new Result(false, "No item found with that name");
//                                }
//                            } else {
//                                return new Result(false, "This item in not effective");
//                            }
//                        }
//                    }
//                }
//                //Stone-->stone
//                //kani ahan-->mineral
//                //AllCrops-->Coffee
                boolean found1 = false;
                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
                    if (itm.getCorrectName().equalsIgnoreCase(item.replace(" ", ""))) {
                        found1 = true;
                        if (!(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
                            if (item.toLowerCase().replace(" ", "").equalsIgnoreCase("stone") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("iron") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("coffee")) {
                                currentPlayer.getInventory().removeItem(itm, 1);
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
                                    return new Result(false, "NPC got " + item);
                                } else {
                                    return new Result(false, "NPC doesn't like " + item);
                                }
                            }
                        } else {
                            return new Result(false, "You can't gift tools");
                        }
                    }
                }
                if (!found1) {
                    return new Result(false, "You don't have that item in your inventory");
                }
                break;

            case "HARVEY":
//                try {
//                    Class<?> clazz = Class.forName(item);
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (clazz.isInstance(itm) && !(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCHARVEY().isGiftedToday()) {
//                                App.getCurrentGame().getNPCHARVEY().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                } catch (Exception e) {
//                    if (item.replace(" ", "").equalsIgnoreCase("coffeebean")) {
//                        boolean found = false;
//                        for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                            if (itm instanceof AllCrop
//                                    && ((AllCrop) itm).getType() == AllCropsEnums.CoffeeBean) {
//                                found = true;
//                                currentPlayer.getInventory().removeItem(itm, 1);
//                                if (!App.getCurrentGame().getNPCHARVEY().isGiftedToday()) {
//                                    App.getCurrentGame().getNPCHARVEY().setGiftedToday(true);
//                                    for (Friendship friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
//                                        if (friendship.getPlayer() == currentPlayer) {
//                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                            return new Result(true, "Gifted successfully");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (!found) {
//                            return new Result(false, "No item found with that name");
//                        }
//                    } else {
//                        return new Result(false, "This item in not effective");
//                    }
//                }
//                //AllCrops-->Coffee
//                //torshi-->???????
//                //sharab-->???????
                boolean found2 = false;
                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
                    if (itm.getCorrectName().equalsIgnoreCase(item.replace(" ", ""))) {
                        found2 = true;
                        if (!(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
                            if (item.toLowerCase().replace(" ", "").equalsIgnoreCase("coffee") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("torshi") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("wine")) {
                                currentPlayer.getInventory().removeItem(itm, 1);
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
                                    return new Result(false, "NPC got " + item);
                                } else {
                                    return new Result(false, "NPC doesn't like " + item);
                                }
                            }
                        } else {
                            return new Result(false, "You can't gift tools");
                        }
                    }
                }
                if (!found2) {
                    return new Result(false, "You don't have that item in your inventory");
                }
                break;

            case "LEAH":
//                try {
//                    Class<?> clazz = Class.forName(item);
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (clazz.isInstance(itm) && !(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCLEAH().isGiftedToday()) {
//                                App.getCurrentGame().getNPCLEAH().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                } catch (Exception e) {
//                    if (item.equalsIgnoreCase("salad")) {
//                        boolean found = false;
//                        for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                            if (itm instanceof FoodCooking && ((FoodCooking) itm).getName() == FoodCookingEnums.Salad) {
//                                found = true;
//                                currentPlayer.getInventory().removeItem(itm, 1);
//                                if (!App.getCurrentGame().getNPCLEAH().isGiftedToday()) {
//                                    App.getCurrentGame().getNPCLEAH().setGiftedToday(true);
//                                    for (Friendship friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
//                                        if (friendship.getPlayer() == currentPlayer) {
//                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                            return new Result(true, "Gifted successfully");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (!found) {
//                            return new Result(false, "No item found with that name");
//                        }
//                    } else {
//                        if (item.equalsIgnoreCase("grape")) {
//                            boolean found = false;
//                            for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                                if ((itm instanceof ForagingCrop && ((ForagingCrop) itm).getType() == ForagingCropsEnums.Grape) ||
//                                        (itm instanceof AllCrop && ((AllCrop) itm).getType() == AllCropsEnums.Grape)) {
//                                    found = true;
//                                    currentPlayer.getInventory().removeItem(itm, 1);
//                                    if (!App.getCurrentGame().getNPCLEAH().isGiftedToday()) {
//                                        App.getCurrentGame().getNPCLEAH().setGiftedToday(true);
//                                        for (Friendship friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
//                                            if (friendship.getPlayer() == currentPlayer) {
//                                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                                return new Result(true, "Gifted successfully");
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if (!found) {
//                                return new Result(false, "No item found with that name");
//                            }
//                        } else {
//                            return new Result(false, "This item in not effective");
//                        }
//                    }
//                }
//                //salad-->Foodcooking
//                //grape -->ForagingCrop,AllCrop
//                //sharab-->???????
//                break;
                boolean found3 = false;
                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
                    if (itm.getCorrectName().equalsIgnoreCase(item.replace(" ", ""))) {
                        found3 = true;
                        if (!(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
                            if (item.toLowerCase().replace(" ", "").equalsIgnoreCase("salad") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("grape") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("wine")) {
                                currentPlayer.getInventory().removeItem(itm, 1);
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
                                    return new Result(false, "NPC got " + item);
                                } else {
                                    return new Result(false, "NPC doesn't like " + item);
                                }
                            }
                        } else {
                            return new Result(false, "You can't gift tools");
                        }
                    }
                }
                if (!found3) {
                    return new Result(false, "You don't have that item in your inventory");
                }
                break;
            case "ROBIN":
//                try {
//                    Class<?> clazz = Class.forName(item);
//                    boolean found = false;
//                    for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                        if (clazz.isInstance(itm) && !(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
//                            found = true;
//                            currentPlayer.getInventory().removeItem(itm, 1);
//                            if (!App.getCurrentGame().getNPCROBIN().isGiftedToday()) {
//                                App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
//                                for (Friendship friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
//                                    if (friendship.getPlayer() == currentPlayer) {
//                                        friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 50);
//                                        return new Result(true, "Gifted successfully");
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        return new Result(false, "No item found with that name");
//                    }
//                } catch (Exception e) {
//                    if (item.equalsIgnoreCase("spaghetti")) {
//                        boolean found = false;
//                        for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                            if (itm instanceof FoodCooking && ((FoodCooking) itm).getName() == FoodCookingEnums.spaghetti) {
//                                found = true;
//                                currentPlayer.getInventory().removeItem(itm, 1);
//                                if (!App.getCurrentGame().getNPCROBIN().isGiftedToday()) {
//                                    App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
//                                    for (Friendship friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
//                                        if (friendship.getPlayer() == currentPlayer) {
//                                            friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                            return new Result(true, "Gifted successfully");
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        if (!found) {
//                            return new Result(false, "No item found with that name");
//                        }
//                    } else {
//                        if (item.equalsIgnoreCase("iron")) {
//                            boolean found = false;
//                            for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
//                                if (itm instanceof Mineral && ((Mineral) itm).getType() == ForagingMineralsEnums.Iron) {
//                                    found = true;
//                                    currentPlayer.getInventory().removeItem(itm, 1);
//                                    if (!App.getCurrentGame().getNPCROBIN().isGiftedToday()) {
//                                        App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
//                                        for (Friendship friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
//                                            if (friendship.getPlayer() == currentPlayer) {
//                                                friendship.setFriendshipLevel(friendship.getFriendshipLevel() + 200);
//                                                return new Result(true, "Gifted successfully");
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if (!found) {
//                                return new Result(false, "No item found with that name");
//                            }
//                        } else {
//                            return new Result(false, "This item in not effective");
//                        }
//                    }
//                }
//                //spaghetti-->FoodCooking
//                //choob-->???????
//                //shemsh ahan-->mineral
//                break;
                boolean found4 = false;
                for (Item itm : currentPlayer.getInventory().getItems().keySet()) {
                    if (itm.getCorrectName().equalsIgnoreCase(item.replace(" ", ""))) {
                        found4 = true;
                        if (!(itm instanceof Axe) && !(itm instanceof Hoe) && !(itm instanceof MilkPail) && !(itm instanceof Pickaxe) && !(itm instanceof Scythe) && !(itm instanceof Shear) && !(itm instanceof WateringCan)) {
                            if (item.toLowerCase().replace(" ", "").equalsIgnoreCase("spaghetti") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("wood") ||
                                item.toLowerCase().replace(" ", "").equalsIgnoreCase("iron")) {
                                currentPlayer.getInventory().removeItem(itm, 1);
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
                                    currentPlayer.getInventory().removeItem(itm, 1);
                                    App.getCurrentGame().getNPCROBIN().setGiftedToday(true);
                                    return new Result(false, "NPC got " + item);
                                } else {
                                    currentPlayer.getInventory().removeItem(itm, 1);
                                    return new Result(false, "NPC doesn't like " + item);
                                }
                            }
                        } else {
                            return new Result(false, "You can't gift tools");
                        }
                    }
                }
                if (!found4) {
                    return new Result(false, "You don't have that item in your inventory");
                }
                break;
            default:
                //System.out.println(npcName);
                return new Result(false, "Invalid NPC name.");
        }
        return null;
    }

    public Result friendshipList() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append("SEBASTIAN: ");
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append("ABIGAIL: ");
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append("HARVEY: ");
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append("LEAH: ");
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                result.append("ROBIN: ");
                result.append("Score: ").append(friendship.getFriendshipLevel()).append(" Level: ").append(friendship.getFriendshipLevel() / 200);
                result.append("\n");
            }
        }
        return new Result(true, result.toString());
    }

    public Result questsList() {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        StringBuilder result = new StringBuilder();
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        int friendshipLVL = -1;

        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append("SEBASTIAN\n");
        for (Object object : App.getCurrentGame().getNPCSEBASTIAN().getQuests().keySet()) {
            NPCItem npcItem = App.getCurrentGame().getNPCSEBASTIAN().getQuests().get(object);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                if (npcItem.getRequiredLevel() == 2) {
                    if (App.getCurrentGame().getCurrentDateTime().getHour() >= 12 && App.getCurrentGame().getCurrentDateTime().getHour() <= 24) {
                        result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                    }
                } else {
                    result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                }
            }
        }

        for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append("ABIGAIL\n");
        for (Object object : App.getCurrentGame().getNPCABIGAIL().getQuests().keySet()) {
            NPCItem npcItem = App.getCurrentGame().getNPCABIGAIL().getQuests().get(object);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                if (npcItem.getRequiredLevel() == 2) {
                    if (App.getCurrentGame().getCurrentWeather() == WeatherEnum.RAIN) {
                        result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                    }
                } else {
                    result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                }
            }
        }

        for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append("HARVEY\n");
        for (Object object : App.getCurrentGame().getNPCHARVEY().getQuests().keySet()) {
            NPCItem npcItem = App.getCurrentGame().getNPCHARVEY().getQuests().get(object);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                if (npcItem.getRequiredLevel() == 2) {
                    if (App.getCurrentGame().getCurrentSeason() == Seasons.Spring) {
                        result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                    }
                } else {
                    result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                }
            }
        }

        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append("LEAH\n");
        for (Object object : App.getCurrentGame().getNPCLEAH().getQuests().keySet()) {
            NPCItem npcItem = App.getCurrentGame().getNPCLEAH().getQuests().get(object);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                if (npcItem.getRequiredLevel() == 2) {
                    if (App.getCurrentGame().getCurrentSeason() == Seasons.Fall || App.getCurrentGame().getCurrentWeather() == WeatherEnum.STORM) {
                        result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                    }
                } else {
                    result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                }
            }
        }

        for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
            if (friendship.getPlayer() == currentPlayer) {
                friendshipLVL = friendship.getFriendshipLevel() / 200;
                break;
            }
        }
        result.append("ROBIN\n");
        for (Object object : App.getCurrentGame().getNPCROBIN().getQuests().keySet()) {
            NPCItem npcItem = App.getCurrentGame().getNPCROBIN().getQuests().get(object);
            if (friendshipLVL >= npcItem.getRequiredLevel()) {
                if (npcItem.getRequiredLevel() == 2) {
                    if (App.getCurrentGame().getCurrentDateTime().getDay() >= 20 || App.getCurrentGame().getCurrentDateTime().getDay() <= 25) {
                        result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                    }
                } else {
                    result.append(npcItem.getRequiredLevel() + " " + npcItem.getQuantity() + " " + object.toString() + "\n");
                }
            }
        }

        return new Result(true, result.toString());
    }

    public Result questsFinish(int index) {
        if (isFainted()) {
            return new Result(false, "You are fainted!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (validNPCPlace(NPCEnums.SEBASTIAN)) {
            System.out.println("SEBASTIAN");

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
                            if (currentPlayer.getInventory().getItems().get(item) < npcItem.getQuantity()) {
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
            if (validNPCPlace(NPCEnums.ABIGAIL)) {
                System.out.println("ABIGAIL");

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
                                if (currentPlayer.getInventory().getItems().get(item) < npcItem.getQuantity()) {
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
                if (validNPCPlace(NPCEnums.HARVEY)) {
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
                                    if (currentPlayer.getInventory().getItems().get(item) < npcItem.getQuantity()) {
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
                    if (validNPCPlace(NPCEnums.LEAH)) {
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
                                        if ((currentPlayer.getInventory().getItems().get(item) < npcItem.getQuantity()) && !(object.getCorrectName().equalsIgnoreCase("wood") && currentPlayer.getWood() >= npcItem.getQuantity())) {
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
                        if (validNPCPlace(NPCEnums.ROBIN)) {
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
                                            if (currentPlayer.getInventory().getItems().get(item) < npcItem.getQuantity() && !(object.getCorrectName().equalsIgnoreCase("wood") && currentPlayer.getWood() >= npcItem.getQuantity())) {
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

    @Override
    public void menuEnter(String menuName) {
        Game game = new Game();
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (isFainted()) {
            System.out.println("You are fainted!");
        }
        //from registermenu we can move to loginmenu
        menuName = menuName.toLowerCase();
//        switch (menuName) {
//            case "blacksmithmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 16) {
//                    if ((currentPlayer.getX() >= game.getBlackSmithTopLeftx() && currentPlayer.getX() <= game.getBlackSmithTopLeftx() + game.getBlackSmithWidth()) &&
//                            (currentPlayer.getY() >= game.getBlackSmithTopLefty() && currentPlayer.getY() <= game.getBlackSmithTopLefty() + game.getBlackSmithHeight())) {
//                        App.setCurrentMenu(Menu.BlacksmithMenu);
//                        System.out.println("You are now in BlacksmithMenu!");
//                    } else {
//                        System.out.println("You have to be in the Market");
//                    }
//                } else {
//                    System.out.println("BlackSmith is opened between 9 and 16");
//                }
//                break;
//            case "carpentersshopmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 20) {
//                    App.setCurrentMenu(Menu.CarpentersshopMenu);
//                    System.out.println("You are now in CarpentersshopMenu!");
//                } else {
//                    System.out.println("CarpentersShop is opened between 9 and 20");
//                }
//                break;
//            case "fishshopmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 17) {
//                    App.setCurrentMenu(Menu.FishshopMenu);
//                    System.out.println("You are now in FishshopMenu!");
//                } else {
//                    System.out.println("FishShop is opened between 9 and 17");
//                }
//                break;
//            case "jojamartmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 23) {
//                    App.setCurrentMenu(Menu.JojamartMenu);
//                    System.out.println("You are now in JojamartMenu!");
//                } else {
//                    System.out.println("JojamartShop is opened between 9 and 23");
//                }
//                break;
//            case "marniesranchmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 16) {
//                    App.setCurrentMenu(Menu.MarniesranchMenu);
//                    System.out.println("You are now in MarniesranchMenu!");
//                } else {
//                    System.out.println("MarniesranchShop is opened between 9 and 16");
//                }
//                break;
//            case "pirresgeneralstoremenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 9 && App.getCurrentGame().getCurrentDateTime().getHour() <= 17) {
//
//                    App.setCurrentMenu(Menu.PirresgeneralstoreMenu);
//                    System.out.println("You are now in PirresgeneralstoreMenu!");
//                } else {
//                    System.out.println("PirresgeneralStore is opened between 9 and 17");
//                }
//                break;
//            case "thestardropsaloonmenu":
//                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 12 && App.getCurrentGame().getCurrentDateTime().getHour() <= 24) {
//
//                    App.setCurrentMenu(Menu.ThestardropsaloonMenu);
//                    System.out.println("You are now in ThestardropsaloonMenu!");
//                } else {
//                    System.out.println("TheStardrposaloon+ is opened between 12 and 24");
//                }
//                break;
//            default:
//                System.out.println("Invalid menu");
//                break;
//        }

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
            "blacksmithmenu", new int[]{game.getBlackSmithTopLeftx(), game.getBlackSmithTopLefty(), game.getBlackSmithWidth(), game.getBlackSmithHeight()},
            "carpentersshopmenu", new int[]{game.getCarpentersShopTopLeftx(), game.getCarpentersShopTopLefty(), game.getCarpentersShopWidth(), game.getCarpentersShopHeight()},
            "fishshopmenu", new int[]{game.getFishShopTopLeftx(), game.getFishShopTopLefty(), game.getFishShopWidth(), game.getFishShopHeight()},
            "jojamartmenu", new int[]{game.getJojoMartTopLeftx(), game.getJojoMartTopLefty(), game.getJojoMartWidth(), game.getJojoMartHeight()},
            "marniesranchmenu", new int[]{game.getMarniesRanchTopLeftx(), game.getMarniesRanchTopLefty(), game.getMarniesRanchWidth(), game.getMarniesRanchHeight()},
            "pirresgeneralstoremenu", new int[]{game.getPierresGeneralStoreTopLeftx(), game.getPierresGeneralStoreTopLefty(), game.getPierresGeneralStoreWidth(), game.getPierresGeneralStoreHeight()},
            "thestardropsaloonmenu", new int[]{game.getTheStardropSaloonTopLeftx(), game.getTheStardropSaloonTopLefty(), game.getTheStardropSaloonWidth(), game.getTheStardropSaloonHeight()}
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

}
