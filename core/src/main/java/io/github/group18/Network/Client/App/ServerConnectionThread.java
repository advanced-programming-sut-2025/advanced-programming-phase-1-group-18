package io.github.group18.Network.Client.App;

import io.github.group18.Network.Client.Controller.C2SConnectionController;
import io.github.group18.Network.Client.Controller.ChangeMenuController;
import io.github.group18.Network.common.models.ConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.io.IOException;
import java.net.Socket;

import static io.github.group18.Network.Client.App.ClientModel.TIMEOUT_MILLIS;

public class ServerConnectionThread extends ConnectionThread {

    protected ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public boolean initialHandshake() {
        try {
            socket.setSoTimeout(TIMEOUT_MILLIS);

            dataInputStream.readUTF();
            Message message1 = C2SConnectionController.status();
            sendMessage(message1);

            socket.setSoTimeout(0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            sendMessage(C2SConnectionController.handleCommand(message));
            return true;
        }else if (message.getMenu()== Message.Menu.CHANGE_MENU){
            sendMessage(ChangeMenuController.handleMessage(message));
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ClientModel.endAll();
    }
}
