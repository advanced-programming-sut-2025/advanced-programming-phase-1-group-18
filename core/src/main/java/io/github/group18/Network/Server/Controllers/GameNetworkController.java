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
    public static void handleMessage(Message message) {
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
        }
    }

    private static void startNewGame(ArrayList<String> users, ArrayList<Integer> maps, User newUser) {
        try {
            Result result = GameMenuController.gameNew(users, maps, newUser);
            if (result.isSuccessful()) {
                ArrayList<ClientConnectionThread> connections = ServerModel.getConnections();
                for (ClientConnectionThread connection : connections) {
                    if (clientConnectionIsaPlayer(connection, users)) {
                        HashMap<String, Object> body = new HashMap<>();
                        body.put("game", App.getCurrentGame());
                        Message msg = new Message(body, Message.Type.load_game_screen, Message.Menu.game_menu);
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
}
