package io.github.group18.Network.Server.App;

import io.github.group18.Model.ChatMessage;
import io.github.group18.Model.App;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerModel {
    public static final int TIMEOUT_MILLIS = 500;
    private static final ArrayList<ClientConnectionThread> connections = new ArrayList<>();
    private static boolean exitFlag = false;
    private static ListenerThread listenerThread;
    private static boolean isRunning = false;

    private static ArrayList<User> onlineUsers = new ArrayList<>();
    private static ArrayList<Lobby> lobbies = new ArrayList<>();

    private static HashMap<Integer,Boolean>voteTerminateGame = new HashMap<>();
    private static HashMap<Integer,Integer>voteRemovePlayer = new HashMap<>();
    private static ArrayList<ChatMessage> messages = new ArrayList<>();


    public static ClientConnectionThread getConnectionByUser(User user) {
        for (ClientConnectionThread connection : connections) {
            if (connection.getUser().getID() == user.getID()) { //other side ?
                return connection;
            }
        }
        return null;
    }

    public static ClientConnectionThread getConnectionByUserName(String username) {
        for (ClientConnectionThread connection : connections) {
            if (connection.getUser().getUsername().equals(username)) {
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

    public static ArrayList<ClientConnectionThread> getConnections() {
        return new ArrayList<>(ServerModel.connections);
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

    public static ArrayList<User> getOnlineUsers() {
        return onlineUsers;
    }

    public static void setOnlineUsers(ArrayList<User> onlineUsers) {
        ServerModel.onlineUsers = onlineUsers;
    }

    public static boolean isExitFlag() {
        return exitFlag;
    }

    public static void setExitFlag(boolean exitFlag) {
        ServerModel.exitFlag = exitFlag;
    }

    public static ListenerThread getListenerThread() {
        return listenerThread;
    }

    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public static void setLobbies(ArrayList<Lobby> lobbies) {
        ServerModel.lobbies = lobbies;
    }

    public static Lobby getLobbyByUser(User user) {
        for (Lobby lobby : lobbies) {
            if (lobby.getUsers().contains(user)) {
                return lobby;
            }
        }
        return null;
    }

    public static Lobby getLobbyById(int id) {
        for (Lobby lobby : lobbies) {
            if (lobby.getId() == id) {
                return lobby;
            }
        }
        return null;
    }

    public static ArrayList<ChatMessage> getMessages() {
        return messages;
    }

    public static void setMessages(ArrayList<ChatMessage> messages) {
        ServerModel.messages = messages;
    }
    public static void addVoteTerminateGame(int id,boolean isTerminated) {
        voteTerminateGame.put(id, isTerminated);
        System.out.println(voteTerminateGame);
        long terminateVotes = voteTerminateGame.values()
            .stream()
            .filter(v -> v)
            .count();
        if (terminateVotes > App.getCurrentGame().getPlayers().size()/2) {
            System.out.println("Terminating game");
        }
    }
    public static void addVoteRemovePlayer(int id,int playerIndex) {
        voteRemovePlayer.put(id, playerIndex);
        System.out.println(voteRemovePlayer);
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            if (getMostVotedPlayer(i)){
                System.out.println("Removing player " + playerIndex + " from game");
            }
        }
    }
    public static boolean getMostVotedPlayer(int playerIndex) {
        int sum = 0;
        for (Integer i : voteRemovePlayer.keySet()) {
            if (voteRemovePlayer.get(i) == playerIndex) {
                sum++;
            }
        }
        return sum > App.getCurrentGame().getPlayers().size()/2;
    }

}
