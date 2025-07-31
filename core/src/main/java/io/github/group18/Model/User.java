package io.github.group18.Model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (Username != null ? !Username.equals(user.Username) : user.Username != null) return false;
        if (Password != null ? !Password.equals(user.Password) : user.Password != null) return false;
        if (Email != null ? !Email.equals(user.Email) : user.Email != null) return false;
        if (Gender != null ? !Gender.equals(user.Gender) : user.Gender != null) return false;
        if (NickName != null ? !NickName.equals(user.NickName) : user.NickName != null) return false;
        if (stayLoggedIn != user.stayLoggedIn) return false;
        if (highestGold != user.highestGold) return false;
        if (avatar != null ? !avatar.equals(user.avatar) : user.avatar != null) return false;
        return true;
    }
}
