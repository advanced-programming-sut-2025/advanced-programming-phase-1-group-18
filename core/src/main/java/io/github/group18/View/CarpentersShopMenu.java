package io.github.group18.View;

import io.github.group18.Controller.CarpentersShopController;
import io.github.group18.enums.MarketMenuEnums;
import io.github.group18.enums.CarpentersShopCommands;

import java.util.Scanner;

public class CarpentersShopMenu extends AppMenu {
    private final CarpentersShopController controller = new CarpentersShopController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (MarketMenuEnums.SHOWALLPRODUCTS.getMather(input) != null) {
            System.out.println("Wood: 2147483647");
            System.out.println(controller.showAllProducts());
        } else if (MarketMenuEnums.SHOWALLAVAILABLEPRODUCTS.getMather(input) != null) {
            System.out.println("Wood: 2147483647");
            System.out.println(controller.showAllAvailableProduct());
        } else if (MarketMenuEnums.PURCHASE.getMather(input) != null) {
            System.out.println(controller.purchase(MarketMenuEnums.PURCHASE.getMather(input).group(1), MarketMenuEnums.PURCHASE.getMather(input).group(2)));
        } else if (MarketMenuEnums.CHEATADD.getMather(input) != null) {
            controller.cheatAdd(Integer.parseInt(MarketMenuEnums.CHEATADD.getMather(input).group(1)));
        } else if (CarpentersShopCommands.BUILDBUILDING.getMather(input) != null) {
            String buildingName = CarpentersShopCommands.BUILDBUILDING.getMather(input).group(1);
            int xOfBuilding = Integer.parseInt(CarpentersShopCommands.BUILDBUILDING.getMather(input).group(2));
            int yOfBuilding = Integer.parseInt(CarpentersShopCommands.BUILDBUILDING.getMather(input).group(3));
//            System.out.println(controller.build(buildingName, xOfBuilding, yOfBuilding));
        } else if (CarpentersShopCommands.MenuEnter.getMather(input) != null) {
            controller.menuEnter(CarpentersShopCommands.MenuEnter.getMather(input).group(1));
        } else {
            System.out.println("Invalid command");
        }
    }
}
