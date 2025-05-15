package Controller;


import Model.App;
import Model.Result;
import Model.User;
import com.google.gson.Gson;
import enums.LoginMenuCommands;
import enums.Menu;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class LoginMenuController implements MenuEnter, ShowCurrentMenu {
    public int add;

    public Result login(String username, String password, String stayLoggedIn) {
        if (username.isEmpty()) {
            return new Result(false, "you should enter username");
        }
        if (password.isEmpty()) {
            return new Result(false, "you should enter password");
        }
//        if (findUserByUsername(username) != null) {
//            return new Result(false, "user already exist");
//        }
        User user = findUserByUsername(username);
        String hashedInput = RegisterMenuController.hashPasswordSHA256(password);
        if (!user.getPassword().equals(hashedInput)) {
            return new Result(false, "wrong password");
        }

        user.setStayLoggedIn(!(stayLoggedIn == null));
        App.setCurrentUser(user);
        App.setCurrentMenu(Menu.MainMenu);


        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("loggedInUser.json")) {
            gson.toJson(user, writer);
            System.out.println("User info saved to loggedInUser.json");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "Login successful but failed to save user data.");
        }
        return new Result(true, "user logged successfully");
    }

    public Result forgetPassword(String username, Scanner scanner) {
        User user = findUserByUsername(username);
        if (user == null) {
            return new Result(false, "user not found");
        }
        Random rand = new Random();
        int rnd1 = rand.nextInt(0, 100);
        int rnd2 = rand.nextInt(0, 100);
        System.out.println("Answer the question : " + rnd1 + " + " + rnd2 + " = ?");
        int answerCommand = Integer.parseInt(scanner.nextLine());
        if (answerCommand == rnd1 + rnd2) {
            String newPassword = PasswordGenerator();
            user.setPassword(newPassword);
            return new Result(true, "your new password is: " + newPassword);
        } else {
            return new Result(false, "wrong answer");
        }
    }

    public String PasswordGenerator() {
        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL = "?><,\"';:\\/|][}{+=)(*&^%$#!";
        int DEFAULT_LENGTH = 10; // می‌تونی بیشتر هم بذاری

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        // اطمینان از داشتن حداقل یک کاراکتر از هر نوع
        List<Character> chars = new ArrayList<>();
        chars.add(LOWER.charAt(random.nextInt(LOWER.length())));
        chars.add(UPPER.charAt(random.nextInt(UPPER.length())));
        chars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        chars.add(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        // بقیه کاراکترها رو تصادفی از تمام مجموعه انتخاب می‌کنیم
        String allChars = LOWER + UPPER + DIGITS + SPECIAL;
        for (int i = 4; i < 10; i++) {
            chars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        // ترتیب رو قاطی می‌کنیم
        Collections.shuffle(chars);

        // تبدیل به رشته
        for (char c : chars) {
            password.append(c);
        }

        return password.toString();
    }

    public void exit() {
        System.exit(0);
    }

    public void menuEnter(String menuName) {
        //from loginmenu we can move to registermenu
        menuName = menuName.toLowerCase();
        switch (menuName) {
            case "registermenu":
                App.setCurrentMenu(Menu.RegisterMenu);
                System.out.println("You are now in RegisterMenu!");
                break;
            case "profilemenu":
                App.setCurrentMenu(Menu.ProfileMenu);
                System.out.println("You are now in ProfileMenu!");
                break;
            case "mainmenu":
                App.setCurrentMenu(Menu.MainMenu);
                System.out.println("You are now in MainMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }

    public User findUserByUsername(String username) {
        for (User user : App.getUsers_List()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
