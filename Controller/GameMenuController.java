package Controller;

import Model.*;
import Model.Cookingrecipe;
import Model.Items.*;
import enums.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMenuController implements ShowCurrentMenu {
    public void exit() {

    }

    public static int getFieldValue(Class<?> gameClass, Object gameInstance, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = gameClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.getInt(gameInstance);
    }

    public Result gameNew(String command, Scanner scanner) throws NoSuchFieldException, IllegalAccessException {
        String[] usernames = new String[command.split("\\s+").length - 3];
        if (command.split("\\s+").length - 3 >= 0)
            System.arraycopy(command.split("\\s+"), 3, usernames, 0, command.split("\\s+").length - 3);
        //check username regex
        for (String username : usernames) {
            boolean check = false;
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
        for (String username : usernames) {
            Boolean check = true;
            for (Game game : App.getGames()) {
                for (Player player : game.getPlayers()) {
                    if (player.getOwner().getUsername().equals(username)) {
                        return new Result(false, "Username is already in a game");
                    }
                }
            }
        }
        Game NewGame = new Game();
        NewGame.setCreator(App.getCurrentUser());
        NewGame.setIndexPlayerinControl(0);
        Player player1 = new Player();
        player1.setOwner(App.getCurrentUser());
        NewGame.getPlayers().add(player1);
        for (String username : usernames) {
            Player player = new Player();
            for (User user : App.getUsers_List()) {
                if (username.equals(user.getUsername())) {
                    player.setOwner(user);
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
        App.setCurrentGame(NewGame);
        App.getGames().add(NewGame);
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            Player player = App.getCurrentGame().getPlayers().get(i);
            System.out.println("Choosing map for: " + player.getOwner().getUsername());

            int number = -1;
            while (true) {
                if (scanner.hasNextInt()) {
                    number = scanner.nextInt();
                    scanner.nextLine();

                    if (number >= 1 && number <= 4) {
                        break;
                    } else {
                        System.out.println("Error: Please enter a number between 1 and 4.");
                    }
                } else {
                    System.out.println("Error: Invalid input. Please enter a **number** between 1 and 4.");
                    scanner.nextLine();
                }
            }

            Class<?> gameClass = Game.class;
            Game gameInstance = new Game();


            int topLeftx = getFieldValue(gameClass, gameInstance, "Player" + i + "TopLeftx");
            int topLefty = getFieldValue(gameClass, gameInstance, "Player" + i + "TopLefty");
            switch (i) {
                case 0:
                    player.getMyFarm().createMap1(topLeftx, topLefty);
                    break;
                case 1:
                    player.getMyFarm().createMap2(topLeftx, topLefty);
                    break;
                case 2:
                    player.getMyFarm().createMap3(topLeftx, topLefty);
                    break;
                case 3:
                    player.getMyFarm().createMap4(topLeftx, topLefty);
                    break;
                default:
                    System.out.println("Unexpected error occurred.");
                    break;
            }
        }
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

    public Result gameMap(int mapNumber) {
        return new Result(true, "");
    }

    public Result exitGame() {
        if (App.getCurrentGame() == null) {
            return new Result(false, "Game not started");
        }
        if (App.getCurrentGame().getCreator().getUsername().equals(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getUsername())) {
            //save game
            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.GameMenu);
            return new Result(true, "Game Exited");
        } else {
            return new Result(false, "You can't exit the game");
        }
    }

    public Result voteTerminateGame(Scanner scanner) {
        int total_neg = 0;
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (!player.getOwner().getUsername().equals(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getUsername())) {
                System.out.println("Enter your vote to terminate game(+/-): ");
                String vote = scanner.nextLine();
                if (vote.equals("-")) {
                    total_neg++;
                }
            }
        }
        if (total_neg > 0) {
            //delete game
            App.setCurrentGame(null);
            App.setCurrentMenu(Menu.GameMenu);
            return new Result(true, "Game Exited");
        } else {
            return new Result(false, "Looks like someone want to still play!");
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

    public void nextTurn() {
        App.getCurrentGame().setIndexPlayerinControl(App.getCurrentGame().getIndexPlayerinControl() + 1);
        if (App.getCurrentGame().getIndexPlayerinControl() == App.getCurrentGame().getPlayers().size()) {
            App.getCurrentGame().setIndexPlayerinControl(0);
            //            start new day
            if (App.getCurrentGame().getCurrentDateTime().getHour() == 23) {
                App.getCurrentGame().setCurrentDateTime(new DateTime(0, App.getCurrentGame().getCurrentDateTime().getDay() + 1));

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
                    Thor thor = new Thor();
                    int farmWide = 50;
                    int farmHeight = 50;
                    int[][] strikePosition = new int[3][2];
                    strikePosition = getRandomPlaces(3, farmWide, farmHeight);
//                                        ArrayList<ArrayList<Kashi>> map= App.getCurrentGame().getMap();
//                    Kashi kashi1 = new Kashi();
//                    kashi1.setX(strikePosition[0][0]);
//                    kashi1.setY(strikePosition[0][1]);
//                    Kashi kashi2 = new Kashi();
//                    kashi2.setX(strikePosition[1][0]);
//                    kashi2.setY(strikePosition[1][1]);
//                    Kashi kashi3 = new Kashi();
//                    kashi3.setX(strikePosition[2][0]);
//                    kashi3.setY(strikePosition[2][1]);
//                    ArrayList<Kashi> kashiList = new ArrayList<>();
//                    kashiList.add(kashi1);
//                    kashiList.add(kashi2);
//                    kashiList.add(kashi3);
//                    thor.setKhordeh(kashiList);
                }
            }
            //            just + hour
            else {
                //TODO
                App.getCurrentGame().setCurrentDateTime(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour() + 1,
                        App.getCurrentGame().getCurrentDateTime().getDay()));
            }
        }
    }

    public Result time() {
        return new Result(true, String.valueOf(App.getCurrentGame().getCurrentDateTime().getHour()));
    }

    public Result date() {
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
                "-" +
                App.getCurrentGame().getCurrentDateTime().getDay();
        return new Result(true, stringBuilder);
    }

    public Result dateTime() {
        String stringBuilder = App.getCurrentGame().getCurrentDateTime().getSeason() +
                "-" +
                App.getCurrentGame().getCurrentDateTime().getDay() +
                "-" +
                App.getCurrentGame().getCurrentDateTime().getHour();
        return new Result(true, stringBuilder);

    }

    public Result dayOfWeek() {
        int dayOfWeek = App.getCurrentGame().getCurrentDateTime().getDay() % 7;
        return switch (dayOfWeek) {
            case 0 -> new Result(true, "Sunday");
            case 1 -> new Result(true, "Monday");
            case 2 -> new Result(true, "Tuesday");
            case 3 -> new Result(true, "Wednesday");
            case 4 -> new Result(true, "Thursday");
            case 5 -> new Result(true, "Friday");
            case 6 -> new Result(true, "Saturday");
            default -> null;
        };
    }

    public Result cheatAdvanceTime(int hour) {
        if (hour < 0) {
            return new Result(false, "cheatCode: Invalid hour");
        }
        int newHour = hour + App.getCurrentGame().getCurrentDateTime().getHour();
        int hourOfDay = newHour % 24;
        int newDay = (newHour / 24) + App.getCurrentGame().getCurrentDateTime().getDay();
        App.getCurrentGame().setCurrentDateTime(new DateTime(hourOfDay, newDay));
        return new Result(true, "cheatCode: Hour changed! New Hour: " + hourOfDay + " New Day: " + newDay);
    }

    public Result cheatAdvanceDate(int day) {
        if (day < 0) {
            return new Result(false, "cheatCode: Invalid day");
        }
        int newDay = day + App.getCurrentGame().getCurrentDateTime().getDay();
        App.getCurrentGame().setCurrentDateTime(new DateTime(day, newDay));
        return new Result(true, "cheatCode: Day changed! New Day: " + newDay);
    }

    public Result season() {
        return new Result(true, App.getCurrentGame().getCurrentDateTime().getSeason());
    }

    public Result cheatThor(int x, int y) {
        Thor thor = new Thor();
        Cord cord = new Cord(x, y);
        //        thor.setKhordeh();
        return new Result(true, "cheatCode: Thor changed! Thor strike at (" + x + "," + y + ")");
    }

    public Result weather() {
        return new Result(true, "Weather : " + App.getCurrentGame().getCurrentWeather().toString());
    }

    public Result weatherForecast() {
        return new Result(true, "Tomorrow Weather : " + App.getCurrentGame().getWeather().getFirst().toString());

    }

    public Result cheatWeather(String Type) {
        Deque<WeatherEnum> weather = new ArrayDeque<>();
        switch (Type) {
            case "Sunny":
                weather.addFirst(WeatherEnum.SUNNY);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Sunny");
            case "Rain":
                weather.addFirst(WeatherEnum.RAIN);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Rain");
            case "Storm":
                weather.addFirst(WeatherEnum.STORM);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Storm");
            case "Snow":
                weather.addFirst(WeatherEnum.SNOW);
                App.getCurrentGame().setWeather(weather);
                return new Result(true, "cheatCode: Snow");
            default:
                return new Result(false, "cheatCode: Invalid Weather");
        }
    }

    public Result greenHouseBiuld() {
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() > 500
                && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() > 1000) {
            App.getCurrentGame().getPlayers()
                    .get(App.getCurrentGame().getIndexPlayerinControl())
                    .getMyFarm().getMyGreenHouse().setStatus(true);

        }
        return new Result(true, "Green House Build successfully");
    }

    public Result walk(int x, int y) {
        return new Result(true, "");
    }

    public void printMap(int x, int y, int size) {
        for (int i = x; i < size + x; i++) {
            for (int j = y; j < size + y; j++) {
                //                if (App.getCurrentGame().getMap().getInside(i,j) == "Tree") {
                //                    System.out.print("\u001B[38;2;255;100;50m" + App.getCurrentGame().getMap().getInside(i, j)+ "\u001B[0m");
                //                } else if (.....) {
                //
                //                }
                //                System.out.println("\u001B[38;2;255;100;50m" + "متن با رنگ RGB" + "\u001B[0m");
            }
            System.out.println();
        }
    }

    public void helpReadingMap() {
        System.out.println("Tree -> T");
        System.out.println("GreenHose -> G");
        System.out.println("Quarry -> Q");
        //        ...
    }

    public void energyShow() {
        System.out.println("Energy: " +
                App.getCurrentGame().getPlayers().get(App.getCurrentGame()
                        .getIndexPlayerinControl()).getEnergy());
    }

    public Result energySet(int value) {
        if (value < 0) {
            return new Result(false, "CheatCode: Invalid energy");
        }
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(value);
        return new Result(true, "CheatCode: Energy set to " + value);
    }

    public void energyUnlimited() {
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl()).setUnlimitedEnergy(true);
        System.out.println("**Energy unlimited**");

    }

    public void inventoryShow() {

    }

    public Result inventoryTrash(String name, int number) {
        return new Result(true, "");
    }

    public Result toolEquip(String toolName) {
        return new Result(true, "");
    }

    public void toolsShowCurrent() {

    }

    public void toolShowAvailable() {

    }

    public Result toolsUpgrade(String toolName) {
        return new Result(true, "");
    }

    public Result toolsUse(String direction) {
        return new Result(true, "");
    }

    public Result craftInfo(String carftName) {
        return new Result(true, "");
    }

    public Result plant(String source, String direction) {
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
        //MixedSeed
        if (source.toLowerCase().replace(" ", "").equals("mixedseeds")) {
            AllCrop allCrop = new AllCrop();
            try {
                boolean valid = false;
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof MixedSeed) {
                        valid = true;
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                    }
                }
                if (!valid) {
                    return new Result(false, "You don't have " + source + " in your inventory");
                }
                Random random = new Random();
                List<MixedSeedsEnums> seasonalCrops = Arrays.stream(MixedSeedsEnums.values())
                        .filter(crop -> crop.isAllowedIn(App.getCurrentGame().getCurrentSeason()))
                        .toList();
                MixedSeedsEnums mse = seasonalCrops.get(random.nextInt(seasonalCrops.size()));
                allCrop.setSourceMixedSeedEnum(mse);
                allCrop.initilizeCrop(mse);
                Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
                Kashi kashi = App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY());
                kashi.setInside(allCrop);
                return new Result(true, "Plant successfully placed");
            } catch (Exception ex) {
            }
        } else {
            //ForagingSeed
            if (Arrays.asList(ForagingSeedsEnums.values()).contains(ForagingSeedsEnums.valueOf(source.replace(" ", "")))) {
                AllCrop allCrop1 = new AllCrop();
                try {
                    ForagingSeedsEnums foragingSeedsEnums = ForagingSeedsEnums.valueOf(source.replace(" ", ""));
                    boolean valid = false;
                    for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                        if (item instanceof ForagingSeed) {
                            ForagingSeed foragingSeed = (ForagingSeed) item;
                            if (foragingSeed.getType() == foragingSeedsEnums) {
                                valid = true;
                                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                                break;
                            }
                        }
                    }
                    if (!valid) {
                        return new Result(false, "You don't have " + source + " in your inventory");
                    }
                    allCrop1.setSourceForagingSeedEnum(foragingSeedsEnums);
                    allCrop1.initilizeCrop(foragingSeedsEnums);
                    Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
                    Kashi kashi = App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY());
                    kashi.setInside(allCrop1);
                    return new Result(true, "Plant successfully placed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                //AllTree
                if (Arrays.asList(TreeSeedEnums.values()).contains(TreeSeedEnums.valueOf(source.replace(" ", "")))) {
                    AllTree allTree = new AllTree();
                    try {
                        TreeSeedEnums allTreesEnums = TreeSeedEnums.valueOf(source.replace(" ", ""));
                        boolean valid = false;
                        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                            if (item instanceof TreeSeed) {
                                TreeSeed treeSeed = (TreeSeed) item;
                                if (treeSeed.getType() == allTreesEnums) {
                                    valid = true;
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                                    break;
                                }
                            }
                        }
                        if (!valid) {
                            return new Result(false, "You don't have " + source + " in your inventory");
                        }
                        allTree.setSource(allTreesEnums);
                        allTree.initilizeCrop(allTreesEnums);
                        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
                        Kashi kashi = App.getCurrentGame().getMap().get(currentPlayer.getX()).get(currentPlayer.getY());
                        kashi.setInside(allTree);
                        return new Result(true, "Tree successfully placed");
                    } catch (Exception e) {

                    }
                } else {
                    return new Result(false, "invalid source");
                }
            }
        }
        return null;
    }

    public Result showPlant(int x, int y) {
        //have to get the kashi from map though
        Kashi targetKashi = new Kashi();
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
                return new Result(false, "No plant on this kashi");
            }
        }
    }

    public Result fertilize(String fertilizer, String direction) {
        return new Result(true, "");
    }

    public Result howMuchWater() {
        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
            if (item instanceof WateringCan) {
                return new Result(true, "Capacity: " + (((WateringCan) item).getCapacity()));
            }
        }
        return new Result(false, "No WateringCan found");
    }

    public void craftingShowRecipes() {

    }

    public Result craftingCraft(String name) {
        return new Result(true, "");
    }

    public Result placeItem(String name, String direction) {
        return new Result(true, "");
    }

    public Result cheatAddItem(String name, int count) {
        return new Result(true, "");
    }

    public Result cookingRefrigerator(String pickOrPut, String itemname) throws ClassNotFoundException {
        if (pickOrPut.equalsIgnoreCase("put")) {
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                try {
                    Class<?> clazz = Class.forName(itemname);
                    if (clazz.isInstance(item)) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().addItem(item, 1);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                        break;
                    }
                    return new Result(true, "Successfully put " + itemname);
                } catch (Exception e) {
                    return new Result(false, "Wrong item name");
                }
            }
        }
        if (pickOrPut.equalsIgnoreCase("pick")) {
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().getItems().keySet()) {
                try {
                    Class<?> clazz = Class.forName(itemname);
                    if (clazz.isInstance(item)) {
                        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().isFull()) {
                            return new Result(false, "Inventory is full");
                        }
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(item, 1);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCottage().getMyRefrigerator().removeItem(item, 1);
                        break;
                    }
                    return new Result(true, "Successfully pick " + itemname);
                } catch (Exception e) {
                    return new Result(false, "Wrong item name");
                }
            }
        }
        return null;
    }

    public Result cookingShowRecipes() {
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingrecipes().isEmpty()) {
            return new Result(false, "You are cooked");
        } else {
            StringBuilder result = new StringBuilder();
            for (Cookingrecipe cookingrecipe : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingrecipes()) {
                result.append(cookingrecipe.toString());
                result.append("\n");
            }
            return new Result(true, result.toString());
        }
    }

    public Result cookingPrepare(String recipeName) {
        Set<String> FOOD_ENUMS = new HashSet<>();
        for (FoodCookingEnums food : FoodCookingEnums.values()) {
            FOOD_ENUMS.add(food.name().toLowerCase());
        }
        if (isValidFood(recipeName, FOOD_ENUMS)) {
            FoodCookingEnums foodCookingEnums = FoodCookingEnums.valueOf(recipeName);
            Cookingrecipe targetCookingRecipe = null;
            for (Cookingrecipe cookingrecipe : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getCookingrecipes()) {
                if (cookingrecipe.getFood() == foodCookingEnums) {
                    targetCookingRecipe = cookingrecipe;
                }
            }
            if (targetCookingRecipe == null) {
                return new Result(false, "You don't have the recipe");
            }
            HashMap<Food, Integer> ingredients = foodCookingEnums.getIngredients();

            for (Food food : ingredients.keySet()) {
                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().hasItem(food) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItemQuantity(food) > ingredients.get(food)) {

                } else {
                    return new Result(false, "Not enough ingredients");
                }
            }
            for (Food food : ingredients.keySet()) {
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(food, ingredients.get(food));
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
        return FOOD_ENUMS.contains(input.trim().toLowerCase());
    }

    public Result eat(String foodName) {
        Set<String> FOOD_ENUMS = new HashSet<>();
        for (FoodCookingEnums food : FoodCookingEnums.values()) {
            FOOD_ENUMS.add(food.name().toLowerCase());
        }
        FoodCookingEnums foodCookingEnums = FoodCookingEnums.valueOf(foodName);
        if (isValidFood(foodName, FOOD_ENUMS)) {
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item instanceof FoodCooking) {
                    FoodCooking foodCooking = (FoodCooking) item;
                    if (foodCooking.getName() == foodCookingEnums) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setEnergy(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getEnergy() + foodCooking.getEnergy());
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, 1);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setFoodBuff(foodCooking.getBuff());
                        return new Result(true, "You ate " + foodName);
                    }
                }
            }
        } else {
            return new Result(false, "Invalid food");
        }
        return null;
    }

    public Result build(String buildingName, int x, int y) {
        return new Result(true, "");
    }

    public Result buyAnimal(String animal, String name) {
        return new Result(true, "");
    }

    public Result pet(String name) {
        return new Result(true, "");
    }

    public Result cheatSetFriendship(String animalName, int amount) {
        return new Result(true, "");
    }

    public void animals() {

    }

    public Result shepherdAnimals(String animalName, int x, int y) {
        return new Result(true, "");
    }

    public Result feedHay(String animalName) {
        return new Result(true, "");
    }

    public void produces() {

    }

    public Result collectProduce(String name) {
        return new Result(true, "");
    }

    public Result sellAnimal(String name) {
        return new Result(true, "");
    }

    public Result fishing(String fishingPole) {
        return new Result(true, "");
    }

    public Result artisanUse(String artisanName, String item1Name) {
        return new Result(true, "");
    }

    public Result artisanGet(String artisanName) {
        return new Result(true, "");
    }

    public Result sell(String productName, String count) {
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        try {
            AllCropsEnums allCropsEnums = AllCropsEnums.valueOf(productName);
            boolean found = false;
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item instanceof AllCrop && ((AllCrop) item).getType() == allCropsEnums) {
                    found = true;
                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                        return new Result(true, "");
                    } else {
                        return new Result(false, "Not enough items in your inventory");
                    }
                }
            }
            if (!found) {
                return new Result(false, "No such item in your inventory");
            }
        } catch (Exception e) {
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item instanceof Fertilizer && ((Fertilizer) item).getName().equals(productName)) {
                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                        return new Result(true, "");
                    } else {
                        return new Result(false, "Not enough items in your inventory");
                    }
                }
            }
            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                if (item instanceof Food && ((Food) item).getName().equals(productName)) {
                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                        return new Result(true, "");
                    } else {
                        return new Result(false, "Not enough items in your inventory");
                    }
                }
            }
            try {
                FoodCookingEnums foodCookingEnums = FoodCookingEnums.valueOf(productName);
                boolean found1 = false;
                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                    if (item instanceof FoodCooking && ((FoodCooking) item).getName() == foodCookingEnums) {
                        found1 = true;
                        if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                            return new Result(true, "");
                        } else {
                            return new Result(false, "Not enough items in your inventory");
                        }
                    }
                }
                if (!found1) {
                    return new Result(false, "No such item in your inventory");
                }
            } catch (Exception ee) {
                try {
                    ForagingCropsEnums foragingCropsEnums = ForagingCropsEnums.valueOf(productName);
                    boolean found2 = false;
                    for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                        if (item instanceof ForagingCrop && ((ForagingCrop) item).getType() == foragingCropsEnums) {
                            found2 = true;
                            if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                return new Result(true, "");
                            } else {
                                return new Result(false, "Not enough items in your inventory");
                            }
                        }
                    }
                    if (!found2) {
                        return new Result(false, "No such item in your inventory");
                    }
                } catch (Exception eee) {
                    try {
                        ForagingSeedsEnums foragingSeedsEnums = ForagingSeedsEnums.valueOf(productName);
                        boolean found3 = false;
                        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                            if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == foragingSeedsEnums) {
                                found3 = true;
                                if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                    return new Result(true, "");
                                } else {
                                    return new Result(false, "Not enough items in your inventory");
                                }
                            }
                        }
                        if (!found3) {
                            return new Result(false, "No such item in your inventory");
                        }
                    } catch (Exception eeee) {
                        if (productName.toLowerCase().equals("hay")) {
                            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                if (item instanceof Hay) {
                                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                        return new Result(true, "");
                                    } else {
                                        return new Result(false, "Not enough items in your inventory");
                                    }
                                }
                            }
                            return new Result(false, "No such item in your inventory");
                        }
                        for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                            if (item instanceof MarketProducts && ((MarketProducts) item).getName().equals(productName)) {
                                if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                    return new Result(true, "");
                                } else {
                                    return new Result(false, "Not enough items in your inventory");
                                }
                            }
                        }
                        if (productName.toLowerCase().equals("milkpail")) {
                            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                if (item instanceof MilkPail) {
                                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                        return new Result(true, "");
                                    } else {
                                        return new Result(false, "Not enough items in your inventory");
                                    }
                                }
                            }
                            return new Result(false, "No such item in your inventory");
                        }
                        try {
                            ForagingMineralsEnums mineral = ForagingMineralsEnums.valueOf(productName);
                            boolean found4 = false;
                            for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                if (item instanceof Mineral && ((Mineral) item).getType() == mineral) {
                                    found4 = true;
                                    if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                        return new Result(true, "");
                                    } else {
                                        return new Result(false, "Not enough items in your inventory");
                                    }
                                }
                            }
                            if (!found4) {
                                return new Result(false, "No such item in your inventory");
                            }
                        } catch (Exception eeeee) {
                            if (productName.toLowerCase().equals("shear")) {
                                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                    if (item instanceof Shear) {
                                        if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                            return new Result(true, "");
                                        } else {
                                            return new Result(false, "Not enough items in your inventory");
                                        }
                                    }
                                }
                                return new Result(false, "No such item in your inventory");
                            }
                            try {
                                TreeSeedEnums treeSeedEnums = TreeSeedEnums.valueOf(productName);
                                boolean found5 = false;
                                for (Item item : App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().keySet()) {
                                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == treeSeedEnums) {
                                        found5 = true;
                                        if (quantity <= App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItems().get(item)) {
                                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getSatl().addItem(item, quantity);
                                            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().removeItem(item, quantity);
                                            return new Result(true, "");
                                        } else {
                                            return new Result(false, "Not enough items in your inventory");
                                        }
                                    }
                                }
                                if (!found5) {
                                    return new Result(false, "No such item in your inventory");
                                }
                            } catch (Exception eeeeee) {
                                return new Result(false, "Invalid product");
                            }
                        }
                    }
                }
            }
        }

        //AllCrop+
        //AnimalProduct-
        //TODO

        //CraftingItem-
        //TODO

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
        return null;
    }

    public void friendships() {

    }

    public Result talk(String username, String message) {
        return new Result(true, "");
    }

    public Result talkHistory(String username) {
        return new Result(true, "");
    }

    public Result gift(String userName, int amount, String item) {
        return new Result(true, "");
    }

    public void giftList() {

    }

    public Result giftRate(int giftNumber, int rate) {
        return new Result(true, "");
    }

    public Result giftHistory(String name) {
        return new Result(true, "");
    }

    public Result hug(String userName) {
        return new Result(true, "");
    }

    public Result flower(String userName) {
        return new Result(true, "");
    }

    public Result askMarriage(String userName, String ring) {
        return new Result(true, "");
    }

    public Result response(String acceptOrReject, String userName) {
        return new Result(true, "");
    }

    public void startTrade() {

    }


}
