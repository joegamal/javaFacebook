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
