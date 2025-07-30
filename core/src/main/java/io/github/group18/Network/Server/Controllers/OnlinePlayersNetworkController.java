package io.github.group18.Network.Server.Controllers;

import com.badlogic.gdx.utils.StringBuilder;
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
        System.out.println("server is gonna handle message");
        switch (message.getType()) {
            case get_online_players:
                String sb = "";
                for (User user : ServerModel.getOnlineUsers()) {
                    sb += user.getUsername();
                    sb += "\n";
                }
                HashMap<String, Object> body = new HashMap<>();
                body.put("onlineUsers", sb);
                return new Message(body, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
            case add_to_online_players:
//                System.out.println("server is gonna add user to online players using this message" + message.getBody().toString());
                String username = message.getFromBody("user");
//                System.out.println("server extracted user from the message");
                User chosenuser = null;
                for (User user : App.getUsers_List()) {
                    if (user.getUsername().equals(username)) {
                        chosenuser = user;
                        break;
                    }
                }
                ServerModel.getOnlineUsers().add(chosenuser);
//                System.out.println("server added user to online players");
                return new Message();
            default:
                return new Message();
        }
    }
}
