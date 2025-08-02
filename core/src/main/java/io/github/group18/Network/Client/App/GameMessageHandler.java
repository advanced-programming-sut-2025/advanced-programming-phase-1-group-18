package io.github.group18.Network.Client.App;

import io.github.group18.Model.Result;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMessageHandler {
    public static void startNewGame (User user) {
        HashMap<String,Object> message = new HashMap<>();
        message.put("user", user);
        Message msg = new Message(message, Message.Type.start_game, Message.Menu.game_menu);
        ClientModel.getServerConnectionThread().sendMessage(msg);
    }
}
