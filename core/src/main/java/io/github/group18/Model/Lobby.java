package io.github.group18.Model;


import io.github.group18.enums.ppEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Lobby {
    private final String name;
    private final int id;
    private static int idcounter = 0;
    private final ppEnum accessLevel;
    private final boolean isVisible;
    private String password;
    private final long creationTime;
    private final List<User> users = new ArrayList<>();
    private ArrayList<Integer>choseMap;
    private User admin;

    public Lobby(String name, ppEnum accessLevel, boolean isVisible, User creator) {
        this.name = name;
        this.id = ++idcounter;
        this.accessLevel = accessLevel;
        this.isVisible = isVisible;
        this.creationTime = System.currentTimeMillis();
        this.admin = creator;
        this.users.add(creator);
    }

    public Lobby(String name, ppEnum accessLevel, String password, boolean isVisible, User creator) {
        this.name = name;
        this.id = ++idcounter;
        this.accessLevel = accessLevel;
        this.isVisible = isVisible;
        this.password = password;
        this.creationTime = System.currentTimeMillis();
        this.admin = creator;
        this.users.add(creator);
    }

    public void declareNewAdmin() {
        users.remove(admin);
        admin = users.get(0);
    }

    public boolean isExpired() {
        return (System.currentTimeMillis() - creationTime > 5 * 60 * 1000) && users.size() <= 1;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public static int getIdcounter() {
        return idcounter;
    }

    public static void setIdcounter(int idcounter) {
        Lobby.idcounter = idcounter;
    }

    public ppEnum getAccessLevel() {
        return accessLevel;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getPassword() {
        return password;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
    public void initMaps(){
        choseMap = new ArrayList<>();
        for (User user : users) {
            choseMap.add(0);
        }
    }

    public ArrayList<Integer> getChoseMap() {
        return choseMap;
    }

    public void setChoseMap(ArrayList<Integer> choseMap) {
        this.choseMap = choseMap;
    }
}
