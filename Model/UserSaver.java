package Model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import Model.User;

public class UserSaver {
    public static void main(String[] args) {
        // Create a sample user
        User user = new User("john_doe", "securePass", "Johnny", "john@example.com", "Male");
        user.setStayLoggedIn(true);

        // Convert to JSON and save
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("user.json")) {
            gson.toJson(user, writer);  // Serialize user to JSON
            System.out.println("User saved successfully to user.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
