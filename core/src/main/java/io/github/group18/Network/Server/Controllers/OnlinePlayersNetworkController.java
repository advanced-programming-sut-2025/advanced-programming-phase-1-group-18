package io.github.group18.Network.Server.Controllers;

import com.badlogic.gdx.utils.StringBuilder;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class OnlinePlayersNetworkController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case get_online_players:
                ArrayList<User> onlinePlayers = new ArrayList<>(ServerModel.getOnlineUsers());
                HashMap<String, Object> body = new HashMap<>();
                body.put("onlineUsers", onlinePlayers);
                return new Message(body, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
            case add_to_online_players:
                ServerModel.getOnlineUsers().add(message.getFromBody("user"));
                return new Message();
            default:
                return new Message();
        }
    }
}
