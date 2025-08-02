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

    public JojaMartController() {

    }

    private Result handleSeedPurchase(ForagingSeedsEnums seedType, int quantity,Player playerrr) {
        for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
            if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == seedType &&
                    App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {

                if (playerrr.getGold() >= ((ForagingSeed) item).getPrice()) {
                    playerrr.getInventory().addItem((ForagingSeed) item, quantity);
                    App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                    ForagingSeed fg = (ForagingSeed) item;
                    playerrr.setGold(playerrr.getGold() - (fg.getCorrectPrice()));
                    return new Result(true, "You purchased " + quantity + " of " + seedType.toString());
                } else {
                    return new Result(false, "You don't have enough money");
                }
            }
        }
        return new Result(false, "Not enough stock in store");
    }

    public Result purchase(String name, String count,Player playerrr) {
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
                        if (playerrr.getGold() >= ((Food) item).getPrice()) {
                            playerrr.getInventory().addItem((Food)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - (((Food)item).getCorrectPrice()));
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
                        if (playerrr.getGold() >= ((ForagingSeed) item).getPrice()) {
                            playerrr.getInventory().addItem((ForagingSeed)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((ForagingSeed)item).getCorrectPrice());
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
                        if (playerrr.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem((Craftingrecipe)item, quantity);
                            //TODO ADD CRAFTINGRECIPE TO PLAYER
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Craftingrecipe)item).getPrice());

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
                        if (playerrr.getGold() >= ((Food) item).getPrice()) {
                            playerrr.getInventory().addItem((Food)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Food)item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            playerrr.getInventory().addItem((AllCrop)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((AllCrop)item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            playerrr.getInventory().addItem((AllCrop)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((AllCrop)item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((ForagingSeed) item).getPrice()) {
                            playerrr.getInventory().addItem((ForagingSeed)item, quantity);
                            App.getCurrentGame().getJojoMartMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((ForagingSeed)item).getCorrectPrice());

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
                return handleSeedPurchase(ForagingSeedsEnums.BeanStarter, quantity,playerrr);
            case "cauliflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CauliflowerSeeds, quantity,playerrr);
            case "potatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PotatoSeeds, quantity,playerrr);
            case "strawberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.StrawberrySeeds, quantity,playerrr);
            case "tulipbulb":
                return handleSeedPurchase(ForagingSeedsEnums.TulipBulb, quantity,playerrr);
            case "kaleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.KaleSeeds, quantity,playerrr);
            case "coffeebean":
                return handleSeedPurchase(ForagingSeedsEnums.CoffeeBean, quantity,playerrr);
            case "carrotseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CarrotSeeds, quantity,playerrr);
            case "rhubarbseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RhubarbSeeds, quantity,playerrr);
            case "jazzseeds":
                return handleSeedPurchase(ForagingSeedsEnums.JazzSeeds, quantity,playerrr);
            case "tomatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.TomatoSeeds, quantity,playerrr);
            case "pepperseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PepperSeeds, quantity,playerrr);
            case "wheatseeds":
                return handleSeedPurchase(ForagingSeedsEnums.WheatSeeds, quantity,playerrr);
            case "summersquashseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SummerSquashSeeds, quantity,playerrr);
            case "radishseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RadishSeeds, quantity,playerrr);
            case "melonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.MelonSeeds, quantity,playerrr);
            case "hopsstarter":
                return handleSeedPurchase(ForagingSeedsEnums.HopsStarter, quantity,playerrr);
            case "poppyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PoppySeeds, quantity,playerrr);
            case "spangleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SpangleSeeds, quantity,playerrr);
            case "starfruitseeds":
                return handleSeedPurchase(ForagingSeedsEnums.StarfruitSeeds, quantity,playerrr);
            case "sunflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SunflowerSeeds, quantity,playerrr);
            case "cornseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CornSeeds, quantity,playerrr);
            case "eggplantseeds":
                return handleSeedPurchase(ForagingSeedsEnums.EggplantSeeds, quantity,playerrr);
            case "pumpkinseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PumpkinSeeds, quantity,playerrr);
            case "broccoliseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BroccoliSeeds, quantity,playerrr);
            case "amaranthseeds":
                return handleSeedPurchase(ForagingSeedsEnums.AmaranthSeeds, quantity,playerrr);
            case "grapestarter":
                return handleSeedPurchase(ForagingSeedsEnums.GrapeStarter, quantity,playerrr);
            case "beetseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BeetSeeds, quantity,playerrr);
            case "yamseeds":
                return handleSeedPurchase(ForagingSeedsEnums.YamSeeds, quantity,playerrr);
            case "bokchoyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BokChoySeeds, quantity,playerrr);
            case "cranberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CranberrySeeds, quantity,playerrr);
            case "fairyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.FairySeeds, quantity,playerrr);
            case "rareseed":
                return handleSeedPurchase(ForagingSeedsEnums.RareSeed, quantity,playerrr);
            case "powdermelonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PowdermelonSeeds, quantity,playerrr);
            default:
                return new Result(false, "Invalid productname");
        }
        return null;
    }

    public void menuEnter(String menuName,Player playerrr) {
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
