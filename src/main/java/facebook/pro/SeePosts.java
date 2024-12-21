package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static facebook.pro.Welcome.cardLayout;
import static facebook.pro.Welcome.cardPanel;

public class SeePosts implements pageLayOut {

    public static String H = "";

    private static Reaction reaction = new Reaction();

    public static JPanel createPostPanel() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel Cpanel = new JPanel();
        Cpanel.setLayout(new BoxLayout(Cpanel, BoxLayout.Y_AXIS));

     String uname;

        if (UserManager.listOfUsers != null && !UserManager.listOfUsers.isEmpty()) {
            for (int i = 0; i < UserManager.listOfUsers.size(); i++) {
                uname = UserManager.listOfUsers.get(i).get("username").toString();

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
        }

        JScrollPane scrollPane = new JScrollPane(Cpanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        postPanel.add(scrollPane, BorderLayout.CENTER);
        return postPanel;
    }

    private static void displayPost(JPanel Cpanel, String uname, LinkedHashMap<String, Object> content) {
        String postContent = (String) content.get("content");

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        JLabel usernameLabel = new JLabel("Username: " + uname);
        userInfoPanel.add(usernameLabel);

        JTextArea textArea = new JTextArea(postContent);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Load and resize icons for Like and Tag
        ImageIcon likeIcon = new ImageIcon(new ImageIcon("thumb-up.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
        ImageIcon tagIcon = new ImageIcon(new ImageIcon("tag.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));

        // Initialize Like counter JLabel
        JLabel likeCountLabel = new JLabel("Likes: 0"); // Default like count

        JButton likeButton = new JButton("LIKE", likeIcon);
        JButton tagButton = new JButton("TAG", tagIcon);

        // Action listener for Like button
        likeButton.addActionListener(e -> {
            String user = JOptionPane.showInputDialog(null, "Enter your username:");
            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty!");
                return;
            }
            reaction.addLike(user);
            // Update like count label based on the number of likes
            likeCountLabel.setText("Likes: " + reaction.getLikeCount());  // Update label text
            JOptionPane.showMessageDialog(null, "You liked the post!");
        });

        // Action listener for Tag button
        tagButton.addActionListener(e -> {
            String tagUser = JOptionPane.showInputDialog(null, "Enter the username to tag:");
            if (tagUser == null || tagUser.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tag username cannot be empty!");
                return;
            } else {
                if (UserManager.searchFriend(tagUser, 1)) {
                    reaction.addTag(tagUser);
                    reaction.displayTags();
                }
            }
        });

        // Action listener for Comment button
        JButton commentButton = new JButton("COMMENT");
        commentButton.addActionListener(e -> new CommentGUI(reaction));

        JPanel buttonPanel = new JPanel();

        // Panel to display the post
        JPanel singlePostPanel = new JPanel(new BorderLayout());
        singlePostPanel.add(userInfoPanel, BorderLayout.NORTH);
        singlePostPanel.add(textArea, BorderLayout.CENTER);

        // Add Like count label below the Like button
        JPanel likePanel = new JPanel();
        likePanel.setLayout(new BoxLayout(likePanel, BoxLayout.Y_AXIS));
        likePanel.add(likeButton);
        likePanel.add(likeCountLabel); // Add the Like count label // Add the Like count label
//        buttonPanel.add(likePanel);

        // Button panel for Like, Tag, and Comment
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(likePanel);
        buttonPanel.add(commentButton);
        buttonPanel.add(tagButton);

        singlePostPanel.add(buttonPanel, BorderLayout.SOUTH);

        Cpanel.add(singlePostPanel);
    }

    public static boolean areFriends(ArrayList<LinkedHashMap<String, Object>> friendsList, String userloop ) {
    if(UserManager.current_user.equals(userloop)){
        return true;
    }
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


    //private static boolean areFriends(String friendUsername) {
      //  for (LinkedHashMap<String, Object> user : UserManager.listOfUsers) {
        //    if (H.equals(user.get("username"))) {
          //      ArrayList<LinkedHashMap<String, Object>> friends =
            //            (ArrayList<LinkedHashMap<String, Object>>) user.get("friends");
            //    if (friends != null) {
              //      for (LinkedHashMap<String, Object> friend : friends) {
                //        if (friendUsername.equals(friend.get("friendname"))) {
                  //          return true;
                    //    }
                //    }
              //  }
        //    }
      //  }
       // return false;
    //}

    public static JPanel createSeePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JButton backButton = new JButton("Back to Home Page");
        pageLayOut.styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));
        panel.add(backButton, BorderLayout.EAST);
        panel.add(createPostPanel(), BorderLayout.CENTER);
        return panel;
    }
}
