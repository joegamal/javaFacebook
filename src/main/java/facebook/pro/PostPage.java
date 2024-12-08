package facebook.pro;

import javax.swing.*;
import java.awt.*;

public class PostPage extends pageLayOut {

    public static JPanel createWritePostsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245)); // Set a light background color for the panel

        // Back button to return to the Home Page
        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel);

        // Panel for the Write Post section
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Adjust spacing

        // Write Post Label
        JLabel writePostLabel = new JLabel("Post:");
        writePostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        postPanel.add(writePostLabel);

        // Text Field for Post Content
        JTextField postContentField = new JTextField(20); // Adjusted width
        styleTextField(postContentField);
        postPanel.add(postContentField);

        // Privacy selection
        String[] privacyOptions = {"Public", "Friends Only"};
        JComboBox<String> privacyComboBox = new JComboBox<>(privacyOptions);
        privacyComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        postPanel.add(privacyComboBox);

        // Post Button
        JButton postButton = new JButton("Post");
        postButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Set larger font
        postButton.setPreferredSize(new Dimension(100, 30));  // Set larger size
        postButton.addActionListener(e -> {
            String postContent = postContentField.getText();
            String privacy = (String) privacyComboBox.getSelectedItem();

            if (!postContent.isEmpty()) {
                try {
                    UserManager.addPost(postContent, privacy); // Add the post using UserManager
                    postContentField.setText(""); // Clear the field
                    JOptionPane.showMessageDialog(frame, "Post submitted successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving post: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please write something before posting.");
            }
        });

        // Add Post Button directly to the card panel
        postPanel.add(postButton);
        panel.add(postPanel);

        return panel;
    }

    public PostPage() {
        createWritePostsPanel();
    }
}
