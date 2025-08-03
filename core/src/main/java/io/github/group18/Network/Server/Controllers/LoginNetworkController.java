package io.github.group18.Network.Server.Controllers;

import io.github.group18.Controller.RegisterMenuController;
import io.github.group18.Model.App;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class LoginNetworkController {
    public static Message handleMessage(Message message,ClientConnectionThread connection) {
        switch (message.getType()) {
            case Login:
                String username = message.getFromBody("username");
                String password = message.getFromBody("password");
                return loginUser(username, password,connection);
            default:
                return message;
        }
    }

    public static Message loginUser(String username, String password, ClientConnectionThread connection) {
        for (User user : App.getUsers_List()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                HashMap<String, Object> response = new HashMap<>();
                response.put("loginSuccess", true);
                response.put("user", user); // 👈 اینو اضافه کن
                ServerModel.getOnlineUsers().add(user);
                connection.setUser(user);
                System.out.println("rezi is cooking " + user.getUsername());
                return new Message(response, Message.Type.Login, Message.Menu.Login);
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("loginSuccess", false);
        return new Message(response, Message.Type.Login, Message.Menu.Login);
    }



}
