package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SeePosts extends pageLayOut{

    public static JPanel createSeePostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    public SeePosts(){
        createSeePostsPanel();
    }
}