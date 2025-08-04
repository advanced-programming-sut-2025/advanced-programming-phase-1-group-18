package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Result;
import io.github.group18.View.MainMenu;
import io.github.group18.View.ProfileMenu;
import io.github.group18.enums.Menu;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProfileMenuController implements MenuEnter, ShowCurrentMenu {
    private ProfileMenu profileMenu;

    public void handleAvatarChange() {
        String selectedPath = chooseAvatarFromSystem();
        if (selectedPath == null) return;

        try {
            // Ensure the avatars directory exists
            FileHandle avatarsDir = Gdx.files.local("avatars/");
            if (!avatarsDir.exists()) {
                avatarsDir.mkdirs();
            }

            // Copy the file to a user-specific path
            String newFileName = "avatar_" + System.currentTimeMillis() + ".png";
            String newPath = "avatars/" + newFileName;

            FileHandle source = Gdx.files.absolute(selectedPath);
            FileHandle dest = Gdx.files.local(newPath);
            source.copyTo(dest);

            // Update UI and save to JSON
            profileMenu.updateAvatarButtonStyle(newPath);
            changeAvatarByFullPath(App.getCurrentUser().getUsername(), newPath);
        } catch (Exception e) {
            Gdx.app.error("Avatar", "Failed to change avatar", e);
        }
    }

    private String chooseAvatarFromSystem() {
        FileDialog dialog = new FileDialog((Frame) null, "Select Avatar", FileDialog.LOAD);
        dialog.setFile("*.png;*.jpg;*.jpeg");
        dialog.setVisible(true);

        if (dialog.getFile() != null) {
            return dialog.getDirectory() + dialog.getFile();
        }
        return null;
    }

    public static void changeAvatarByFullPath(String username, String newAvatarPath) {
        try {
            FileHandle file = Gdx.files.local("users.json");
            JsonValue jsonData = new JsonReader().parse(file);

            // Find and update the user's avatar path
            for (JsonValue user = jsonData.child; user != null; user = user.next) {
                if (user.getString("Username").equals(username)) {
                    user.remove("avatar");
                    user.addChild("avatar", new JsonValue(newAvatarPath));
                    break;
                }
            }

            // Save changes
            file.writeString(jsonData.toJson(JsonWriter.OutputType.json), false);
            App.getCurrentUser().setAvatar(newAvatarPath);
        } catch (Exception e) {
            Gdx.app.error("JSON", "Failed to update avatar path", e);
        }
    }

    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty()) {
            return new Result(false, "Username cannot be empty");
        }
        if (App.getCurrentUser().getUsername().equals(newUsername)) {
            return new Result(false, "This is your current username");
        }

        for (int i = 0; i < App.getUsers_List().size(); i++) {
            if (App.getUsers_List().get(i).getUsername().equals(newUsername)) {
                return new Result(false, "Username is already taken");
            }
        }

        for (int i = 0; i < newUsername.length(); i++) {
            if (!((newUsername.charAt(i) >= 'a' && newUsername.charAt(i) <= 'z') ||
                (newUsername.charAt(i) >= 'A' && newUsername.charAt(i) <= 'Z') ||
                (newUsername.charAt(i) >= '0' && newUsername.charAt(i) <= '9') ||
                (newUsername.charAt(i) == '-'))) {
                return new Result(false, "invalid username letters.");
            }
        }
        App.getCurrentUser().setUsername(newUsername);
        return new Result(true, "Username changed successfully");
    }

    public Result changeNickname(String newNickname) {
        if (newNickname.isEmpty()) {
            return new Result(false, "Nickname is empty");
        }
        if (App.getCurrentUser().getUsername().equals(newNickname)) {
            return new Result(false, "Nickname is already taken");
        }
        App.getCurrentUser().setNickName(newNickname);
        return new Result(true, "Nickname changed successfully");
    }

    public Result changeEmail(String newEmail) {
        if (newEmail.isEmpty()) {
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

    public Result changePassword(String newPassword, String oldPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            return new Result(false, "Passwords cannot be empty");
        }
        if (!App.getCurrentUser().getPassword().equals(RegisterMenuController.hashPasswordSHA256(oldPassword))) {
            System.out.println(App.getCurrentUser().getPassword());
            return new Result(false, "Wrong Password");
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
        App.getCurrentUser().setPassword(RegisterMenuController.hashPasswordSHA256(newPassword));
        return new Result(true, "Password changed successfully");
    }

    public void userInfo() {
        System.out.println(App.getCurrentUser().getUsername());
        System.out.println(App.getCurrentUser().getNickName());
        System.out.println(App.getCurrentUser().getHighestGold());
        System.out.println(App.getCurrentUser().getTimesPlayed());
    }

    public void menuEnter(String menuName) {
        //from profilemenu we can move to mainmenu
        menuName = menuName.toLowerCase();
        if (menuName.equals("mainmenu")) {//App.setCurrentMenu(Menu.MainMenu);
            System.out.println("You are now in MainMenu!");
        } else {
            System.out.println("Invalid menu");
        }
    }

    public void setView(ProfileMenu profileMenu) {
        this.profileMenu = profileMenu;
        setListeners();
    }

    private void setListeners() {
        profileMenu.getApplyUsername().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = changeUsername(profileMenu.getUsernameTextField().getText());
                if (!result.isSuccessful()) {
                    showErrorDialog(result.getMessage());
                } else {
                    RegisterMenuController.saveUsersToFile();
                    showSuccessDialog(result.getMessage());
                }
            }
        });

        profileMenu.getRandomPassword().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profileMenu.getPasswordTextField().setText(RegisterMenuController.PasswordGenerator());
            }
        });

        profileMenu.getApplyPassword().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = changePassword(profileMenu.getPasswordTextField().getText(), profileMenu.getOldpasswordTextField().getText());
                if (!result.isSuccessful()) {
                    showErrorDialog(result.getMessage());
                } else {
                    RegisterMenuController.saveUsersToFile();
                    showSuccessDialog(result.getMessage());
                }
            }
        });

        profileMenu.getApplyNickname().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = changeNickname(profileMenu.getNicknameTextField().getText());
                if (!result.isSuccessful()) {
                    showErrorDialog(result.getMessage());
                } else {
                    RegisterMenuController.saveUsersToFile();
                    showSuccessDialog(result.getMessage());
                }
            }
        });

        profileMenu.getApplyEmail().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = changeEmail(profileMenu.getEmailTextField().getText());
                if (!result.isSuccessful()) {
                    showErrorDialog(result.getMessage());
                } else {
                    RegisterMenuController.saveUsersToFile();
                    showSuccessDialog(result.getMessage());
                }
            }
        });

        profileMenu.getBackButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        });
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog("Error", GameAssetManager.getGameAssetManager().getSkin());
        dialog.text(message);

        TextButton okButton = new TextButton("OK", GameAssetManager.getGameAssetManager().getSkin());
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.button(okButton);
        dialog.show(profileMenu.getStage());
    }

    private void showSuccessDialog(String message) {
        Dialog dialog = new Dialog("Success", GameAssetManager.getGameAssetManager().getSkin());
        dialog.text(message);

        TextButton okButton = new TextButton("OK", GameAssetManager.getGameAssetManager().getSkin());
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.button(okButton);
        dialog.show(profileMenu.getStage());
    }

}
