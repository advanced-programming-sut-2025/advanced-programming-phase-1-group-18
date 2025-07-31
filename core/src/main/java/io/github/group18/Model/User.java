package io.github.group18.Model;

import io.github.group18.Controller.RegisterMenuController;

public class User {

    protected String Username;
    protected String Password;
    protected String Email;
    protected String Gender;
    protected String NickName;
    protected boolean stayLoggedIn;
    protected int timesPlayed;
    protected int highestGold;
    protected String avatar;
    protected int ID;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        this.NickName = nickName;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }


    public User(String Username, String Password, String NickName, String Email, String Gender) {
        this.Username = Username;
        this.Password = Password;
        this.Email = Email;
        this.Gender = Gender;
        this.NickName = NickName;
        this.avatar = "avatar.png";
    }

    public User() {

    }


    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public int getHighestGold() {
        return highestGold;
    }

    public void setHighestGold(int highestGold) {
        this.highestGold = highestGold;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
//    public String getHashedPassword() {
//        return RegisterMenuController.hashPasswordSHA256(Password);
//    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
