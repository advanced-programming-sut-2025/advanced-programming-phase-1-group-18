package Controller;

import Model.App;
import Model.Items.Mineral;
import Model.Player;
import Model.Result;
import enums.ForagingMineralsEnums;
import enums.Menu;

import java.util.HashMap;

public class BlackSmithController implements MenuEnter, ShowCurrentMenu, MarketController<Mineral> {
    Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

    @Override
    public HashMap<Mineral, Integer> getStock() {
        return App.getCurrentGame().getBlackSmithMarket().getStock();
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
            case "copper ore":
                boolean validquantity = false;
                for (Mineral mineral : App.getCurrentGame().getBlackSmithMarket().getStock().keySet()) {
                    if (mineral.getType() == ForagingMineralsEnums.Copper && App.getCurrentGame().getBlackSmithMarket().getStock().get(mineral) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getMoney() >= mineral.getPrice()) {
                            currentPlayer.getInventory().addItem(mineral, quantity);
                            App.getCurrentGame().getBlackSmithMarket().removeItem(mineral, quantity);
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
            case "iron ore":
                boolean validquantity1 = false;
                for (Mineral mineral : App.getCurrentGame().getBlackSmithMarket().getStock().keySet()) {
                    if (mineral.getType() == ForagingMineralsEnums.Iron && App.getCurrentGame().getBlackSmithMarket().getStock().get(mineral) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getMoney() >= mineral.getPrice()) {
                            currentPlayer.getInventory().addItem(mineral, quantity);
                            App.getCurrentGame().getBlackSmithMarket().removeItem(mineral, quantity);
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
            case "coal":
                boolean validquantity2 = false;
                for (Mineral mineral : App.getCurrentGame().getBlackSmithMarket().getStock().keySet()) {
                    if (mineral.getType() == ForagingMineralsEnums.Coal && App.getCurrentGame().getBlackSmithMarket().getStock().get(mineral) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getMoney() >= mineral.getPrice()) {
                            currentPlayer.getInventory().addItem(mineral, quantity);
                            App.getCurrentGame().getBlackSmithMarket().removeItem(mineral, quantity);
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
            case "gold ore":
                boolean validquantity3 = false;
                for (Mineral mineral : App.getCurrentGame().getBlackSmithMarket().getStock().keySet()) {
                    if (mineral.getType() == ForagingMineralsEnums.Gold && App.getCurrentGame().getBlackSmithMarket().getStock().get(mineral) >= quantity) {
                        validquantity3 = true;
                        if (currentPlayer.getMoney() >= mineral.getPrice()) {
                            currentPlayer.getInventory().addItem(mineral, quantity);
                            App.getCurrentGame().getBlackSmithMarket().removeItem(mineral, quantity);
                            return new Result(true, "");
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity3) {
                    return new Result(false, "Not enough stock in store");
                }
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
