package io.github.group18.Network.Client.App;

import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class LoginMessageHandler {

    public static boolean login(String username, String hashedPassword) {
        HashMap<String,Object> message = new HashMap<>();
        message.put("username", username);
        message.put("password", hashedPassword);

        Message msg = new Message(message, Message.Type.Login, Message.Menu.Login);
        Message res = ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);

        if (res == null) return false;
        return (boolean) res.getFromBody("loginSuccess");
    }
}
