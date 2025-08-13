package io.github.group18.Network.Server.App;

import io.github.group18.Model.User;
import io.github.group18.Network.Server.Controllers.*;
import io.github.group18.Network.common.models.ConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static io.github.group18.Network.Server.App.ServerModel.TIMEOUT_MILLIS;

public class ClientConnectionThread extends ConnectionThread {
    User user = new User();
    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }


    @Override
    public boolean initialHandshake() {
        try {
            boolean init = refreshStatus();
            if (init) {
                ServerModel.addClientConnection(this);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean refreshStatus() {
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "status");
        Message res = sendAndWaitForResponse(new Message(body, Message.Type.command, Message.Menu.Basic), TIMEOUT_MILLIS);
        return ((String) res.getFromBody("response")).equalsIgnoreCase("ok");
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.getMenu()) {
            case Register:
                sendMessage(RegisterNetworkController.handleMessage(message));
                return true;
            case OnlinePlayers:
                OnlinePlayersNetworkController.handleMessage(message,this);
                return true;
            case Login:
                sendMessage(LoginNetworkController.handleMessage(message,this));
                return true;
            case lobby:
                LobbyNetworkController.handleMessage(message,this);
                return true;
            case choosing_map:
                sendMessage(ChooseMapNetworkController.handleMessage(message,this));
                return true;
            case CHANGE_MENU:
                ChangeScreenNetController.handleMessage(message);
                return true;
            case game_menu:
                GameNetworkController.handleMessage(message,this);
                return true;
            case game:
                GameNetworkController.handleMessage(message,this);
                return true;
            case trade:
                return TradeNetworkController.handleMessage(message, this);

        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ServerModel.removeClientConnection(this);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
