package io.github.group18.Network.Server.Controllers;

import io.github.group18.Controller.RegisterMenuController;
import io.github.group18.Model.App;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class LoginNetworkController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case Login:
                String username = message.getFromBody("username");
                String password = message.getFromBody("password");
                return loginUser(username, password);
            default:
                return message;
        }
    }

    public static Message loginUser(String username, String password) {
        for (User user : App.getUsers_List()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                HashMap<String, Object> response = new HashMap<>();
                response.put("loginSuccess", true);
                response.put("user", user); // 👈 اینو اضافه کن
                return new Message(response, Message.Type.Login, Message.Menu.Login);
            }
        }
        HashMap<String, Object> response = new HashMap<>();
        response.put("loginSuccess", false);
        return new Message(response, Message.Type.Login, Message.Menu.Login);
    }



}
