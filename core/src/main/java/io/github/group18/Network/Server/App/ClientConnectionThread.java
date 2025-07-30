package io.github.group18.Network.Server.App;

import io.github.group18.Network.Server.Controllers.RegisterNetworkController;
import io.github.group18.Network.common.models.ConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static io.github.group18.Network.Server.App.ServerModel.TIMEOUT_MILLIS;

public class ClientConnectionThread extends ConnectionThread {

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
        if (res.getFromBody("response") == "ok") {
            return true;
        }
        return false;
    }

    @Override
    protected boolean handleMessage(Message message) {
        switch (message.getMenu()) {
            case Register:
                sendMessage(RegisterNetworkController.handleMessage(message));
                return true;
            case Basic:
                sendMessage(RegisterNetworkController.handleMessage(message));
                return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ServerModel.removeClientConnection(this);
    }
}
