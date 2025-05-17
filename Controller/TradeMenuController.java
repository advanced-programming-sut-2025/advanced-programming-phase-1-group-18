package Controller;

import Model.*;
import Model.Items.Item;
import enums.Menu;
import enums.TradeStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TradeMenuController extends GameMenuController implements MenuEnter, ShowCurrentMenu{


    public void checkForIncomingTrades(Player currentPlayer) {
        List<Trade> allTrades = TradeManager.getAllTrades();
        boolean found = false;

        for (Trade trade : allTrades) {
            if (trade.getReceiver() != null &&
                    trade.getReceiver().equals(currentPlayer) &&
                    trade.getStatus() == TradeStatus.PENDING) {

                System.out.println("📬 You have a new trade request from: " + trade.getSender().getOwner().getUsername() +
                        " → Type: " + trade.getType() +
                        ", Item: " + trade.getItem().getCorrectName() +
                        ", Amount: " + trade.getAmount());

                found = true;
            }
        }

        if (!found) {
            System.out.println("✅ No new trade requests.");
        }
    }


    public void startTrade()
    {
        checkForIncomingTrades( App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()));
        for(Player player : App.getCurrentGame().getPlayers())
        {
            System.out.printf("player : %s\n",player.getOwner().getUsername());
        }
    }
    public Result tradeWithMoney(String username, String type, String item, int amount, int price) {

        Player targetPlayer = findPlayerByUsername(username);
        if (targetPlayer == null) {
            return new Result(false, "Your entered username was not found!");
        }

        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Integer> itemInventory = inventory.getItems();

        if (type.equalsIgnoreCase("offer")) {

            Item itemToOffer = null;
            for (Item item1 : itemInventory.keySet()) {
                if (item1.getCorrectName().equals(item.toLowerCase().replace(" ",""))) {
                    itemToOffer = item1;
                    break;
                }
            }

            if (itemToOffer == null) {
                return new Result(false, "You don't have that item to offer!");
            }

            if (itemInventory.get(itemToOffer) < amount) {
                return new Result(false, "You don't have enough items to offer!");
            }

            if (currentPlayer.getGold() < price) {
                return new Result(false, "You don't have enough gold to offer!");
            }

            Trade trade = new Trade("offer", itemToOffer, amount, price, null, null, currentPlayer);
            trade.setReceiver(targetPlayer);
            App.getCurrentGame().getTrades().add(trade);
            TradeManager.addTrade(trade);
            return new Result(true, "Trade offer sent successfully. Awaiting response from target player.");
        }
        else if (type.equalsIgnoreCase("request")) {

            if (currentPlayer.getGold() < price) {
                return new Result(false, "You don't have enough gold to request!");
            }

            Item itemToRequest = null;
            for (Item item1 : targetPlayer.getInventory().getItems().keySet()) {
                if (item1.getCorrectName().equals(item.toLowerCase().replace(" ",""))) {
                    itemToRequest = item1;
                    break;
                }
            }
            if(itemToRequest == null) {
                return new Result(false, "destination don't have that item to give you!");
            }


            Trade trade = new Trade("request", itemToRequest, amount, price, null, null, currentPlayer);
            trade.setReceiver(targetPlayer);
            App.getCurrentGame().getTrades().add(trade);
            TradeManager.addTrade(trade);
            return new Result(true, "Trade request sent successfully. Awaiting response from target player.");
        }

        return new Result(false, "Invalid trade type.");
    }


    public Result tradeWithItem(String username, String type, String item, int amount, String targetItem, int targetAmount) {
        Player targetPlayer = findPlayerByUsername(username);
        if (targetPlayer == null) {
            return new Result(false, "Your entered username was not found!");
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Inventory inventory = currentPlayer.getInventory();
        Map<Item, Integer> itemInventory = inventory.getItems();

        if (type.equalsIgnoreCase("offer")) {

            Item itemToOffer = null;
            for (Item item1 : itemInventory.keySet()) {
                if (item1.getName().equals(item.toLowerCase().replace(" ",""))) {
                    itemToOffer = item1;
                    break;
                }
            }

            if (itemToOffer == null) {
                return new Result(false, "You don't have the item to offer!");
            }


            if (itemInventory.get(itemToOffer) < amount) {
                return new Result(false, "You don't have enough items to offer!");
            }


            Item targetItemToReceive = null;
            for (Item item1 : targetPlayer.getInventory().getItems().keySet()) {
                if (item1.getCorrectName().equals(targetItem)) {
                    targetItemToReceive = item1;
                    break;
                }
            }

            if (targetItemToReceive == null) {
                return new Result(false, "Target player doesn't have the item you want!");
            }


            if (targetPlayer.getInventory().getItemQuantity(targetItemToReceive) < targetAmount) {
                return new Result(false, "Target player doesn't have enough items to offer!");
            }


            Trade trade = new Trade("offer", itemToOffer, amount, null, targetItemToReceive, targetAmount, currentPlayer);
            trade.setReceiver(targetPlayer);
            App.getCurrentGame().getTrades().add(trade);
            TradeManager.addTrade(trade);
            return new Result(true, "Trade offer sent successfully. Awaiting response from target player.");
        }
        else if (type.equalsIgnoreCase("request")) {

            Item itemToRequest = null;
            for (Item item1 : itemInventory.keySet()) {
                if (item1.getCorrectName().equals(item.toLowerCase().replace(" ",""))) {
                    itemToRequest = item1;
                    break;
                }
            }

            System.out.println(itemToRequest.getCorrectName());
            Item targetItemToReceive = null;
            for (Item item1 : targetPlayer.getInventory().getItems().keySet()) {
                if (item1.getCorrectName().equals(targetItem.toLowerCase().replace(" ",""))) {
                    targetItemToReceive = item1;
                    break;
                }
            }

            if (targetItemToReceive == null) {
                return new Result(false, "Target player doesn't have the item you're requesting!");
            }
            Trade trade = new Trade("request", itemToRequest, amount, null, targetItemToReceive, targetAmount, currentPlayer);
            trade.setReceiver(targetPlayer);
            App.getCurrentGame().getTrades().add(trade);
            TradeManager.addTrade(trade);
            return new Result(true, "Trade request sent successfully. Awaiting response from target player.");
        }

        return new Result(false, "Invalid trade type.");
    }

    public void tradeList()
    {
        List<Trade> allTrades = TradeManager.getAllTrades();
        for (Trade trade : allTrades) {
            System.out.println("=== Trade ID: " + trade.getId() + " ===");
            System.out.println("Status: " + trade.getStatus());
            System.out.println("Type: " + trade.getType());
            System.out.println("Item: " + trade.getItem().getCorrectName());
            System.out.println("Amount: " + trade.getAmount());

            if (trade.getPrice() != null)
                System.out.println("Price: " + trade.getPrice());

            if (trade.getTargetItem() != null)
                System.out.println("Target Item: " + trade.getTargetItem().getCorrectName());

            if (trade.getTargetAmount() != null)
                System.out.println("Target Amount: " + trade.getTargetAmount());

            System.out.println("Sender: " + trade.getSender().getOwner().getUsername());

            if (trade.getReceiver() != null)
                System.out.println("Receiver: " + trade.getReceiver().getOwner().getUsername());

            System.out.println();
        }

    }
    public Result tradeResponse(String acceptOrReject, int tradeId) {
        Trade trade = findTradeById(tradeId);
        if (trade == null) {
            return new Result(false, "Trade with given ID not found!");
        }

        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        if (trade.getReceiver() != currentPlayer) {
            return new Result(false, "This trade is not directed to you.");
        }


        if (trade.getStatus() != TradeStatus.PENDING) {
            return new Result(false, "This trade has already been processed.");
        }


        if (acceptOrReject.equalsIgnoreCase("accept")) {
            trade.setStatus(TradeStatus.ACCEPTED);
            return executeTrade(trade);
        }

        else if (acceptOrReject.equalsIgnoreCase("reject")) {
            trade.setStatus(TradeStatus.REJECTED);
            return new Result(true, "Trade request has been rejected.");
        }

        else {
            return new Result(false, "Invalid response. Please use 'accept' or 'reject'.");
        }
    }

    private Result executeTrade(Trade trade) {
        Player sender = trade.getSender();
        Player receiver = trade.getReceiver();


        if (trade.getType().equals("offer")) {

            if (trade.getPrice() != null) {
                sender.setGold(sender.getGold() - trade.getPrice());
                receiver.setGold(receiver.getGold() + trade.getPrice());
            }
            if (trade.getItem() != null) {
                sender.getInventory().removeItem(trade.getItem(), trade.getAmount());
                receiver.getInventory().addItem(trade.getItem(), trade.getAmount());
            }


            if (trade.getTargetItem() != null) {
                sender.getInventory().removeItem(trade.getTargetItem(), trade.getTargetAmount());
                receiver.getInventory().addItem(trade.getTargetItem(), trade.getTargetAmount());
            }

            return new Result(true, "Trade has been successfully executed.");
        }
        else if (trade.getType().equals("request")) {


            if (trade.getPrice() != null && receiver.getGold() < trade.getPrice()) {
                return new Result(false, "Receiver does not have enough gold to fulfill the request.");
            }


            if (trade.getItem() != null) {
                if (!receiver.getInventory().hasItem(trade.getItem()) ||
                        receiver.getInventory().getItems().get(trade.getItem()) < trade.getAmount()) {
                    System.out.println( receiver.getInventory().getItems().get(trade.getItem()));
                    System.out.println("****NOOOOOOOO****");
                    return new Result(false, "Receiver does not have enough target items to fulfill the request.");
                }
            }


            if (trade.getPrice() != null) {
                receiver.setGold(receiver.getGold() - trade.getPrice());
                sender.setGold(sender.getGold() + trade.getPrice());
            }


            if (trade.getItem() != null) {
                receiver.getInventory().removeItem(trade.getItem(), trade.getAmount());
                sender.getInventory().addItem(trade.getItem(), trade.getAmount());
            }

            return new Result(true, "Trade has been successfully executed.");
        }


        return new Result(false, "Failed to execute trade.");
    }


    public void tradeHistory() {

        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());

        Game currentGame = App.getCurrentGame();
        List<Trade> trades = currentGame.getTrades();


        List<Trade> currentPlayerTrades = new ArrayList<>();
        for (Trade trade : trades) {
            if (trade.getReceiver() != null && trade.getReceiver().equals(currentPlayer)) {
                currentPlayerTrades.add(trade);
            }
        }
        if (currentPlayerTrades.isEmpty()) {
            System.out.println("No trade history available for the current player.");
        } else {

            for (Trade trade : currentPlayerTrades) {
                System.out.printf("Trade ID: %d\n", trade.getId());
                System.out.printf("Type: %s\n", trade.getType());
                System.out.printf("Item: %s\n", trade.getItem() != null ? trade.getItem().getCorrectName() : "—");
                System.out.printf("Amount: %d\n", trade.getAmount());
                if (trade.getPrice() != null) {
                    System.out.printf("Price: %d\n", trade.getPrice());
                }
                if (trade.getTargetItem() != null) {
                    System.out.printf("Target Item: %s\n", trade.getTargetItem().getCorrectName());
                    System.out.printf("Target Amount: %d\n", trade.getTargetAmount());
                }
                System.out.printf("Status: %s\n", trade.getStatus().name());
                System.out.println("----------------------------");
            }
        }
    }

    public Player findPlayerByUsername(String username)
    {
        for(Player player : App.getCurrentGame().getPlayers())
        {
            if(player.getOwner().getUsername().equals(username))
            {
                return player;
            }
        }
        return null;
    }
    public Trade findTradeById(int tradeId) {
        for (Trade trade : App.getCurrentGame().getTrades()) {
            if (trade.getId() == tradeId) {
                return trade;
            }
        }
        return null;
    }

    public void menuEnter(String menuName) {
        //from trademenu we can move to gamemenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
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
