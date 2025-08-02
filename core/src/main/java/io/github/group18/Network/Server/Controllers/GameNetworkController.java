package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.Result;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameNetworkController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case start_game :
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userjson = gson.toJson(userObj);
                User newUser = gson.fromJson(userjson, User.class);
                Lobby lobby = ServerModel.getLobbyByUser(newUser);
                ArrayList<Integer>maps = new ArrayList<>();
                ArrayList<String>usernames = new ArrayList<>();
                for (User user : lobby.getUsers()) {
                    maps.add(1);
                    usernames.add(user.getUsername());
                }
                maps.add(1);
                return startNewGame(usernames,maps,newUser);
        }
        return message;
    }

    private static Message startNewGame(ArrayList<String> users, ArrayList<Integer> maps,User newUser) {
        try {
            HashMap<String,Object>body = new HashMap<>();
            Result result = GameMenuController.gameNew(users,maps,newUser);
            body.put("result",result);
            return new Message(body, Message.Type.start_game, Message.Menu.game_menu);
        } catch (Exception e) {

            e.printStackTrace();
        }
        HashMap<String,Object>body = new HashMap<>();
        body.put("result","error");
        return new Message(body, Message.Type.start_game, Message.Menu.game_menu);
    }
}
