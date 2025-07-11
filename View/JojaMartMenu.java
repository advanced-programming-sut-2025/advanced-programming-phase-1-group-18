package io.githubgroup18.View;

import io.githubgroup18.Controller.FishShopController;
import io.githubgroup18.Controller.JojaMartController;
import io.githubgroup18.Model.App;
import io.githubgroup18.enums.MarketMenuEnums;

import java.util.Scanner;

public class JojaMartMenu extends AppMenu
{
    private final JojaMartController controller = new JojaMartController();

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
        }else if (MarketMenuEnums.MenuEnter.getMather(input) != null) {
            controller.menuEnter(MarketMenuEnums.MenuEnter.getMather(input).group(1));
        }else {
            System.out.println("Invalid command");
        }
    }
}
