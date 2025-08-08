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
    public static void playerActionPop (int Id, String action) {
        HashMap<String,Object> message = new HashMap<>();
        message.put("Id", Id);
        message.put("Action", action);
        Message msg = new Message(message, Message.Type.action_pop, Message.Menu.game_menu);
        Message res = ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
    }
}
