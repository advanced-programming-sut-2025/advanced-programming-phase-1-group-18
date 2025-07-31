package io.github.group18.Network.Server.Controllers;

import com.google.gson.Gson;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.ppEnum;

import java.util.HashMap;
import java.util.Iterator;

public class LobbyNetworkController {
    public static void handleMessage(Message message, ClientConnectionThread clientConnectionThread) {
        Iterator<Lobby> iterator = ServerModel.getLobbies().iterator();
        while (iterator.hasNext()) {
            Lobby lobby = iterator.next();
            if (lobby.isExpired()) {
                iterator.remove();
            }
        }

        switch (message.getType()) {
            case add_user_to_lobby:
                Gson gson = new Gson();
                Object userObj = message.getFromBody("user");
                String userjson = gson.toJson(userObj);
                User newUser = gson.fromJson(userjson, User.class);
                Gson gson1 = new Gson();
                Object lobbyObj = message.getFromBody("lobby");
                String lobbyjson = gson.toJson(lobbyObj);
                Lobby newLobby = gson.fromJson(lobbyjson, Lobby.class);
                for (Lobby lobby : ServerModel.getLobbies()) {
                    if (lobby.getId() == newLobby.getId()) {
                        if (lobby.getUsers().isEmpty()) lobby.setAdmin(newUser);
                        lobby.getUsers().add(newUser);
                        break;
                    }
                }
                break;
            case create_lobby:
                String lobbyname = message.getFromBody("name");
                String password = message.getFromBody("password");
                boolean isPrivate = message.getFromBody("isprivate");
                boolean isVisible = message.getFromBody("isvisible");
                gson = new Gson();
                userObj = message.getFromBody("admin");
                userjson = gson.toJson(userObj);
                User admin = gson.fromJson(userjson, User.class);
                ppEnum accessLevel = isPrivate ? ppEnum.PRIVATE : ppEnum.PUBLIC;
                if (accessLevel == ppEnum.PRIVATE) {
                    Lobby lobby = new Lobby(lobbyname, accessLevel, password, isVisible, admin);
                    lobby.setAdmin(admin);
                    ServerModel.getLobbies().add(lobby);
                } else {
                    try {
                        Lobby lobby = new Lobby(lobbyname, accessLevel, isVisible, admin);
                        lobby.setAdmin(admin);
                        ServerModel.getLobbies().add(lobby);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case get_all_lobbies:
                HashMap<String, Object> body = new HashMap<>();
                body.put("lobbies", ServerModel.getLobbies());
                clientConnectionThread.sendMessage(new Message(body, Message.Type.get_all_lobbies, Message.Menu.lobby));
                break;
            case remove_user_to_lobby:
                gson = new Gson();
                userObj = message.getFromBody("user");
                userjson = gson.toJson(userObj);
                newUser = gson.fromJson(userjson, User.class);
                gson1 = new Gson();
                lobbyObj = message.getFromBody("lobby");
                lobbyjson = gson.toJson(lobbyObj);
                newLobby = gson.fromJson(lobbyjson, Lobby.class);

                for (Lobby lobby : ServerModel.getLobbies()) {
                    if (lobby.getId() == newLobby.getId()) {
                        if (lobby.getUsers().isEmpty()) lobby.setAdmin(newUser);
                        if (newUser.getUsername().equals(lobby.getAdmin().getUsername()) && lobby.getUsers().size() == 1) {
                            ServerModel.getLobbies().remove(lobby);
                            break;
                        } else if (newUser.getUsername().equals(lobby.getAdmin().getUsername())) {
                            lobby.declareNewAdmin();
                            break;
                        } else {
                            for (User user : lobby.getUsers()) {
                                if (user.getUsername().equals(newUser.getUsername())) {
                                    lobby.getUsers().remove(user);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                break;
        }
    }
}
