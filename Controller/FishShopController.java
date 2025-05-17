package Controller;

import Model.App;
import Model.Items.CraftingItem;
import Model.Items.FishingPole;
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
                    if (item.getCorrectName().equalsIgnoreCase("fishsmoker") && App.getCurrentGame().getFishShopMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getFishShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());
                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            case "trout soup":
                // Handle trout soup logic
                break;

            case "bamboo pole":
                boolean validquantity1 = false;
                for (Item item : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Bamboo") && App.getCurrentGame().getFishShopMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getFishShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity1) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            case "training rod":
                boolean validquantity2 = false;
                for (Item item : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Training") && App.getCurrentGame().getFishShopMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getFishShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity2) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            case "fiberglass rod":
                boolean validquantity3 = false;
                for (Item item : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
//                    System.out.println(quantity + " " + item.getCorrectName());
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Fiberglass") && App.getCurrentGame().getFishShopMarket().getStock().get(item) >= quantity) {
                        validquantity3 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getFishShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity3) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            case "iridium rod":
                boolean validquantity4 = false;
                for (Item item : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Iridium") && App.getCurrentGame().getFishShopMarket().getStock().get(item) >= quantity) {
                        validquantity4 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getFishShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity4) {
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
