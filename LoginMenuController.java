package Controller;

import Model.App;
import Model.Result;
import enums.Menu;

public class LoginMenuController implements MenuEnter, ShowCurrentMenu{
    public Result login(String username, String password) {
        return new Result(true, "");
    }

    public Result forgetPassword(String username) {
        return new Result(true, "");
    }

    public String randomPasswordGenerator() {
        return "";
    }

    public void exit() {
        System.exit(0);
    }

    public void menuEnter(String menuName) {
        //from loginmenu we can move to registermenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
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
