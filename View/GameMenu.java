package View;

import Controller.GameMenuController;
import enums.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (GameMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (GameMenuCommands.GameNew.getMather(input) != null) {
            try {
                System.out.println(controller.gameNew(input, scanner));
            } catch (Exception e) {
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
            System.out.println(controller.showPlant(Integer.parseInt(GameMenuCommands.Plant.getMather(input).group("x")), Integer.parseInt(GameMenuCommands.Plant.getMather(input).group("y"))));
        } else if (GameMenuCommands.HowMuchWater.getMather(input) != null) {
            System.out.println(controller.howMuchWater());
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
        } else if (GameMenuCommands.PrintMap.getMather(input) != null) {
            controller.printMap(Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(1)),
                    Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(2)),
                    Integer.parseInt(GameMenuCommands.PrintMap.getMather(input).group(3)));
        } else if (GameMenuCommands.HelpReadingMap.getMather(input) != null) {
            controller.helpReadingMap();
        } else if (GameMenuCommands.EnergyShow.getMather(input) != null) {
            controller.energyShow();
        } else if (GameMenuCommands.EnergySet.getMather(input) != null) {
            System.out.println(controller.energySet(Integer.parseInt(GameMenuCommands.EnergySet.getMather(input).group(1))));
        } else if (GameMenuCommands.EnergyUnlimited.getMather(input) != null) {
            controller.energyUnlimited();
        } else if (GameMenuCommands.COOKINGREFRIGERATOR.getMather(input) != null) {
            String action = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(1);
            String item = GameMenuCommands.COOKINGREFRIGERATOR.getMather(input).group(2);
            try {
                System.out.println(controller.cookingRefrigerator(action, item));
            } catch (Exception _) {

            }
        } else if (GameMenuCommands.COOKINGSHOWRECIPES.getMather(input) != null) {
            System.out.println(controller.cookingShowRecipes());
        } else if (GameMenuCommands.COOKINGPREPARE.getMather(input) != null) {
            System.out.println(controller.cookingPrepare(GameMenuCommands.COOKINGPREPARE.getMather(input).group(1)));
        } else if (GameMenuCommands.EAT.getMather(input) != null) {
            System.out.println(controller.eat(GameMenuCommands.EAT.getMather(input).group(1)));
        } else if (GameMenuCommands.SELL.getMather(input) != null) {
            System.out.println(controller.sell(GameMenuCommands.SELL.getMather(input).group(1), GameMenuCommands.SELL.getMather(input).group(2)));
        } else if (GameMenuCommands.FriendShip.getMather(input) != null) {
            System.out.println(controller.friendships());
        } else if (GameMenuCommands.Talk.getMather(input) != null) {
            System.out.println(controller.talk(GameMenuCommands.Talk.getMather(input).group(1),
                    GameMenuCommands.Talk.getMather(input).group(2)));
        }else if (GameMenuCommands.TalkHistory.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.TalkHistory.getMather(input);
            System.out.println(controller.talkHistory(matcher.group(1)));
        } else if (GameMenuCommands.Gift.getMather(input) != null) {
            Matcher matcher = GameMenuCommands.Gift.getMather(input);
            System.out.println(controller.gift(
                    matcher.group(1),
                    matcher.group(2),
                    Integer.parseInt(matcher.group(3))));
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
        }
    }
}
