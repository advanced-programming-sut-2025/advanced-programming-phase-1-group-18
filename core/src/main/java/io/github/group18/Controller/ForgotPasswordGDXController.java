package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.User;
import io.github.group18.View.ForgotPasswordGDXView;
import io.github.group18.View.LoginGDXView;
import io.github.group18.View.RegisterGDXView;
import io.github.group18.View.RegisterLoginGdxView;
import io.github.group18.enums.Menu;

public class ForgotPasswordGDXController
{
    private ForgotPasswordGDXView view;

    public void setView(ForgotPasswordGDXView view) {
        this.view = view;
    }

    public void handleForgotPasswordsButtons() {
        if (view != null)
        {
            view.setAnswerErrorLabel("");
            view.setUsernameErrorLabel("");
            view.setNumberErrorLabel("");
            view.setNewPasswordErrorLabel("");

            if(view.getGoBackButton().isChecked())
            {
                Main.getMain().setScreen(new LoginGDXView(new LoginGDXController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getVerifyButton().isChecked())
            {

                int answer = 0;
                String username = view.getUsername().getText();
                String number = view.getNumber().getText();
                try{
                    String answerr  = view.getAnswer().getText();
                    answer = Integer.parseInt(answerr);
                }
                catch(Exception e)
                {
                    view.setAnswerErrorLabel("answer should be a number");
                }

                String password = view.getNewPassword().getText();
                //errors

                boolean isOkay = true;
                //first:
                if(LoginMenuController.findUserByUsername(username) == null)
                {
                    view.setUsernameErrorLabel("username not found!");
                    isOkay = false;
                }
                //second
                if(!(number.equals("1") || number.equals("2") || number.equals("3")))
                {
                    view.setNumberErrorLabel("wrong number!");
                    isOkay = false;
                }
                //third
                int value1 = ForgotPasswordGDXView.getRandomMade1();
                int value2 = ForgotPasswordGDXView.getRandomMade2();
                int value3 = ForgotPasswordGDXView.getRandomMade3();

                switch (number) {
                    case "1":
                        if (!(answer == value1)) {
                            view.setAnswerErrorLabel("wrong answer!");
                            isOkay = false;
                        }
                        break;
                    case "2":
                        if (!(answer == value2)) {
                            view.setAnswerErrorLabel("wrong answer!");
                            isOkay = false;
                        }
                        break;
                    case "3":
                        if (!(answer == value3)) {
                            view.setAnswerErrorLabel("wrong answer!");
                            isOkay = false;
                        }
                    default:
                        break;
                }

                //for password:
                if (!password.matches("^[a-zA-Z0-9!@#$%^&*()_+=\\-{}\\[\\]:;\"'<>,.?/\\\\|]+$")) {
                    view.setNewPasswordErrorLabel("Invalid password: only letters, numbers, and allowed special characters are permitted");
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
                    view.setNewPasswordErrorLabel("Weak password: must contain " + issues);
                    isOkay = false;
                }



                if(isOkay)
                {
                    User user = LoginMenuController.findUserByUsername(username);
                    String hashedPassword = RegisterMenuController.hashPasswordSHA256(password);
                    user.setPassword(hashedPassword);
                    view.setNewPasswordErrorLabel("Your Password Changed Successfully!");
                }
            }
        }
    }
}
