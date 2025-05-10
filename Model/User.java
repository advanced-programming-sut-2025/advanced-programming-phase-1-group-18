package Model;

public class User {

    protected String Username;
    protected String Password;
    protected String Email;
    protected String Gender;
    protected String NickName;
    protected boolean stayLoggedIn;

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
    }

    public User() {

    }
}