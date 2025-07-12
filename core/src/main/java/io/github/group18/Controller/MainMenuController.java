package io.github.group18.Controller;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetMannager;
import io.github.group18.Model.Result;
import io.github.group18.View.GameMenu;
import io.github.group18.View.MainMenu;
import io.github.group18.View.ProfileMenu;
import io.github.group18.View.RegisterLoginGdxView;
import io.github.group18.enums.Menu;

public class MainMenuController implements MenuEnter, ShowCurrentMenu{
    private MainMenu mainMenu;

    public Result logout() {
        App.setCurrentUser(null);
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true,"You are logged out");
    }

    public void menuEnter(String menuName) {
        //from mainmenu we can move to gamemenu,profilemenu,loginmenu,registermenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
            case "gamemenu":
                App.setCurrentMenu(Menu.GameMenu);
                System.out.println("You are now in GameMenu!");
                break;
            case "profilemenu":
                App.setCurrentMenu(Menu.ProfileMenu);
                System.out.println("You are now in ProfileMenu!");
                break;
            case "loginmenu":
                App.setCurrentMenu(Menu.LoginMenu);
                System.out.println("You are now in LoginMenu!");
                break;
            case "registermenu":
//                App.setCurrentMenu(Menu.RegisterMenu);
                System.out.println("You are now in RegisterMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }

    public void handleMainMenuButtons() {
        if (mainMenu.getProfileMenuButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ProfileMenu(new ProfileMenuController(), GameAssetMannager.getGameAssetMannager().getSkin()));
        } else if (mainMenu.getGameMenuButton().isChecked()) {

            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new GameMenu(new GameMenuController(),GameAssetMannager.getGameAssetMannager().getSkin()));

            //TODO
        } else if (mainMenu.getLogoutButton().isChecked()) {
            logout();
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new RegisterLoginGdxView(new RegisterLoginGdxController(),GameAssetMannager.getGameAssetMannager().getSkin()));

            //TODO
        }
    }

    public void setView(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }
}
