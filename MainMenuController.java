package Controller;

import Model.App;
import enums.Menu;

public class MainMenuController {
    public void exit() {
        App.setCurrentuser(null); //?
        App.setCurrentMenu(Menu.LoginMenu);
    }

    public String showCurrentMenu() {
        return App.getCurrentMenu().toString();
    }

    public void logout() {
        App.setCurrentuser(null);
        App.setCurrentMenu(Menu.LoginMenu);
    }

    public void menuEnter(String menuName) {
        switch (menuName) {
            case "Profile Menu":
                App.setCurrentMenu(Menu.ProfileMenu);
                break;
            case "Avatar Menu":
                App.setCurrentMenu(Menu.AvatarMenu);
                break;
            case "Game Menu":
                App.setCurrentMenu(Menu.GameMenu);
                break;
        }

    }
}
