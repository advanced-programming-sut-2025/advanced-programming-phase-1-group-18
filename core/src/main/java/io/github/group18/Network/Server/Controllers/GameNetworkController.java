package io.github.group18.Network.Server.Controllers;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.FishingPole;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.ActionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameNetworkController {
    public static void handleMessage(Message message, ClientConnectionThread clientConnectionThread) {
        switch (message.getType()) {
            case start_game:
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userjson = gson.toJson(userObj);
                User newUser = gson.fromJson(userjson, User.class);
                Lobby lobby = ServerModel.getLobbyByUser(newUser);
                ArrayList<Integer> maps = new ArrayList<>();
                ArrayList<String> usernames = new ArrayList<>();
                for (User user : lobby.getUsers()) {
                    maps.add(1);
                    usernames.add(user.getUsername());
                }
                maps.add(1);
                maps = lobby.getChoseMap();
                startNewGame(usernames, maps, newUser);
                break;
            case get_kashi_using_x_y:
                Message send_map = new Message(get_kashi(message, clientConnectionThread), Message.Type.get_kashi_using_x_y, Message.Menu.game);
                clientConnectionThread.sendMessage(send_map);
                break;
            case get_kashi_using_x1_y:
                Message send1_map = new Message(get_kashi(message, clientConnectionThread), Message.Type.get_kashi_using_x1_y, Message.Menu.game);
                clientConnectionThread.sendMessage(send1_map);
                break;
            case get_kashi_using_x1_y1:
                Message send2_map = new Message(get_kashi(message, clientConnectionThread), Message.Type.get_kashi_using_x1_y1, Message.Menu.game);
                clientConnectionThread.sendMessage(send2_map);
                break;
//            case get_kashis_using_2x_2y:
//                int startx = message.getIntFromBody("startX");
//                int starty = message.getIntFromBody("startY");
//                int endx = message.getIntFromBody("endX");
//                int endy = message.getIntFromBody("endY");
//
//                ArrayList<ArrayList<Kashi>> tileMap = new ArrayList<>();
//
//                for (int i = startx; i <= endx; i++) {
//                    ArrayList<Kashi> currentRow = new ArrayList<>();
//                    for (int j = starty; j <= endy; j++) {
//                        Kashi kashiTile = App.getCurrentGame().getMap().get(i).get(j);
//                        currentRow.add(kashiTile);
//                    }
//                    tileMap.add(currentRow);
//                }
//                HashMap<String, Object> map1 = new HashMap<>();
//                map1.put("kashis", tileMap);
//                Message send1 = new Message(map1, Message.Type.get_kashis_using_2x_2y, Message.Menu.game);
//                clientConnectionThread.sendMessage(send1);
//                break;
//            case get_kashi_row:
//                int starty = message.getIntFromBody("startY");
//                int endy = message.getIntFromBody("endY");
//                int rowIndex = message.getIntFromBody("rowIndex");
//                ArrayList<Kashi> currentRow = App.getCurrentGame().getMap().get(rowIndex);
//                List<Kashi> visibleRow = currentRow.subList(starty, endy + 1);
//                HashMap<String, Object> rowMap = new HashMap<>();
//                rowMap.put("row", visibleRow);
//                Message sendRow = new Message(rowMap, Message.Type.get_kashi_row, Message.Menu.game);
//                clientConnectionThread.sendMessage(sendRow);
//                break;
            case get_dateTime:
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("dateTime", App.getCurrentGame().getCurrentDateTime());
                Message send2 = new Message(map2, Message.Type.get_dateTime, Message.Menu.game);
                clientConnectionThread.sendMessage(send2);
                break;
            case get_npc_position:
                HashMap<String, Object> map3 = new HashMap<>();
                map3.put("sebastianx", String.valueOf(App.getCurrentGame().getNPCSEBASTIAN().getX()));
                map3.put("abigailx", String.valueOf(App.getCurrentGame().getNPCABIGAIL().getX()));
                map3.put("harveyx", String.valueOf(App.getCurrentGame().getNPCHARVEY().getX()));
                map3.put("leahx", String.valueOf(App.getCurrentGame().getNPCLEAH().getX()));
                map3.put("robinx", String.valueOf(App.getCurrentGame().getNPCROBIN().getX()));
                map3.put("sebastiany", String.valueOf(App.getCurrentGame().getNPCSEBASTIAN().getY()));
                map3.put("abigaily", String.valueOf(App.getCurrentGame().getNPCABIGAIL().getY()));
                map3.put("harveyy", String.valueOf(App.getCurrentGame().getNPCHARVEY().getY()));
                map3.put("leahy", String.valueOf(App.getCurrentGame().getNPCLEAH().getY()));
                map3.put("robiny", String.valueOf(App.getCurrentGame().getNPCROBIN().getY()));
                Message send3 = new Message(map3, Message.Type.get_npc_position, Message.Menu.game);
                clientConnectionThread.sendMessage(send3);
                break;
            case get_players:
                HashMap<String, Object> map4 = new HashMap<>();
                map4.put("numberOfPlayers", String.valueOf(App.getCurrentGame().getPlayers().size()));
                int count = 1;
                for (Player player : App.getCurrentGame().getPlayers()) {
                    map4.put(String.valueOf(count), String.valueOf(player.getX()));
                    map4.put(String.valueOf(count + 1), String.valueOf(player.getY()));
                    map4.put(String.valueOf(count + 2), String.valueOf(player.getMovingDirection()));
                    map4.put(String.valueOf(count + 3), String.valueOf(player.getState()));
                    map4.put(String.valueOf(count + 4), String.valueOf(player.getFaintTimer()));
                    map4.put(String.valueOf(count + 5), String.valueOf(player.getEatingTimer()));
                    map4.put(String.valueOf(count + 6), player.getFoodBuff());
                    map4.put(String.valueOf(count + 7), player.getAction());
                    count += 8;
                }
                Message send4 = new Message(map4, Message.Type.get_players, Message.Menu.game);
                clientConnectionThread.sendMessage(send4);
                break;
            case get_gold:
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(message.getFromBody("username"))) {
                        HashMap map5 = new HashMap<>();
                        map5.put("gold", player.getGold());
                        clientConnectionThread.sendMessage(new Message(map5, Message.Type.get_gold, Message.Menu.game));
                    }
                }
                break;
            case get_gold1:
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(message.getFromBody("username"))) {
                        HashMap map55 = new HashMap<>();
                        map55.put("gold", player.getGold());
                        clientConnectionThread.sendMessage(new Message(map55, Message.Type.get_gold1, Message.Menu.game));
                    }
                }
                break;
            case get_weather:
                HashMap<String, Object> map6 = new HashMap<>();
                map6.put("weather", App.getCurrentGame().getCurrentWeather());
                clientConnectionThread.sendMessage(new Message(map6, Message.Type.get_weather, Message.Menu.game));
                break;
            case player_pos_update:
                String username = message.getFromBody("username");
                double x_player = Double.parseDouble(message.getFromBody("x"));
                double y_player = Double.parseDouble(message.getFromBody("y"));
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(username)) {
                        player.setX(x_player);
                        player.setY(y_player);
                        break;
                    }
                }
                break;
            case player_movingdirection_update:
                String movedplayer_username = message.getFromBody("username");
                int movingDirection = Integer.parseInt(message.getFromBody("movingdirection"));
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(movedplayer_username)) {
//                        System.out.println("moving direction for: " + movedplayer_username + "set to: " + movingDirection);
                        player.setMovingDirection(movingDirection);
                        break;
                    }
                }
                break;
            case get_num_players:
                HashMap<String, Object> map7 = new HashMap<>();
                map7.put("numberOfPlayers", String.valueOf(App.getCurrentGame().getPlayers().size()));
                clientConnectionThread.sendMessage(new Message(map7, Message.Type.get_num_players, Message.Menu.game));
                break;
            case get_npc_friendship_and_talktoday:
                System.out.println("we are in get_npc_friendship_and_talktoday switch case");
                String user_name = message.getFromBody("username");
                String npc_name = message.getFromBody("npc");
                System.out.println("server side: " + user_name + " " + npc_name);
                HashMap<String, Object> map8 = new HashMap<>();

                int friendshipLevel = -1;
                boolean isTalkedWithToday = false;
                switch (npc_name.toUpperCase()) {
                    case "SEBASTIAN":
                        System.out.println("server side: in case sebastian");
                        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                            System.out.println("server side, this is all the frienships sebastian has: " + friendship.getPlayer().getOwner().getUsername());
                            if (friendship.getPlayer().getOwner().getUsername().equals(user_name)) {
                                friendshipLevel = friendship.getFriendshipLevel();
                                isTalkedWithToday = App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday();
                                break;
                            }
                        }
                        break;
                    case "ABIGAIL":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(user_name)) {
                                friendshipLevel = friendship.getFriendshipLevel();
                                isTalkedWithToday = App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday();
                                break;
                            }
                        }
                        break;
                    case "HARVEY":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(user_name)) {
                                friendshipLevel = friendship.getFriendshipLevel();
                                isTalkedWithToday = App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday();
                                break;
                            }
                        }
                        break;
                    case "LEAH":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(user_name)) {
                                friendshipLevel = friendship.getFriendshipLevel();
                                isTalkedWithToday = App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday();
                                break;
                            }
                        }
                        break;
                    case "ROBIN":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(user_name)) {
                                friendshipLevel = friendship.getFriendshipLevel();
                                isTalkedWithToday = App.getCurrentGame().getNPCSEBASTIAN().isTalkedWithToday();
                                break;
                            }
                        }
                        break;
                }
                map8.put("friendshipLevel", String.valueOf(friendshipLevel));
                map8.put("talkedwithtoday", isTalkedWithToday);
                clientConnectionThread.sendMessage(new Message(map8, Message.Type.get_npc_friendship_and_talktoday, Message.Menu.game));
                break;
            case set_npc_friendship_and_talktoday:
                String _username = message.getFromBody("username");
                String _npcname = message.getFromBody("npc");
                int friendShipLeveltoSet = message.getFromBody("friendshipLevel");
                Boolean talkedwithtodaytoSet = message.getFromBody("talkedwithtoday") == "TRUE" ? true : false;

                switch (_npcname) {
                    case "SEBASTIAN":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCSEBASTIAN().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(_username)) {
                                friendship.setFriendshipLevel(friendShipLeveltoSet);
                                App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(talkedwithtodaytoSet);
                                break;
                            }
                        }
                        break;
                    case "ABIGAIL":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCABIGAIL().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(_username)) {
                                friendship.setFriendshipLevel(friendShipLeveltoSet);
                                App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(talkedwithtodaytoSet);
                                break;
                            }
                        }
                        break;
                    case "HARVEY":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCHARVEY().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(_username)) {
                                friendship.setFriendshipLevel(friendShipLeveltoSet);
                                App.getCurrentGame().getNPCSEBASTIAN().setTalkedWithToday(talkedwithtodaytoSet);
                                break;
                            }
                        }
                        break;
                    case "LEAH":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCLEAH().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(_username)) {
                                friendship.setFriendshipLevel(friendShipLeveltoSet);
                                break;
                            }
                        }
                        break;
                    case "ROBIN":
                        for (Friendshipali friendship : App.getCurrentGame().getNPCROBIN().getFriendships()) {
                            if (friendship.getPlayer().getOwner().getUsername().equals(_username)) {
                                friendship.setFriendshipLevel(friendShipLeveltoSet);
                                break;
                            }
                        }
                        break;
                }
                break;
            case get_store_stock:
                String market_name = message.getFromBody("store");
                HashMap<String, Object> stockbody = new HashMap<>();
                if (market_name.equalsIgnoreCase("BlackSmith")) {
                    stockbody.put("type", "Mineral");
                    stockbody.put("stock", App.getCurrentGame().getBlackSmithMarket().getStock());
                } else if (market_name.equalsIgnoreCase("CarpentersShop")) {
                    stockbody.put("type", "Object");
                    stockbody.put("stock", App.getCurrentGame().getCarpentersShopMarket().getStock());
                } else if (market_name.equalsIgnoreCase("FishShop")) {
                    HashMap<FishingPole, Integer> rawStock = App.getCurrentGame().getFishShopMarket().getStock();
                    ArrayList<StockEntry> stockList = new ArrayList<>();

                    for (Map.Entry<FishingPole, Integer> entry : rawStock.entrySet()) {
                        stockList.add(new StockEntry(entry.getKey(), entry.getValue()));
                    }

                    stockbody.put("type", "Item");
                    stockbody.put("stock", stockList);
                } else if (market_name.equalsIgnoreCase("JojaMart")) {
                    stockbody.put("type", "Object");
                    stockbody.put("stock", App.getCurrentGame().getJojoMartMarket().getStock());
                } else if (market_name.equalsIgnoreCase("MarniesRanch")) {
                    stockbody.put("type", "Item");
                    stockbody.put("stock", App.getCurrentGame().getMarniesRanchMarket().getStock());
                } else if (market_name.equalsIgnoreCase("PierresGeneralStore")) {
                    stockbody.put("type", "Object");
                    stockbody.put("stock", App.getCurrentGame().getPierresGeneralStoreMarket().getStock());
                } else if (market_name.equalsIgnoreCase("TheStardropSaloon")) {
                    stockbody.put("type", "Object");
                    stockbody.put("stock", App.getCurrentGame().getTheStardropSaloonMarket().getStock());
                }
                Message send_stock = new Message(stockbody, Message.Type.get_store_stock, Message.Menu.game);
                clientConnectionThread.sendMessage(send_stock);
                break;
            case set_gold:
                String gold_name = message.getFromBody("username");
                int goldint = Integer.parseInt(message.getFromBody("gold"));
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(gold_name)) {
                        player.setGold(player.getGold() + goldint);
                    }
                }
                break;
            case set_gold1:
                String gold_name1 = message.getFromBody("username");
                int goldint1 = Integer.parseInt(message.getFromBody("gold"));
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(gold_name1)) {
                        player.setGold(player.getGold() + goldint1);
                    }
                }
                break;
            case set_fishing_stock:
                String item_name = message.getFromBody("item");
                int item_quantity = Integer.parseInt(message.getFromBody("quantity"));
                for (FishingPole fishingPole : App.getCurrentGame().getFishShopMarket().getStock().keySet()) {
                    if (fishingPole.getCorrectName().equalsIgnoreCase(item_name)) {
                        App.getCurrentGame().getFishShopMarket().removeItem(fishingPole, item_quantity);
                        break;
                    }
                }
                break;
            case get_ScoreBoard_info:
                ArrayList<ScoreBoardPlayerInfo> infos = new ArrayList<>();
                for (Player player : App.getCurrentGame().getPlayers()) {
                    int skill = player.getExtractionSkill().getLevel() + player.getFarmingSkill().getLevel() + player.getFishingSkill().getLevel() +
                            player.getForagingSkill().getLevel() + player.getMiningSkill().getLevel();
                    infos.add(new ScoreBoardPlayerInfo(player.getOwner().getUsername(), skill, player.getGold()));
                }
                HashMap<String, Object> board = new HashMap<>();
                board.put("players", infos);
                clientConnectionThread.sendMessage(new Message(board, Message.Type.get_ScoreBoard_info, Message.Menu.game));
                break;
            case get_linked_list:
                HashMap<String, Object> bdy = new HashMap<>();
                int userId = message.getIntFromBody("userId");
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getID() == userId) {
                        bdy.put("linkedList", player.getActionQueue());
                        break;
                    }
                }
                clientConnectionThread.sendMessage(new Message(bdy, Message.Type.get_linked_list, Message.Menu.game));
                break;
            case move_to_front:
                System.out.println("message received");
                int userId1 = message.getIntFromBody("userId");
                String action = message.getFromBody("action");
                ActionEnum actionEnum = ActionEnum.valueOf(action);
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getID() == userId1) {
                        System.out.println("From Server: move to front");
                        player.moveToFront(actionEnum);
                        break;
                    }
                }
                break;
            case set_action:
                System.out.println("message received");
                userId = message.getIntFromBody("userId");
                action = message.getFromBody("action");
                actionEnum = ActionEnum.valueOf(action);
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getID() == userId) {
                        player.setAction(actionEnum);
                        System.out.println("From Server: set action");
                        break;
                    }
                }
                break;
            case action_off:
                userId = message.getIntFromBody("userId");
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getID() == userId) {
                        player.setAction(null);
                        break;
                    }
                }
                break;
        }
    }

    private static HashMap<String, Object> get_kashi(Message message, ClientConnectionThread clientConnectionThread) {
        int x = message.getIntFromBody("x");
        int y = message.getIntFromBody("y");
        HashMap<String, Object> map = new HashMap<>();
        Kashi kashi = App.getCurrentGame().getMap().get(x).get(y);
        map.put("ShokhmZadeh", kashi.isShokhmZadeh());
        map.put("Enterance", kashi.getEnterance());
        map.put("Walkable", kashi.getWalkable());
        if (kashi.getInside() == null) {
            map.put("inside", "null");
        } else {
            map.put("inside", "full");
            map.put("insideOBJ", kashi.getInside());
            map.put("insideCLASS", kashi.getInside().getClass().getName());
        }
        return map;
    }

    private static void startNewGame(ArrayList<String> users, ArrayList<Integer> maps, User newUser) {
        try {
            Result result = GameMenuController.gameNew(users, maps, newUser);
            if (result.isSuccessful()) {
                ArrayList<ClientConnectionThread> connections = ServerModel.getConnections();
                for (ClientConnectionThread connection : connections) {
                    if (clientConnectionIsaPlayer(connection, users)) {
                        Message msg = new Message(new HashMap<>(), Message.Type.load_game_screen, Message.Menu.game_menu);
                        connection.sendMessage(msg);
                    }
                }
            } else {
                System.out.println("game be raft");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static boolean clientConnectionIsaPlayer(ClientConnectionThread connection, ArrayList<String> users) {
        for (String user : users) {
            if (connection.getUser().getUsername().equals(user)) {
                return true;
            }
        }
        return false;
    }

    private static boolean clientConnectionIsaPlayer(ClientConnectionThread connection) {
        for (Player player : App.getCurrentGame().getPlayers()) {
            if (connection.getUser().getUsername().equals(player.getOwner().getUsername())) {
                return true;
            }
        }
        return false;
    }
}
