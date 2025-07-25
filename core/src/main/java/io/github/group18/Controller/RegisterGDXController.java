package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Result;
import io.github.group18.Model.User;
import io.github.group18.View.MainMenu;
import io.github.group18.View.RegisterGDXView;
import io.github.group18.View.RegisterLoginGdxView;
import io.github.group18.enums.Menu;

public class RegisterGDXController
{
    private RegisterGDXView view;

    public void setView(RegisterGDXView view) {
        this.view = view;
    }

    public void handleRegisterGDXButtons() {
        if (view != null)
        {

            view.setUsernameErrorLabel("");
            view.setPasswordErrorLabel("");
            view.setRepasswordErrorLabel("");
            view.setEmailErrorLabel("");
            view.setGenderErrorLabel("");


            if(view.getGoBackButton().isChecked())
            {
                Main.getMain().setScreen(new RegisterLoginGdxView(new RegisterLoginGdxController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getVerifyButton().isChecked())
            {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String repassword = view.getRepasswordField().getText();
                String nickname = view.getNicknameField().getText();
                String email  = view.getEmailField().getText();
                String gender = view.getGenderField().getText();



                //errors/////
                boolean isOkay = true;
                //first
                boolean usernamefound = RegisterMenuController.isUsernameUnique(username);
                if (usernamefound) {
                    view.setUsernameErrorLabel("Your username is not unique!");
                    isOkay = false;
                }

                //second
                if(!password.matches(repassword)) {
                    view.setRepasswordErrorLabel("Passwords do not match!");
                    isOkay = false;

                }

                //third
                if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]+$")) {
                    view.setPasswordErrorLabel("Invalid password: only letters, numbers, and allowed special characters are permitted");
                    isOkay = false;
                }

                //forth
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
                    view.setPasswordErrorLabel("Weak password: must contain " + issues);
                    isOkay = false;
                }

                //fifth
                if (!email.contains("@") || email.indexOf('@') != email.lastIndexOf('@')) {
                    view.setEmailErrorLabel("Email must contain exactly one '@'");
                    isOkay = false;
                }

                String[] parts = email.split("@");
                if (parts.length != 2)
                {
                    view.setEmailErrorLabel("Email format is incorrect");
                    isOkay = false;
                }

                // Check for illegal characters in the whole email
                if (email.matches(".*[?><,\"'`;:\\\\|\\]\\[\\}\\{\\+=\\)\\(\\*&\\^%$#!].*")) {
                    view.setEmailErrorLabel("Email contains illegal characters");
                }

                if(!((gender.toLowerCase().equals("male")) || (gender.toLowerCase().equals("female"))))
                {
                    view.setGenderErrorLabel("Your gender is not Okay!");
                }

                if(isOkay) {
                    String hashedPassword = RegisterMenuController.hashPasswordSHA256(password);
                    User newUser = new User(username, hashedPassword, nickname, email, gender);
                    App.getUsers_List().add(newUser);
                    App.setCurrentUser(newUser);
                    App.setCurrentMenu(Menu.MainMenu);
                    RegisterMenuController.saveUsersToFile();
                    Main.getMain().setScreen(new MainMenu(new MainMenuController(),GameAssetManager.getGameAssetManager().getSkin()));
                }
            }

        }
    }

}
