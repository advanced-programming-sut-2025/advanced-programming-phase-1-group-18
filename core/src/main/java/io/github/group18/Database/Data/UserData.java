package io.github.group18.Database.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private int id;
    private String username;
    private int score;
    private int level;

    public UserData(String username, int score, int level) {
        this.username = username;
        this.score = score;
        this.level = level;
    }

    public UserData(int id, String username, int score, int level) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.level = level;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getScore() { return score; }
    public int getLevel() { return level; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setScore(int score) { this.score = score; }
    public void setLevel(int level) { this.level = level; }

    @Override
    public String toString() {
        return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", score=" + score +
            ", level=" + level +
            '}';
    }
}

