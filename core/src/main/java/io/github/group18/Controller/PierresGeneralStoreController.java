package io.github.group18.Controller;

import io.github.group18.Model.App;
import io.github.group18.Model.Craftingrecipe;

import io.github.group18.Model.Items.*;
import io.github.group18.Model.Player;
import io.github.group18.Model.Result;
import io.github.group18.enums.ForagingSeedsEnums;
import io.github.group18.enums.Menu;
import io.github.group18.enums.TreeSeedEnums;

import java.util.HashMap;

public class PierresGeneralStoreController implements MenuEnter, ShowCurrentMenu, MarketController<Object> {
    Player currentPlayer;

    public PierresGeneralStoreController() {
        if (!(App.getCurrentGame() == null || App.getCurrentGame().getPlayers() == null)) {
            this.currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        }
    }

    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getPierresGeneralStoreMarket().getStock();
    }

    private Result handleSeedPurchase(ForagingSeedsEnums seedType, int quantity) {
        for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
            if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == seedType &&
                    App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {

                if (currentPlayer.getGold() >= ((ForagingSeed) item).getPrice()) {
                    currentPlayer.getInventory().addItem((ForagingSeed) item, quantity);
                    App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
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
        switch (name.toLowerCase()) {
            case "large pack":
                if (App.getCurrentGame().getPierresGeneralStoreMarket().isLargePackBougth()) {
                    return new Result(false, "Out of Stock");
                } else {
                    if (currentPlayer.getGold() > 2000) {
                        currentPlayer.setGold(currentPlayer.getGold() - 2000);
                        currentPlayer.getInventory().setMaxQuantity(24);
                        App.getCurrentGame().getPierresGeneralStoreMarket().setLargePackBougth(true);
                        return new Result(true, "Bought Large Pack");
                    } else {
                        return new Result(false, "Not enough money");
                    }
                }
            case "deluxe pack":
                if (App.getCurrentGame().getPierresGeneralStoreMarket().isDeluxePackBought()) {
                    return new Result(false, "Out of Stock");
                } else {
                    if (currentPlayer.getGold() > 2000) {
                        currentPlayer.setGold(currentPlayer.getGold() - 10000);
                        currentPlayer.getInventory().setMaxQuantity(Integer.MAX_VALUE);
                        App.getCurrentGame().getPierresGeneralStoreMarket().setDeluxePackBought(true);
                        return new Result(true, "Bought Deluxe Pack");
                    } else {
                        return new Result(false, "Not enough money");
                    }
                }
            case "unmilledrice":
                boolean validquantity = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Food && ((Food) item).getName().equals("rice") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem((Food) item, quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Food) item).getCorrectPrice());

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
            case "wheat":
                boolean validquantity1 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof AllCrop && ((AllCrop) item).getCorrectName().equalsIgnoreCase("wheatseeds") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(((AllCrop) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((AllCrop) item).getCorrectPrice());

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
            case "bouquet":
                boolean validquantity2 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof MarketProducts && ((MarketProducts) item).getCorrectName().equals("bouquet") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= ((MarketProducts) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((MarketProducts) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((MarketProducts) item).getCorrectPrice());

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
            case "weddingring":
                boolean validquantity3 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof MarketProducts && ((MarketProducts) item).getCorrectName().equals("weddingring") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity3 = true;
                        if (currentPlayer.getGold() >= ((MarketProducts) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((MarketProducts) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((MarketProducts) item).getCorrectPrice());

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
            case "dehydrator":
                boolean validquantity40 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Craftingrecipe && ((Craftingrecipe) item).getName().equals("Dehydrator") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity40 = true;
                        if (currentPlayer.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem(((Craftingrecipe) item), quantity);
                            //TODO
                            //currentPlayer.getCraftingRecipes().add((Craftingrecipe) item);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Craftingrecipe) item).getPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity40) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "grassstarter":
                boolean validquantity4 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Craftingrecipe && ((Craftingrecipe) item).getName().equals("GrassStarter") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity4 = true;
                        if (currentPlayer.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem(((Craftingrecipe) item), quantity);
                            //TODO
                            //currentPlayer.getCraftingRecipes().add((Craftingrecipe) item);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Craftingrecipe) item).getPrice());

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
            case "sugar":
                boolean validquantity5 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Food && ((Food) item).getName().equals("sugar") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity5 = true;
                        if (currentPlayer.getGold() >= ((Food) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((Food) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);

                            currentPlayer.setGold(currentPlayer.getGold() - ((Food) item).getCorrectPrice());
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
            case "oilcorn":
                boolean validquantity6 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof ArtisanGoods && ((ArtisanGoods) item).getCorrectName().equals("Oil_Corn") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity6 = true;
                        if (currentPlayer.getGold() >= ((ArtisanGoods) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((ArtisanGoods) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((ArtisanGoods) item).getCorrectPrice());

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
            case "vinegar":
                boolean validquantity7 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof ArtisanGoods && ((ArtisanGoods) item).getCorrectName().equals("Vinegar") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity7 = true;
                        if (currentPlayer.getGold() >= ((ArtisanGoods) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((ArtisanGoods) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((ArtisanGoods) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity7) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "deluxeretainingsoil":
                boolean validquantity8 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Fertilizer && ((Fertilizer) item).getName().equals("Deluxe Retaining Soil") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity8 = true;
                        if (currentPlayer.getGold() >= ((Fertilizer) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Fertilizer) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity8) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "speed-gro":
                boolean validquantity9 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Fertilizer && ((Fertilizer) item).getName().equals("Speed-Gro") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity9 = true;
                        if (currentPlayer.getGold() >= ((Fertilizer) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Fertilizer) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity9) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "applesapling":
                boolean validquantity10 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.AppleSapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity10 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);

                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());
                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity10) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "apricotsapling":
                boolean validquantity11 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.ApricotSapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity11 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity11) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "cherrysapling":
                boolean validquantity12 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.CherrySapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity12 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity12) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "orangesapling":
                boolean validquantity13 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.OrangeSapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity13 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity13) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "peachsapling":
                boolean validquantity14 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.PeachSapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity14 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity14) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "pomegranatesapling":
                boolean validquantity15 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof TreeSeed && ((TreeSeed) item).getType() == TreeSeedEnums.PomegranateSapling && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity15 = true;
                        if (currentPlayer.getGold() >= ((TreeSeed) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((TreeSeed) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity15) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "basicretainingsoil":
                boolean validquantity16 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Fertilizer && ((Fertilizer) item).getName().equals("Basic Retaining Soil") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity16 = true;
                        if (currentPlayer.getGold() >= ((Fertilizer) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Fertilizer) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity16) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "qualityretainingsoil":
                boolean validquantity17 = false;
                for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
                    if (item instanceof Fertilizer && ((Fertilizer) item).getName().equals("Quality Retaining Soil") && App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {
                        validquantity17 = true;
                        if (currentPlayer.getGold() >= ((Fertilizer) item).getPrice()) {
                            currentPlayer.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - ((Fertilizer) item).getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity17) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "parsnipseeds":
                return handleSeedPurchase(ForagingSeedsEnums.ParsnipSeeds, quantity);
            case "beanstarter":
                return handleSeedPurchase(ForagingSeedsEnums.BeanStarter, quantity);
            case "cauliflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CauliflowerSeeds, quantity);
            case "potatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PotatoSeeds, quantity);
            case "tulipbulb":
                return handleSeedPurchase(ForagingSeedsEnums.TulipBulb, quantity);
            case "kaleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.KaleSeeds, quantity);
            case "jazzseeds":
                return handleSeedPurchase(ForagingSeedsEnums.JazzSeeds, quantity);
            case "garlicseeds":
                return handleSeedPurchase(ForagingSeedsEnums.GarlicSeeds, quantity);
            case "riceshoot":
                return handleSeedPurchase(ForagingSeedsEnums.RiceShoot, quantity);
            case "melonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.MelonSeeds, quantity);
            case "tomatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.TomatoSeeds, quantity);
            case "blueberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BlueberrySeeds, quantity);
            case "pepperseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PepperSeeds, quantity);
            case "wheatseeds":
                return handleSeedPurchase(ForagingSeedsEnums.WheatSeeds, quantity);
            case "radishseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RadishSeeds, quantity);
            case "poppyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PoppySeeds, quantity);
            case "spangleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SpangleSeeds, quantity);
            case "hopsstarter":
                return handleSeedPurchase(ForagingSeedsEnums.HopsStarter, quantity);
            case "cornseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CornSeeds, quantity);
            case "sunflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SunflowerSeeds, quantity);
            case "redcabbageseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RedCabbageSeeds, quantity);
            case "eggplantseeds":
                return handleSeedPurchase(ForagingSeedsEnums.EggplantSeeds, quantity);
            case "pumpkinseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PumpkinSeeds, quantity);
            case "bokchoyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BokChoySeeds, quantity);
            case "yamseeds":
                return handleSeedPurchase(ForagingSeedsEnums.YamSeeds, quantity);
            case "cranberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CranberrySeeds, quantity);
            case "fairyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.FairySeeds, quantity);
            case "amaranthseeds":
                return handleSeedPurchase(ForagingSeedsEnums.AmaranthSeeds, quantity);
            case "grapestarter":
                return handleSeedPurchase(ForagingSeedsEnums.GrapeStarter, quantity);
            case "artichokeseeds":
                return handleSeedPurchase(ForagingSeedsEnums.ArtichokeSeeds, quantity);
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
