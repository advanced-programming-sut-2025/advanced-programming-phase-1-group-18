package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.ActionEnum;

import java.util.ArrayList;
import java.util.HashMap;

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
            case action_pop:
                int Id = message.getIntFromBody("Id");
                String action = message.getFromBody("Action");
                ActionEnum actionEnum = ActionEnum.valueOf(action);
                return setActionToPlayer(Id,actionEnum);
        }
        return message;
    }

    private static Message setActionToPlayer(int id, ActionEnum action) {
        for (Player player1 :App.getCurrentGame().getPlayers()){
            if (player1.getID() == id){
                player1.setAction(action);
                HashMap<String,Object>body = new HashMap<>();
                body.put("result",true);
                return new Message(body,Message.Type.action_pop, Message.Menu.game_menu);
            }
        }
        return null;
    }

    private static Message startNewGame(ArrayList<String> users, ArrayList<Integer> maps,User newUser) {
        try {
            HashMap<String,Object>body = new HashMap<>();
            Result result = GameMenuController.gameNew(users,maps,newUser);
            body.put("result",result.getMessage());
            body.put("success",result.isSuccessful());
            return new Message(body, Message.Type.start_game, Message.Menu.game_menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String,Object>body = new HashMap<>();
        body.put("result","error");
        return new Message(body, Message.Type.start_game, Message.Menu.game_menu);
    }
}
