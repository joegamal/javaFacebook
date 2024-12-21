package facebook.pro;
import javax.swing.*;
import java.awt.*;

import static facebook.pro.Welcome.cardLayout;
import static facebook.pro.Welcome.cardPanel;


public class MainMenuPanel implements pageLayOut{

    public static JPanel menupan(){
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
        pageLayOut.styleButton(registerButton);
        registerButton.addActionListener(e -> cardLayout.show(cardPanel, "Register"));
        buttonPanel.add(registerButton);
        
        JButton loginButton = new JButton("Login");
        pageLayOut.styleButton(loginButton);
        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));
        buttonPanel.add(loginButton);
        
        JButton exitButton = new JButton("Exit");
        pageLayOut.styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);
        
        panel.add(buttonPanel);
        return panel;
    }   
    
    public MainMenuPanel(){
        menupan();
    }

    
}
