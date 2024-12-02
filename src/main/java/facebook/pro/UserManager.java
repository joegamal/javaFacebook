package facebook.pro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;

public class UserManager {
    private static final String FILE_NAME = "users.json";

    // Method to register a user
    public static void registerUser(User user) throws IOException {
        JSONArray users = loadUsers();
        if (users == null) {
            users = new JSONArray();
        }

        // Add the new user as a JSONObject
        users.add(user.toJson());

        // Write the updated array back to the file
        try (FileWriter file = new FileWriter(FILE_NAME)) {
            file.write(users.toJSONString());
            file.flush();
        } catch (IOException e) {
            throw new IOException("Error saving user data", e);
        }
    }

    // Method to check if a user exists by email
    public static boolean userExists(String email) {
        JSONArray users = loadUsers();
        if (users == null) {
            return false;
        }

        // Iterate through the array to check for matching email
        for (Object obj : users) {
            JSONObject jsonUser = (JSONObject) obj;
            if (email.equals(jsonUser.get("email"))) {
                return true;
            }
        }
        return false;
    }

    // Method to validate login credentials
    public static boolean loginUser(String email, String password) {
        JSONArray users = loadUsers();
        if (users == null) {
            return false;
        }

        // Iterate through the array to check for matching credentials
        for (Object obj : users) {
            JSONObject jsonUser = (JSONObject) obj;
            if (email.equals(jsonUser.get("email")) && password.equals(jsonUser.get("password"))) {
                return true;
            }
        }
        return false;
    }

    // Helper method to load users from the JSON file
    private static JSONArray loadUsers() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            return (JSONArray) obj;
        } catch (FileNotFoundException e) {
            // File doesn't exist yet; return an empty JSONArray
            return new JSONArray();
        } catch (IOException | ParseException e) {
            System.out.println("Error reading user data.");
            return null;
        }
    }
}
