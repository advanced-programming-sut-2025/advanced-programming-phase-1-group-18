package io.github.group18.Controller;

import io.github.group18.Model.App;
import io.github.group18.Model.Items.FishingPole;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Player;
import io.github.group18.Model.Result;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.Menu;

import java.util.HashMap;

public class FishShopController implements MenuEnter, ShowCurrentMenu, MarketController<FishingPole> {
    @Override
    public HashMap<FishingPole, Integer> getStock() {
        return App.getCurrentGame().getFishShopMarket().getStock();
    }

    @Override
    public Result purchase(String name, String count, Player playerrr) {
        return null;
    }

    public static Result purchase1(String name, String count, Player playerrr, HashMap<FishingPole, Integer> stock) {
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        Player currentPlayer = playerrr;
        switch (name.toLowerCase()) {
            case "bamboofishingpole":
                boolean validquantity1 = false;
                for (FishingPole item : stock.keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Bamboo") && stock.get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("item", item.getCorrectName());
                            body.put("quantity", String.valueOf(quantity));
                            Message message = new Message(body, Message.Type.set_fishing_stock, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(message);


                            HashMap<String, Object> map = new HashMap<>();
                            map.put("username", playerrr.getOwner().getUsername());
                            map.put("gold", String.valueOf(-1 * item.getCorrectPrice()));
                            Message send = new Message(map, Message.Type.set_gold1, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(send);

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

            case "trainingfishingpole":
                boolean validquantity2 = false;
                for (FishingPole item : stock.keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Training") && stock.get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("item", item.getCorrectName());
                            body.put("quantity", String.valueOf(quantity));
                            Message message = new Message(body, Message.Type.set_fishing_stock, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(message);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("username", playerrr.getOwner().getUsername());
                            map.put("gold", String.valueOf(-1 * item.getCorrectPrice()));
                            Message send = new Message(map, Message.Type.set_gold1, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(send);

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

            case "fiberglassfishingpole":
                boolean validquantity3 = false;
                for (FishingPole item : stock.keySet()) {
//                    System.out.println(quantity + " " + item.getCorrectName());
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Fiberglass") && stock.get(item) >= quantity) {
                        validquantity3 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("item", item.getCorrectName());
                            body.put("quantity", String.valueOf(quantity));
                            Message message = new Message(body, Message.Type.set_fishing_stock, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(message);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("username", playerrr.getOwner().getUsername());
                            map.put("gold", String.valueOf(-1 * item.getCorrectPrice()));
                            Message send = new Message(map, Message.Type.set_gold1, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(send);

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

            case "iridiumfishingpole":
                boolean validquantity4 = false;
                for (FishingPole item : stock.keySet()) {
                    if (item instanceof FishingPole && ((FishingPole) item).getJens().equalsIgnoreCase("Iridium") && stock.get(item) >= quantity) {
                        validquantity4 = true;
                        if (currentPlayer.getGold() >= item.getCorrectPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            HashMap<String, Object> body = new HashMap<>();
                            body.put("item", item.getCorrectName());
                            body.put("quantity", String.valueOf(quantity));
                            Message message = new Message(body, Message.Type.set_fishing_stock, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(message);
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("username", playerrr.getOwner().getUsername());
                            map.put("gold", String.valueOf(-1 * item.getCorrectPrice()));
                            Message send = new Message(map, Message.Type.set_gold1, Message.Menu.game);
                            ClientModel.getServerConnectionThread().sendMessage(send);

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

    public void menuEnter(String menuName, Player playerrr) {
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
