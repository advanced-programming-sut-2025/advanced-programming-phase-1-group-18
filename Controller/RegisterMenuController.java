package Controller;

import Model.*;
import enums.Menu;

import java.util.Scanner;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Model.User;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterMenuController implements MenuEnter, ShowCurrentMenu {

    public static String hashPasswordSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    //first part of controller: Register
    public Result register(String username, String password, String repassword, String nickname, String email,
                           String gender) {
        App.loadUsersFromFile();
        if (username.isEmpty()) {
            return new Result(false, "Username cannot be empty");
        }
        boolean usernamefound = isUsernameUnique(username);
        if (usernamefound) {
            return new Result(false, "Username already exists");
        }

        if (password.isEmpty()) {
            return new Result(false, "Password cannot be empty");
        }
        if (repassword.isEmpty()) {
            return new Result(false, "PasswordConfirm cannot be empty");
        }
        if (nickname.isEmpty()) {
            return new Result(false, "Nickname cannot be empty");
        }
        if (email.isEmpty()) {
            return new Result(false, "Email cannot be empty");
        }
        if (email.length() <= 8) {
            return new Result(false, "Email is invalid because it is too short");
        }
        if (gender.isEmpty()) {
            return new Result(false, "Gender cannot be empty");
        }
        if (!password.equals(repassword)) {
            return new Result(false, "Passwords do not match");
        }

        if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]+$")) {
            return new Result(false, "Invalid password: only letters, numbers, and allowed special characters are permitted");
        }

        StringBuilder passwordIssues = new StringBuilder();
        if (password.length() < 8) {
            passwordIssues.append("at least 8 characters, ");
        }
        if (!password.matches(".*[a-z].*")) {
            passwordIssues.append("lowercase letter, ");
        }
        if (!password.matches(".*[A-Z].*")) {
            passwordIssues.append("uppercase letter, ");
        }
        if (!password.matches(".*\\d.*")) {
            passwordIssues.append("a digit, ");
        }
        if (!password.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|].*")) {
            passwordIssues.append("a special character, ");
        }

        if (passwordIssues.length() > 0) {
            String issues = passwordIssues.toString();
            issues = issues.substring(0, issues.length() - 2);
            return new Result(false, "Weak password: must contain " + issues);
        }

        if (!email.contains("@") || email.indexOf('@') != email.lastIndexOf('@')) {
            return new Result(false, "Email must contain exactly one '@'");
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return new Result(false, "Email format is invalid");
        }

        String local = parts[0];
        String domain = parts[1];

        for (int i = 0; i < username.length(); i++) {
            if ((username.charAt(i) >= 'a' && username.charAt(i) <= 'z') ||
                    (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z') ||
                    (username.charAt(i) >= '0' && username.charAt(i) <= '9') ||
                    (username.charAt(i) == '-')) {

            } else {
                return new Result(false, "invalid uesrname letters.");
            }
        }
// Check local part
        if (!local.matches("^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]$")) {
            return new Result(false, "Invalid local part in email");
        }

// Check domain part
        if (!domain.matches("^[a-zA-Z0-9]([a-zA-Z0-9-]*\\.)+[a-zA-Z]{2,}$")) {
            return new Result(false, "Invalid domain part in email");
        }
        // Check for illegal characters in the whole email
        if (email.matches(".*[?><,\"'`;:\\\\|\\]\\[\\}\\{\\+=\\)\\(\\*&\\^%$#!].*")) {
            return new Result(false, "Email contains illegal characters");
        }
        String hashedPassword = hashPasswordSHA256(password);
        User newUser = new User(username, hashedPassword, nickname, email, gender);
        App.getUsers_List().add(newUser);
        App.setCurrentUser(newUser);
        App.setCurrentMenu(Menu.MainMenu);
        saveUsersToFile();
        return new Result(true, "You Registered Successfully");
    }

    public Result register2(String username, String nickname, String email,
                            String gender, Scanner scanner) {
        App.loadUsersFromFile();
        if (username.isEmpty()) {
            return new Result(false, "Username cannot be empty");
        }
        boolean usernamefound = isUsernameUnique(username);
        if (usernamefound) {
            return new Result(false, "Username already exists");
        }

        if (nickname.isEmpty()) {
            return new Result(false, "Nickname cannot be empty");
        }
        if (email.isEmpty()) {
            return new Result(false, "Email cannot be empty");
        }
        if (email.length() <= 8) {
            return new Result(false, "Email is invalid because it is too short");
        }
        if (gender.isEmpty()) {
            return new Result(false, "Gender cannot be empty");
        }

        if (!email.contains("@") || email.indexOf('@') != email.lastIndexOf('@')) {
            return new Result(false, "Email must contain exactly one '@'");
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return new Result(false, "Email format is invalid");
        }

        String local = parts[0];
        String domain = parts[1];

        // Check local part
        if (!local.matches("^[a-zA-Z0-9](?!.*\\.\\.)[a-zA-Z0-9._-]*[a-zA-Z0-9]$")) {
            return new Result(false, "Invalid local part in email");
        }

        // Check domain part
        if (!domain.matches("^[a-zA-Z0-9]([a-zA-Z0-9-]*\\.)+[a-zA-Z]{2,}$")) {
            return new Result(false, "Invalid domain part in email");
        }
        // Check for illegal characters in the whole email
        if (email.matches(".*[?><,\"'`;:\\\\|\\]\\[\\}\\{\\+=\\)\\(\\*&\\^%$#!].*")) {
            return new Result(false, "Email contains illegal characters");
        }

        //new random password
        //password show
        //voroodi (yes.no)
        //if yes --> voroodi(pick question)
        //
        String generatedpassword = PasswordGenerator();
        System.out.println(generatedpassword);
        String yesOrNo = scanner.nextLine();
        if (yesOrNo.equalsIgnoreCase("no")) {
            return new Result(false, "Okay");

        } else {
            Random rand = new Random();
            int random1 = rand.nextInt(100);
            int random2 = rand.nextInt(100);
            int random3 = rand.nextInt(100);
            int random4 = rand.nextInt(100);
            int random5 = rand.nextInt(100);
            int random6 = rand.nextInt(100);
            System.out.println("1: " + random1 + " + " + random2);
            System.out.println("2: " + random3 + " + " + random4);
            System.out.println("3: " + random5 + " + " + random6);
            System.out.println("choose your question by pick question");
            String voroodi = scanner.nextLine();
            int questionNumber = Integer.parseInt(voroodi.split("\\s+")[3]);
            int answer = Integer.parseInt(voroodi.split("\\s+")[5]);
            int answerconfirm = Integer.parseInt(voroodi.split("\\s+")[7]);
            if (answerconfirm != answer) {
                return new Result(false, "They are not same!");
            }
            switch (questionNumber) {
                case 1:
                    if (answer == random1 + random2) {
                        User newUser = new User(username, generatedpassword, nickname, email, gender);
                        App.getUsers_List().add(newUser);
                        App.setCurrentUser(newUser);
                        App.setCurrentMenu(Menu.MainMenu);
                        return new Result(true, "You Registered Successfully");
                    } else {
                        return new Result(false, "Your answer is incorrect!");
                    }
                case 2:
                    if (answer == random3 + random4) {
                        User newUser = new User(username, generatedpassword, nickname, email, gender);
                        App.getUsers_List().add(newUser);
                        App.setCurrentUser(newUser);
                        App.setCurrentMenu(Menu.MainMenu);
                        return new Result(true, "You Registered Successfully");
                    } else {
                        return new Result(false, "Your answer is incorrect!");
                    }
                case 3:
                    if (answer == random5 + random6) {
                        String hashedPassword = hashPasswordSHA256(generatedpassword);
                        User newUser = new User(username, hashedPassword, nickname, email, gender);
                        App.getUsers_List().add(newUser);
                        App.setCurrentUser(newUser);
                        App.setCurrentMenu(Menu.MainMenu);
                        saveUsersToFile();
                        return new Result(true, "You Registered Successfully");
                    } else {
                        return new Result(false, "Your answer is incorrect!");
                    }
                default: {
                    return new Result(false, "Your entered number is not between 1 and 4!");
                }

            }
        }
        /// //////
    }

    public void exit() {
        System.exit(0);
    }

    private void saveUsersToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(App.getUsers_List(), writer);
            System.out.println("Users saved to users.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Result pickQuestion(int questionNumber, String answer, String reAnswer) {
        return new Result(true, "");
    }

    public void menuEnter(String menuName) {
        //from registermenu we can move to loginmenu
        menuName = menuName.toLowerCase();
        switch (menuName) {
            case "loginmenu":
                App.setCurrentMenu(Menu.LoginMenu);
                System.out.println("You are now in loginMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }

    //check the username is Unique
    public boolean isUsernameUnique(String username) {
        for (User user : App.getUsers_List()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String usernamegenerator(String username) {
        Random random = new Random();
        int prefix = random.nextInt(900) + 10;
        int suffix = random.nextInt(90) + 10;
        return prefix + username + suffix;
    }

    public static String PasswordGenerator() {
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
}
