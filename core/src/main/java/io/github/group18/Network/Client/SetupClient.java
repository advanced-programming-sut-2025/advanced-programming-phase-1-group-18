package io.github.group18.Network.Client;

import io.github.group18.Network.Client.App.ClientModel;

public class SetupClient implements Runnable {
    @Override
    public void run() {
        try {
            ClientModel.initFromArgs();
            ClientModel.connectServer();
            System.out.println("Connected to server");
        } catch (Exception e) {
            System.err.println("Error initializing peer: " + e.getMessage());
            return;
        }
    }

    public static void startClientConnectionThread() {
        new Thread(new SetupClient(), "Client-Network-Thread").start();
    }
}
