package Controller;
import Model.App;
import Model.Result;
import enums.Menu;

public class MainMenuController implements MenuEnter, ShowCurrentMenu{

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
                App.setCurrentMenu(Menu.RegisterMenu);
                System.out.println("You are now in RegisterMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}
