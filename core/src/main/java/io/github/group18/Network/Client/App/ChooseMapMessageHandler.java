package io.github.group18.Network.Client.App;

import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class ChooseMapMessageHandler {
    public static Message refreshMaps (User user) {
        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("user", user);
        Message msg = new Message(messageBody, Message.Type.refresh_maps, Message.Menu.choosing_map);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
    }
    public static Message addMap (User user,int mapNum) {
        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("user", user);
        messageBody.put("mapNum", mapNum);
        Message msg = new Message(messageBody, Message.Type.choose_map, Message.Menu.choosing_map);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
    }
}
