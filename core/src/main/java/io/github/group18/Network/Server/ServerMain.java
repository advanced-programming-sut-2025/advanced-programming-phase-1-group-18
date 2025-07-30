package io.github.group18.Network.Server;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.group18.Model.App;
import io.github.group18.Network.Server.App.ListenerThread;
import io.github.group18.Network.Server.App.ServerModel;


public class ServerMain {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Silent Mode");
        config.setIdleFPS(1);

        new Lwjgl3Application(new SetupServer(), config);
        App.loadUsersFromFile();
        try {
            int port = 12345;
            ServerModel.setIsRunning(true);
            ServerModel.setListenerThread(new ListenerThread(port));
            ServerModel.startListening();
        } catch (Exception e) {
            System.err.println("Error starting Server: " + e.getMessage());
            return;
        }
    }
}
