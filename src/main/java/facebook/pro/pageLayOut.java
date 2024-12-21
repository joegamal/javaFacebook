package facebook.pro;
import javax.swing.*;
import java.awt.*;


public interface pageLayOut {

    
    static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(75, 144, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(211, 210, 210)));
        textField.setPreferredSize(new Dimension(250, 30));
    }


    static void styleTextField(JPasswordField passwordField) {
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(211, 210, 210)));
        passwordField.setPreferredSize(new Dimension(250, 30));
    }

    public static void clearFields(JTextField emailField, JPasswordField passwordField, JTextField usernameField, JTextField genderField, JTextField birthDateField) {
        emailField.setText("");
        passwordField.setText("");
        usernameField.setText("");
        genderField.setText("");
        birthDateField.setText("");
    }

    static void clearFields(JTextField emailField, JPasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    static void clearFields(JTextField friendNameField) {
        friendNameField.setText("");
    }



}
