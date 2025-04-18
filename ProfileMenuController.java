package Controller;

import Model.App;
import Model.Result;
import Model.User;
import enums.Menu;

public class ProfileMenuController {

    public String showCurrentMenu() {
        return App.getCurrentMenu().toString();
    }

    public void menuEnter(String menuName) {
        switch (menuName) {
            case "Main Menu":
                App.setCurrentMenu(Menu.ProfileMenu);
                break;
            default:
                System.out.println("invalid menu name");
        }

    }
    public void exit() {
        App.setCurrentMenu(Menu.MainMenu);
    }
    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty()){
            return new Result(false, "Username cannot be empty");
        }
        if (App.getCurrentuser().getUsername().equals(newUsername)) {
            return new Result(false, "Username is already taken");
        }
        //get validator from register controller
//        if (usernameValidator(username) == flase){
//            return new Result(false, "Username format is not valid");
//        }
        App.getCurrentuser().setUsername(newUsername);
        return new Result(true, "Username changed successfully");
    }

    public Result changeNickname(String newNickname) {
        if (newNickname.isEmpty()){
            return new Result(false, "Nickname is empty");
        }
        if (App.getCurrentuser().getUsername().equals(newNickname)) {
            return new Result(false, "Nickname is already taken");
        }
        App.getCurrentuser().setNickName(newNickname);
        return new Result(true, "Nickname changed successfully");
    }

    public Result changeEmail(String newEmail) {
        if (newEmail.isEmpty()){
            return new Result(false, "Email is empty");
        }
        if (App.getCurrentuser().getEmail().equals(newEmail)) {
            return new Result(false, "Email is already taken");
        }
        //get validator from register controller
//        if (emailValidator(email)==false){
//            return new Result(false, "Email format is not valid");
//        }
        App.getCurrentuser().setEmail(newEmail);
        return new Result(true, "Email changed successfully");
    }

    public Result changePassword(String oldPassword, String newPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty()){
            return new Result(false, "Passwords cannot be empty");
        }
        if (!App.getCurrentuser().getPassword().equals(oldPassword)) {
            return new Result(false, "Password does not match");
        }
        if (oldPassword.equals(newPassword)) {
            return new Result(true, "passwords are same. try again");
        }
        //get validator from register controller
//        if (passwordValidator(newPassword)==false){
//            return new Result(false, "new password format is not valid");
//        }
        App.getCurrentuser().setPassword(newPassword);
        return new Result(true, "Password changed successfully");
    }

    public void userInfo() {
        System.out.println(App.getCurrentuser().getUsername());
        System.out.println(App.getCurrentuser().getNickName());
//        System.out.println(App.getCurrentuser().getMostEarnedMoney());
//        System.out.println(App.getCurrentuser().getTimePlayed);
    }
}
