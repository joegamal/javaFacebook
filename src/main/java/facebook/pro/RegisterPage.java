package facebook.pro;

import java.io.IOException;

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
import java.util.ArrayList;

public class RegisterPage extends pageLayOut{

    public static JPanel createRegisterPanel() {
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
                    if (!UserManager.userExists(email)) {
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

    public RegisterPage(){
        createRegisterPanel();
    }
    
}
