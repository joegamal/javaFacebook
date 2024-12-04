package facebook.pro;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class FreindListShow extends pageLayOut{

    public static String name = SeeFriends.Name;
   
    public static JPanel createFriendsListPanel(){
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back to Home Page");
        backButton.setPreferredSize(new Dimension(150, 170));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));
        
        JLabel friendName = new JLabel("friend's name");
                
        JButton chaButton = new JButton("Send Messages");
        chaButton.setPreferredSize(new Dimension(150, 170));

        JButton addFriendButton = new JButton("Add Friend");
        addFriendButton.setPreferredSize(new Dimension(150, 170));

        chaButton.addActionListener(e -> {
            //yusuf hassan gui work will be here
        });
        

        addFriendButton.addActionListener(e -> {
            JSONArray users = UserManager.loadUsers();

            for (Object friendToSearch : users){
                JSONObject ObjectToSearch = (JSONObject) friendToSearch;
                JSONArray friends = UserManager.loadUsers();

                
            }
        });


        panel.add(backButton);
        panel.add(friendName);
        panel.add(chaButton);
        panel.add(addFriendButton);
    
        return panel;
    }
        

    public FreindListShow(){
        createFriendsListPanel();
    }

}
