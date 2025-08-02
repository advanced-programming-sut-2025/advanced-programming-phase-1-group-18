package io.github.group18.Network.Client.App;

import com.google.gson.Gson;
import io.github.group18.Model.Player;
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
        } else if (message.getMenu() == Message.Menu.CHANGE_MENU) {
            sendMessage(ChangeMenuController.handleMessage(message));
            return true;
        } else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.load_game_screen) {
            System.out.println("open game page");
        } else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.add_player_to_Clientmain) {
            Gson gson1 = new Gson();
            Object playerObj1 = message.getFromBody("player");
            String playerjson1 = gson1.toJson(playerObj1);
            Player player = gson1.fromJson(playerjson1, Player.class);
            ClientModel.setPlayer(player);
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ClientModel.endAll();
    }
}
