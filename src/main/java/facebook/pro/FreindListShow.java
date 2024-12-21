package facebook.pro;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;

import static facebook.pro.Welcome.cardLayout;
import static facebook.pro.Welcome.cardPanel;


class FreindListShow implements pageLayOut{

    public static String friendName ;
    public static JPanel createFriendsListPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton backButton = new JButton("Back to Home Page");
        pageLayOut.styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Home Page"));

        JButton addfriendButton = new JButton("add friend");
        pageLayOut.styleButton(addfriendButton);
        addfriendButton.addActionListener(e -> {
            try {
                UserManager.addfreind(SeeFriends.fName);
                UserManager.store();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton chatButton = new JButton("send message");
        //chatButton.addActionListener();
        //yusuf hasan work will be here
        chatButton.addActionListener(e->cardLayout.show(cardPanel,"Message"));
        pageLayOut.styleButton(chatButton);



        JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        topRightPanel.add(backButton);
        topRightPanel.add(chatButton);
        topRightPanel.add(addfriendButton);
        panel.add(topRightPanel);

        return panel;
    }

    public FreindListShow(){
        createFriendsListPanel();
    }

}