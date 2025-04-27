package View;

import java.util.Scanner;

public class LoginMenu extends AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        //Matcher goToMenu = MainMenuCommands.GO_TO_MENU.getMather(input);
        if (LoginMenuCommands.Login.getMather(input) != null) {
            String username = LoginMenuCommands.Login.getMather(input).group("username");
            String password = LoginMenuCommands.Login.getMather(input).group("password");
            String stayLoggedIn = LoginMenuCommands.Login.getMather(input).group("stayLoggedIn");
            controller.login(username, password, stayLoggedIn);
        } else if (LoginMenuCommands.MenuExit.getMather(input) != null) {
            controller.exit();
        } else if (LoginMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = LoginMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (LoginMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (LoginMenuCommands.ForgetPassword.getMather(input) != null) {
            String username = LoginMenuCommands.Login.getMather(input).group("username");
            controller.forgetPassword(username, scanner);
        } else {
            System.out.println("invalid command");
        }
    }
}