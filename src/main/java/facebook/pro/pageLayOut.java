package facebook.pro;
import javax.swing.*;
import java.awt.*;


public class pageLayOut {

    protected static JFrame frame;
    public static CardLayout cardLayout;
    protected static JPanel cardPanel;
    
    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(75, 144, 255));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(150, 50));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180)));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(211, 210, 210)));
        textField.setPreferredSize(new Dimension(250, 30));
    }


    public static void styleTextField(JPasswordField passwordField) {
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

    public static void clearFields(JTextField emailField, JPasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    public static void clearFields(JTextField friendNameField) {
        friendNameField.setText("");
    }

    protected JPanel createSectionPanel(String title) {
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

}
