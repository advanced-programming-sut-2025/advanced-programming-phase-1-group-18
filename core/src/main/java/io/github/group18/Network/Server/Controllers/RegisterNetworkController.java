//package io.github.group18.Network.Server.Controllers;
//
//import io.github.group18.Controller.RegisterMenuController;
//import io.github.group18.Model.App;
//import io.github.group18.Model.User;
//import io.github.group18.Network.common.models.Message;
//
//import java.util.HashMap;
//
//public class RegisterNetworkController {
//    public static Message handleMessage(Message message) {
//        switch (message.getType()) {
//            case Is_Unique :
//                String username = message.getFromBody("username");
//                return isUsernameUnique(username);
//            case Register:
//                String username1 = message.getFromBody("username");
//                String password = message.getFromBody("password");
//                String nickname = message.getFromBody("nickname");
//                String email = message.getFromBody("email");
//                String gender = message.getFromBody("gender");
//                User user = new User(username1, password, nickname, email, gender);
//                return RegisterNetworkController.registerUser(user);
//            default :
//                return message;
//        }
//    }
//    public static Message isUsernameUnique(String username) {
//        boolean unique = true;
////        if (App.getUsers_List() == null) {
////            unique= true;
////        }
//        for (User user : App.getUsers_List()) {
//            if (user.getUsername().equals(username)) {
//                unique=false;
//                break;
//            }
//        }
//        HashMap<String,Object> message = new HashMap<>();
//        message.put("isUnique", unique);
//        return new Message(message, Message.Type.Is_Unique, Message.Menu.Register);
//    }
//    public static Message registerUser (User newUser) {
//        System.out.println("Received (register user) request");
//        App.getUsers_List().add(newUser);
////        App.setCurrentUser(newUser);
////        App.setCurrentMenu(Menu.MainMenu);
//        RegisterMenuController.saveUsersToFile();
//        HashMap<String,Object> message = new HashMap<>();
//        message.put("register", true);
//        return new Message(message, Message.Type.Register,Message.Menu.Register);
//    }
//}
