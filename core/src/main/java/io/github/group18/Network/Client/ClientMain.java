package io.github.group18.Network.Client;

import io.github.group18.Network.Client.App.ClientApp;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {
            ClientApp.initFromArgs();
            ClientApp.connectServer();
//            ClientApp.startListening();
        } catch (Exception e) {
            System.err.println("Error initializing peer: " + e.getMessage());
            return;
        }

        while (!ClientApp.isEnded()) {
//            String result = PeerCLIController.processCommand(scanner.nextLine().trim());
//            System.out.println(result);
        }
//        scanner.close();
    }
}
