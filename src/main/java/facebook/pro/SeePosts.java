package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeePosts extends pageLayOut {
    public static String H="";

    public static JPanel createPostPanel() {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel Cpanel = new JPanel();
        Cpanel.setLayout(new BoxLayout(Cpanel, BoxLayout.Y_AXIS));

        if (UserManager.listOfUsers != null) {
            for (int i = 0; i < UserManager.listOfUsers.size(); i++) {
                String uname = UserManager.listOfUsers.get(i).get("username").toString();

                ArrayList<LinkedHashMap<String, Object>> posts =
                        (ArrayList<LinkedHashMap<String, Object>>) UserManager.listOfUsers.get(i).get("posts");
                if (posts != null) {
                    for (LinkedHashMap<String, Object> content : posts) {
                        String privacy = content.get("privacy").toString();
                        if(uname.equals(H)){
                            displayPost(Cpanel, uname, content);
                        }else if  ("Public".equals(privacy) || uname.equals(H) ) {
                            displayPost(Cpanel, uname, content);
                        } else if ("Friends Only".equals(privacy) && areFriends()) {
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
    private static boolean areFriends() {
        for (LinkedHashMap<String, Object> user : UserManager.listOfUsers) {
            if (H.equals(user.get("username"))) {
                String PostOwner = user.get("username").toString();
                ArrayList<LinkedHashMap<String, Object>> friends = (ArrayList<LinkedHashMap<String, Object>>) user.get("friends");
                if (friends != null) {
                    for (LinkedHashMap<String, Object> friend : friends) {
                        if (PostOwner.equals(friend.get("friendname"))) {
                            return true;
                        }
                    }
                }
            }

        } return false;
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