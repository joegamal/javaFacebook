package facebook.pro;
import java.io.Serializable;
import org.json.simple.JSONObject;

public class User implements Serializable {
    private final String email;
    private final String password;
    private final String username;
    private final String gender;
    private final String birthDate;

    // Constructor
    public User(String email, String password, String username, String gender, String birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    // Convert User to JSON
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        json.put("username", username);
        json.put("gender", gender);
        json.put("birthDate", birthDate);
        return json;
    }

    // Convert JSON to User
    public static User fromJson(JSONObject json) {
        if (json == null) {
            throw new IllegalArgumentException("JSON object cannot be null");
        }

        return new User(
                (String) json.get("email"),         // Cast the value to String
                (String) json.get("password"),
                (String) json.get("username"),
                (String) json.get("gender"),
                (String) json.get("birthDate")
        );
    }
}




/*
 * 
 * import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private String username;
    private String gender;
    private String birthDate;
    private ArrayList<String> friends; // List of friends (can be represented as a list of usernames)
    private ArrayList<String> posts;   // List of posts (can be represented as a list of post content)

    // Constructor
    public User(String email, String password, String username, String gender, String birthDate) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.birthDate = birthDate;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    // Getters and Setters for all fields
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<String> posts) {
        this.posts = posts;
    }

    // Method to convert the User object to JSON format
    public JSONObject toJson() {
        JSONObject userJson = new JSONObject();
        userJson.put("email", this.email);
        userJson.put("password", this.password);
        userJson.put("username", this.username);
        userJson.put("gender", this.gender);
        userJson.put("birthDate", this.birthDate);

        // Convert the ArrayLists to JSON arrays
        JSONArray friendsJson = new JSONArray();
        friendsJson.addAll(this.friends);

        JSONArray postsJson = new JSONArray();
        postsJson.addAll(this.posts);

        userJson.put("friends", friendsJson);
        userJson.put("posts", postsJson);

        return userJson;
    }

    // Add methods to manage friends and posts
    public void addFriend(String friendUsername) {
        this.friends.add(friendUsername);
    }

    public void addPost(String postContent) {
        this.posts.add(postContent);
    }
}
 */