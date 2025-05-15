package Controller;

import Model.*;
import Model.Items.*;
import enums.*;

import java.util.HashMap;
import java.util.Objects;

public class TheStardropSaloonController implements MenuEnter, ShowCurrentMenu, MarketController<Object> {

    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getTheStardropSaloonMarket().getStock();
    }


    Player currentPlayer;

    public TheStardropSaloonController() {
        if (!(App.getCurrentGame() == null || App.getCurrentGame().getPlayers() == null)) {
            this.currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        }
    }


    private Result handlePurchase(FoodCookingEnums cookingEnums, int quantity) {
        currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
            if (item instanceof Cookingrecipe && ((Cookingrecipe) item).getFood() == cookingEnums &&
                    App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {

                if (currentPlayer.getGold() >= ((Cookingrecipe) item).getPrice()) {
                    currentPlayer.getCookingRecipes().add((Cookingrecipe) item);
                    App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                    currentPlayer.setGold(currentPlayer.getGold() - ((Cookingrecipe) item).getPrice());
                    return new Result(true, "You purchased " + quantity + " of " + ((Cookingrecipe) item).getFood().toString() + " recipe");
                } else {
                    return new Result(false, "You don't have enough money");
                }
            }
        }
        return new Result(false, "Not enough stock in store");
    }

    public Result purchase(String name, String count) {
        currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        switch (name.toLowerCase()) {
            case "beer":
                boolean validquantity = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof Food && ((Food) item).getName().equals("Beer") && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem((Food) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Food) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((Food) item, quantity);
                            return new Result(true, "You purchased " + quantity + " of " + ((Food) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "salad":
                boolean validquantity1 = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof FoodCooking && ((FoodCooking) item).getNamee() == FoodCookingEnums.Salad && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= ((FoodCooking) item).getSellPrice()) {
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((FoodCooking) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);

                            return new Result(true, "You purchased " + quantity + " of " + ((FoodCooking) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity1) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "bread":
                boolean validquantity2 = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof FoodCooking && ((FoodCooking) item).getNamee() == FoodCookingEnums.Bread && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= ((FoodCooking) item).getSellPrice()) {
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((FoodCooking) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);
                            return new Result(true, "You purchased " + quantity + " of " + ((FoodCooking) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity2) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "spaghetti":
                boolean validquantity3 = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof FoodCooking && ((FoodCooking) item).getNamee() == FoodCookingEnums.Spaghetti && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity3 = true;
                        if (currentPlayer.getGold() >= ((FoodCooking) item).getSellPrice()) {
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((FoodCooking) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);

                            return new Result(true, "You purchased " + quantity + " of " + ((FoodCooking) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity3) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "pizza":
                boolean validquantity4 = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof FoodCooking && ((FoodCooking) item).getNamee() == FoodCookingEnums.Pizza && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity4 = true;
                        if (currentPlayer.getGold() >= ((FoodCooking) item).getSellPrice()) {
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((FoodCooking) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((FoodCooking) item, quantity);

                            return new Result(true, "You purchased " + quantity + " of " + ((FoodCooking) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity4) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "coffee":
                boolean validquantity5 = false;
                for (Object item : App.getCurrentGame().getTheStardropSaloonMarket().getStock().keySet()) {
                    if (item instanceof Food && ((Food) item).getName().equals("coffee") && App.getCurrentGame().getTheStardropSaloonMarket().getStock().get(item) >= quantity) {
                        validquantity5 = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem((Food) item, quantity);
                            App.getCurrentGame().getTheStardropSaloonMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Food) item).getCorrectPrice());
                            currentPlayer.getInventory().addItem((Food) item, quantity);

                            return new Result(true, "You purchased " + quantity + " of " + ((Food) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity5) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "hashbrowns recipe":
                return handlePurchase(FoodCookingEnums.HashBrowns, quantity);
            case "omelet recipe":
                return handlePurchase(FoodCookingEnums.Omelet, quantity);
            case "pancakes recipe":
                return handlePurchase(FoodCookingEnums.Pancakes, quantity);
            case "bread recipe":
                return handlePurchase(FoodCookingEnums.Bread, quantity);
            case "tortilla recipe":
                return handlePurchase(FoodCookingEnums.Tortilla, quantity);
            case "pizza recipe":
                return handlePurchase(FoodCookingEnums.Pizza, quantity);
            case "maki roll recipe":
                return handlePurchase(FoodCookingEnums.MakiRoll, quantity);
            case "triple shot espresso recipe":
                return handlePurchase(FoodCookingEnums.TripleShotEspresso, quantity);
            case "cookie recipe":
                return handlePurchase(FoodCookingEnums.Cookie, quantity);

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
