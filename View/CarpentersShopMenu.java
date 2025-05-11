package View;

import Controller.BlackSmithController;
import Controller.CarpentersShopController;
import Controller.GameMenuController;
import enums.GameMenuCommands;
import enums.MarketMenuEnums;
import Controller.CarpentersShopController;
import enums.CarpentersShopCommands;
import enums.GameMenuCommands;

import java.util.Scanner;

public class CarpentersShopMenu extends AppMenu {
    private final CarpentersShopController controller = new CarpentersShopController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (CarpentersShopCommands.BUILDBUILDING.getMather(input) != null) {
            String buildingName = CarpentersShopCommands.BUILDBUILDING.getMather(input).group(1);
            int xOfBuilding = Integer.parseInt(CarpentersShopCommands.BUILDBUILDING.getMather(input).group(2));
            int yOfBuilding = Integer.parseInt(CarpentersShopCommands.BUILDBUILDING.getMather(input).group(3));
            System.out.println(controller.build(buildingName, xOfBuilding, yOfBuilding));
        } else if (CarpentersShopCommands.MenuEnter.getMather(input) != null) {
            controller.menuEnter(CarpentersShopCommands.MenuEnter.getMather(input).group(1));
        }
    }
}