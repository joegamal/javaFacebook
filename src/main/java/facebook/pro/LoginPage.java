package facebook.pro;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static facebook.pro.Welcome.*;

public class LoginPage implements pageLayOut{
    

    public static JPanel createLoginPanel() {
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
        pageLayOut.styleButton(loginButton);
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
        pageLayOut.styleButton(backButton);
        backButton.addActionListener(e -> {
            clearFields(emailField, passwordField); // Clear fields when navigating back
            cardLayout.show(cardPanel, "Main Menu");
        });
        buttonPanel.add(backButton);

        panel.add(buttonPanel);
        return panel;
    }

    public static void clearFields(JTextField emailField, JPasswordField passwordField) {
    }

    public static void styleTextField(JTextField emailField) {
    }

    public LoginPage(){
        createLoginPanel();
    }
}
