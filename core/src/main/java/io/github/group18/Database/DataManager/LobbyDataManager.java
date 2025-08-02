package io.github.group18.Database.DataManager;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LobbyDataManager {
//===========================================
//CREATE TABLE lobbies (
//    id INT PRIMARY KEY AUTO_INCREMENT,
//    name VARCHAR(100),
//    access_level ENUM('PUBLIC', 'PRIVATE'),
//    is_visible BOOLEAN,
//    password VARCHAR(100),
//    creation_time BIGINT,
//    admin_user_id INT
//);
//===========================================
//===========================================
//CREATE TABLE lobby_users (
//    lobby_id INT,
//    user_id INT,
//    FOREIGN KEY (lobby_id) REFERENCES lobbies(id),
//    FOREIGN KEY (user_id) REFERENCES users(id)
//);
//===========================================

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mygamedb", "root", "computer@");
    }

    public static void saveLobby(Lobby lobby) {
        String sql = "INSERT INTO lobbies (id, name, access_level, is_visible, password, creation_time, admin_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lobby.getId());
            stmt.setString(2, lobby.getName());
            stmt.setString(3, lobby.getAccessLevel().name());
            stmt.setBoolean(4, lobby.isVisible());
            stmt.setString(5, lobby.getPassword());
            stmt.setLong(6, lobby.getCreationTime());
            stmt.setInt(7, lobby.getAdmin().getID());

            stmt.executeUpdate();

            for (User user : lobby.getUsers()) {
                addUserToLobby(lobby.getId(), user.getID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserToLobby(int lobbyId, int userId) {
        String sql = "INSERT INTO lobby_users (lobby_id, user_id) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lobbyId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUserFromLobby(int lobbyId, int userId) {
        String sql = "DELETE FROM lobby_users WHERE lobby_id = ? AND user_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lobbyId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLobby(int lobbyId) {
        try (Connection conn = getConnection()) {
            PreparedStatement deleteUsers = conn.prepareStatement("DELETE FROM lobby_users WHERE lobby_id = ?");
            deleteUsers.setInt(1, lobbyId);
            deleteUsers.executeUpdate();

            PreparedStatement deleteLobby = conn.prepareStatement("DELETE FROM lobbies WHERE id = ?");
            deleteLobby.setInt(1, lobbyId);
            deleteLobby.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getUsersInLobby(int lobbyId) {
        List<Integer> users = new ArrayList<>();
        String sql = "SELECT user_id FROM lobby_users WHERE lobby_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lobbyId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(rs.getInt("user_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean lobbyExists(int lobbyId) {
        String sql = "SELECT COUNT(*) FROM lobbies WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, lobbyId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
