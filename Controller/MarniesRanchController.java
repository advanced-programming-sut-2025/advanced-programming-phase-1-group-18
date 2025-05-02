package Controller;

import Model.App;
import Model.Items.Hay;
import Model.Items.Item;
import Model.Items.MilkPail;
import Model.Items.Shear;
import Model.Player;
import Model.Result;
import enums.Menu;

import java.util.HashMap;

public class MarniesRanchController implements MenuEnter, ShowCurrentMenu, MarketController<Item> {
    @Override
    public HashMap<Item, Integer> getStock() {
        return App.getCurrentGame().getMarniesRanchMarket().getStock();
    }

    public Result purchase(String name, String count) {
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        switch (name.toLowerCase()) {
            case "hay":
                boolean validquantity = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof Hay && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getMoney() >= ((Hay) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            return new Result(true, "");
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "milk pail":
                boolean validquantity1 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof MilkPail && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getMoney() >= ((MilkPail) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            return new Result(true, "");
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity1) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "shears":
                boolean validquantity2 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof Shear && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getMoney() >= ((MilkPail) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            return new Result(true, "");
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity2) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            default:
                return new Result(false, "Invalid product name");
        }

        return null;
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
