package Controller;

import Model.App;
import Model.Result;
import enums.Menu;

public class BlackSmithController implements MenuEnter, ShowCurrentMenu {
    public void showAllProducts() {

    }

    public void showAllAvailableProduct() {

    }

    public Result purchase(String Name, int count) {
        return new Result(true, "");
    }

    public Result cheatAdd(int count) {
        return new Result(true, "");
    }

    public void menuEnter(String menuName) {
        //from markets we can move to gamemenu
        menuName = menuName.toLowerCase();
        switch (menuName) {
            case "gamemenu":
                App.setCurrentMenu(Menu.GameMenu);
                System.out.println("You are now in GameMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}
