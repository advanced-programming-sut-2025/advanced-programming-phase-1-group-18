package io.github.group18.Network.Server.App;

import io.github.group18.Model.User;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;
import java.util.List;

public class ChangeScreenMsgHandler {
    public static void ChangeScreenToMap(List<User> users) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("changeMenu", "choosingMap");
        Message msg = new Message(body, Message.Type.change_menu, Message.Menu.CHANGE_MENU);
//        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        for (User user : users) {
            System.out.println("From Server: " + user.getUsername());
            ClientConnectionThread connectionThread = ServerModel.getConnectionByUser(user);
            if(connectionThread == null) continue;
            connectionThread.sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);

        }
    }
}
