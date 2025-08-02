package io.github.group18.Controller;


import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.StoneItem;
import io.github.group18.enums.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarpentersShopController extends GameMenuController implements MenuEnter, ShowCurrentMenu, MarketController<Object> {

    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getCarpentersShopMarket().getStock();
    }

    public static Result build(String nameofBuilding, int x, int y,Player playerrr) {
        nameofBuilding = nameofBuilding.toLowerCase();
        if (nameofBuilding.equals("barn")) {
            if (playerrr.getWood() < 350 ||
                playerrr.getGold() < 6000) {
                return new Result(false, "Your sources are not enough for build!" + playerrr.getWood() + " " + playerrr.getGold());
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building!");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isBarn()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        int newGold = playerrr.getGold() - 6000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 350;
                        playerrr.setWood(newWood);
                        // stone hanooz mondeh
                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 150);
                                break;
                            }
                        }
                        //System.out.println(App.getCurrentGame().getCarpentersShopMarket().isBarn());
                        App.getCurrentGame().getCarpentersShopMarket().setBarn(true);
                        //System.out.println(App.getCurrentGame().getCarpentersShopMarket().isBarn());
                        playerrr.getMyFarm().setMyTavileh(new Tavileh());

                        Tavileh playerTavileh = playerrr.getMyFarm().getMyTavileh();

                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y), new Cord(x + 6, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                                new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)
                        ));
                        playerTavileh.adaptMap(cords);

                        playerrr.getMyFarm().getMyTavileh().setStatus(true);

                        return new Result(true, "**your barn created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("big barn")) {
            if (playerrr.getWood() < 450 ||
                playerrr.getGold() < 12000) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isBigbarn()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        App.getCurrentGame().getCarpentersShopMarket().setBigbarn(true);
                        int newGold = playerrr.getGold() - 6000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 350;
                        playerrr.setWood(newWood);
                        //stone
                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 200);
                                break;
                            }
                        }
                        Object Item = null;
                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof BigBarn && ((BigBarn) item).getCorrectName().equalsIgnoreCase("bigbarn") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        playerrr.getMyFarm().setMyBigBarn(new BigBarn());
                        BigBarn playerBigBarn = playerrr.getMyFarm().getMyBigBarn();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                                new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)
                        ));
                        playerBigBarn.adaptMap(cords);
                        playerrr.getMyFarm().getMyBigBarn().setStatus(true);
                        return new Result(true, "**your big barn created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("deluxe barn")) {
            if (playerrr.getWood() < 550 ||
                playerrr.getGold() < 25000) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isDeluxebarn()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        App.getCurrentGame().getCarpentersShopMarket().setDeluxebarn(true);
                        int newGold = playerrr.getGold() - 25000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 550;
                        playerrr.setWood(newWood);
                        //stone

                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 300);
                                break;
                            }
                        }

                        Object Item = null;
                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof BigBarn && ((BigBarn) item).getCorrectName().equalsIgnoreCase("bigbarn") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                        playerrr.getMyFarm().setMyDeluxeBarn(new DeluxeBarn());
                        DeluxeBarn playerDeluxeBarn = playerrr.getMyFarm().getMyDeluxeBarn();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y), new Cord(x + 6, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                                new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)
                        ));
                        playerDeluxeBarn.adaptMap(cords);
                        playerrr.getMyFarm().getMyDeluxeBarn().setStatus(true);

                        return new Result(true, "**your deluxe barn created**");
                    }
                }

            }
        }
        if (nameofBuilding.equals("coop")) {
            if (playerrr.getWood() < 300 ||
                playerrr.getGold() < 4000 ) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isCoop()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        int newGold = playerrr.getGold() - 4000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 300;
                        playerrr.setWood(newWood);
                        //stone
                        App.getCurrentGame().getCarpentersShopMarket().setCoop(true);

                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 100);
                                break;
                            }
                        }
                        Object Item = null;
                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof Cage && ((Cage) item).getCorrectName().equalsIgnoreCase("coop") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                        playerrr.getMyFarm().setMyCage(new Cage());
                        Cage playerCage = playerrr.getMyFarm().getMyCage();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                        ));
                        playerCage.adaptMap(cords);
                        playerrr.getMyFarm().getMyCage().setStatus(true);
                        return new Result(true, "**your coop created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("big coop")) {
            if (playerrr.getWood() < 400 ||
                playerrr.getGold() < 10000 ) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isBigcoop()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        int newGold = playerrr.getGold() - 4000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 300;
                        playerrr.setWood(newWood);
                        //stone 150
                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 150);
                                break;
                            }
                        }
                        App.getCurrentGame().getCarpentersShopMarket().setBigcoop(true);
                        Object Item = null;
                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof BigCoop && ((BigCoop) item).getCorrectName().equalsIgnoreCase("bigcoop") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                        playerrr.getMyFarm().setMyBigCoop(new BigCoop());
                        BigCoop playerBigCoop = playerrr.getMyFarm().getMyBigCoop();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                        ));
                        playerBigCoop.adaptMap(cords);
                        playerrr.getMyFarm().getMyBigCoop().setStatus(true);
                        return new Result(true, "**your big coop created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("deluxe coop")) {
            if (playerrr.getWood() < 500 ||
                playerrr.getGold() < 20000 ) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    if (App.getCurrentGame().getCarpentersShopMarket().isDeluxecoop()) {
                        founded = true;
                    }
                    if (founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        int newGold = playerrr.getGold() - 20000;
                        playerrr.setGold(newGold);
                        int newWood = playerrr.getWood() - 500;
                        playerrr.setWood(newWood);
                        //stone 200
                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 200);
                                break;
                            }
                        }
                        Object Item = null;
                        App.getCurrentGame().getCarpentersShopMarket().setDeluxecoop(true);

                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof DeluxeCoop && ((DeluxeCoop) item).getCorrectName().equalsIgnoreCase("deluxecoop") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                        playerrr.getMyFarm().setMyDeluxeCoop(new DeluxeCoop());
                        DeluxeCoop playerDeluxeCoop = playerrr.getMyFarm().getMyDeluxeCoop();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                        ));
                        playerDeluxeCoop.adaptMap(cords);
                        playerrr.getMyFarm().getMyDeluxeCoop().setStatus(true);
                        return new Result(true, "**your deluxe coop created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("well")) {
            if (playerrr.getGold() < 1000 ) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3,playerrr);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    boolean founded = false;
                    for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                        if (item instanceof Well && ((Well) item).getCorrectName().equalsIgnoreCase("well") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                            founded = true;
                        }
                    }
                    if (!founded) {
                        return new Result(false, "At this time CarpentersShop doesn't have your item!");
                    } else {
                        Object Item = null;
                        for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                            if (item instanceof Well && ((Well) item).getCorrectName().equalsIgnoreCase("well") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                                Item = item;
                            }
                        }
                        for (Item item : playerrr.getInventory().getItems().keySet()) {
                            if (item instanceof StoneItem) {
                                playerrr.getInventory().removeItem(item, 75);
                                break;
                            }
                        }
                        //App.getCurrentGame().getCarpentersShopMarket().set(true);
                        App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                        playerrr.getMyFarm().setMyWell(new Well());
                        Well playerWell = playerrr.getMyFarm().getMyWell();
                        ArrayList<Cord> cords = new ArrayList<>(List.of(
                                new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y),
                                new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1),
                                new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2)
                        ));
                        playerWell.adaptMap(cords);
                        return new Result(true, "**your well created**");
                    }
                }
            }
        }
        if (nameofBuilding.equals("shipping bin")) {
            if (playerrr.getWood() < 150 ||
                playerrr.getGold() < 250) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean founded = false;
                for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                    if (item instanceof ShippingBin && ((ShippingBin) item).getCorrectName().equalsIgnoreCase("well") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                        founded = true;
                    }
                }
                if (!founded) {
                    return new Result(false, "At this time CarpentersShop doesn't have your item!");
                } else {
                    Object Item = null;
                    for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                        if (item instanceof ShippingBin && ((ShippingBin) item).getCorrectName().equalsIgnoreCase("shippingbi") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) > 0) {
                            Item = item;
                        }
                    }
                    App.getCurrentGame().getCarpentersShopMarket().removeItem(Item, 1);
                    playerrr.getMyFarm().setMyShippingBin(new ShippingBin());
                    ShippingBin playerShippingBin = playerrr.getMyFarm().getMyShippingBin();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(new Cord(x, y)));
                    playerShippingBin.adaptMap(cords);
                    return new Result(true, "**your shipping bin created**");
                }
            }
        }
        return new Result(false, "Your entered name was not found!");
    }

    // for checking map area
    public static boolean isAreaCompletelyEmpty(int topLeftX, int topLeftY, int xlength, int ylength,Player playerrr) {
        ArrayList<ArrayList<Kashi>> Map = App.getCurrentGame().getMap();
//        int index = App.getCurrentGame().getIndexPlayerinControl();
        int playerTopleftx = 0;
        int playerToplefty = 0;
        int playerWidth = 0;
        int playerHeight = 0;
//        switch (index) {
//            case 0:
//                playerTopleftx = App.getCurrentGame().getPlayer1TopLeftx();
//                playerToplefty = App.getCurrentGame().getPlayer1TopLefty();
//                playerWidth = App.getCurrentGame().getPlayer1Width();
//                playerHeight = App.getCurrentGame().getPlayer1Height();
//                break;
//            case 1:
//                playerTopleftx = App.getCurrentGame().getPlayer2TopLeftx();
//                playerToplefty = App.getCurrentGame().getPlayer2TopLefty();
//                playerWidth = App.getCurrentGame().getPlayer2Width();
//                playerHeight = App.getCurrentGame().getPlayer2Height();
//                break;
//            case 2:
//                playerTopleftx = App.getCurrentGame().getPlayer3TopLeftx();
//                playerToplefty = App.getCurrentGame().getPlayer3TopLefty();
//                playerWidth = App.getCurrentGame().getPlayer3Width();
//                playerHeight = App.getCurrentGame().getPlayer3Height();
//                break;
//            case 3:
//                playerTopleftx = App.getCurrentGame().getPlayer3TopLeftx();
//                playerToplefty = App.getCurrentGame().getPlayer3TopLefty();
//                playerWidth = App.getCurrentGame().getPlayer3Width();
//                playerHeight = App.getCurrentGame().getPlayer3Height();
//                break;
//        }
        for (int dx = 0; dx < xlength; dx++) {
            for (int dy = 0; dy < ylength; dy++) {
                int x = topLeftX + dx;
                int y = topLeftY + dy;
                if (x > playerTopleftx + playerWidth) {
                    return false;
                }
                if (y > playerToplefty + playerHeight) {
                    return false;
                }
                if (x < playerTopleftx) {
                    return false;
                }
                if (y < playerToplefty) {
                    return false;
                }
                if (x >= 0 && x < Map.size() && y >= 0 && y < Map.get(x).size()) {
                    Kashi kashi = Map.get(x).get(y);
                    if (kashi.getInside() != null)
                        return false;
                }
            }
        }
        return true;
    }

    public Result purchase(String name, String count,Player playerrr) {
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        Player currentPlayer = playerrr;
        switch (name.toLowerCase()) {
            case "wood": {
                if (currentPlayer.getGold() < 10 * quantity) {
                    return new Result(false, "Your golds are not enough!");
                }
                if (quantity < 0) {
                    return new Result(false, "Your entered count was negative!");
                }
                int newGold = currentPlayer.getGold() - 10 * quantity;
                currentPlayer.setGold(newGold);
                int newWood = currentPlayer.getWood() + quantity;
                currentPlayer.setWood(newWood);
                return new Result(true, "You bought " + quantity + " wood and added to your woods!");
            }
            case "stone":
                boolean validquantity = false;
                for (Object item : App.getCurrentGame().getCarpentersShopMarket().getStock().keySet()) {
                    if (item instanceof StoneItem && ((StoneItem) item).getCorrectName().equalsIgnoreCase("stone") && App.getCurrentGame().getCarpentersShopMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= ((StoneItem) item).getPrice()) {
                            currentPlayer.getInventory().addItem((StoneItem) item, quantity);
                            App.getCurrentGame().getCarpentersShopMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - 20 * quantity);
                            return new Result(true, "You purchased " + quantity + " of " + ((StoneItem) item).getCorrectName());
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
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
