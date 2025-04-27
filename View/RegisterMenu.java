package View;

import java.util.Scanner;

public class RegisterMenu extends AppMenu{
    RegisterMenuController controller = new RegisterMenuController();
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (RegisterMenuCommands.Register.getMatcher(input) != null) {
            String username = RegisterMenuCommands.Register.getMatcher(input).group("username");
            String password = RegisterMenuCommands.Register.getMatcher(input).group("password");
            String passwordconfirm = RegisterMenuCommands.Register.getMatcher(input).group("passwordconfirm");
            String nickname = RegisterMenuCommands.Register.getMatcher(input).group("nickname");
            String email = RegisterMenuCommands.Register.getMatcher(input).group("email");
            String gender = RegisterMenuCommands.Register.getMatcher(input).group("gender");
            System.out.println(controller.register(username, password, passwordconfirm, nickname, email, gender));
        } else if (RegisterMenuCommands.MenuExit.getMatcher(input) != null) {
            controller.exit();
        } else if (RegisterMenuCommands.MenuEnter.getMatcher(input) != null) {
            String menuName = RegisterMenuCommands.MenuEnter.getMatcher(input).group("menuname");
            controller.menuEnter(menuName);
        } else if (RegisterMenuCommands.ShowCurrentMenu.getMatcher(input) != null) {
            controller.showCurrentMenu();
        }
        else if (RegisterMenuCommands.PickQuestion.getMatcher(input) != null)
        {
           // System.out.println(controller.PickQuestion());
        }else {
            System.out.println("invalid command");
        }
    }
}
