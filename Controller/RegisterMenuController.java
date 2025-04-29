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

public class RegisterMenuController implements MenuEnter, ShowCurrentMenu
{
    //first part of controller: Register
    public Result register(String username, String password, String repassword, String nickname, String email,
            String gender)
    {
        if(username.isEmpty())
        {
            return new Result(false, "Username cannot be empty");
        }
        boolean usernamefound = isUsernameUnique(username);
        if(usernamefound)
        {
            return new Result(false, "Username already exists");
        }

        if(password.isEmpty())
        {
            return new Result(false, "Password cannot be empty");
        }
        if(repassword.isEmpty())
        {
            return new Result(false, "PasswordConfirm cannot be empty");
        }
        if(nickname.isEmpty())
        {
            return new Result(false, "Nickname cannot be empty");
        }
        if(email.isEmpty())
        {
            return new Result(false, "Email cannot be empty");
        }
        if(email.length()<=8)
        {
            return new Result(false, "Email is invalid because it is too short");
        }
        if(gender.isEmpty())
        {
            return new Result(false, "Gender cannot be empty");
        }
        if(!password.equals(repassword))
        {
            return new Result(false, "Passwords do not match");
        }

        //check is your username Unique or not

        //Acceptation
        else
        {
             User newUser = new User(username, password, nickname, email, gender);
             App.getUsers_List().add(newUser);
             App.setCurrentUser(newUser);
             App.setCurrentMenu(Menu.MainMenu);
             return new Result(true,"You Registered Successfully");
        }

    }
    public void exit()
    {
        System.exit(0);
    }


    public Result pickQuestion(int questionNumber, String answer, String reAnswer) {
        return new Result(true, "");
    }

    public void menuEnter(String menuName) {
        //from registermenu we can move to loginmenu
        menuName = menuName.toLowerCase();
        switch(menuName)
        {
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
        for(User user : App.getUsers_List())
        {
            if(user.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }
    public String usernamegenerator(String username)
    {
        Random random = new Random();
        int prefix = random.nextInt(900) + 10;
        int suffix = random.nextInt(90) + 10;
        return prefix + username + suffix;
    }
    public static String  PasswordGenerator (){
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
