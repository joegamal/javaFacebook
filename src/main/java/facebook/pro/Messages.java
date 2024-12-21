package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Messages extends pageLayOut implements ActionListener {
    JTextField messagefield;
    JButton sendbutton;
    JButton showbutton;
    JButton back;
    JPanel elementpanel;
    JScrollPane scroll;
    JPanel toppanel;
    JPanel botpanel;

    public ArrayList<String> GetyourpastMessages(){
        ArrayList<String>pastmessages= new ArrayList<>();
        for (LinkedHashMap<String,Object>user:UserManager.listOfUsers)
        {
            if(user.get("email").equals(UserManager.current_user))
            {
                ArrayList<LinkedHashMap<String,Object>>friend=(ArrayList<LinkedHashMap<String, Object>>) user.get("friends");

                for (LinkedHashMap<String ,Object>frienddata:friend)
                {
                    if (frienddata.get("friendname").equals(UserManager.FRIENDNAME))
                    {
                        ArrayList<String>messagesfromuser=(ArrayList<String>) frienddata.get("messagesYouwrite");

                        for (int i = 0; i < (messagesfromuser.size()); i++) {
                            pastmessages.add(messagesfromuser.get(i));
                        }
                    }
                }
            }
        }
        return pastmessages;
    }

    public ArrayList<String> GetfriendpastMessages(){
        ArrayList<String>pastmessages= new ArrayList<>();
        for (LinkedHashMap<String,Object>user:UserManager.listOfUsers)
        {
            if(user.get("email").equals(UserManager.current_user))
            {
                ArrayList<LinkedHashMap<String,Object>>friend=(ArrayList<LinkedHashMap<String, Object>>) user.get("friends");
                for (LinkedHashMap<String ,Object>frienddata:friend)
                {
                    if (frienddata.get("friendname").equals(UserManager.FRIENDNAME))
                    {
                        ArrayList<String>messagesfromfriend=(ArrayList<String>) frienddata.get("messages(He/she)write");
                        for (int i = 0; i < (messagesfromfriend.size()); i++) {
                            pastmessages.add(messagesfromfriend.get(i));
                        }
                    }
                }
            }
        }
        return pastmessages;
    }

    private JPanel MessagePan() {

        //intializing panels
        elementpanel = new JPanel();
        elementpanel.setLayout(new BoxLayout(elementpanel, BoxLayout.PAGE_AXIS));
        toppanel = new JPanel();
        botpanel = new JPanel();
        JPanel messagepan = new JPanel(new BorderLayout());



        //Go back button
        back = new JButton("<Back");
        styleButton(back);
        back.setPreferredSize(new Dimension(150, 30));
        back.addActionListener(e ->
        {cardLayout.show(cardPanel, "Friend");
            elementpanel.removeAll();});

        //show past messages Button
        showbutton=new JButton("Show Past Messages");
        styleButton(showbutton);
        showbutton.setPreferredSize(new Dimension(150,50));
        showbutton.addActionListener(this);

        //The send button.
        sendbutton = new JButton("Send>");
        styleButton(sendbutton);
        sendbutton.setPreferredSize(new Dimension(80, 50));
        sendbutton.addActionListener(this);

        //The Field message will be written in.
        messagefield = new JTextField();
        styleTextField(messagefield);
        messagefield.setBackground(Color.LIGHT_GRAY);

        //scrollbar for tall chats
        scroll = new JScrollPane(elementpanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //panel of inputs
        botpanel.setLayout(new BorderLayout());
        botpanel.add(messagefield, BorderLayout.CENTER);
        botpanel.add(sendbutton, BorderLayout.EAST);

        //panel of top button
        toppanel.setLayout(new BorderLayout());
        toppanel.add(back, BorderLayout.WEST);
        toppanel.add(showbutton,BorderLayout.EAST);
        toppanel.setPreferredSize(new Dimension(120, 50));

        //final panel shown to the user
        messagepan.add(scroll, BorderLayout.CENTER);
        messagepan.add(botpanel, BorderLayout.SOUTH);
        messagepan.add(toppanel, BorderLayout.NORTH);
        return messagepan;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sendbutton) {
            // send button function

            String textsent = messagefield.getText();

            if (textsent.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No Message Found", "text not found", JOptionPane.ERROR_MESSAGE);
            } else {
                JTextArea messagesent = new JTextArea();
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.setPreferredSize(new Dimension(120, 50));
                panel.setBorder(BorderFactory.createTitledBorder("message"));
                panel.setBackground(Color.gray);
                messagesent.setVisible(true);
                messagesent.setForeground(Color.red);
                messagesent.setLineWrap(true);
                messagesent.setWrapStyleWord(true);
                messagesent.setEditable(false);
                messagesent.setText(textsent);

                elementpanel.add(Box.createRigidArea(new Dimension(0, 10)));
                messagefield.setText("");
                ;
                panel.add(messagesent);
                elementpanel.add(panel);
                elementpanel.revalidate();
                elementpanel.repaint();
                UserManager.sendMessage(textsent);
            }
        }
        else if(e.getSource()==showbutton)
        {
            if(GetfriendpastMessages().size()==0&&GetyourpastMessages().size()==0)
            {
                JOptionPane.showMessageDialog(null,"No Past Messages ","No Text Found",JOptionPane.ERROR_MESSAGE);
            }
            //show past messages function case 1
            else if (GetyourpastMessages().size() >= GetfriendpastMessages().size()) {

                for (int i = 0; i < GetyourpastMessages().size(); i++) {

                    //get your message
                    JPanel pan = new JPanel();
                    JTextArea textarea = new JTextArea();
                    String textsent = GetyourpastMessages().get(i);
                    pan.setLayout(new FlowLayout(FlowLayout.LEFT));
                    pan.setPreferredSize(new Dimension(120, 50));
                    pan.setBorder(BorderFactory.createTitledBorder("message"));
                    pan.setBackground(Color.gray);
                    textarea.setVisible(true);
                    textarea.setForeground(Color.red);
                    textarea.setLineWrap(true);
                    textarea.setWrapStyleWord(true);
                    textarea.setEditable(false);
                    textarea.setText(textsent);
                    elementpanel.add(Box.createRigidArea(new Dimension(0, 10)));


                    pan.add(textarea);
                    elementpanel.add(pan);
                    elementpanel.revalidate();
                    elementpanel.repaint();

//get the other message
                    if (i < GetfriendpastMessages().size())
                    {
                        JPanel pan2 = new JPanel();
                        JTextArea textarea2 = new JTextArea();
                        String textsent2 = GetfriendpastMessages().get(i);
                        pan2.setLayout(new FlowLayout(FlowLayout.LEFT));
                        pan2.setPreferredSize(new Dimension(120, 50));
                        pan2.setBorder(BorderFactory.createTitledBorder("message"));

                        textarea2.setVisible(true);
                        textarea2.setForeground(Color.red);
                        textarea2.setLineWrap(true);
                        textarea2.setWrapStyleWord(true);
                        textarea2.setEditable(false);
                        textarea2.setText(textsent2);
                        elementpanel.add(Box.createRigidArea(new Dimension(0, 10)));

                        pan2.add(textarea2);

                        elementpanel.add(pan2);
                        elementpanel.revalidate();
                        elementpanel.repaint();
                    }
                }
            }

            //show past messages case 2

            else if (GetyourpastMessages().size() < GetfriendpastMessages().size()) {

                for (int i = 0; i < GetfriendpastMessages().size(); i++) {
                    //get your message
                    if (i<GetyourpastMessages().size()) {
                        JPanel pan = new JPanel();
                        JTextArea textarea = new JTextArea();
                        String textsent = GetyourpastMessages().get(i);
                        pan.setLayout(new FlowLayout(FlowLayout.LEFT));
                        pan.setPreferredSize(new Dimension(120, 50));
                        pan.setBorder(BorderFactory.createTitledBorder("message"));
                        pan.setBackground(Color.gray);

                        textarea.setVisible(true);
                        textarea.setForeground(Color.red);
                        textarea.setLineWrap(true);
                        textarea.setWrapStyleWord(true);
                        textarea.setEditable(false);
                        textarea.setText(textsent);
                        elementpanel.add(Box.createRigidArea(new Dimension(0, 10)));
                        pan.add(textarea);
                        elementpanel.add(pan);
                        elementpanel.revalidate();
                        elementpanel.repaint();
                    }

                    //get the other message
                    JPanel pan2 = new JPanel();
                    JTextArea textarea2 = new JTextArea();
                    String textsent2 = GetfriendpastMessages().get(i);
                    pan2.setLayout(new FlowLayout(FlowLayout.LEFT));
                    pan2.setPreferredSize(new Dimension(120, 50));
                    pan2.setBorder(BorderFactory.createTitledBorder("message"));
                    textarea2.setVisible(true);
                    textarea2.setForeground(Color.red);
                    textarea2.setLineWrap(true);
                    textarea2.setWrapStyleWord(true);
                    textarea2.setEditable(false);
                    textarea2.setText(textsent2);
                    pan2.add(textarea2);
                    elementpanel.add(Box.createRigidArea(new Dimension(0, 10)));
                    elementpanel.add(pan2);
                    elementpanel.revalidate();
                    elementpanel.repaint();
                }
            }
        }
    }

    public JPanel getMessagePan() {
        return MessagePan();
    }

}