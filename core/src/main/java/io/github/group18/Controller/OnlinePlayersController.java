package io.github.group18.Controller;

import com.badlogic.gdx.utils.StringBuilder;
import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;
import io.github.group18.Network.common.models.Message;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.View.MainMenu;
import io.github.group18.View.OnlinePlayersMenu;
import io.github.group18.View.ProfileMenu;
import io.github.group18.View.RegisterLoginGdxView;

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

    public String getOnlinePlayers() {
        HashMap<String, Object> message = new HashMap<>();
        Message msg = new Message(message, Message.Type.get_online_players, Message.Menu.OnlinePlayers);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(msg, ClientModel.TIMEOUT_MILLIS);
        return response.getFromBody("onlineUsers");
    }

    public void handleButtons() {
        if (onlinePlayersMenu.getBackButton().isChecked()) {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        }
    }
}
