
package facebook.pro;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static facebook.pro.pageLayOut.*;


public class UserManager {

    public static String FRIENDNAME ;
    public static String current_user;

    public static ObjectMapper mapper = new ObjectMapper();

    public static  ArrayList<LinkedHashMap<String, Object>> listOfUsers;

    public static File jsonFile = new File("users.json");

    public static void loadUserFromjsonFile(String path){
        try {
            // Read the JSON file content into a string
            String jsonString = new String(Files.readAllBytes(Paths.get("users.json")));

            // Print the JSON string for debugging
//            System.out.println("JSON File Content: \n" + jsonString);

            if(!(jsonString == null || jsonString.isEmpty() || jsonString.equals(""))) {
                // Deserialize JSON into a List of User objects
                listOfUsers = mapper.readValue(new File(path), ArrayList.class);
            }else{
                listOfUsers = new ArrayList<LinkedHashMap<String, Object>>();
            }

//            System.out.println("Parsed Users:");
//
//            listOfUsers.forEach(obj -> System.out.println(obj.get("username")));
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error occurred while parsing JSON:");
        }
    }


    // Method to register a user
    public static void registerUser(User user) throws IOException {
        LinkedHashMap<String, Object> list = new LinkedHashMap<String, Object>();
        list.put("username", user.username);
        list.put("birthDate", user.birthDate);
        list.put("gender", user.gender);
        list.put("email", user.email);
        list.put("password", user.password);
        list.put("friends",user.friends);
        if(listOfUsers == null){
            listOfUsers = new ArrayList<LinkedHashMap<String, Object>>();
        }
        listOfUsers.add(list);
        for(int i = 0; i < listOfUsers.size(); i++){
            System.out.println(listOfUsers.get(i).get("email"));
        }
        UserManager.store();
        UserManager.loadUserFromjsonFile("users.json");
    }
    // Method to check if a user exists by emai
    public static boolean userExists(String email) {

        if (!(listOfUsers == null)) {
            // Iterate through the array to check for matching email
            for (int i = 0; i < listOfUsers.size(); i++) {

                if (email.equals(listOfUsers.get(i).get("email"))) {
                    return false;
                }
            }
        }
        return true;
    }
    //method returns current username


    // Method to validate login credentials
    public static boolean loginUser(String email, String password) {
        if(!(listOfUsers == null)) {
            // Iterate through the array to check for matching credentials
            for (int i = 0; i < listOfUsers.size(); i++) {
                if (email.equals(listOfUsers.get(i).get("email")) && password.equals(listOfUsers.get(i).get("password"))) {
                    current_user = email;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchUsers(String usernameToFind){

        boolean userFound = false;
        for (int i = 0; i < listOfUsers.size(); i++) {
            if (usernameToFind.equals(listOfUsers.get(i).get("username"))) {
                userFound = true;
            }

        }
        if(userFound){
            return true;
        }else{
            return false;
        }
    }

    public static void store () throws IOException{

        jsonFile.delete();
        File jsonFile = new File("users.json");
        String input = "";
        if(!(listOfUsers == null))
            input = listOfUsers.toString();
        // Convert to valid JSON

        String validJson = input
                .replace("=", ":") ; // Close quotes for array elements
//        System.out.println("Valid JSON String: \n" + validJson);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listOfUsers);


            // Write to JSON file
            //File outputFile = new File("output.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("users.json"), listOfUsers);

            System.out.println("Data successfully written to output.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void searchFriends(String name){
        boolean to = false;
        for(int i = 0; i < listOfUsers.size(); i++){
            if(name.equals(listOfUsers.get(i).get("username"))) {
                cardLayout.show(cardPanel, "Friend");
                to = true;
                FRIENDNAME  = name;
            }
        }
        if(to == false){
            JOptionPane.showMessageDialog(frame, "user doesnt exist!");
        }
    }

    public static void addFriend(String friendname, String username){

        boolean one = false;
        int index = 0;
        for(int i = 0; i < listOfUsers.get(User.friends.size()).size(); i++){
            if(listOfUsers.get(i).get("username").equals(username) && listOfUsers.get(i).get(User.friends.get(i)).equals(friendname)){
                JOptionPane.showMessageDialog(frame, "user already a friend!");
                one = true;
                index = i;
            }
        }
        if(one == false){
            listOfUsers.get(index).get(User.friends.add(friendname));
            JOptionPane.showMessageDialog(frame, "friend added !");

        }
    }

    //messaging part----------------------------------
    //send message to a friend
    public static void sendMessage(String message) {
        LinkedHashMap<String, Object> messageDetails = new LinkedHashMap<>();
        messageDetails.put("messagessent", message);
        messageDetails.put("current_Friend", FRIENDNAME);

        for (LinkedHashMap<String, Object> user : listOfUsers) {
            if (user.get("email").equals(current_user)) {
                ArrayList<LinkedHashMap<String, Object>> messagesStored = (ArrayList<LinkedHashMap<String, Object>>) user.get("messages");
                if (messagesStored == null) {
                    messagesStored = new ArrayList<>();
                    user.put("messages", messagesStored);
                }
                messagesStored.add(messageDetails);
                break;
            }
        }
        for (LinkedHashMap<String, Object> user2 : listOfUsers)
        {
            if (user2.get("username").equals(FRIENDNAME))
            {
                ArrayList<LinkedHashMap<String, Object>> messagesStored2 = (ArrayList<LinkedHashMap<String, Object>>) user2.get("messages");
                if (messagesStored2 == null) {
                    messagesStored2 = new ArrayList<>();
                    user2.put("messages", messagesStored2);
                }
                messagesStored2.add(messageDetails);
                break;
            }
        }
        try {
            store();
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
    //getpast messages
    public static ArrayList Getpastmessages()
    {
        ArrayList<String>pastmessage=new ArrayList<>();
        for(LinkedHashMap<String, Object> user : listOfUsers)
        {
            if (user.get("email").equals(current_user))
            {

            }
        }
        return pastmessage;
    }
    //end of messaging part------------------------------------

    public static void addPost(String content, String privacy) {
        // Create a new post with a unique numeric ID (4 digits)
        LinkedHashMap<String, Object> postDetails = new LinkedHashMap<>();
        postDetails.put("postId", String.format("%04d", (int) (Math.random() * 10000))); // Generate a 4-digit numeric ID
        postDetails.put("content", content);
        postDetails.put("privacy", privacy);

        // Find the current user and add the post
        for (LinkedHashMap<String, Object> user : listOfUsers) {
            if (user.get("email").equals(current_user)) {
                ArrayList<LinkedHashMap<String, Object>> posts =
                        (ArrayList<LinkedHashMap<String, Object>>) user.get("posts");

                if (posts == null) {
                    posts = new ArrayList<>();
                    user.put("posts", posts);
                }

                posts.add(postDetails);
                break;
            }
        }

        // Save the updated data to the file
        try {
            store();
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

}

