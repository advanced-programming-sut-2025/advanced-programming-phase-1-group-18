package io.github.group18.Network.Server;

import com.badlogic.gdx.Game;
import io.github.group18.Model.App;
import io.github.group18.Network.Server.App.ListenerThread;
import io.github.group18.Network.Server.App.ServerModel;

public class SetupServer extends Game {
    private final int serverPort = 12345;

    @Override
    public void create() {
        System.out.println("Gdx initialized.");
        App.loadUsersFromDatabase();
        try {
            ServerModel.setListenerThread(new ListenerThread(serverPort));
            ServerModel.startListening();
        } catch (Exception e) {
            System.err.println("Error starting Server: " + e.getMessage());
        }

    }
}
