package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class ChooseMapNetworkController {
    public static Message handleMessage(Message message, ClientConnectionThread connection) {
        switch (message.getType()) {
            case refresh_maps:
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userjson = gson.toJson(userObj);
                User newUser = gson.fromJson(userjson, User.class);
                Lobby lobby =ServerModel.getLobbyByUser(newUser);
                return (refreshMaps(lobby));
            case choose_map:
                gson = new Gson();
                userObj = message.getFromBody("user");
                userjson = gson.toJson(userObj);
                newUser = gson.fromJson(userjson, User.class);
                int mapNum = message.getIntFromBody("mapNum");
                return selectMap(newUser,mapNum);
            default:
                return message;
        }
    }
    public static Message refreshMaps(Lobby lobby) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("usersNum",lobby.getUsers().size());
        int i = 0;
        for (User user : lobby.getUsers()) {
            body.put(String.valueOf(i), lobby.getChoseMap().get(i));
            i++;
        }
//        System.out.println("From choose menu: msg:"+body);
        return new Message(body, Message.Type.refresh_maps, Message.Menu.choosing_map);
    }
    public static Message selectMap(User user, int mapNum) {
        Lobby lobby =ServerModel.getLobbyByUser(user);
        HashMap<String, Object> body = new HashMap<>();
        lobby.getChoseMap().set(lobby.getUsers().indexOf(user), mapNum);
        body.put("mapNum", mapNum);
        body.put("user", user);
        return new Message(body, Message.Type.choose_map, Message.Menu.choosing_map);
    }
}
