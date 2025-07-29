package io.github.group18.Network.Server.App;

import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    public static final int TIMEOUT_MILLIS = 500;
    private static final ArrayList<ClientConnectionThread> connections = new ArrayList<>();
    private static boolean exitFlag = false;
    private static ListenerThread listenerThread;
    private static boolean isRunning = false;



    public static ClientConnectionThread getConnectionByIpPort(String ip, int port) {
        for (ClientConnectionThread connection : connections) {
            if (connection.getOtherSideIP().equalsIgnoreCase(ip) && connection.getOtherSidePort() == port) { //other side ?
                return connection;
            }
        }
        return null;
    }

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void setListenerThread(ListenerThread listenerThread) {
        ServerModel.listenerThread = listenerThread;

    }

    public static List<ClientConnectionThread> getConnections() {
        return List.copyOf(ServerModel.connections);
    }

    public static void startListening() {

        if (listenerThread != null && !listenerThread.isAlive()) {
            System.out.println("Listening for client connections...");
            listenerThread.start();
        } else {
            throw new IllegalStateException("Listener thread is already running or not set.");
        }
    }

    public static void endAll() {
        exitFlag = true;
        for (ClientConnectionThread connection : connections)
            connection.end();
        connections.clear();
        listenerThread.interrupt();
        System.exit(0);
    }

    public static void removeClientConnection(ClientConnectionThread ClientConnectionThread) {
        if (ClientConnectionThread != null) {
            connections.remove(ClientConnectionThread);
            ClientConnectionThread.end();
        }
    }

    public static void addClientConnection(ClientConnectionThread ClientConnectionThread) {
        if (ClientConnectionThread != null && !connections.contains(ClientConnectionThread))
            connections.add(ClientConnectionThread);
    }

    public static boolean isIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(boolean isRunning) {
        ServerModel.isRunning = isRunning;
    }
}
