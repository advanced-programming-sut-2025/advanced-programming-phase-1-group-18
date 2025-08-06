package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
                startNewGame(usernames, maps, newUser);
                break;
            case get_kashi_using_x_y:
                int x = message.getIntFromBody("x");
                int y = message.getIntFromBody("y");
//                if (x == 0 && y <= 3) {
//                    System.out.println("client wants the block " + x + " " + y);
//                }
                HashMap<String, Object> map = new HashMap<>();
                Kashi kashi = App.getCurrentGame().getMap().get(x).get(y);
                map.put("ShokhmZadeh", kashi.isShokhmZadeh());
                map.put("Enterance", kashi.getEnterance());
                map.put("Walkable", kashi.getWalkable());
//                if (x == 0 && y <= 3) {
//                    System.out.println("exception block 0 ta 3 " + kashi.isShokhmZadeh() + " " + kashi.getEnterance() + " " + kashi.getWalkable());
//                }
                if (kashi.getInside() == null) {
                    map.put("inside", "null");
                } else {
                    map.put("inside", "full");
                    map.put("insideOBJ", kashi.getInside());
                    map.put("insideCLASS", kashi.getInside().getClass().getName());
                }

                Message send = new Message(map, Message.Type.get_kashi_using_x_y, Message.Menu.game);
                clientConnectionThread.sendMessage(send);
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
            case get_npc:
                HashMap<String, Object> map3 = new HashMap<>();
                map3.put("sebastian", App.getCurrentGame().getNPCSEBASTIAN());
                map3.put("abigail", App.getCurrentGame().getNPCABIGAIL());
                map3.put("harvey", App.getCurrentGame().getNPCHARVEY());
                map3.put("leah", App.getCurrentGame().getNPCLEAH());
                map3.put("robin", App.getCurrentGame().getNPCROBIN());
                Message send3 = new Message(map3, Message.Type.get_npc, Message.Menu.game);
                clientConnectionThread.sendMessage(send3);
                break;
            case get_players:
                HashMap<String, Object> map4 = new HashMap<>();
                int count = 1;
                for (Player player : App.getCurrentGame().getPlayers()) {
                    map4.put(String.valueOf(count), String.valueOf(player.getX()));
                    map4.put(String.valueOf(count + 1), String.valueOf(player.getY()));
                    map4.put(String.valueOf(count + 2), String.valueOf(player.getMovingDirection()));
                    map4.put(String.valueOf(count + 3), String.valueOf(player.getState()));
                    map4.put(String.valueOf(count + 4), String.valueOf(player.getFaintTimer()));
                    map4.put(String.valueOf(count + 5), String.valueOf(player.getEatingTimer()));
                    map4.put(String.valueOf(count + 6), player.getFoodBuff());
                    count += 7;
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
            case get_weather:
                HashMap<String, Object> map6 = new HashMap<>();
                map6.put("weather", App.getCurrentGame().getCurrentWeather());
//                System.out.println("server is giving weahter: " + new Message(map6, Message.Type.get_weather, Message.Menu.game).getBody().toString());
                clientConnectionThread.sendMessage(new Message(map6, Message.Type.get_weather, Message.Menu.game));
                break;
            case get_num_players:
                HashMap<String, Object> map7 = new HashMap<>();
                map7.put("numberOfPlayers", String.valueOf(App.getCurrentGame().getPlayers().size()));
                Message send5 = new Message(map7, Message.Type.get_num_players, Message.Menu.game);
                clientConnectionThread.sendMessage(send5);
                break;
            case update_player_pos:
                double movedplayer_x = Double.parseDouble(message.getFromBody("x"));
                double movedplayer_y = Double.parseDouble(message.getFromBody("y"));
                String movedplayer_username = message.getFromBody("username");
                for (Player player : App.getCurrentGame().getPlayers()) {
                    if (player.getOwner().getUsername().equals(movedplayer_username)) {
                        System.out.println("update player: " + movedplayer_username + " 's position in app.currentgame server");
                        player.setX(movedplayer_x);
                        player.setY(movedplayer_y);
                        break;
                    }
                }
                System.out.println("someone moved to they are sending update for everyone");
                ArrayList<ClientConnectionThread> connections = ServerModel.getConnections();
                for (ClientConnectionThread connection : connections) {
                    if (clientConnectionIsaPlayer(connection)) {
                        System.out.println("updating " + connection.getUser().getUsername() + " 's GameView");
                        Message msg = new Message(new HashMap<>(), Message.Type.update_player_pos, Message.Menu.game_menu);
                        connection.sendMessage(msg);
                    }
                }
                break;
        }
    }

    private static void startNewGame(ArrayList<String> users, ArrayList<Integer> maps, User newUser) {
        try {
            Result result = GameMenuController.gameNew(users, maps, newUser);
//            System.out.println("init players done(owner + energy + x + y are set)");
            if (result.isSuccessful()) {
                ArrayList<ClientConnectionThread> connections = ServerModel.getConnections();
                for (ClientConnectionThread connection : connections) {
                    if (clientConnectionIsaPlayer(connection, users)) {
                        Message msg = new Message(new HashMap<>(), Message.Type.load_game_screen, Message.Menu.game_menu);
                        connection.sendMessage(msg);
                    }
                }
            } else {
                System.out.println("game be gaj raft");
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
