package io.github.group18.Network.Client.App;

import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class ChangeMenuHandler {
    public static Message changeMenu(User user,int lobbyId) {
        HashMap<String ,Object>body = new HashMap<>();
        body.put("user", user);
        body.put("toMenu","choosingMenu");
        body.put("lobby", lobbyId);
        Message message = new Message(body, Message.Type.change_menu, Message.Menu.CHANGE_MENU);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(message,ClientModel.TIMEOUT_MILLIS);
    }
}
