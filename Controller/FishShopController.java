package Controller;

import Model.App;
import Model.Items.Item;
import Model.Items.Mineral;
import Model.Player;
import Model.Result;
import enums.ForagingMineralsEnums;
import enums.Menu;

import java.util.HashMap;

public class FishShopController implements MenuEnter, ShowCurrentMenu, MarketController<Item> {
    @Override
    public HashMap<Item, Integer> getStock() {
        return App.getCurrentGame().getFishShopMarket().getStock();
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
            case "fish smoker":
                boolean validquantity = false;
                for (Item item : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
//                    if (item.getType() == ForagingMineralsEnums.Copper && App.getCurrentGame().getBlackSmithMarket().getStock().get(mineral) >= quantity) {
//                        validquantity = true;
//                        if (currentPlayer.getMoney() >= mineral.getPrice()) {
//                            currentPlayer.getInventory().addItem(mineral, quantity);
//                            App.getCurrentGame().getBlackSmithMarket().removeItem(mineral, quantity);
//                            return new Result(true, "");
//                        } else {
//                            return new Result(false, "You don't have enough money");
//                        }
//                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            case "trout soup":
                // Handle trout soup logic
                break;

            case "bamboo pole":
                // Handle bamboo pole logic
                break;

            case "training rod":
                // Handle training rod logic
                break;

            case "fiberglass rod":
                // Handle fiberglass rod logic
                break;

            case "iridium rod":
                // Handle iridium rod logic
                break;

            default:
                return new Result(false, "Invalid productname");
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
