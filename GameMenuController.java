package Controller;

import Model.*;
import enums.Menu;
import enums.Seasons;

import java.util.Scanner;

public class GameMenuController implements ShowCurrentMenu {
    public void exit() {

    }

    public Result gameNew(String command, Scanner scanner) {
        String[] usernames = new String[command.split("\\s+").length - 3];
        for (int i = 3; i < command.split("\\s+").length; i++) {
            usernames[i - 3] = command.split("\\s+")[i];
        }
        //check username regex
        for (String username : usernames) {
            Boolean check = false;
            for (User user : App.getUsers_List()) {
                if (username.equals(user.getUsername())) {
                    check = true;
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
        App.setCurrentGame(NewGame);
        for (Player player : App.getCurrentGame().getPlayers()) {
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

            switch (number) {
                case 1:
                    player.getMyFarm().createMap1();
                    break;
                case 2:
                    player.getMyFarm().createMap2();
                    break;
                case 3:
                    player.getMyFarm().createMap3();
                    break;
                case 4:
                    player.getMyFarm().createMap4();
                    break;
                default:
                    System.out.println("Unexpected error occurred.");
                    break;
            }
        }
        App.getGames().add(NewGame);
        return new Result(true, "Game Created");
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
        }
    }


    public void time() {

    }

    public void date() {

    }

    public void dateTime() {

    }

    public Result cheatAdvanceTime(int hour) {
        return new Result(true, "");
    }

    public Result cheatAdvanceDate(int hour) {
        return new Result(true, "");
    }

    public void season() {

    }

    public Result cheatThor(int x, int y) {
        return new Result(true, "");
    }

    public void weather() {

    }

    public void weatherForecast() {

    }

    public Result cheatWeather(String Type) {
        return new Result(true, "");
    }

    public Result greenHouseBiuld() {
        return new Result(true, "");
    }

    public Result walk(int x, int y) {
        return new Result(true, "");
    }

    public void printMap(int x, int y, int size) {

    }

    public void helpReadingMap() {

    }

    public void energyShow() {

    }

    public Result energySet(int value) {
        return new Result(true, "");
    }

    public void energyUnlimited() {

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

    public Result plant(String seed, String direction) {
        return new Result(true, "");
    }

    public Result showPlant(int x, int y) {
        return new Result(true, "");
    }

    public Result fertilize(String fertilizer, String direction) {
        return new Result(true, "");
    }

    public void howMuchWater() {

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

    public Result cookingRefrigerator(String pickOrPut, String item) {
        return new Result(true, "");
    }

    public void cookingShowRecipes() {

    }

    public Result cookingPrepare(String recipeName) {
        return new Result(true, "");
    }

    public Result eat(String foodName) {
        return new Result(true, "");
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

    public Result cheatAdd(int count) {
        return new Result(true, "");
    }

    public Result sell(String productName, int count) {
        return new Result(true, "");
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
