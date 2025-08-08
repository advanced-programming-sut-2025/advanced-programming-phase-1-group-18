package io.github.group18.Network.Client.App;

import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.ActionEnum;

import java.util.HashMap;

public class ActionMessageHandler {
    public static Message getLinkedList(int userId) {
        HashMap<String ,Object> body = new HashMap<>();
        body.put("userId", userId);
        Message message = new Message(body, Message.Type.get_linked_list, Message.Menu.game_menu);
        Message response = null;
        while (response == null || response.getType() != Message.Type.get_linked_list) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(message, ClientModel.TIMEOUT_MILLIS);
        }
        return response;
    }
    public static void sendToFrontAction(int userId, ActionEnum action) {
        HashMap<String ,Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("action", action.toString());
        Message message = new Message(body,Message.Type.move_to_front, Message.Menu.game_menu);
        ClientModel.getServerConnectionThread().sendMessage(message);
        System.out.println("Message sent");
    }
    public static void setAction (int userId, ActionEnum action) {
        HashMap<String ,Object> body = new HashMap<>();
        body.put("userId", userId);
        body.put("action", action.toString());
        Message message = new Message(body,Message.Type.set_action, Message.Menu.game_menu);
        ClientModel.getServerConnectionThread().sendMessage(message);
    }
    public static void actionOff (int userId) {
        HashMap<String ,Object> body = new HashMap<>();
        body.put("userId", userId);
        Message message = new Message(body,Message.Type.action_off, Message.Menu.game_menu);
        ClientModel.getServerConnectionThread().sendMessage(message);
    }
}
