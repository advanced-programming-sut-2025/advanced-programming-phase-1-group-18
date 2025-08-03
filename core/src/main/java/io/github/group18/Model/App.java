package io.github.group18.Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Database.DataManager.UserDataManager;
import io.github.group18.enums.Menu;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class App {
    protected static ArrayList<Game> Games = new ArrayList<>();
    protected static Menu CurrentMenu = Menu.RegisterMenu;
    protected static User CurrentUser;
    protected static Game CurrentGame;
    protected static GameMenuController GameMenuController;
    protected static GameController GameController;
    protected static ArrayList<User> Users_List = new ArrayList<>();
    private static final String USER_FILE = "users.json";

    public static ArrayList<User> getUsers_List() {
        if (Users_List == null) {
            Users_List = new ArrayList<>();
        }
        return Users_List;
    }

    public static void setUsers_List(ArrayList<User> users) {
        Users_List = users;
    }

    // Load users from users.json
    public static void loadUsersFromFile() {
        Users_List = UserDataManager.loadAllUsers();
//        System.out.println("Loaded " + Users_List.size() + " users");
//        File file = new File(USER_FILE);
//        if (!file.exists()) return;
//
//        try (Reader reader = new FileReader(file)) {
//            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
//            Users_List = new Gson().fromJson(reader, userListType);
//            System.out.println("Loaded " + Users_List.size() + " users");
//        } catch (IOException e) {
//            System.err.println("Failed to load users: " + e.getMessage());
//        }
    }
    public static void loadUsersFromDatabase() {
        Users_List = UserDataManager.loadAllUsers();
//        System.out.println("Loaded " + Users_List.size() + " users");
//        File file = new File(USER_FILE);
//        if (!file.exists()) return;
//
//        try (Reader reader = new FileReader(file)) {
//            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
//            Users_List = new Gson().fromJson(reader, userListType);
//            System.out.println("Loaded " + Users_List.size() + " users");
//        } catch (IOException e) {
//            System.err.println("Failed to load users: " + e.getMessage());
//        }
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

    public static GameMenuController getGameMenuController() {
        return GameMenuController;
    }

    public static void setGameMenuController(GameMenuController gameMenuController) {
        GameMenuController = gameMenuController;
    }

    public static GameController getGameController() {
        return GameController;
    }

    public static void setGameController(GameController gameController) {
        GameController = gameController;
    }
}
