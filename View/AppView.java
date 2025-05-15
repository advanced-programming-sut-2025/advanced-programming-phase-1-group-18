package View;
import Model.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import enums.Menu;

public class AppView {
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        // Load users from users.json
        App.loadUsersFromFile();

        // Line 12: Check if loggedinuser.json exists and is not empty
        File loggedInUserFile = new File("loggedInUser.json");
        if (loggedInUserFile.exists() && loggedInUserFile.length() > 0) {
            try (FileReader reader = new FileReader(loggedInUserFile)) {
                User user = new Gson().fromJson(reader, User.class);
                if (user != null) {
                    App.setCurrentUser(user);
                    App.setCurrentMenu(Menu.MainMenu);
                    System.out.println("Welcome back, " + user.getUsername());
                }
            } catch (IOException e) {
                System.err.println("Failed to read logged-in user: " + e.getMessage());
            }
        }
        do {

            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.Exit);
    }
}
