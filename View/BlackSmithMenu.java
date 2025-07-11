package io.githubgroup18.View;

import io.githubgroup18.Controller.BlackSmithController;
import io.githubgroup18.Controller.GameMenuController;
import io.githubgroup18.enums.CarpentersShopCommands;
import io.githubgroup18.enums.GameMenuCommands;
import io.githubgroup18.enums.MarketMenuEnums;

import java.util.Scanner;

public class BlackSmithMenu extends AppMenu {
    private final BlackSmithController controller = new BlackSmithController();
    private final GameMenuController gameController = new GameMenuController();


    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (MarketMenuEnums.SHOWALLPRODUCTS.getMather(input) != null) {
            System.out.println(controller.showAllProducts());
        } else if (MarketMenuEnums.SHOWALLAVAILABLEPRODUCTS.getMather(input) != null) {
            System.out.println(controller.showAllAvailableProduct());
        } else if (MarketMenuEnums.PURCHASE.getMather(input) != null) {
            System.out.println(controller.purchase(MarketMenuEnums.PURCHASE.getMather(input).group(1), MarketMenuEnums.PURCHASE.getMather(input).group(2)));
        } else if (MarketMenuEnums.CHEATADD.getMather(input) != null) {
            controller.cheatAdd(Integer.parseInt(MarketMenuEnums.CHEATADD.getMather(input).group(1)));
        } else if (MarketMenuEnums.MenuEnter.getMather(input) != null) {
            controller.menuEnter(MarketMenuEnums.MenuEnter.getMather(input).group(1));
        } else if (GameMenuCommands.TOOLSUPGRADE.getMather(input) != null) {
            String type = GameMenuCommands.TOOLSUPGRADE.getMather(input).group(1);
            System.out.println(gameController.toolsUpgrade(type));
        } else {
            System.out.println("Invalid command");
        }
    }
}
