//package io.github.group18.Model;
//
//import io.github.group18.Network.GameClient;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Lobby {
//    public final String name;
//    public final int id;
//    public final boolean isPrivate;
//    public final boolean isVisible;
//    public final String password;
//    public final long creationTime;
//    public final List<GameClient> players = new ArrayList<>();
//    private GameClient admin;
//
//    public Lobby(String name, boolean isPrivate, boolean isVisible, String password, GameClient creator) {
//        this.name = name;
//        this.id = new Random().nextInt(100000); // میشه uniqueتر هم ساخت
//        this.isPrivate = isPrivate;
//        this.isVisible = isVisible;
//        this.password = password;
//        this.creationTime = System.currentTimeMillis();
//        this.admin = creator;
//        this.players.add(creator);
//    }
//
//    public GameClient getAdmin() { return admin; }
//
//    public void promoteNewAdmin() {
//        if (players.size() > 0)
//            admin = players.get(0);
//    }
//
//    public boolean isExpired() {
//        return (System.currentTimeMillis() - creationTime > 5 * 60 * 1000) && players.size() <= 1;
//    }
//}
