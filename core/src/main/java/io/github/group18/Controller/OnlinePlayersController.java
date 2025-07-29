package io.github.group18.Controller;

import io.github.group18.Model.Player;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.View.OnlinePlayersMenu;

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
        HashMap<String, Object> message = new HashMap<>();
        Message msg = new Message(message, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
        return ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS).getFromBody("onlineUsers");
    }
}
