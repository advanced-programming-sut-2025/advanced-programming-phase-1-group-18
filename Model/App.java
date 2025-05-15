package Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enums.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class App {
    protected static Menu CurrentMenu = Menu.RegisterMenu;
    protected static ArrayList<Game> Games = new ArrayList<>();
    protected static User CurrentUser;
    protected static Game CurrentGame;
    protected static ArrayList<User> Users_List = new ArrayList<>();
    private static final String USER_FILE = "users.json";

    public static ArrayList<User> getUsers_List() {
        return Users_List;
    }

    public static void setUsers_List(ArrayList<User> users) {
        Users_List = users;
    }

    // Load users from users.json
    public static void loadUsersFromFile() {
        File file = new File(USER_FILE);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            Users_List = new Gson().fromJson(reader, userListType);
        } catch (IOException e) {
            System.err.println("Failed to load users: " + e.getMessage());
        }
    }

    // Save users to users.json
    public static void saveUsersToFile() {
        try (Writer writer = new FileWriter(USER_FILE)) {
            new Gson().toJson(Users_List, writer);
        } catch (IOException e) {
            System.err.println("Failed to save users: " + e.getMessage());
        }
    }
    public static Menu getCurrentMenu() {
        return CurrentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        CurrentMenu = currentMenu;
    }

    public static ArrayList<Game> getGames() {
        return Games;
    }

    public static void setGames(ArrayList<Game> games) {
        Games = games;
    }

    public static User getCurrentUser() {
        return CurrentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentUser = currentUser;
    }

    public static Game getCurrentGame() {
        return CurrentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        CurrentGame = currentGame;
    }
}