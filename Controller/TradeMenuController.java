package Controller;

import Model.App;
import Model.Result;
import enums.Menu;


public class TradeMenuController implements MenuEnter, ShowCurrentMenu{


    public Result trade(String command)
    {
        return new Result(true, "");
    }


    public void tradeList()
    {

    }
    public Result tradeResponse(String acceptOrReject,int id)
    {

        return new Result(true, "");
    }
    public void tradeHistory()
    {

    }
    public void goBackToGame()
    {

    }


    public void menuEnter(String menuName) {
        //from trademenu we can move to gamemenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
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
