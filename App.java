package Model;

import enums.Menu;
import Model.*;
import java.util.ArrayList;

public class App {
    protected static Season currentseason = new Season();
    protected static Menu currentMenu = Menu.RegisterMenu;
    protected static User currentuser;
    protected static ArrayList<User> users = new ArrayList<>();

    protected static DateTime currentDateTime = new DateTime();
    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu)
    {
        App.currentMenu = currentMenu;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        App.users = users;
    }

    public static Season getCurrentseason() {
        return currentseason;
    }

    public static void setCurrentseason(Season currentseason) {
        App.currentseason = currentseason;
    }

    public static User getCurrentuser() {
        return currentuser;
    }

    public static void setCurrentuser(User currentuser) {
        App.currentuser = currentuser;
    }

    public static DateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public static void setCurrentDateTime(DateTime currentDateTime) {
        App.currentDateTime = currentDateTime;
    }
}