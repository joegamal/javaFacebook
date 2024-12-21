package facebook.pro;

import java.awt.*;
import javax.swing.*;

public class HomePage extends pageLayOut {

    public static JPanel createHomePagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add "Facebook" logo label
        JLabel logoLabel = new JLabel("facebook");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 27));
        logoLabel.setForeground(new Color(30, 144, 255));
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.add(logoLabel);
        panel.add(logoPanel, BorderLayout.NORTH);

        // Center panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Create See Posts button
        JButton seePostsButton = new JButton("See Posts");
        styleButton(seePostsButton);
        seePostsButton.addActionListener(e -> cardLayout.show(cardPanel, "See Posts"));
        buttonPanel.add(seePostsButton);

        // Create Friends button
        JButton friendsButton = new JButton("Friends");
        styleButton(friendsButton);
        friendsButton.addActionListener(e -> cardLayout.show(cardPanel, "Friends"));
        buttonPanel.add(friendsButton);

        // Create Write Posts button
        JButton writePostsButton = new JButton("Write Posts");
        styleButton(writePostsButton);
        writePostsButton.addActionListener(e -> cardLayout.show(cardPanel, "PostWrite"));
        buttonPanel.add(writePostsButton);

        // Create Logout button
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> cardLayout.show(cardPanel, "Main Menu"));
        buttonPanel.add(logoutButton);

        // Add button panel to the center
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;

    }

    public HomePage() {
        createHomePagePanel();
    }
}