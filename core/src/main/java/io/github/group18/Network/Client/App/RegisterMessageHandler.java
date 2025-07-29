package io.github.group18.Network.Client.App;

import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class RegisterMessageHandler {
    public static boolean isUsernameUnique(String username) {
        HashMap<String,Object> message = new HashMap<>();
        message.put("username", username);
        Message msg = new Message(message, Message.Type.Is_Unique, Message.Menu.Register);
        Message res = ClientApp.getServerConnectionThread().sendAndWaitForResponse(msg,ClientApp.TIMEOUT_MILLIS);
        return res.getFromBody("isUnique");
    }
    public static void register(String username, String hashedPassword,String nickname , String email, String gender) {
        HashMap<String,Object> fld = new HashMap<>();

        fld.put("username", username);
        fld.put("password", hashedPassword);
        fld.put("nickname", nickname);
        fld.put("email", email);
        fld.put("gender", gender);

        Message msg = new Message(fld, Message.Type.Register, Message.Menu.Register);
        Message res1 = ClientApp.getServerConnectionThread().sendAndWaitForResponse(msg,ClientApp.TIMEOUT_MILLIS);
        System.out.println((boolean)res1.getFromBody("register"));
    }
}
