package facebook.pro;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    public String email;
    public String password;
    public String username;
    public String gender;
    public String birthDate;
    public ArrayList<Post> posts;
    public ArrayList<Messages> messages;
    public static ArrayList<Friends> friends;
    // Constructor
    public User(String email, String password, String username, String gender, String birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
        this.posts = new ArrayList<>();
        this.messages=new ArrayList<>();
    }




}

