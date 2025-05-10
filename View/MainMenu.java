package View;

import Controller.MainMenuController;
import enums.MainMenuCommands;

import java.util.Scanner;

public class MainMenu extends AppMenu {
    private final MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        //Matcher goToMenu = MainMenuCommands.GO_TO_MENU.getMather(input);
        if (MainMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = MainMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (MainMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (MainMenuCommands.Logout.getMather(input) != null) {
            System.out.println(controller.logout());
        } else {
            System.out.println("invalid command");
        }
    }
}