package Controller;

import Model.App;
import Model.Craftingrecipe;
import Model.Items.*;
import Model.Player;
import Model.Result;
import enums.ForagingMineralsEnums;
import enums.ForagingSeedsEnums;
import enums.Menu;

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
            case "joja cola":
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
            case "ancient seed":
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
            case "grass starter":
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
            case "wheat flour":
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
            case "rice":
                boolean validquantity5 = false;
                for (Object item : App.getCurrentGame().getJojoMartMarket().getStock().keySet()) {
                    if (item instanceof AllCrop && ((AllCrop) item).getName().equals("unmilledrice") && App.getCurrentGame().getJojoMartMarket().getStock().get(item) >= quantity) {
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
            case "parsnip seeds":
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
            case "bean starter":
                return handleSeedPurchase(ForagingSeedsEnums.BeanStarter, quantity);
            case "cauliflower seeds":
                return handleSeedPurchase(ForagingSeedsEnums.CauliflowerSeeds, quantity);
            case "potato seeds":
                return handleSeedPurchase(ForagingSeedsEnums.PotatoSeeds, quantity);
            case "strawberry seeds":
                return handleSeedPurchase(ForagingSeedsEnums.StrawberrySeeds, quantity);
            case "tulip bulb":
                return handleSeedPurchase(ForagingSeedsEnums.TulipBulb, quantity);
            case "kale seeds":
                return handleSeedPurchase(ForagingSeedsEnums.KaleSeeds, quantity);
            case "coffee beans":
                return handleSeedPurchase(ForagingSeedsEnums.CoffeeBean, quantity);
            case "carrot seeds":
                return handleSeedPurchase(ForagingSeedsEnums.CarrotSeeds, quantity);
            case "rhubarb seeds":
                return handleSeedPurchase(ForagingSeedsEnums.RhubarbSeeds, quantity);
            case "jazz seeds":
                return handleSeedPurchase(ForagingSeedsEnums.JazzSeeds, quantity);
            case "tomato seeds":
                return handleSeedPurchase(ForagingSeedsEnums.TomatoSeeds, quantity);
            case "pepper seeds":
                return handleSeedPurchase(ForagingSeedsEnums.PepperSeeds, quantity);
            case "wheat seeds":
                return handleSeedPurchase(ForagingSeedsEnums.WheatSeeds, quantity);
            case "summer squash seeds":
                return handleSeedPurchase(ForagingSeedsEnums.SummerSquashSeeds, quantity);
            case "radish seeds":
                return handleSeedPurchase(ForagingSeedsEnums.RadishSeeds, quantity);
            case "melon seeds":
                return handleSeedPurchase(ForagingSeedsEnums.MelonSeeds, quantity);
            case "hops starter":
                return handleSeedPurchase(ForagingSeedsEnums.HopsStarter, quantity);
            case "poppy seeds":
                return handleSeedPurchase(ForagingSeedsEnums.PoppySeeds, quantity);
            case "spangle seeds":
                return handleSeedPurchase(ForagingSeedsEnums.SpangleSeeds, quantity);
            case "starfruit seeds":
                return handleSeedPurchase(ForagingSeedsEnums.StarfruitSeeds, quantity);
            case "sunflower seeds":
                return handleSeedPurchase(ForagingSeedsEnums.SunflowerSeeds, quantity);
            case "corn seeds":
                return handleSeedPurchase(ForagingSeedsEnums.CornSeeds, quantity);
            case "eggplant seeds":
                return handleSeedPurchase(ForagingSeedsEnums.EggplantSeeds, quantity);
            case "pumpkin seeds":
                return handleSeedPurchase(ForagingSeedsEnums.PumpkinSeeds, quantity);
            case "broccoli seeds":
                return handleSeedPurchase(ForagingSeedsEnums.BroccoliSeeds, quantity);
            case "amaranth seeds":
                return handleSeedPurchase(ForagingSeedsEnums.AmaranthSeeds, quantity);
            case "grape starter":
                return handleSeedPurchase(ForagingSeedsEnums.GrapeStarter, quantity);
            case "beet seeds":
                return handleSeedPurchase(ForagingSeedsEnums.BeetSeeds, quantity);
            case "yam seeds":
                return handleSeedPurchase(ForagingSeedsEnums.YamSeeds, quantity);
            case "bok choy seeds":
                return handleSeedPurchase(ForagingSeedsEnums.BokChoySeeds, quantity);
            case "cranberry seeds":
                return handleSeedPurchase(ForagingSeedsEnums.CranberrySeeds, quantity);
            case "fairy seeds":
                return handleSeedPurchase(ForagingSeedsEnums.FairySeeds, quantity);
            case "rare seed":
                return handleSeedPurchase(ForagingSeedsEnums.RareSeed, quantity);
            case "powdermelon seeds":
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
