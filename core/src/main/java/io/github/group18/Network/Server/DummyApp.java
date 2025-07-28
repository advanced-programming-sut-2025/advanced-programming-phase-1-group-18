package io.github.group18.Network.Server;

import com.badlogic.gdx.ApplicationAdapter;
import io.github.group18.Model.App;
import io.github.group18.Network.Server.App.ListenerThread;
import io.github.group18.Network.Server.App.ServerApp;

public class DummyApp extends ApplicationAdapter {
    @Override
    public void create() {
        System.out.println("Gdx initialized.");
        App.loadUsersFromFile();
        try {
            int port = 12345;
            ServerApp.setListenerThread(new ListenerThread(port));
            ServerApp.startListening();
        } catch (Exception e) {
            System.err.println("Error starting Server: " + e.getMessage());
        }
//        new Thread(() -> {
//        }, "Server-Thread").start();
        // حالا Gdx.files در همه‌جا فعال شده
    }
}
