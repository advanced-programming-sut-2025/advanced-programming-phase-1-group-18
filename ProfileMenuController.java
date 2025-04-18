package Controller;

import Model.App;
import Model.Result;
import enums.Menu;

public class ProfileMenuController implements MenuEnter, ShowCurrentMenu{
    public void exit() {

    }

    public Result changeUsername(String newUsername) {
        return new Result(true, "");
    }

    public Result changeNickname(String newNickname) {
        return new Result(true, "");
    }

    public Result changeEmail(String newEmail) {
        return new Result(true, "");
    }

    public Result changePassword(String oldPassword, String newPassword) {
        return new Result(true, "");
    }

    public void userInfo() {

    }
    public void menuEnter(String menuName) {
        //from profilemenu we can move to mainmenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
            case "mainmenu":
                App.setCurrentMenu(Menu.MainMenu);
                System.out.println("You are now in MainMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}
