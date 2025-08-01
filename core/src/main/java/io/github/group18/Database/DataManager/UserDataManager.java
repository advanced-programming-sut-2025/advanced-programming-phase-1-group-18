package io.github.group18.Database.DataManager;

import io.github.group18.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//===========================================
//CREATE TABLE users (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    username VARCHAR(50) NOT NULL UNIQUE,
//    hashed_password VARCHAR(255) NOT NULL,
//    email VARCHAR(100),
//    gender VARCHAR(10),
//    nickname VARCHAR(50),
//    stay_logged_in BOOLEAN DEFAULT FALSE,
//    times_played INT DEFAULT 0,
//    highest_gold INT DEFAULT 0,
//    avatar VARCHAR(255),
//    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//);
//===========================================
public class UserDataManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mygamedb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "computer";

    public static void saveUser(User user) {
        String sql = "INSERT INTO users (username, hashed_password, email, gender, nickname, stay_logged_in, times_played, highest_gold, avatar) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getGender());
            stmt.setString(5, user.getNickName());
            stmt.setBoolean(6, user.isStayLoggedIn());
            stmt.setInt(7, user.getTimesPlayed());
            stmt.setInt(8, user.getHighestGold());
            stmt.setString(9, user.getAvatar());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User loadUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("hashed_password"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setNickName(rs.getString("nickname"));
                user.setStayLoggedIn(rs.getBoolean("stay_logged_in"));
                user.setTimesPlayed(rs.getInt("times_played"));
                user.setHighestGold(rs.getInt("highest_gold"));
                user.setAvatar(rs.getString("avatar"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<User> loadAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("hashed_password"),
                    rs.getString("nickname"),
                    rs.getString("email"),
                    rs.getString("gender")
                );
                user.setStayLoggedIn(rs.getBoolean("stay_logged_in"));
                user.setTimesPlayed(rs.getInt("times_played"));
                user.setHighestGold(rs.getInt("highest_gold"));
                user.setAvatar(rs.getString("avatar"));
                user.setID(rs.getInt("id"));
                System.out.println(rs.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public static void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void saveUsers(List<User> users) throws SQLException {
        for (User user : users) {
            if (userExists(user.getID())) {
                updateUser(user);
            } else {
                saveUser(user);
            }
        }
    }
    public static boolean userExists(int id) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // returns true if user exists
            }
        }
    }
    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, hashed_password = ?, email = ?, gender = ?, nickname = ?," +
            " stay_logged_in = ?, times_played = ?, highest_gold = ?, avatar= ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getGender());
            stmt.setString(5, user.getNickName());
            stmt.setBoolean(6, user.isStayLoggedIn());
            stmt.setInt(7, user.getTimesPlayed());
            stmt.setInt(8, user.getHighestGold());
            stmt.setString(9, user.getAvatar());
            stmt.setInt(10, user.getID());

            stmt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void insertUser(User user) throws SQLException {
//        String sql = "INSERT INTO users (username, password, score, avatar, last_login) VALUES (?, ?, ?, ?, ?)";
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, user.getUsername());
//            stmt.setString(2, user.getPasswordHash());
//            stmt.setInt(3, user.getScore());
//            stmt.setString(4, user.getAvatar());
//            stmt.setTimestamp(5, Timestamp.valueOf(user.getLastLogin()));
//            stmt.executeUpdate();
//        }
//    }

}


