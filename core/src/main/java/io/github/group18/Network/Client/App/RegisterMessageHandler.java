package io.github.group18.Network.Client.App;

import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class RegisterMessageHandler {
    public static boolean isUsernameUnique(String username) {
        HashMap<String,Object> message = new HashMap<>();
        message.put("username", username);
        Message msg = new Message(message, Message.Type.Is_Unique, Message.Menu.Register);
        Message res = ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        return res.getFromBody("isUnique");
    }
    public static Message register(String username, String hashedPassword, String nickname, String email, String gender) {
        HashMap<String,Object> fld = new HashMap<>();

        fld.put("username", username);
        fld.put("password", hashedPassword);
        fld.put("nickname", nickname);
        fld.put("email", email);
        fld.put("gender", gender);

        Message msg = new Message(fld, Message.Type.Register, Message.Menu.Register);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
    }
}
