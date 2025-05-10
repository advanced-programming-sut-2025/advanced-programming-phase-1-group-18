package Controller;

import Model.App;
import Model.Result;
import enums.Menu;

public class ProfileMenuController implements MenuEnter, ShowCurrentMenu{

    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty()){
            return new Result(false, "Username cannot be empty");
        }
        if (App.getCurrentUser().getUsername().equals(newUsername)) {
            return new Result(false, "Username is already taken");
        }
        for (int i = 0; i < newUsername.length(); i++) {
            if ((newUsername.charAt(i) >= 'a' && newUsername.charAt(i) <= 'z') ||
                    (newUsername.charAt(i) >= 'A' && newUsername.charAt(i) <= 'Z') ||
                    (newUsername.charAt(i) >= '0' && newUsername.charAt(i) <= '9') ||
                    (newUsername.charAt(i) == '-')) {

            } else {
                return new Result(false, "invalid username letters.");
            }
        }
        App.getCurrentUser().setUsername(newUsername);
        return new Result(true, "Username changed successfully");
    }

    public Result changeNickname(String newNickname) {
        if (newNickname.isEmpty()){
            return new Result(false, "Nickname is empty");
        }
        if (App.getCurrentUser().getUsername().equals(newNickname)) {
            return new Result(false, "Nickname is already taken");
        }
        App.getCurrentUser().setNickName(newNickname);
        return new Result(true, "Nickname changed successfully");
    }

    public Result changeEmail(String newEmail) {
        if (newEmail.isEmpty()){
            return new Result(false, "Email is empty");
        }
        if (App.getCurrentUser().getEmail().equals(newEmail)) {
            return new Result(false, "Email is already taken");
        }
        if (newEmail.length() <= 8) {
            return new Result(false, "Email is invalid because it is too short");
        }
        if (!newEmail.contains("@") || newEmail.indexOf('@') != newEmail.lastIndexOf('@')) {
            return new Result(false, "Email must contain exactly one '@'");
        }
        String[] parts = newEmail.split("@");
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
        if (newEmail.matches(".*[?><,\"'`;:\\\\|\\]\\[\\}\\{\\+=\\)\\(\\*&\\^%$#!].*")) {
            return new Result(false, "Email contains illegal characters");
        }
        App.getCurrentUser().setEmail(newEmail);
        return new Result(true, "Email changed successfully");
    }

    public Result changePassword(String newPassword,String oldPassword ) {
        if (oldPassword.isEmpty() || newPassword.isEmpty()){
            return new Result(false, "Passwords cannot be empty");
        }
        if (!App.getCurrentUser().getPassword().equals(oldPassword)) {
            return new Result(false, "Password does not match");
        }
        if (oldPassword.equals(newPassword)) {
            return new Result(true, "passwords are same. try again");
        }
        if (!newPassword.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]+$")) {
            return new Result(false, "Invalid new password: only letters, numbers, and allowed special characters are permitted");
        }

        StringBuilder passwordIssues = new StringBuilder();
        if (newPassword.length() < 8) {
            passwordIssues.append("at least 8 characters, ");
        }
        if (!newPassword.matches(".*[a-z].*")) {
            passwordIssues.append("lowercase letter, ");
        }
        if (!newPassword.matches(".*[A-Z].*")) {
            passwordIssues.append("uppercase letter, ");
        }
        if (!newPassword.matches(".*\\d.*")) {
            passwordIssues.append("a digit, ");
        }
        if (!newPassword.matches(".*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|].*")) {
            passwordIssues.append("a special character, ");
        }

        if (!passwordIssues.isEmpty()) {
            String issues = passwordIssues.toString();
            issues = issues.substring(0, issues.length() - 2);
            return new Result(false, "Weak password: must contain " + issues);
        }
        App.getCurrentUser().setPassword(newPassword);
        return new Result(true, "Password changed successfully");
    }

    public void userInfo() {
        System.out.println(App.getCurrentUser().getUsername());
        System.out.println(App.getCurrentUser().getNickName());
        //        System.out.println(App.getCurrentUser().getMostEarnedMoney());
        //        System.out.println(App.getCurrentUser().getTimePlayed)
    }

    public void menuEnter(String menuName) {
        //from profilemenu we can move to mainmenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
            case "mainmenu":
                App.setCurrentMenu(Menu.MainMenu);
                System.out.println("You are now in MainMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}