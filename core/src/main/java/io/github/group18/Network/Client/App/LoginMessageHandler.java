package io.github.group18.Network.Client.App;

import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class LoginMessageHandler {
    public static Message login(String username, String hashedPassword) {
        HashMap<String, Object> messageBody = new HashMap<>();
        messageBody.put("username", username);
        messageBody.put("password", hashedPassword);
        Message msg = new Message(messageBody, Message.Type.Login, Message.Menu.Login);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
    }
}
