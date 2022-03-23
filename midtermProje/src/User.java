import posts.BasePost;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User {
    // işlem yapmayaa başlamadan öncee
    // getter ve setter ayarlıyorum
    // doğum yılını date çevirme işlemi
    static int i = 1;
    String name, username, graduatedSchool;
    private String password;
    private int userID = 0;
    private Date dateOfBirth;
    private Date lastLogin;
    ArrayList<User> blockedUsers = new ArrayList<User>();
    ArrayList<User> friendsList = new ArrayList<User>();
    ArrayList<BasePost> collectionOfPosts = new ArrayList<BasePost>();

    public User(String name, String username, String password, Date dateOfBirth, String graduatedSchool) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.graduatedSchool = graduatedSchool;
        userID = i++;
    }
    public User(){

    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getDateOfBirth() {
        return new SimpleDateFormat("MM/dd/yyyy").format(dateOfBirth);

    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // it formats the date to the String as "MM/dd/yyyy"
    public String getLastLogin() {
        return new SimpleDateFormat("MM/dd/yyyy").format(dateOfBirth);

    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", graduatedSchool='" + graduatedSchool + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public void addPost(BasePost basePost){

        collectionOfPosts.add(basePost);

    }



}
