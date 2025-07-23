package io.github.group18.Controller;

import io.github.group18.Model.App;
import io.github.group18.Model.Craftingrecipe;
import io.github.group18.Model.Items.AllCrop;
import io.github.group18.Model.Items.Food;
import io.github.group18.Model.Items.ForagingSeed;
import io.github.group18.Model.Player;
import io.github.group18.Model.Result;
import io.github.group18.enums.AllCropsEnums;
import io.github.group18.enums.ForagingSeedsEnums;
import io.github.group18.enums.Menu;

import java.util.HashMap;

public class JojaMartController implements MenuEnter, ShowCurrentMenu, MarketController<Object> {

    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getJojoMartMarket().getStock();
    }

    Player currentPlayer;

    public JojaMartController() {
        if (!(App.getCurrentGame() == null || App.getCurrentGame().getPlayers() == null)) {
            this.currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        }
    }

    private Result handleSeedPurchase(ForagingSeedsEnums seedType, int quantity) {
        for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
            if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == seedType &&
                    App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {

                if (currentPlayer.getGold() >= ((ForagingSeed) item).getPrice()) {
                    currentPlayer.getInventory().addItem((ForagingSeed) item, quantity);
                    App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                    ForagingSeed fg = (ForagingSeed) item;
                    currentPlayer.setGold(currentPlayer.getGold() - (fg.getCorrectPrice()));
                    return new Result(true, "You purchased " + quantity + " of " + seedType.toString());
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
        //System.out.println(name.toLowerCase() + "bache haye karaj");
        switch (name.toLowerCase()) {
            // General Items
            case "jojacola":
                boolean validquantity = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof Food && ((Food) item).getName().equals("Joja Cola") && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem((Food)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - (((Food)item).getCorrectPrice()));
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
            case "ancientseeds":
                boolean validquantity1 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == ForagingSeedsEnums.AncientSeeds && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= ((ForagingSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem((ForagingSeed)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((ForagingSeed)item).getCorrectPrice());
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
            case "grassstarter":
                boolean validquantity2 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof Craftingrecipe && ((Craftingrecipe) item).getName().equals("Grass Starter") && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem((Craftingrecipe)item, quantity);
                            //TODO ADD CRAFTINGRECIPE TO PLAYER
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Craftingrecipe)item).getPrice());

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
            case "sugar":
                boolean validquantity3 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if ((item instanceof Food && ((Food) item).getName().equals("sugar") && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity)) {
                        validquantity3 = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem((Food)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Food)item).getCorrectPrice());

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
            case "wheat":
                boolean validquantity4 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if ((item instanceof AllCrop && ((AllCrop) item).getCorrectName().equals("wheat") && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity)) {
                        validquantity4 = true;
                        if (currentPlayer.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            currentPlayer.getInventory().addItem((AllCrop)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((AllCrop)item).getCorrectPrice());

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
            case "unmilledrice":
                boolean validquantity5 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof AllCrop && ((AllCrop) item).getType().equals(AllCropsEnums.UnmilledRice) && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
                        validquantity5 = true;
                        if (currentPlayer.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            currentPlayer.getInventory().addItem((AllCrop)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((AllCrop)item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity5) {
                    return new Result(false, "Not enough stock in store");
                }
                break;

            // Spring Seeds
            case "parsnipseeds":
                boolean validquantity6 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == ForagingSeedsEnums.ParsnipSeeds && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
                        validquantity6 = true;
                        if (currentPlayer.getGold() >= ((ForagingSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem((ForagingSeed)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((ForagingSeed)item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity6) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "beanstarter":
                return handleSeedPurchase(ForagingSeedsEnums.BeanStarter, quantity);
            case "cauliflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CauliflowerSeeds, quantity);
            case "potatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PotatoSeeds, quantity);
            case "strawberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.StrawberrySeeds, quantity);
            case "tulipbulb":
                return handleSeedPurchase(ForagingSeedsEnums.TulipBulb, quantity);
            case "kaleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.KaleSeeds, quantity);
            case "coffeebean":
                return handleSeedPurchase(ForagingSeedsEnums.CoffeeBean, quantity);
            case "carrotseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CarrotSeeds, quantity);
            case "rhubarbseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RhubarbSeeds, quantity);
            case "jazzseeds":
                return handleSeedPurchase(ForagingSeedsEnums.JazzSeeds, quantity);
            case "tomatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.TomatoSeeds, quantity);
            case "pepperseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PepperSeeds, quantity);
            case "wheatseeds":
                return handleSeedPurchase(ForagingSeedsEnums.WheatSeeds, quantity);
            case "summersquashseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SummerSquashSeeds, quantity);
            case "radishseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RadishSeeds, quantity);
            case "melonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.MelonSeeds, quantity);
            case "hopsstarter":
                return handleSeedPurchase(ForagingSeedsEnums.HopsStarter, quantity);
            case "poppyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PoppySeeds, quantity);
            case "spangleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SpangleSeeds, quantity);
            case "starfruitseeds":
                return handleSeedPurchase(ForagingSeedsEnums.StarfruitSeeds, quantity);
            case "sunflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SunflowerSeeds, quantity);
            case "cornseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CornSeeds, quantity);
            case "eggplantseeds":
                return handleSeedPurchase(ForagingSeedsEnums.EggplantSeeds, quantity);
            case "pumpkinseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PumpkinSeeds, quantity);
            case "broccoliseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BroccoliSeeds, quantity);
            case "amaranthseeds":
                return handleSeedPurchase(ForagingSeedsEnums.AmaranthSeeds, quantity);
            case "grapestarter":
                return handleSeedPurchase(ForagingSeedsEnums.GrapeStarter, quantity);
            case "beetseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BeetSeeds, quantity);
            case "yamseeds":
                return handleSeedPurchase(ForagingSeedsEnums.YamSeeds, quantity);
            case "bokchoyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BokChoySeeds, quantity);
            case "cranberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CranberrySeeds, quantity);
            case "fairyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.FairySeeds, quantity);
            case "rareseed":
                return handleSeedPurchase(ForagingSeedsEnums.RareSeed, quantity);
            case "powdermelonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PowdermelonSeeds, quantity);
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
