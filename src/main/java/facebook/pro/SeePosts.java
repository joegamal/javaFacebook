package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeePosts extends pageLayOut {

    //public static boolean logedIn = false;
    public static JPanel createPostPanel() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel Cpanel = new JPanel();
        Cpanel.setLayout(new BoxLayout(Cpanel, BoxLayout.Y_AXIS));

        String uname;

        for (int i = 0; i < UserManager.listOfUsers.size(); i++) {

            //current user to dispaly posta.
            uname = UserManager.listOfUsers.get(i).get("username").toString();


            //friendsList to be sent to the function.
            ArrayList<LinkedHashMap<String, Object>> friendsList =
                    (ArrayList<LinkedHashMap<String, Object>>) UserManager.listOfUsers.get(i).get("friends");

            ArrayList<LinkedHashMap<String, Object>> posts =
                    (ArrayList<LinkedHashMap<String, Object>>) UserManager.listOfUsers.get(i).get("posts");


            if (posts != null) {
                for (LinkedHashMap<String, Object> content : posts) {
                    String privacy = content.get("privacy").toString();
                    if (uname.equals(UserManager.current_user)) {
                        displayPost(Cpanel, uname, content);
                    } else if ("Public".equals(privacy)) {
                        displayPost(Cpanel, uname, content);
                    } else if ("Friends Only".equals(privacy) && areFriends(friendsList, uname)) {
                        displayPost(Cpanel, uname, content);
                    }
                }
            }
        }






        JScrollPane scrollPane = new JScrollPane(Cpanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        postPanel.add(scrollPane, BorderLayout.CENTER);
        return postPanel;
    }





    public static void displayPost(JPanel Cpanel, String uname, LinkedHashMap<String, Object> content) {
        String postContent = content.get("content").toString();

        // User info panel
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        JLabel usernameLabel = new JLabel("Username: " + uname);
        userInfoPanel.add(usernameLabel);

        JTextArea textArea = new JTextArea(postContent);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Load and resize icons
        ImageIcon likeIcon = new ImageIcon(new ImageIcon("thumb-up.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        ImageIcon commentIcon = new ImageIcon(new ImageIcon("comment.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        ImageIcon tagIcon = new ImageIcon(new ImageIcon("tag.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

        JButton likeButton = new JButton("LIKE", likeIcon);
        JButton commentButton = new JButton("COMMENT", commentIcon);
        JButton tagButton = new JButton("TAG", tagIcon);

        // Adding action listeners to buttons
        likeButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "You liked the post!"));
        commentButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "You commented on the post!"));
        tagButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "You tagged this post!"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(likeButton);
        buttonPanel.add(commentButton);
        buttonPanel.add(tagButton);

        JPanel singlePostPanel = new JPanel(new GridLayout(2, 2, 30, 40));
        singlePostPanel.setLayout(new BorderLayout());
        singlePostPanel.add(userInfoPanel, BorderLayout.NORTH);
        singlePostPanel.add(textArea, BorderLayout.CENTER);
        singlePostPanel.add(buttonPanel, BorderLayout.SOUTH);


        Cpanel.add(singlePostPanel);
    }


    public static boolean areFriends(ArrayList<LinkedHashMap<String, Object>> friendsList, String userloop ) {
        if(UserManager.current_user.equals(userloop)){ return true; }
        else {
            if(friendsList != null) {
                for (LinkedHashMap<String, Object> friend : friendsList) {
                    if (UserManager.current_user.equals(friend.get("friendname"))) {
                        return true;
                    }
                }
            }

        }
         return false;
    }
    public static JPanel createSeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));
        panel.add(backButton, BorderLayout.EAST);
        panel.add(createPostPanel(), BorderLayout.CENTER);

        return panel;
    }
}