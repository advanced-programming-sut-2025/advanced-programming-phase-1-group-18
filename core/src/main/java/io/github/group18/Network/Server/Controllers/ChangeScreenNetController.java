package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ChangeScreenMsgHandler;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class ChangeScreenNetController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case change_menu:
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userjson = gson.toJson(userObj);
                User newUser = gson.fromJson(userjson, User.class);
                return changeScreen();
            default:
                return message;
        }
    }

    private static Message changeScreen() {
        ChangeScreenMsgHandler.ChangeScreenToMap(ServerModel.getOnlineUsers());
        HashMap<String,Object>body = new HashMap<>();
        body.put("result", true);
        return new Message(body, Message.Type.change_menu, Message.Menu.CHANGE_MENU);
    }
}
