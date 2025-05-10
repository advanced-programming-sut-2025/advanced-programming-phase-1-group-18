package View;

import Controller.RegisterMenuController;
import enums.LoginMenuCommands;
import Controller.LoginMenuController;
import enums.RegisterMenuCommands;

import java.util.Scanner;
import java.util.Scanner;

public class RegisterMenu extends AppMenu {
    RegisterMenuController controller = new RegisterMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (RegisterMenuCommands.Register2.getMatcher(input) != null) {
            String username = RegisterMenuCommands.Register2.getMatcher(input).group(1);
            String nickname = RegisterMenuCommands.Register2.getMatcher(input).group(2);
            String email = RegisterMenuCommands.Register2.getMatcher(input).group(3);
            String gender = RegisterMenuCommands.Register2.getMatcher(input).group(4);
            System.out.println(controller.register2(username, nickname, email, gender, scanner));
        } else if (RegisterMenuCommands.Register.getMatcher(input) != null) {
            String username = RegisterMenuCommands.Register.getMatcher(input).group(1);
            String password = RegisterMenuCommands.Register.getMatcher(input).group(2);
            String passwordconfirm = RegisterMenuCommands.Register.getMatcher(input).group(3);
            String nickname = RegisterMenuCommands.Register.getMatcher(input).group(4);
            String email = RegisterMenuCommands.Register.getMatcher(input).group(5);
            String gender = RegisterMenuCommands.Register.getMatcher(input).group(6);
            System.out.println(controller.register(username, password, passwordconfirm, nickname, email, gender));
        } else if (RegisterMenuCommands.MenuExit.getMatcher(input) != null) {
            controller.exit();
        } else if (RegisterMenuCommands.MenuEnter.getMatcher(input) != null) {
            String menuName = RegisterMenuCommands.MenuEnter.getMatcher(input).group(1);
            controller.menuEnter(menuName);
        } else if (RegisterMenuCommands.ShowCurrentMenu.getMatcher(input) != null) {
            controller.showCurrentMenu();
        } else {
            System.out.println("invalid command");
        }
    }
}
