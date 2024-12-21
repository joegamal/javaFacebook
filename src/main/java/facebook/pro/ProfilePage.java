package facebook.pro;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

import static facebook.pro.Welcome.cardLayout;
import static facebook.pro.Welcome.cardPanel;


public class ProfilePage implements pageLayOut {
     public static JPanel createWritePostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        pageLayOut.styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topRightPanel.add(backButton);
        panel.add(topRightPanel, BorderLayout.NORTH);

        return panel;
    }

    public ProfilePage(){
        createWritePostsPanel();
    }
    
}
