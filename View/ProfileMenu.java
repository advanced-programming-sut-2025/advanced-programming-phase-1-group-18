package View;

import Controller.MainMenuController;
import Controller.ProfileMenuController;
import enums.MainMenuCommands;
import enums.ProfileMenuCommands;

import java.util.Scanner;

public class ProfileMenu extends AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (ProfileMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = ProfileMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (ProfileMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (ProfileMenuCommands.ChangeUsername.getMather(input) != null) {
            System.out.println(controller.changeUsername(ProfileMenuCommands.ChangeUsername.getMather(input).group("username")));
        } else if (ProfileMenuCommands.ChangePassword.getMather(input) != null) {
            String newPassword = ProfileMenuCommands.ChangePassword.getMather(input).group(1);
            String oldPassword = ProfileMenuCommands.ChangePassword.getMather(input).group(2);
            System.out.println(controller.changePassword(newPassword, oldPassword));
        } else if (ProfileMenuCommands.ChangeNickname.getMather(input) != null) {
            String nickname = ProfileMenuCommands.ChangeNickname.getMather(input).group("nickname");
            System.out.println(controller.changeNickname(nickname));
        } else if (ProfileMenuCommands.ChangeEmail.getMather(input) != null) {
            String email = ProfileMenuCommands.ChangeEmail.getMather(input).group("email");
            System.out.println(controller.changeEmail(email));
        } else if (ProfileMenuCommands.ShowInfo.getMather(input) != null) {
            controller.userInfo();
        } else {
            System.out.println("invalid command");
        }
    }
}