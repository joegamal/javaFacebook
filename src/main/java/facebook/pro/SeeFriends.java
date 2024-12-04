package facebook.pro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.cliftonlabs.json_simple.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SeeFriends extends pageLayOut{

    

    public static JPanel createFriendsPanel() {


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));
        
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 30, 40));
        formPanel.setBackground(Color.WHITE);
        
        JLabel SearchForFriends = new JLabel("Search for friend:");
        
        JTextField friendNameField = new JTextField(20);

        styleTextField(friendNameField);
        formPanel.add(SearchForFriends);
        formPanel.add(friendNameField);
        
        JButton searchButton = new JButton("search");

        styleButton(searchButton);
        searchButton.addActionListener(e -> {
            String fName = friendNameField.getText();
            searchFriends(fName);
        });
        
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        topRightPanel.add(formPanel);
        topRightPanel.add(searchButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    public static String Name ;

    public static void searchFriends(String usernameToFind){

        JSONArray users = UserManager.loadUsers();


        boolean userFound = false;
        for (Object objectToSearch : users) {

            JSONObject ObjectToSearch = (JSONObject) objectToSearch;
               
            if (usernameToFind.equals(ObjectToSearch.get("username"))) {
                userFound = true;
                Name = ObjectToSearch.get("username").toString();
            }
        }
        if(userFound){
            cardLayout.show(cardPanel, "Friend");
            FreindListShow.createFriendsListPanel().setName("usernameToFind");
        }else{
            JOptionPane.showMessageDialog(frame, "user doesnt exist!");
        }
    }   
    

    public SeeFriends(){
        createFriendsPanel();
    }

}
