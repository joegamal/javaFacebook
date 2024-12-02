package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        // Create the frame and layout
        frame = new JFrame("facebook");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        ImageIcon image = new ImageIcon("logo.png");
        frame.setIconImage(image.getImage());

        // Set up the main menu panel
        JPanel mainMenuPanel = createMainMenuPanel();

        // Set up the registration panel
        JPanel registerPanel = createRegisterPanel();

        // Set up the login panel
        JPanel loginPanel = createLoginPanel();

        // Set up the home page panel
        JPanel homePagePanel = createHomePagePanel();


        // Set up the additional panels for the home page features

        JPanel profilePanel = createSectionPanel("Profile");
        JPanel seePostsPanel = createSectionPanel("See Posts");
        JPanel friendsPanel = createSectionPanel("Friends");
        JPanel writePostsPanel = createSectionPanel("Write Posts");

        // Add all panels to the card panel
        cardPanel.add(mainMenuPanel, "Main Menu");
        cardPanel.add(registerPanel, "Register");
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(homePagePanel, "Home Page");
        cardPanel.add(profilePanel, "Profile");
        cardPanel.add(seePostsPanel, "See Posts");
        cardPanel.add(friendsPanel, "Friends");
        cardPanel.add(writePostsPanel, "Write Posts");

        // Add the card panel to the frame
        frame.add(cardPanel);

        // Set frame properties
        frame.setSize(380, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    private static JPanel createMainMenuPanel() {
        // Main menu code remains the same
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("facebook      ", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(30, 144, 255));
        panel.add(Box.createVerticalStrut(50));
        panel.add(welcomeLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JButton registerButton = new JButton("Register");
        styleButton(registerButton);
        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "Register"));
        buttonPanel.add(registerButton);

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        buttonPanel.add(loginButton);

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        panel.add(buttonPanel);
        return panel;
    }

    private static JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 20, 35));
        formPanel.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        styleTextField(emailField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        styleTextField(usernameField);
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        JLabel genderLabel = new JLabel("Gender (M/F):");
        JTextField genderField = new JTextField(20);
        styleTextField(genderField);
        formPanel.add(genderLabel);
        formPanel.add(genderField);

        JLabel birthDateLabel = new JLabel("Birth Date (yyyy-mm-dd):");
        JTextField birthDateField = new JTextField(20);
        styleTextField(birthDateField);
        formPanel.add(birthDateLabel);
        formPanel.add(birthDateField);

        panel.add(formPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 70));

        JButton submitButton = new JButton("Register");
        styleButton(submitButton);
        submitButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String username = usernameField.getText();
            String gender = genderField.getText().toUpperCase();
            String birthDate = birthDateField.getText();

            if (email.isEmpty() || password.isEmpty() || username.isEmpty() || gender.isEmpty() || birthDate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.");
            } else if (!gender.equals("M") && !gender.equals("F")) {
                JOptionPane.showMessageDialog(frame, "Gender must be 'M' or 'F'.");
            } else if (password.length() < 6) {
                JOptionPane.showMessageDialog(frame, "Password must be at least 6 characters.");
            } else {
                try {
                    if (UserManager.userExists(email)) {
                        JOptionPane.showMessageDialog(frame, "Email already exists.");
                    } else {
                        UserManager.registerUser(new User(email, password, username, gender, birthDate));
                        JOptionPane.showMessageDialog(frame, "Registration successful!");
                        clearFields(emailField, passwordField, usernameField, genderField, birthDateField);
                        cardLayout.show(cardPanel, "Main Menu");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "An error occurred.");
                }
            }
        });
        buttonPanel.add(submitButton);

        JButton backButton = new JButton("Back to Main Menu");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            clearFields(emailField, passwordField, usernameField, genderField, birthDateField);
            cardLayout.show(cardPanel, "Main Menu");
        });
        buttonPanel.add(backButton);

        panel.add(buttonPanel);
        return panel;
    }

    // Restore clearFields method
    private static void clearFields(JTextField emailField, JPasswordField passwordField, JTextField usernameField, JTextField genderField, JTextField birthDateField) {
        emailField.setText("");
        passwordField.setText("");
        usernameField.setText("");
        genderField.setText("");
        birthDateField.setText("");
    }
    // Overloaded clearFields method for login panel
    public static void clearFields(JTextField emailField, JPasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    private static JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        // Add form labels and fields with consistent width
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 30, 40));
        formPanel.setBackground(Color.WHITE);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        styleTextField(emailField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        panel.add(formPanel);

        // Center and add buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 120));

        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (UserManager.loginUser(email, password)) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                clearFields(emailField, passwordField); // Clear fields after successful login
                cardLayout.show(cardPanel, "Home Page"); // Redirect to Home Page
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email or password. Please try again.");
            }
        });
        buttonPanel.add(loginButton);

        JButton backButton = new JButton("Back to Main Menu");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            clearFields(emailField, passwordField); // Clear fields when navigating back
            cardLayout.show(cardPanel, "Main Menu");
        });
        buttonPanel.add(backButton);

        panel.add(buttonPanel);
        return panel;
    }

    private static JPanel createHomePagePanel() {
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


    // Create generic section panels like Profile, See Posts, Write Posts, Friends
    private static JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());  // Use BorderLayout for the full control over components

        // Create the button to navigate back to the Home Page
        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        // Create a top-right corner panel to hold the back button
        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);  // Add button to the right side of the panel
        panel.add(topRightPanel, BorderLayout.NORTH);  // Attach the panel to the NORTH of the layout

        return panel;
    }

    // For Profile Panel
    private static JPanel createProfilePanel() {
        JPanel panel = new JPanel(new BorderLayout());  // Set the layout to BorderLayout

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));  // FlowLayout to place at the top-right
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);  // Attach the top-right panel to the NORTH

        return panel;
    }

    // Similarly, update the methods for SeePosts, WritePosts, Friends
    private static JPanel createSeePostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    private static JPanel createWritePostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    private static JPanel createFriendsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }




    private static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(75, 144, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(211, 210, 210)));
        textField.setPreferredSize(new Dimension(250, 30));
    }

    private static void styleTextField(JPasswordField passwordField) {
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(211, 210, 210)));
        passwordField.setPreferredSize(new Dimension(250, 30));
    }
}
