package facebook.pro;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePage extends pageLayOut{

    public static JPanel createHomePagePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        // Create Profile button
        JButton profileButton = new JButton("Profile");
        styleButton(profileButton);
        profileButton.addActionListener(e -> cardLayout.show(cardPanel, "Profile"));
        panel.add(profileButton);

        // Create See Posts button
        JButton seePostsButton = new JButton("See Posts");
        styleButton(seePostsButton);
        seePostsButton.addActionListener(e -> cardLayout.show(cardPanel, "See Posts"));
        panel.add(seePostsButton);

        // Create Friends button
        JButton friendsButton = new JButton("Friends");
        styleButton(friendsButton);
        friendsButton.addActionListener(e -> cardLayout.show(cardPanel, "Friends"));
        panel.add(friendsButton);

        // Create Write Posts button
        JButton writePostsButton = new JButton("Write Posts");
        styleButton(writePostsButton);
        writePostsButton.addActionListener(e -> cardLayout.show(cardPanel, "Write Posts"));
        panel.add(writePostsButton);

        // Create Logout button and place it in the bottom-right corner
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> cardLayout.show(cardPanel, "Main Menu"));

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        panel.add(logoutPanel);

        return panel;
    }

    public HomePage(){
        createHomePagePanel();
    }
}
