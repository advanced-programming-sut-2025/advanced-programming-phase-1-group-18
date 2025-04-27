package Controller;

public class ProfileMenuController implements MenuEnter, ShowCurrentMenu{

    public Result changeUsername(String newUsername) {
        if (newUsername.isEmpty()){
            return new Result(false, "Username cannot be empty");
        }
        if (App.getCurrentUser().getUsername().equals(newUsername)) {
            return new Result(false, "Username is already taken");
        }
        //get validator from register controller
        //        if (usernameValidator(username) == flase){
        //            return new Result(false, "Username format is not valid");
        //        }
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
        //get validator from register controller
        //        if (emailValidator(email)==false){
        //            return new Result(false, "Email format is not valid");
        //        }
        App.getCurrentUser().setEmail(newEmail);
        return new Result(true, "Email changed successfully");
    }

    public Result changePassword(String oldPassword, String newPassword) {
        if (oldPassword.isEmpty() || newPassword.isEmpty()){
            return new Result(false, "Passwords cannot be empty");
        }
        if (!App.getCurrentUser().getPassword().equals(oldPassword)) {
            return new Result(false, "Password does not match");
        }
        if (oldPassword.equals(newPassword)) {
            return new Result(true, "passwords are same. try again");
        }
        //get validator from register controller
        //        if (passwordValidator(newPassword)==false){
        //            return new Result(false, "new password format is not valid");
        //        }
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
