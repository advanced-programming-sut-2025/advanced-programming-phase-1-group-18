package View;

import java.util.Scanner;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (GameMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else {
            if (GameMenuCommands.GameNew.getMather(input) != null) {
                System.out.println(controller.gameNew(input, scanner));
            } else {
                if (GameMenuCommands.ExitGame.getMather(input) != null) {
                    controller.exitGame();
                } else {
                    if (GameMenuCommands.VoteTerminateGame.getMather(input) != null) {
                        System.out.println(controller.voteTerminateGame(scanner));
                    } else {
                        if (GameMenuCommands.NextTurn.getMather(input) != null) {
                            controller.nextTurn();
                        } else {
                            if (GameMenuCommands.Plant.getMather(input) != null) {
                                System.out.println(controller.plant(GameMenuCommands.Plant.getMather(input).group("seed"), GameMenuCommands.Plant.getMather(input).group("direction")));
                            } else {
                                if (GameMenuCommands.ShowPlant.getMather(input) != null) {
                                    System.out.println(controller.showPlant(Integer.parseInt(GameMenuCommands.Plant.getMather(input).group("x")), Integer.parseInt(GameMenuCommands.Plant.getMather(input).group("y"))));
                                } else {
                                    if (GameMenuCommands.HowMuchWater.getMather(input) != null) {
                                        System.out.println(controller.howMuchWater());
                                    } else {
                                        if (GameMenuCommands.TIME.getMather(input) != null) {
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
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
