package io.github.group18.Network.Server.Controllers;

import io.github.group18.Controller.RegisterMenuController;
import io.github.group18.Model.App;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.Menu;

import java.util.*;

public class ServerConnectionController {
    public static Message handleCommand(Message message) {
        // TODO: Handle incoming peer-to-tracker commands
        // 1. Validate message type and content
        // 2. Find peers having the requested file
        // 3. Check for hash consistency
        // 4. Return peer information or error

        if (message.getType().equals(Message.Type.Is_Unique)) {
            String username = message.getFromBody("username");
            return isUsernameUnique(username);
//            boolean found = false;
//            if (App.getUsers_List() == null) {
//                found = false;
//            }
//            for (User user : App.getUsers_List()) {
//                if (user.getUsername().equals(username)) {
//                    found = true;
//                }
//            }
//            HashMap<String, Object> res = new HashMap<>();
//            res.put("isUnique", !found);
//            String MD5Hash = "";
//            boolean hashFound = false;
//            List<PeerConnectionThread> connections = TrackerApp.getConnections();
//            ArrayList<PeerConnectionThread> validConnections = new ArrayList<>();
//            for (PeerConnectionThread connection : connections) {
//
//                System.out.println(connection.getFileAndHashes());
//                if (connection.getFileAndHashes().containsKey(fileName)) {
//                    validConnections.add(connection);
//                    if (hashFound) {
//                        if (!MD5Hash.equals(connection.getFileAndHashes().get(fileName))) {
//                            HashMap<String, Object> body = new HashMap<>();
//                            body.put("response", "error");
//                            body.put("error", "multiple_hash");
//                            return new Message(body, Message.Type.response);
//                        }
//                    }
//                    MD5Hash = connection.getFileAndHashes().get(fileName);
//                    hashFound = true;
//                    found = true;
//                }
//            }
//            if (!found) {
//                HashMap<String, Object> body = new HashMap<>();
//                body.put("response", "error");
//                body.put("error", "not_found");
//                System.out.println("not found");
//                return new Message(body, Message.Type.response);
//            }
//            PeerConnectionThread peer = validConnections.get(new Random().nextInt(validConnections.size()));
//            HashMap<String, Object> body = new HashMap<>();
//            body.put("response", "peer_found");
//            body.put("md5", peer.getFileAndHashes().get(fileName));
//            body.put("peer_have", peer.getOtherSideIP());
//            body.put("peer_port", peer.getOtherSidePort());
//            return new Message(body, Message.Type.response);
        }else if (message.getType().equals(Message.Type.Register)){
            String username = message.getFromBody("username");
            String password = message.getFromBody("password");
            String nickname = message.getFromBody("nickname");
            String email = message.getFromBody("email");
            String gender = message.getFromBody("gender");
            User user = new User(username, password, nickname, email, gender);
            return registerUser(user);
        } else {
            return message;
        }
//		throw new UnsupportedOperationException("handleCommand not implemented yet");
    }

//    public static Map<String, List<String>> getSends(PeerConnectionThread connection) {
//        // TODO: Get list of files sent by a peer
//        // 1. Build command message
//        // 2. Send message and wait for response
//        // 3. Parse and return sent files map
//        try {
//            HashMap<String, Object> body = new HashMap<>();
//            body.put("command", "get_sends");
//            Message command = new Message(body, Message.Type.command);
//            Message javab = connection.sendAndWaitForResponse(command, TrackerApp.TIMEOUT_MILLIS);
//            return javab.getFromBody("sent_files");
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("getSends not implemented yet");
//        }
//    }
//
//    public static Map<String, List<String>> getReceives(PeerConnectionThread connection) {
//        // TODO: Get list of files received by a peer
//        // 1. Build command message
//        try {
//            HashMap<String, Object> body = new HashMap<>();
//            body.put("command", "get_receives");
//            Message command = new Message(body, Message.Type.command);
//            // 2. Send message and wait for response
//            Message javab = connection.sendAndWaitForResponse(command, TrackerApp.TIMEOUT_MILLIS);
//            // 3. Parse and return received files map
//            return javab.getFromBody("received_files");
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("getReceives not implemented yet");
//        }
//    }
    public static Message isUsernameUnique(String username) {
        boolean unique = true;
//        if (App.getUsers_List() == null) {
//            unique= true;
//        }
        for (User user : App.getUsers_List()) {
            if (user.getUsername().equals(username)) {
                unique=false;
                break;
            }
        }
        HashMap<String,Object> message = new HashMap<>();
        message.put("isUnique", unique);
        return new Message(message, Message.Type.Is_Unique, Message.Menu.Register);
    }
    public static Message registerUser (User newUser) {
        System.out.println("Received (register user) request");
        App.getUsers_List().add(newUser);
//        App.setCurrentUser(newUser);
//        App.setCurrentMenu(Menu.MainMenu);
        RegisterMenuController.saveUsersToFile();
        HashMap<String,Object> message = new HashMap<>();
        message.put("register", true);
        return new Message(message, Message.Type.Register, Message.Menu.Register);
    }
}
