package Controller;

import Model.*;
import enums.Menu;

public class RegisterMenuController implements MenuEnter, ShowCurrentMenu{
    public Result register(String username, String password, String repassword, String nickname, String email,
            String gender) {
        return new Result(true, "");
    }

    public void exit() {
        System.exit(0);
    }


    public Result pickQuestion(int questionNumber, String answer, String reAnswer) {
        return new Result(true, "");
    }

    public void menuEnter(String menuName) {
        //from registermenu we can move to loginmenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
            case "loginmenu":
                App.setCurrentMenu(Menu.LoginMenu);
                System.out.println("You are now in loginMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}
