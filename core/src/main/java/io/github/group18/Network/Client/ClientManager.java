package io.github.group18.Network.Client;

import io.github.group18.Network.Client.App.ClientApp;

public class ClientManager implements Runnable {
    @Override
    public void run() {
        try {
            ClientApp.initFromArgs();
            ClientApp.connectServer();
            System.out.println("Connected to server");
        } catch (Exception e) {
            System.err.println("Error initializing peer: " + e.getMessage());
            return;
        }
    }

    public static void startInThread() {
        new Thread(new ClientManager(), "Client-Network-Thread").start();
    }
}
