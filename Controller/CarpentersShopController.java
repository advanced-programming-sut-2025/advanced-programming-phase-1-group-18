package Controller;

import Model.*;
import Model.Items.Mineral;
import enums.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CarpentersShopController extends GameMenuController implements MenuEnter, ShowCurrentMenu, MarketController<Object> {
    @Override
    public HashMap<Object, Integer> getStock() {
        return App.getCurrentGame().getCarpentersShopMarket().getStock();
    }

    public Result build(String nameofBuilding, int x, int y) {
        nameofBuilding = nameofBuilding.toLowerCase();
        if (nameofBuilding.equals("barn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 350 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 6000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 150) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building!");
                } else {
                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 6000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 350;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    // stone hanooz mondeh

                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyTavileh(new Tavileh());
                    Tavileh playerTavileh = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y), new Cord(x + 6, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                            new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)


                    ));
                    playerTavileh.adaptMap(cords);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().setStatus(true);
                    return new Result(true, "**your barn created**");
                }
            }
        }
        if (nameofBuilding.equals("big barn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 450 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 12000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 200) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {

                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 6000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 350;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    //stone


                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyBigBarn(new BigBarn());
                    BigBarn playerBigBarn = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                            new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)
                    ));
                    playerBigBarn.adaptMap(cords);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().setStatus(true);
                    return new Result(true, "**your big barn created**");
                }
            }
        }
        if (nameofBuilding.equals("deluxe barn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 550 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 25000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 300) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 7, 4);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 25000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 550;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    //stone 300
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyDeluxeBarn(new DeluxeBarn());
                    DeluxeBarn playerDeluxeBarn = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y), new Cord(x + 6, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1), new Cord(x + 6, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2), new Cord(x + 6, y + 2),
                            new Cord(x, y + 3), new Cord(x + 1, y + 3), new Cord(x + 2, y + 3), new Cord(x + 3, y + 3), new Cord(x + 4, y + 3), new Cord(x + 5, y + 3), new Cord(x + 6, y + 3)
                    ));
                    playerDeluxeBarn.adaptMap(cords);

                    return new Result(true, "**your deluxe barn created**");
                }

            }
        }
        if (nameofBuilding.equals("coop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 300 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 4000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 100) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 4000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 300;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    //stone
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyCage(new Cage());
                    Cage playerCage = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                    ));
                    playerCage.adaptMap(cords);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().setStatus(true);
                    return new Result(true, "**your coop created**");
                }
            }
        }
        if (nameofBuilding.equals("big coop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 400 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 10000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 150) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 4000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 300;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    //stone 150

                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyBigCoop(new BigCoop());
                    BigCoop playerBigCoop = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                    ));
                    playerBigCoop.adaptMap(cords);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().setStatus(true);
                    return new Result(true, "**your big coop created**");

                }
            }
        }
        if (nameofBuilding.equals("deluxe coop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 500 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 20000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 200) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 20000;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
                    int newWood = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() - 500;
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setWood(newWood);
                    //stone 200
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyDeluxeCoop(new DeluxeCoop());
                    DeluxeCoop playerDeluxeCoop = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop();
                    ArrayList<Cord> cords = new ArrayList<>(List.of(
                            new Cord(x, y), new Cord(x + 1, y), new Cord(x + 2, y), new Cord(x + 3, y), new Cord(x + 4, y), new Cord(x + 5, y),
                            new Cord(x, y + 1), new Cord(x + 1, y + 1), new Cord(x + 2, y + 1), new Cord(x + 3, y + 1), new Cord(x + 4, y + 1), new Cord(x + 5, y + 1),
                            new Cord(x, y + 2), new Cord(x + 1, y + 2), new Cord(x + 2, y + 2), new Cord(x + 3, y + 2), new Cord(x + 4, y + 2), new Cord(x + 5, y + 2)

                    ));
                    playerDeluxeCoop.adaptMap(cords);
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().setStatus(true);
                    return new Result(true, "**your deluxe coop created**");
                }
            }
        }
        if (nameofBuilding.equals("well")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 1000 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().size() < 200) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                boolean isOkayforbuild = isAreaCompletelyEmpty(x, y, 6, 3);
                if (!isOkayforbuild) {
                    return new Result(false, "That area is not OKAY for building");
                } else {
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyWell(new Well());
                    Well playerWell = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyWell();
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
        if (nameofBuilding.equals("shipping bin")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 150 ||
                    App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 250) {
                return new Result(false, "Your sources are not enough for build!");
            } else {
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyShippingBin(new ShippingBin());
                ShippingBin playerShippingBin = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyShippingBin();
                ArrayList<Cord> cords = new ArrayList<>(List.of(new Cord(x, y)));
                playerShippingBin.adaptMap(cords);
                return new Result(true, "**your shipping bin created**");
            }
        }
        return new Result(false, "Your entered name was not found!");
    }

    // for checking map area
    public boolean isAreaCompletelyEmpty(int topLeftX, int topLeftY, int xlength, int ylength) {
        ArrayList<ArrayList<Kashi>> Map = App.getCurrentGame().getMap();
        int index = App.getCurrentGame().getIndexPlayerinControl();
        int playerTopleftx = 0;
        int playerToplefty = 0;
        int playerWidth = 0;
        int playerHeight = 0;
        switch (index) {
            case 0:
                playerTopleftx = App.getCurrentGame().getPlayer1TopLeftx();
                playerToplefty = App.getCurrentGame().getPlayer1TopLefty();
                playerWidth = App.getCurrentGame().getPlayer1Width();
                playerHeight = App.getCurrentGame().getPlayer1Height();
                break;
            case 1:
                playerTopleftx = App.getCurrentGame().getPlayer2TopLeftx();
                playerToplefty = App.getCurrentGame().getPlayer2TopLefty();
                playerWidth = App.getCurrentGame().getPlayer2Width();
                playerHeight = App.getCurrentGame().getPlayer2Height();
                break;
            case 2:
                playerTopleftx = App.getCurrentGame().getPlayer3TopLeftx();
                playerToplefty = App.getCurrentGame().getPlayer3TopLefty();
                playerWidth = App.getCurrentGame().getPlayer3Width();
                playerHeight = App.getCurrentGame().getPlayer3Height();
                break;
            case 3:
                playerTopleftx = App.getCurrentGame().getPlayer3TopLeftx();
                playerToplefty = App.getCurrentGame().getPlayer3TopLefty();
                playerWidth = App.getCurrentGame().getPlayer3Width();
                playerHeight = App.getCurrentGame().getPlayer3Height();
                break;
        }
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