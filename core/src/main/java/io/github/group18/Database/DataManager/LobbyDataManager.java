package io.github.group18.Database.DataManager;
import io.github.group18.Model.Lobby;
import io.github.group18.Model.User;
import io.github.group18.enums.ppEnum;

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
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mygamedb", "root", "computer");
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
    public static void changeLobbyAdmin(int lobbyId, int newAdminId) {
        String sql = "UPDATE lobbies SET admin_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newAdminId);
            stmt.setInt(2, lobbyId);

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("No lobby found with id: " + lobbyId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Lobby> getAllLobbies() {
        List<Lobby> lobbies = new ArrayList<>();
        String sql = "SELECT * FROM lobbies";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Lobby lobby = null;
                int adminId = rs.getInt("admin_id");
                User admin = new User();
                for (User user : UserDataManager.loadAllUsers()){
                    if (user.getID() == adminId){
                      admin = user;
                    }
                }
                switch (ppEnum.valueOf(rs.getString("access_level"))){
                    case PUBLIC -> lobby = new Lobby(rs.getString("name"),ppEnum.PUBLIC,
                        rs.getBoolean("is_visible"),admin);
                    case PRIVATE -> lobby = new Lobby(rs.getString("name"),ppEnum.PRIVATE,
                        rs.getString("password"),rs.getBoolean("is_visible"),admin);
                }
                lobby.setId(rs.getInt("id"));
//                lobby.setName(rs.getString("name"));
//                lobby.setAccessLevel(ppEnum.valueOf(rs.getString("access_level")));
//                lobby.setVisible(rs.getBoolean("is_visible"));
//                lobby.setPassword(rs.getString("password"));
//                lobby.setCreationTime(rs.getLong("creation_time"));

//
//                User admin = UserDataManager.loadAllUsers()getUserById(adminId); // توابعی مثل getUserById باید داشته باشی
//                lobby.setAdmin(admin);

                List<Integer> users = getUsersInLobby(lobby.getId());
//                ArrayList<User> users1 = new ArrayList<>();setUsers(users);
                for (User user : UserDataManager.loadAllUsers()){
                    if (user.getID() == adminId){
                        admin = user;
                    }
                }
                lobbies.add(lobby);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lobbies;
    }
}
