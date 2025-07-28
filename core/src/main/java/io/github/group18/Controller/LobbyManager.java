//package io.github.group18.Controller;
//
//import io.github.group18.Model.Lobby;
//import io.github.group18.Network.GameClient;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class LobbyManager {
//    private final Map<Integer, Lobby> lobbies = new HashMap<>();
//
//    public synchronized Lobby createLobby(String name, boolean isPrivate, boolean isVisible, String password, GameClient creator) {
//        Lobby lobby = new Lobby(name, isPrivate, isVisible, password, creator);
//        lobbies.put(lobby.id, lobby);
//        return lobby;
//    }
//
//    public synchronized void removeLobby(int id) {
//        lobbies.remove(id);
//    }
//
//    public synchronized List<Lobby> getVisibleLobbies() {
//        return lobbies.values().stream()
//            .filter(l -> l.isVisible)
//            .collect(Collectors.toList());
//    }
//
//    public synchronized Lobby getLobbyById(int id) {
//        return lobbies.get(id);
//    }
//
//    public synchronized void cleanupExpiredLobbies() {
//        lobbies.values().removeIf(Lobby::isExpired);
//    }
//}
