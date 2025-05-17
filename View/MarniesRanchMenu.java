package View;

import Controller.JojaMartController;
//import Controller.MarniesRanchController;
import Controller.MarniesRanchController;
import enums.MarketMenuEnums;
import enums.MarniesRanchCommands;

import java.util.Scanner;

public class MarniesRanchMenu extends AppMenu {
    private final MarniesRanchController controller = new MarniesRanchController();

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
        } else if (MarniesRanchCommands.BUYANIMAL.getMather(input) != null) {
            String typeOfAnimal = MarniesRanchCommands.BUYANIMAL.getMather(input).group(1);
            String nameOfAnimal = MarniesRanchCommands.BUYANIMAL.getMather(input).group(2);
            System.out.println(controller.buyAnimal(typeOfAnimal, nameOfAnimal));
        } else {
            System.out.println("Invalid command");
        }
    }
}
