package io.github.group18.Network.Server;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.group18.Model.App;
import io.github.group18.Network.Server.App.ListenerThread;
import io.github.group18.Network.Server.App.ServerApp;


public class ServerMain {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Silent Mode");
        config.setIdleFPS(1); // مصرف کم

        new Lwjgl3Application(new DummyApp(), config);
        App.loadUsersFromFile();
        try {
            int port = 12345;
            ServerApp.setIsRunning(true);
            ServerApp.setListenerThread(new ListenerThread(port));
            ServerApp.startListening();
        } catch (Exception e) {
            System.err.println("Error starting Server: " + e.getMessage());
            return;
        }
    }
}
