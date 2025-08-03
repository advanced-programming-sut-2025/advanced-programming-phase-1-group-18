package io.github.group18.Controller;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;
import io.github.group18.Network.Client.App.ChangeMenuHandler;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Client.App.GameMessageHandler;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.ChoosingMapView;
import io.github.group18.View.MainMenu;
import io.github.group18.View.RegisterLoginGdxView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class LobbyController {

    public void joinLobby(Lobby lobby, User user) {
        if (lobby.getUsers().size() > 4) return;
        for (User user1 : lobby.getUsers()) {
            if (user1.getUsername().equals(user.getUsername())) {
                return;
            }
        }
        addUsertoLobby(lobby, user);
    }

    public void joinLobby(Lobby lobby, User user, String password) {
        if (lobby.getUsers().size() > 4) return;
        for (User user1 : lobby.getUsers()) {
            if (user1.getUsername().equals(user.getUsername())) {
                return;
            }
        }
        if (!lobby.getPassword().equals(password)) return;
        addUsertoLobby(lobby, user);
        System.out.println("joined lobby");
    }

    private void addUsertoLobby(Lobby lobby, User user) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", user);
        body.put("lobby", lobby);
        Message message = new Message(body, Message.Type.add_user_to_lobby, Message.Menu.lobby);
        ClientModel.getServerConnectionThread().sendMessage(message);
    }

    public void goBack() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public void chooseMap(User user, Stage stage,Lobby lobby) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", user);
        body.put("lobby", lobby.getId());
        Message message = new Message(body, Message.Type.choose_map, Message.Menu.lobby);
        Message res = ClientModel.getServerConnectionThread().sendAndWaitForResponse(message, ClientModel.TIMEOUT_MILLIS);
//        Message res = ClientModel.getServerConnectionThread().sendAndWaitForResponse(message,ClientModel.TIMEOUT_MILLIS);
        if (res==null) return;
        if(res.getFromBody("success")){
            int lobbyId = res.getIntFromBody("lobby");
//            Main.getMain().getScreen().dispose();
//            Main.getMain().setScreen(new ChoosingMapView(new ChoosingMapController(), GameAssetManager.getGameAssetManager().getSkin()));
            ChangeMenuHandler.changeMenu(App.getCurrentUser(),lobbyId);
        }else{
            Dialog error = new Dialog("error",GameAssetManager.getGameAssetManager().getSkin());
            error.text((String) res.getFromBody("error"));
            error.button("close");
            error.show(stage);
        }
    }
    public String showInvisibleLobbyInfo(int id) {
        List<Lobby> lobbies = getAllLobbies();
        for (Lobby lobby : lobbies) {
            if (lobby.getId() == id) {
                StringBuilder s = new StringBuilder("Name: " + lobby.getName() + "\n" +
                    "ID: " + lobby.getId() + "\n" +
                    "Number of players: " + lobby.getUsers().size() + "\n");
                for (User user : lobby.getUsers()) {
                    s.append("Username: ").append(user.getUsername()).append("\n");
                }
                return s.toString();
            }
        }
        return "lobby not found";
    }

    public void createLobby(String name, boolean isPrivate, String password, boolean isVisible, User admin) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("name", name);
        body.put("isprivate", isPrivate);
        body.put("password", password);
        body.put("isvisible", isVisible);
        body.put("admin", admin);
        Message message = new Message(body, Message.Type.create_lobby, Message.Menu.lobby);
        ClientModel.getServerConnectionThread().sendMessage(message);
    }

    public String getLobbyInfo(Lobby lobby) {
        StringBuilder s = new StringBuilder("Name: " + lobby.getName() + "\n" +
            "ID: " + lobby.getId() + "\n" +
            "Number of players: " + lobby.getUsers().size() + "\n");
        for (User user : lobby.getUsers()) {
            s.append("Username: ").append(user.getUsername()).append("\n");
        }
        return s.toString();
    }

    public boolean isAdmin(User user, Lobby lobby) {
        return lobby.getAdmin().getUsername().equals(user.getUsername());
    }

    public boolean isAdmin(User user, int lobbyID) {
        List<Lobby> lobbies = getAllLobbies();
        for (Lobby lobby : lobbies) {
            if (lobby.getId() == lobbyID) {
                return lobby.getAdmin().getUsername().equals(user.getUsername());
            }
        }
        return false;
    }

    public ArrayList<Lobby> getVisibleLobbies() {
        List<Lobby> lobbies = getAllLobbies();
        ArrayList<Lobby> visibleLobbies = new ArrayList<>();
        for (Lobby lobby : lobbies) {
            if (lobby.isVisible()) {
                visibleLobbies.add(lobby);
            }
        }
        return visibleLobbies;
    }

    public List<Lobby> getAllLobbies() {
        HashMap<String, Object> body = new HashMap<>();
        Message message = new Message(body, Message.Type.get_all_lobbies, Message.Menu.lobby);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(message, ClientModel.TIMEOUT_MILLIS);
        ArrayList<Lobby> lobbies;
        Gson gson = new Gson();
        Object lobbyArraylistOBJ = response.getBody().get("lobbies");
        String lobbyArraylist = gson.toJson(lobbyArraylistOBJ);
        Type lobbyListType = new TypeToken<ArrayList<Lobby>>() {
        }.getType();
        lobbies = gson.fromJson(lobbyArraylist, lobbyListType);
        return lobbies;
    }

    public void leaveLobby(Lobby lobby, User currentUser) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("user", currentUser);
        body.put("lobby", lobby);
        Message message = new Message(body, Message.Type.remove_user_to_lobby, Message.Menu.lobby);
        ClientModel.getServerConnectionThread().sendMessage(message);
    }
}
