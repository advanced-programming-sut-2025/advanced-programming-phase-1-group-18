package io.github.group18.Network.Server.App;

import io.github.group18.Model.*;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerModel {
    public static final int TIMEOUT_MILLIS = 500;
    private static final ArrayList<ClientConnectionThread> connections = new ArrayList<>();
    private static boolean exitFlag = false;
    private static ListenerThread listenerThread;
    private static boolean isRunning = false;
    private static GameThread gameThread;

    private static ArrayList<User> onlineUsers = new ArrayList<>();
    private static ArrayList<Lobby> lobbies = new ArrayList<>();

    private static HashMap<String, Boolean> voteTerminateGame = new HashMap<>();
    private static ArrayList<Pair<String, String>> voteRemovePlayer = new ArrayList<>();
    private static ArrayList<ChatMessage> messages = new ArrayList<>();

    private static final List<Map<String, Object>> tradeHistory = new ArrayList<>();

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

    public static void addVoteTerminateGame(String voter, boolean isTerminated) {
        voteTerminateGame.put(voter, isTerminated);
        if (voteTerminateGame.size() == App.getCurrentGame().getPlayers().size()) {
            long terminateVotes = voteTerminateGame.values()
                .stream()
                .filter(v -> v)
                .count();
            if (terminateVotes > App.getCurrentGame().getPlayers().size() / 2) {
                System.out.println("Terminating game");
                for (Player player : App.getCurrentGame().getPlayers()) {
                    ClientConnectionThread clientConnectionThread = getConnectionByUserName(player.getUsername());
                    clientConnectionThread.sendMessage(new Message(new HashMap<>(), Message.Type.remove_user_from_game, Message.Menu.game));
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }
    }

    public static void addVoteRemovePlayer(String voter, String playerToRemove) {
        voteRemovePlayer.add(new Pair<>(playerToRemove, voter));

        for (Player player : App.getCurrentGame().getPlayers()) {
            if (getMostVotedPlayer(player.getOwner().getUsername())) {
                App.getCurrentGame().getPlayers().remove(player);
                ClientConnectionThread clientConnectionThread = getConnectionByUserName(player.getUsername());
                clientConnectionThread.sendMessage(new Message(new HashMap<>(), Message.Type.remove_user_from_game, Message.Menu.game));
            }
        }
    }

    public static boolean getMostVotedPlayer(String username) {
        int count = 0;
        for (int i = 0; i < voteRemovePlayer.size(); i++) {
            Pair<String, String> pair = voteRemovePlayer.get(i);
            if (pair.first.equals(username)) {
                count++;
            }
        }
        return count > App.getCurrentGame().getPlayers().size() / 2;
    }

    public static synchronized void addTradeHistory(Map<String, Object> tradeRecord) {
        tradeHistory.add(tradeRecord);
        if (tradeHistory.size() > 100) {
            tradeHistory.remove(0);
        }
    }

    public static synchronized List<Map<String, Object>> getTradeHistory() {
        return new ArrayList<>(tradeHistory);
    }


    public static void broadcast(Message msg) {
        for (ClientConnectionThread connection : connections) {
            connection.sendMessage(msg);
        }
    }


    public static GameThread getGameThread() {
        return gameThread;
    }

    public static void setGameThread(GameThread gameThread) {
        ServerModel.gameThread = gameThread;
    }

    public static HashMap<String, Boolean> getVoteTerminateGame() {
        return voteTerminateGame;
    }

    public static void setVoteTerminateGame(HashMap<String, Boolean> voteTerminateGame) {
        ServerModel.voteTerminateGame = voteTerminateGame;
    }

    public static ArrayList<Pair<String, String>> getVoteRemovePlayer() {
        return voteRemovePlayer;
    }

    public static void setVoteRemovePlayer(ArrayList<Pair<String, String>> voteRemovePlayer) {
        ServerModel.voteRemovePlayer = voteRemovePlayer;
    }
}
