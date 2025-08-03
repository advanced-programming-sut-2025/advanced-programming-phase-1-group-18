package io.github.group18.Network.Client.App;

import com.google.gson.Gson;
import io.github.group18.Controller.GameController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;
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
            //open game page
            GameController gameController = new GameController(Main.getMain());
            gameController.init();
            gameController.run();
        } else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.add_player_to_Clientmain) {
            Gson gson1 = new Gson();
            Object userObj1 = message.getFromBody("owner");
            String userjson1 = gson1.toJson(userObj1);
            User user = gson1.fromJson(userjson1, User.class);
            double energy = message.getFromBody("energy");
            double x = message.getFromBody("x");
            double y = message.getFromBody("y");
            Player player = new Player();
            player.setOwner(user);
            player.setEnergy(energy);
            player.setX(x);
            player.setY(y);
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
