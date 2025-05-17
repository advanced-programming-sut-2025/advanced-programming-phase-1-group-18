package View;

import Controller.GameMenuController;
import enums.GameMenuCommands;
import enums.LoginMenuCommands;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

import Controller.TradeMenuController;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private final TradeMenuController tradeController = new TradeMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (GameMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (GameMenuCommands.GameNew.getMather(input) != null) {
            try {
                System.out.println(controller.gameNew(input, scanner));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (GameMenuCommands.ExitGame.getMather(input) != null) {
            System.out.println(controller.exitGame());
        } else if (GameMenuCommands.VoteTerminateGame.getMather(input) != null) {
            System.out.println(controller.voteTerminateGame(scanner));
        } else if (GameMenuCommands.NextTurn.getMather(input) != null) {
            controller.nextTurn();
        } else if (GameMenuCommands.Plant.getMather(input) != null) {
            System.out.println(controller.plant(GameMenuCommands.Plant.getMather(input).group("seed"), GameMenuCommands.Plant.getMather(input).group("direction")));
        } else if (GameMenuCommands.ShowPlant.getMather(input) != null) {
            System.out.println(controller.showPlant(Integer.parseInt(GameMenuCommands.ShowPlant.getMather(input).group(1)), Integer.parseInt(GameMenuCommands.ShowPlant.getMather(input).group(2))));
        } else if (GameMenuCommands.HowMuchWater.getMather(input) != null) {
            System.out.println(controller.howMuchWater());
        } else if (GameMenuCommands.Fertilize.getMather(input) != null) {
            System.out.println(controller.fertilize(GameMenuCommands.Fertilize.getMather(input).group(1), GameMenuCommands.Fertilize.getMather(input).group(2)));
        } else if (GameMenuCommands.TIME.getMather(input) != null) {
            System.out.println(controller.time());
        } else if (GameMenuCommands.DATE.getMather(input) != null) {
            System.out.println(controller.date());
        } else if (GameMenuCommands.DATETIME.getMather(input) != null) {
            System.out.println(controller.dateTime());
        } else if (GameMenuCommands.DAY_OF_WEEK.getMather(input) != null) {
            System.out.println(controller.dayOfWeek());
        } else if (GameMenuCommands.CHEAT_ADVANCE_DATE.getMather(input) != null) {
            System.out.println(controller.cheatAdvanceDate(Integer.parseInt(GameMenuCommands.CHEAT_ADVANCE_DATE.
                    getMather(input).group(1).trim())));
        } else if (GameMenuCommands.CHEAT_ADVANCE_TIME.getMather(input) != null) {
            System.out.println(controller.cheatAdvanceTime(Integer.parseInt(GameMenuCommands.CHEAT_ADVANCE_TIME.getMather(input).group(1))));
        } else if (GameMenuCommands.SEASON.getMather(input) != null) {
            System.out.println(controller.season());
        } else if (GameMenuCommands.CHEAT_THOR.getMather(input) != null) {
            System.out.println(controller.cheatThor(Integer.parseInt(GameMenuCommands.CHEAT_THOR.getMather(input).group(1)),
                    Integer.parseInt(GameMenuCommands.CHEAT_THOR.getMather(input).group(2))));
        } else if (GameMenuCommands.WEATHER.getMather(input) != null) {
            System.out.println(controller.weather());
        } else if (GameMenuCommands.WEATHER_FORECAST.getMather(input) != null) {
            System.out.println(controller.weatherForecast());
        } else if (GameMenuCommands.CHEAT_WEATHER_SET.getMather(input) != null) {
            System.out.println(controller.cheatWeather(GameMenuCommands.CHEAT_WEATHER_SET.getMather(input).group(1)));
        } else if (GameMenuCommands.GREENHOUSE_BUILD.getMather(input) != null) {
            System.out.println(controller.greenHouseBiuld());
        } else if (GameMenuCommands.Walk.getMather(input) != null) {
            System.out.println(controller.walk(Integer.parseInt(GameMenuCommands.Walk.getMather(input).group(1)), Integer.parseInt(GameMenuCommands.Walk.getMather(input).group(2))));
        } else if (GameMenuCommands.PrintMap.getMather(input) != null) {
            controller.printMap(Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(1)),
                    Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(2)),
                    Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(3)),
                    Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(4)));

        } else if (GameMenuCommands.HelpReadingMap.getMather(input) != null) {
            controller.helpReadingMap();
        } else if (GameMenuCommands.EnergyShow.getMather(input) != null) {
            controller.energyShow();
        } else if (GameMenuCommands.EnergySet.getMather(input) != null) {
            System.out.println(controller.energySet(Integer.parseInt(GameMenuCommands.EnergySet.getMather(input).group(1))));
        } else if (GameMenuCommands.EnergyUnlimited.getMather(input) != null) {
            controller.energyUnlimited();
        } else if (GameMenuCommands.INVENTORYSHOW.getMather(input) != null) {
            controller.inventoryShow();
        } else if (GameMenuCommands.INVENTORYTRASH.getMather(input) != null) {
            String item = GameMenuCommands.INVENTORYTRASH.getMather(input).group(1);
            int number = Integer.parseInt(GameMenuCommands.INVENTORYTRASH.getMather(input).group(2));
            System.out.println(controller.inventoryTrash(item, number));
        } else if (GameMenuCommands.TOOLEQUIP.getMather(input) != null) {
            String name = GameMenuCommands.TOOLEQUIP.getMather(input).group(1);
            System.out.println(controller.toolEquip(name));
        } else if (GameMenuCommands.TOOLSSHOWCURRENT.getMather(input) != null) {
            System.out.println(controller.toolsShowCurrent());
        } else if (GameMenuCommands.TOOLSSHOWAVAILABLE.getMather(input) != null) {
            System.out.println(controller.toolShowAvailable());
        } else if (GameMenuCommands.TOOLSUSE.getMather(input) != null) {
            String direction = GameMenuCommands.TOOLSUSE.getMather(input).group(1);
            System.out.println(controller.toolsUse(direction));
        } else if (GameMenuCommands.COOKINGREFRIGERATOR.getMather(input) != null) {
            String action = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(1);
            String item = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(2);
            try {
                System.out.println(controller.cookingRefrigerator(action, item));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (GameMenuCommands.COOKINGSHOWRECIPES.getMather(input) != null) {
            System.out.println(controller.cookingShowRecipes());
        } else if (GameMenuCommands.COOKINGPREPARE.getMather(input) != null) {
            System.out.println(controller.cookingPrepare(GameMenuCommands.COOKINGPREPARE.getMather(input).group(1)));
        } else if (GameMenuCommands.EAT.getMather(input) != null) {
            System.out.println(controller.eat(GameMenuCommands.EAT.getMather(input).group(1)));
        } else if (GameMenuCommands.SELL.getMather(input) != null) {
            System.out.println(controller.sell(GameMenuCommands.SELL.getMather(input).group(1), GameMenuCommands.SELL.getMather(input).group(2)));
        } else if (GameMenuCommands.MEETNPC.getMather(input) != null) {
            System.out.println(controller.meetNPC(GameMenuCommands.MEETNPC.getMather(input).group(1)));
        } else if (GameMenuCommands.GIFTNPC.getMather(input) != null) {
            System.out.println(controller.giftNPC(GameMenuCommands.GIFTNPC.getMather(input).group(1), GameMenuCommands.GIFTNPC.getMather(input).group(2)));
        } else if (GameMenuCommands.FRIENDSHIPNPCLIST.getMather(input) != null) {
            System.out.println(controller.friendshipList());
        } else if (GameMenuCommands.QUESTSLIST.getMather(input) != null) {
            System.out.println(controller.questsList());
        } else if (GameMenuCommands.QUESTSFINISH.getMather(input) != null) {
            System.out.println(controller.questsFinish(Integer.parseInt(GameMenuCommands.QUESTSFINISH.getMather(input).group(1))));
        } else if (GameMenuCommands.FriendShip.getMather(input) != null) {
            System.out.println(controller.friendships());
        } else if (GameMenuCommands.Talk.getMather(input) != null) {
            System.out.println(controller.talk(GameMenuCommands.Talk.getMather(input).group(1),
                    GameMenuCommands.Talk.getMather(input).group(2)));
        } else if (GameMenuCommands.TalkHistory.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.TalkHistory.getMather(input);
            System.out.println(controller.talkHistory(matcher.group(1)));
        } else if (GameMenuCommands.Gift.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.Gift.getMather(input);
            System.out.println(controller.gift(
                    matcher.group(1),
                    Integer.parseInt(matcher.group(3)),
                    matcher.group(2)));
        } else if (GameMenuCommands.GiftList.getMather(input) != null) {
            System.out.println(controller.giftList());
        } else if (GameMenuCommands.GiftRate.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.GiftRate.getMather(input);
            System.out.println(controller.giftRate(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))));
        } else if (GameMenuCommands.GiftHistory.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.GiftHistory.getMather(input);
            System.out.println(controller.giftHistory(matcher.group(1)));
        } else if (GameMenuCommands.Hug.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.Hug.getMather(input);
            System.out.println(controller.hug(matcher.group(1)));
        } else if (GameMenuCommands.Flower.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.Flower.getMather(input);
            System.out.println(controller.flower(matcher.group(1)));
        } else if (GameMenuCommands.CRAFT_INFO.getMather(input) != null) {
            System.out.println(controller.craftInfo(GameMenuCommands.CRAFT_INFO.getMather(input).group(1)));
        } else if (GameMenuCommands.SHOW_RECIPES.getMather(input) != null) {
            System.out.println(controller.craftingShowRecipes());
        } else if (GameMenuCommands.CRAFT_ITEM.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.CRAFT_ITEM.getMather(input);
            System.out.println(controller.craftingCraft(matcher.group(1).trim()));
        } else if (GameMenuCommands.PLACE_ITEM.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.PLACE_ITEM.getMather(input);
            String name = matcher.group(1).trim();
            String destination = matcher.group(2).trim();
            System.out.println(controller.placeItem(name, destination));
        } else if (GameMenuCommands.CHEAT_ADD_ITEM.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.CHEAT_ADD_ITEM.getMather(input);
            String name = matcher.group(1).trim();
            int count = Integer.parseInt(matcher.group(2));
            System.out.println(controller.cheatAddItem(name, count));
        } else if (GameMenuCommands.Fishing.getMather(input) != null) {
            System.out.println(controller.fishing(GameMenuCommands.Fishing.getMather(input).group(1)));
        } else if (GameMenuCommands.ArtisanUse.getMather(input) != null) {
            String[] parts = input.split("\\s+");
            if (parts.length >= 3 && parts[0].equals("artisan") && parts[1].equals("use")) {
                String artisanName = parts[2];
                ArrayList<String> items = new ArrayList<>();
                for (int i = 3; i < parts.length; i++) {
                    items.add(parts[i]);
                }
                System.out.println(controller.artisanUse(artisanName, items));
            }
        } else if (LoginMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = LoginMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (GameMenuCommands.ArtisanGet.getMather(input) != null) {
            System.out.println(controller.artisanGet(GameMenuCommands.ArtisanGet.getMather(input).group(1)));

        } else if (GameMenuCommands.WHOAMI.getMather(input) != null) {
            System.out.println(controller.whoAmI());
        } else if (GameMenuCommands.ASKMARRIAGE.getMather(input) != null) {
            String username = GameMenuCommands.ASKMARRIAGE.getMather(input).group(1);
            String ring = GameMenuCommands.ASKMARRIAGE.getMather(input).group(2);
            System.out.println(controller.askMarriage(username, ring));
        } else if (GameMenuCommands.RESPOND.getMather(input) != null) {
            String acceptOrReject = GameMenuCommands.RESPOND.getMather(input).group(1);
            String username = GameMenuCommands.RESPOND.getMather(input).group(2);
            System.out.println(controller.response(acceptOrReject, username));
        } else if (GameMenuCommands.SHOWMYMARRIAGEPROPOSALS.getMather(input) != null) {
            controller.showMyMarriageProposals();
        } else if (GameMenuCommands.SHOW_RECIPES.getMather(input) != null) {
            controller.showMyMarriageProposals();
        } else if (GameMenuCommands.STARTTRADE.getMather(input) != null) {
            tradeController.startTrade();
        } else if (GameMenuCommands.TRADING.getMather(input) != null) {
            String username = GameMenuCommands.TRADING.getMather(input).group(1);
            String type = GameMenuCommands.TRADING.getMather(input).group(2);
            String item = GameMenuCommands.TRADING.getMather(input).group(3);
            int amount = Integer.parseInt(GameMenuCommands.TRADING.getMather(input).group(4));
            String price = (GameMenuCommands.TRADING.getMather(input).group(5));
            String targetItem = GameMenuCommands.TRADING.getMather(input).group(6);
            String targetAmount = GameMenuCommands.TRADING.getMather(input).group(7);
            if (price == null && targetItem != null && targetAmount != null) {
                int finalAmount = Integer.parseInt(targetAmount);
                System.out.println(tradeController.tradeWithItem(username, type, item, amount, targetItem, finalAmount));
            }
            if (targetItem == null && price != null) {
                int finalPrice = Integer.parseInt(price);
                System.out.println(tradeController.tradeWithMoney(username, type, item, amount, finalPrice));
            } else {
                System.out.println("You can not put both!");
            }
        } else if (GameMenuCommands.TRADERESPONSE.getMather(input) != null) {
            int id = Integer.parseInt(GameMenuCommands.TRADERESPONSE.getMather(input).group(2));
            String acceptOrReject = GameMenuCommands.TRADERESPONSE.getMather(input).group(1);
            System.out.println(tradeController.tradeResponse(acceptOrReject, id));
        } else if (GameMenuCommands.TRADEHISTORY.getMather(input) != null) {
            tradeController.tradeHistory();
        }
        // new Trade
        else if (GameMenuCommands.TRADELIST.getMather(input) != null) {
            tradeController.tradeList();
        }
        //animalssssss ..............
        else if (GameMenuCommands.SHEPHERDOUT.getMather(input) != null) {
            String nameOfAnimal = GameMenuCommands.SHEPHERDOUT.getMather(input).group(1);
            int xOfAnimalPlacing = Integer.parseInt(GameMenuCommands.SHEPHERDOUT.getMather(input).group(2));
            int yOfAnimalPlacing = Integer.parseInt(GameMenuCommands.SHEPHERDOUT.getMather(input).group(3));
            System.out.println(controller.shepherdOutAnimals(nameOfAnimal, xOfAnimalPlacing, yOfAnimalPlacing));
        } else if (GameMenuCommands.SHEPHERDIN.getMather(input) != null) {
            String nameofAnimal = GameMenuCommands.SHEPHERDIN.getMather(input).group(1);
            System.out.println(controller.shepherdInAnimals(nameofAnimal));
        } else if (GameMenuCommands.FEEDHAY.getMather(input) != null) {
            String nameOfAnimal = GameMenuCommands.FEEDHAY.getMather(input).group(1);
            System.out.println(controller.feedHay(nameOfAnimal));
        } else if (GameMenuCommands.ANIMALSINFORMATIONS.getMather(input) != null) {
            System.out.println(controller.animals());
        } else if (GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input) != null) {
            String name = GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input).group(1);
            int amount = Integer.parseInt(GameMenuCommands.CHEATSETFRIENSHIPWITHANIMAL.getMather(input).group(2));
            System.out.println(controller.cheatSetFriendship(name, amount));
        } else if (GameMenuCommands.PET.getMather(input) != null) {
            String name = GameMenuCommands.PET.getMather(input).group(1);
            System.out.println(controller.pet(name));
        } else if (GameMenuCommands.SELL.getMather(input) != null) {
            String name = GameMenuCommands.SELL.getMather(input).group(1);
            System.out.println(controller.sellAnimal(name));
        } else {
            System.out.println("Invalid command");
        }
    }
}
