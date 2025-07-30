package io.github.group18.Network.Server.Controllers;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.StringBuilder;
import com.google.gson.Gson;
import io.github.group18.Model.App;
import io.github.group18.Model.User;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class OnlinePlayersNetworkController {
    public static Message handleMessage(Message message) {
//        System.out.println("server is gonna handle message");
        switch (message.getType()) {
            case get_online_players:
                HashMap<String, Object> body = new HashMap<>();
                body.put("onlineUsers", ServerModel.getOnlineUsers());
                return new Message(body, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
            case remove_from_online_players:
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userJson = gson.toJson(userObj);
                User user = gson.fromJson(userJson, User.class);
                for (User u : ServerModel.getOnlineUsers()) {
                    if(u.getUsername().equals(user.getUsername())) {
                        ServerModel.getOnlineUsers().remove(u);
                        break;
                    }
                }
                return new Message();
            default:
                return new Message();
        }
    }
}
