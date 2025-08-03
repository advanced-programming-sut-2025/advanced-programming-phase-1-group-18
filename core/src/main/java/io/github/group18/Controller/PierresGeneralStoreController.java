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

    public PierresGeneralStoreController() {
    }

    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getPierresGeneralStoreMarket().getStock();
    }

    private Result handleSeedPurchase(ForagingSeedsEnums seedType, int quantity,Player playerrr) {
        for (Object item : App.getCurrentGame().getPierresGeneralStoreMarket().getStock().keySet()) {
            if (item instanceof ForagingSeed && ((ForagingSeed) item).getType() == seedType &&
                    App.getCurrentGame().getPierresGeneralStoreMarket().getStock().get(item) >= quantity) {

                if (playerrr.getGold() >= ((ForagingSeed) item).getPrice()) {
                    playerrr.getInventory().addItem((ForagingSeed) item, quantity);
                    App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
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
        switch (name.toLowerCase()) {
            case "large pack":
                if (App.getCurrentGame().getPierresGeneralStoreMarket().isLargePackBougth()) {
                    return new Result(false, "Out of Stock");
                } else {
                    if (playerrr.getGold() > 2000) {
                        playerrr.setGold(playerrr.getGold() - 2000);
                        playerrr.getInventory().setMaxQuantity(24);
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
                    if (playerrr.getGold() > 2000) {
                        playerrr.setGold(playerrr.getGold() - 10000);
                        playerrr.getInventory().setMaxQuantity(Integer.MAX_VALUE);
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
                        if (playerrr.getGold() >= ((Food) item).getPrice()) {
                            playerrr.getInventory().addItem((Food) item, quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Food) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((AllCrop) item).getCorrectPrice()) {
                            playerrr.getInventory().addItem(((AllCrop) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((AllCrop) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((MarketProducts) item).getPrice()) {
                            playerrr.getInventory().addItem(((MarketProducts) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((MarketProducts) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((MarketProducts) item).getPrice()) {
                            playerrr.getInventory().addItem(((MarketProducts) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((MarketProducts) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem(((Craftingrecipe) item), quantity);
                            //currentPlayer.getCraftingRecipes().add((Craftingrecipe) item);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Craftingrecipe) item).getPrice());

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
                        if (playerrr.getGold() >= ((Craftingrecipe) item).getPrice()) {
//                            currentPlayer.getInventory().addItem(((Craftingrecipe) item), quantity);
                            //currentPlayer.getCraftingRecipes().add((Craftingrecipe) item);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Craftingrecipe) item).getPrice());

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
                        if (playerrr.getGold() >= ((Food) item).getPrice()) {
                            playerrr.getInventory().addItem(((Food) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);

                            playerrr.setGold(playerrr.getGold() - ((Food) item).getCorrectPrice());
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
                        if (playerrr.getGold() >= ((ArtisanGoods) item).getPrice()) {
                            playerrr.getInventory().addItem(((ArtisanGoods) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((ArtisanGoods) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((ArtisanGoods) item).getPrice()) {
                            playerrr.getInventory().addItem(((ArtisanGoods) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((ArtisanGoods) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((Fertilizer) item).getPrice()) {
                            playerrr.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Fertilizer) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((Fertilizer) item).getPrice()) {
                            playerrr.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Fertilizer) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);

                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());
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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((TreeSeed) item).getPrice()) {
                            playerrr.getInventory().addItem(((TreeSeed) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((TreeSeed) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((Fertilizer) item).getPrice()) {
                            playerrr.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Fertilizer) item).getCorrectPrice());

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
                        if (playerrr.getGold() >= ((Fertilizer) item).getPrice()) {
                            playerrr.getInventory().addItem(((Fertilizer) item), quantity);
                            App.getCurrentGame().getPierresGeneralStoreMarket().removeItem(item, quantity);
                            playerrr.setGold(playerrr.getGold() - ((Fertilizer) item).getCorrectPrice());

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
                return handleSeedPurchase(ForagingSeedsEnums.ParsnipSeeds, quantity,playerrr);
            case "beanstarter":
                return handleSeedPurchase(ForagingSeedsEnums.BeanStarter, quantity,playerrr);
            case "cauliflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CauliflowerSeeds, quantity,playerrr);
            case "potatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PotatoSeeds, quantity,playerrr);
            case "tulipbulb":
                return handleSeedPurchase(ForagingSeedsEnums.TulipBulb, quantity,playerrr);
            case "kaleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.KaleSeeds, quantity,playerrr);
            case "jazzseeds":
                return handleSeedPurchase(ForagingSeedsEnums.JazzSeeds, quantity,playerrr);
            case "garlicseeds":
                return handleSeedPurchase(ForagingSeedsEnums.GarlicSeeds, quantity,playerrr);
            case "riceshoot":
                return handleSeedPurchase(ForagingSeedsEnums.RiceShoot, quantity,playerrr);
            case "melonseeds":
                return handleSeedPurchase(ForagingSeedsEnums.MelonSeeds, quantity,playerrr);
            case "tomatoseeds":
                return handleSeedPurchase(ForagingSeedsEnums.TomatoSeeds, quantity,playerrr);
            case "blueberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BlueberrySeeds, quantity,playerrr);
            case "pepperseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PepperSeeds, quantity,playerrr);
            case "wheatseeds":
                return handleSeedPurchase(ForagingSeedsEnums.WheatSeeds, quantity,playerrr);
            case "radishseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RadishSeeds, quantity,playerrr);
            case "poppyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PoppySeeds, quantity,playerrr);
            case "spangleseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SpangleSeeds, quantity,playerrr);
            case "hopsstarter":
                return handleSeedPurchase(ForagingSeedsEnums.HopsStarter, quantity,playerrr);
            case "cornseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CornSeeds, quantity,playerrr);
            case "sunflowerseeds":
                return handleSeedPurchase(ForagingSeedsEnums.SunflowerSeeds, quantity,playerrr);
            case "redcabbageseeds":
                return handleSeedPurchase(ForagingSeedsEnums.RedCabbageSeeds, quantity,playerrr);
            case "eggplantseeds":
                return handleSeedPurchase(ForagingSeedsEnums.EggplantSeeds, quantity,playerrr);
            case "pumpkinseeds":
                return handleSeedPurchase(ForagingSeedsEnums.PumpkinSeeds, quantity,playerrr);
            case "bokchoyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.BokChoySeeds, quantity,playerrr);
            case "yamseeds":
                return handleSeedPurchase(ForagingSeedsEnums.YamSeeds, quantity,playerrr);
            case "cranberryseeds":
                return handleSeedPurchase(ForagingSeedsEnums.CranberrySeeds, quantity,playerrr);
            case "fairyseeds":
                return handleSeedPurchase(ForagingSeedsEnums.FairySeeds, quantity,playerrr);
            case "amaranthseeds":
                return handleSeedPurchase(ForagingSeedsEnums.AmaranthSeeds, quantity,playerrr);
            case "grapestarter":
                return handleSeedPurchase(ForagingSeedsEnums.GrapeStarter, quantity,playerrr);
            case "artichokeseeds":
                return handleSeedPurchase(ForagingSeedsEnums.ArtichokeSeeds, quantity,playerrr);
            default:
                return new Result(false, "Invalid product name");
        }

        return null;
    }

    public void menuEnter(String menuName,Player playerrrplayerrr) {
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
