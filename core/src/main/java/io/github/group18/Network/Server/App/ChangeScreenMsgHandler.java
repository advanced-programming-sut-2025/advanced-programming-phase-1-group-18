package io.github.group18.Network.Server.App;

import io.github.group18.Model.User;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChangeScreenMsgHandler {
    public static void ChangeScreenToMap(List<User> users) {
        System.out.println("Change screen to Map with users: " + users);
        HashMap<String, Object> body = new HashMap<>();
        body.put("changeMenu", "choosingMap");
        ArrayList<String>userNames = new ArrayList<>();
        for (User user : users) {
            userNames.add(user.getUsername());
        }
        body.put("users", userNames);
        Message msg = new Message(body, Message.Type.change_menu, Message.Menu.CHANGE_MENU);
//        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        for (User user : users) {
            ClientConnectionThread connectionThread = ServerModel.getConnectionByUser(user);
            if(connectionThread == null) continue;
            System.out.println("sending change screen message to "+user.getUsername());
            connectionThread.sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        }
    }
}
