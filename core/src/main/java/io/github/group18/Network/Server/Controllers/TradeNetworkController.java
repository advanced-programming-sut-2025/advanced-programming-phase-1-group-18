package io.github.group18.Network.Server.Controllers;

import io.github.group18.Model.*;

import io.github.group18.Model.Items.Item;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;
import java.util.Map;

public class TradeNetworkController {

    public static boolean handleMessage(Message message, ClientConnectionThread sender) {
        switch (message.getType()) {
            case trade_request:
                return handleTradeRequest(message, sender);
            case trade_response:
                System.out.println("salaleikom");
                return handleTradeResponse(message, sender);
            default:
                return false;
        }
    }

    private static boolean handleTradeRequest(Message message, ClientConnectionThread sender) {
        System.out.println("Trade Request");
        String targetUsername = message.getFromBody("targetUsername");
        System.out.println(targetUsername);
        ClientConnectionThread targetConn = ServerModel.getConnectionByUserName(targetUsername);

        if (targetConn == null) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("status", "error");
            res.put("msg", "Target player not found!");
            sender.sendMessage(new Message(res, Message.Type.trade_result, Message.Menu.trade));
            return true;
        }


        HashMap<String, Object> requestBody = new HashMap<>(message.getBody());
        requestBody.put("fromUser", sender.getUser().getUsername());

        targetConn.sendMessage(new Message(requestBody, Message.Type.trade_offer_received, Message.Menu.trade));
        return true;
    }

    private static boolean handleTradeResponse(Message message, ClientConnectionThread responder) {
        System.out.println("Handling trade response...");

        String fromUser = (String) message.getFromBody("fromUser");
        boolean accepted = (boolean) message.getFromBody("accepted");

        ClientConnectionThread fromConn = ServerModel.getConnectionByUserName(fromUser);

        if (fromConn == null) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("status", "error");
            res.put("msg", "Requesting player not found!");
            responder.sendMessage(new Message(res, Message.Type.trade_result, Message.Menu.trade));
            return true;
        }

        if (accepted) {
            System.out.println("Trade Accepted");
            Result result = processTrade(message, responder, fromConn);

            HashMap<String, Object> res1 = new HashMap<>();
            res1.put("status", result.isSuccessful() ? "success" : "error");
            res1.put("msg", result.getMessage());

            fromConn.sendMessage(new Message(res1, Message.Type.trade_result, Message.Menu.trade));
            responder.sendMessage(new Message(res1, Message.Type.trade_result, Message.Menu.trade));


            recordTrade(
                fromUser,
                responder.getUser().getUsername(),
                result.isSuccessful(),
                result.getMessage(),
                message
            );

        } else {
            HashMap<String, Object> res = new HashMap<>();
            res.put("status", "rejected");
            res.put("msg", "Trade was rejected by target player.");
            fromConn.sendMessage(new Message(res, Message.Type.trade_result, Message.Menu.trade));
            recordTrade(
                fromUser,
                responder.getUser().getUsername(),
                false,
                "Trade was rejected by target player.",
                message
            );
        }

        return true;
    }


    private static Result processTrade(Message message, ClientConnectionThread responder, ClientConnectionThread requester) {
        String tradeType = message.getFromBody("tradeType");
        String mode = message.getFromBody("mode");
        String itemName = message.getFromBody("item");
        int amount = message.getIntFromBody("amount");

        String currentUsername = requester.getUser().getUsername();
        String targetUsername = responder.getUser().getUsername();

        System.out.println(currentUsername);
        System.out.println(targetUsername);

        Player currentPlayer = Game.getPlayerByUsername(currentUsername);
        System.out.println(currentPlayer.getOwner().getUsername());
        Player targetPlayer = Game.getPlayerByUsername(targetUsername);
        System.out.println(targetPlayer.getOwner().getUsername());
        if (tradeType.equalsIgnoreCase("money")) {
            int price = message.getIntFromBody("price");

            return tradeWithMoney(currentPlayer, targetPlayer, mode, itemName, amount, price);

        } else if (tradeType.equalsIgnoreCase("item")) {
            String targetItemName = message.getFromBody("targetItem");
            int targetAmount = message.getIntFromBody("targetAmount");

            return tradeWithItem(currentPlayer, targetPlayer, mode, itemName, amount, targetItemName, targetAmount);
        }

        return new Result(false, "Invalid trade type.");
    }

    private static Result tradeWithMoney(Player currentPlayer, Player targetPlayer, String type,
                                         String itemName, int amount, int price) {

        System.out.println("tradeWithMoney");
        System.out.println(type);
        System.out.println(itemName);
        System.out.println(amount);
        System.out.println(price);
        Inventory currentInventory = currentPlayer.getInventory();
        Map<Item, Pair<Integer, Integer>> currentItems = currentInventory.getItems();

        Item itemToOffer = null;
        for (Item item : currentItems.keySet()) {
            if (item.getCorrectName().equalsIgnoreCase(itemName.replace(" ", ""))) {
                itemToOffer = item;
                break;
            }
        }

        if (itemToOffer == null) {
            System.out.println("You don't have that item to offer!");
            return new Result(false, "You don't have that item to offer!");
        }

        if (type.equalsIgnoreCase("offer")) {

            if (currentItems.get(itemToOffer).first < amount) {
                System.out.println("You don't have enough items to offer!");
                return new Result(false, "You don't have enough items to offer!");
            }

            if (currentPlayer.getGold() < price) {
                System.out.println("You don't have enough gold to offer!");
                return new Result(false, "You don't have enough gold to offer!");
            }

            Inventory targetInventory = targetPlayer.getInventory();


            if (targetInventory.getMaxQuantity() - targetInventory.getItems().size() <= 0) {
                System.out.println("Target player does not have enough space in inventory");
                return new Result(false, "Target player does not have enough space in inventory");
            }


            Pair<Integer, Integer> currentData = currentItems.get(itemToOffer);
            if (currentData.first - amount == 0) {
                currentInventory.removeItem(itemToOffer, amount);
            } else {
                currentItems.put(itemToOffer, new Pair<>(currentData.first - amount, currentData.second));
            }

            currentPlayer.setGold(currentPlayer.getGold() - price);


            Map<Item, Pair<Integer, Integer>> targetItems = targetInventory.getItems();
            if (targetItems.containsKey(itemToOffer)) {
                Pair<Integer, Integer> targetData = targetItems.get(itemToOffer);
                targetItems.put(itemToOffer, new Pair<>(targetData.first + amount, targetData.second));
            } else {
                targetInventory.addItem(itemToOffer, amount);
            }
            targetPlayer.setGold(targetPlayer.getGold() + price);

            System.out.println("Trade with money completed successfully!");
            return new Result(true, "Trade with money completed successfully!");

        } else if (type.equalsIgnoreCase("request")) {

            if (currentPlayer.getGold() < price) {
                System.out.println("You don't have enough gold to request!");
                return new Result(false, "You don't have enough gold to request!");
            }

            Inventory targetInventory = targetPlayer.getInventory();
            Map<Item, Pair<Integer, Integer>> targetItems = targetInventory.getItems();

            Item itemToRequest = null;
            for (Item item : targetItems.keySet()) {
                if (item.getCorrectName().equalsIgnoreCase(itemName.replace(" ", ""))) {
                    itemToRequest = item;
                    break;
                }
            }

            if (itemToRequest == null) {
                return new Result(false, "Target player doesn't have that item to give you!");
            }

            if (targetItems.get(itemToRequest).first < amount) {
                return new Result(false, "Target player doesn't have enough items to offer!");
            }


            if (currentInventory.getMaxQuantity() - currentInventory.getItems().size() <= 0) {
                return new Result(false, "You don't have enough space in your inventory");
            }


            Pair<Integer, Integer> targetData = targetItems.get(itemToRequest);
            if (targetData.first - amount == 0) {
                targetInventory.removeItem(itemToRequest, amount);
            } else {
                targetItems.put(itemToRequest, new Pair<>(targetData.first - amount, targetData.second));
            }
            targetPlayer.setGold(targetPlayer.getGold() + price);
            currentInventory.addItem(itemToRequest, amount);
            currentPlayer.setGold(currentPlayer.getGold() - price);

            return new Result(true, "Trade request with money completed successfully!");
        }

        return new Result(false, "Invalid trade type.");
    }

    private static Result tradeWithItem(Player currentPlayer, Player targetPlayer, String type,
                                        String itemName, int amount, String targetItemName, int targetAmount) {

        Inventory currentInventory = currentPlayer.getInventory();
        Inventory targetInventory = targetPlayer.getInventory();

        Map<Item, Pair<Integer, Integer>> currentItems = currentInventory.getItems();
        Map<Item, Pair<Integer, Integer>> targetItems = targetInventory.getItems();

        Item itemToOffer = null;
        Item targetItemToReceive = null;

        if (type.equalsIgnoreCase("offer")) {
            for (Item item : currentItems.keySet()) {
                if (item.getCorrectName().equalsIgnoreCase(itemName.replace(" ", ""))) {
                    itemToOffer = item;
                    break;
                }
            }
            if (itemToOffer == null) {
                return new Result(false, "You don't have the item to offer!");
            }
            if (currentItems.get(itemToOffer).first < amount) {
                return new Result(false, "You don't have enough items to offer!");
            }

            for (Item item : targetItems.keySet()) {
                if (item.getCorrectName().equalsIgnoreCase(targetItemName.replace(" ", ""))) {
                    targetItemToReceive = item;
                    break;
                }
            }
            if (targetItemToReceive == null) {
                return new Result(false, "Target player doesn't have the item you want!");
            }
            if (targetItems.get(targetItemToReceive).first < targetAmount) {
                return new Result(false, "Target player doesn't have enough items to offer!");
            }


            if (targetInventory.getMaxQuantity() - targetInventory.getItems().size() <= 0 ||
                currentInventory.getMaxQuantity() - currentInventory.getItems().size() <= 0) {
                return new Result(false, "Not enough space in inventory for trade.");
            }


            Pair<Integer, Integer> currentData = currentItems.get(itemToOffer);
            if (currentData.first - amount == 0) {
                currentInventory.removeItem(itemToOffer, amount);
            } else {
                currentItems.put(itemToOffer, new Pair<>(currentData.first - amount, currentData.second));
            }

            Pair<Integer, Integer> targetData = targetItems.get(targetItemToReceive);
            if (targetData.first - targetAmount == 0) {
                targetInventory.removeItem(targetItemToReceive, targetAmount);
            } else {
                targetItems.put(targetItemToReceive, new Pair<>(targetData.first - targetAmount, targetData.second));
            }

            if (targetItems.containsKey(itemToOffer)) {
                Pair<Integer, Integer> targetItemData = targetItems.get(itemToOffer);
                targetItems.put(itemToOffer, new Pair<>(targetItemData.first + amount, targetItemData.second));
            } else {
                targetInventory.addItem(itemToOffer, amount);
            }

            if (currentItems.containsKey(targetItemToReceive)) {
                Pair<Integer, Integer> currentItemData = currentItems.get(targetItemToReceive);
                currentItems.put(targetItemToReceive, new Pair<>(currentItemData.first + targetAmount, currentItemData.second));
            } else {
                currentInventory.addItem(targetItemToReceive, targetAmount);
            }

            return new Result(true, "Trade with items completed successfully!");

        } else if (type.equalsIgnoreCase("request")) {
            for (Item item : currentItems.keySet()) {
                if (item.getCorrectName().equalsIgnoreCase(itemName.replace(" ", ""))) {
                    itemToOffer = item;
                    break;
                }
            }
            for (Item item : targetItems.keySet()) {
                if (item.getCorrectName().equalsIgnoreCase(targetItemName.replace(" ", ""))) {
                    targetItemToReceive = item;
                    break;
                }
            }
            if (targetItemToReceive == null) {
                return new Result(false, "Target player doesn't have the item you're requesting!");
            }
            if (targetItems.get(targetItemToReceive).first < targetAmount) {
                return new Result(false, "Target player doesn't have enough items to offer!");
            }


            if (currentInventory.getMaxQuantity() - currentInventory.getItems().size() <= 0) {
                return new Result(false, "You don't have enough space in your inventory");
            }


            Pair<Integer, Integer> targetData = targetItems.get(targetItemToReceive);
            if (targetData.first - targetAmount == 0) {
                targetInventory.removeItem(targetItemToReceive, targetAmount);
            } else {
                targetItems.put(targetItemToReceive, new Pair<>(targetData.first - targetAmount, targetData.second));
            }
            currentInventory.addItem(targetItemToReceive, targetAmount);

            return new Result(true, "Trade request with items completed successfully!");
        }

        return new Result(false, "Invalid trade type.");
    }


    private static void recordTrade(String fromUser, String toUser, boolean success, String msg, Message originalMsg) {
        Map<String, Object> record = new HashMap<>();
        record.put("from", fromUser);
        record.put("to", toUser);
        record.put("status", success ? "success" : "error");
        record.put("message", msg);
        record.put("tradeType", originalMsg.getFromBody("tradeType"));
        record.put("item", originalMsg.getFromBody("item"));
        record.put("amount", originalMsg.getIntFromBody("amount"));
        record.put("timestamp", System.currentTimeMillis());

        ServerModel.addTradeHistory(record);

        HashMap<String, Object> body = new HashMap<>();
        body.put("history", ServerModel.getTradeHistory());

        Message historyUpdate = new Message(
            body,
            Message.Type.trade_history_update,
            Message.Menu.trade
        );

        for (ClientConnectionThread conn : ServerModel.getConnections()) {
            conn.sendMessage(historyUpdate);
        }
    }


}

