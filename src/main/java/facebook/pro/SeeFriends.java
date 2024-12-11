package facebook.pro;



import com.fasterxml.jackson.databind.JsonNode;
import com.github.cliftonlabs.json_simple.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SeeFriends extends pageLayOut{

    public static String fName;

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
            fName = friendNameField.getText();
            UserManager.searchFriends(fName);
        });
        
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        topRightPanel.add(formPanel);
        topRightPanel.add(searchButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    public static String Name ;

    public SeeFriends(){
        createFriendsPanel();
    }

}
