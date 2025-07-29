package io.github.group18.Network.Client.Controller;

import io.github.group18.Model.App;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class C2SConnectionController {
    public static Message handleCommand(Message message) {

        Message response = new Message();
        try {
            // 1. Parse command from message
            String commandName = message.getFromBody("command");

            // 2. Call appropriate handler
            response = switch (commandName.toLowerCase()) {
                case "status" -> status();
//                case "get_files_list" -> getFilesList();
//                case "get_sends" -> getSends();
//                case "get_receives" -> getReceives();
                default -> response;
            };
        } catch (Exception e) {
            throw new UnsupportedOperationException("handleCommand not implemented yet");
        }
        return response;
    }

    public static Message status() {
        try {
            HashMap<String, Object> body = new HashMap<>();
            body.put("command", "status");
            body.put("response", "ok");

            Message res = new Message(body, Message.Type.status, Message.Menu.Basic);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("status failed");
        }
    }
}
