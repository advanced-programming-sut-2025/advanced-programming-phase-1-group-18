package Controller;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LoginMenuController implements MenuEnter, ShowCurrentMenu{

    public Result login(String username, String password, String stayLoggedIn) {
        if (username.isEmpty()) {
            return new Result(false, "you should enter username");
        }
        if (password.isEmpty()) {
            return new Result(false, "you should enter password");
        }
        if (findUserByUsername(username) != null) {
            return new Result(false, "user already exist");
        }
        User user = findUserByUsername(username);
        if (!user.getPassword().equals(password)) {
            return new Result(false, "wrong password");
        }
        else{
            user.setStayLoggedIn(!stayLoggedIn.isEmpty());
            App.setCurrentUser(user);
            App.setCurrentMenu(Menu.MainMenu);
            return new Result(true, "user logged successfully");
        }
    }

    public Result forgetPassword(String username, Scanner scanner) {
        User user = findUserByUsername(username);
        if (user == null) {
            return new Result(false, "user not found");
        }
        String answerCommand = scanner.nextLine();
        if (LoginMenuCommands.Answer.getMather(answerCommand) != null) {
            String answer = LoginMenuCommands.Answer.getMather(answerCommand).group("answer");
            //handling answering task ...
        }
        else {
            return new Result(false, "wrong answer format");
        }
        return null;
    }

    public String PasswordGenerator (){
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
        switch(menuName)
        {
            case "registermenu":
                App.setCurrentMenu(Menu.RegisterMenu);
                System.out.println("You are now in RegisterMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }

    public User findUserByUsername (String username){
        for(User user : App.getUsers_List()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

}
