package io.github.group18.Controller;

import com.badlogic.gdx.utils.StringBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;
import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.View.MainMenu;
import io.github.group18.View.OnlinePlayersMenu;
import io.github.group18.View.ProfileMenu;
import io.github.group18.View.RegisterLoginGdxView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class OnlinePlayersController {
    private OnlinePlayersMenu onlinePlayersMenu;

    public OnlinePlayersMenu getOnlinePlayersMenu() {
        return onlinePlayersMenu;
    }

    public void setOnlinePlayersMenu(OnlinePlayersMenu onlinePlayersMenu) {
        this.onlinePlayersMenu = onlinePlayersMenu;
    }

    public ArrayList<User> getOnlinePlayers() {
        HashMap<String, Object> body = new HashMap<>();
        Message msg = new Message(body, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        ArrayList<User> onlineUsers;
        Gson gson = new Gson();
        Object usersArraylistOBJ = response.getBody().get("onlineUsers");
        String userArraylist = gson.toJson(usersArraylistOBJ);
        Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
        onlineUsers = gson.fromJson(userArraylist, userListType);
        return onlineUsers;
    }

    public void handleButtons() {
        if (onlinePlayersMenu.getBackButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
