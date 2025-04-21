package Model;

import enums.*;

import java.util.ArrayList;

public class App {
    protected static Menu CurrentMenu = Menu.RegisterMenu;
    protected static ArrayList<User> Users_List = new ArrayList<>();
    protected static ArrayList<Game> Games = new ArrayList<>();
    protected static User CurrentUser;
    protected static Game CurrentGame;

    public static Menu getCurrentMenu() {
        return CurrentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        CurrentMenu = currentMenu;
    }

    public static ArrayList<User> getUsers_List() {
        return Users_List;
    }

    public static void setUsers_List(ArrayList<User> users_List) {
        Users_List = users_List;
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