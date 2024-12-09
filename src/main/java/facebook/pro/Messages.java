package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Messages extends pageLayOut implements ActionListener {
    private ArrayList <String> messages;
    JTextField messagefield;
    JButton sendbutton;
    JButton back;
    static String Friendname;
    JPanel elementpanel;
    JScrollPane scroll;
    ArrayList<String>messageswithfriend;

    private JPanel MessagePan() {
        //Getting past messages sent
//        GetPastMessages();

        //Go back button
        back =new JButton("<Back");
        styleButton(back);
        back.setPreferredSize(new Dimension(150,50));
        back.addActionListener(e->cardLayout.show(cardPanel,"Friend"));


        //The send button.
        sendbutton = new JButton("Send>");
        styleButton(sendbutton);
        sendbutton.setPreferredSize(new Dimension(80,50));
        sendbutton.addActionListener(this);
        //The Field message will be written in.
        messagefield=new JTextField();
        styleTextField(messagefield);
        messagefield.setBackground(Color.LIGHT_GRAY);

        //panel contains the page elements.
        elementpanel= new JPanel();
        elementpanel.setLayout(new BoxLayout(elementpanel, BoxLayout.Y_AXIS));
        elementpanel.add(back);


        //scrollbar for tall chats
        scroll=new JScrollPane(elementpanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //panel of inputs
        JPanel inputPanel=new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(messagefield,BorderLayout.CENTER);
        inputPanel.add(sendbutton,BorderLayout.EAST);

        //final panel shown to the user
        JPanel messagepan= new JPanel(new BorderLayout());
        messagepan.add(scroll,BorderLayout.CENTER);
        messagepan.add(inputPanel,BorderLayout.SOUTH);

        return messagepan;
    }

    @Override
    public  void actionPerformed(ActionEvent e) {
        if (e.getSource()==sendbutton)
        {
            String textsent=messagefield.getText();
            if(textsent.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"No Message Found","text not found",JOptionPane.ERROR_MESSAGE);
            }
            else{
                JTextArea messagesent=new JTextArea();
                messagesent.setVisible(true);
                messagesent.setBackground(Color.black);
                messagesent.setForeground(Color.yellow);
                messagesent.setLineWrap(true);
                messagesent.setWrapStyleWord(true);
                messagesent.setEditable(false);
                messagesent.setText(textsent);
                elementpanel.setLayout(null);
                messagesent.setBounds(0,150,250,40);
                messagefield.setText("");;
                elementpanel.add(messagesent);
                elementpanel.revalidate();
                elementpanel.repaint();
                JScrollBar vertical=scroll.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
                UserManager.sendMessage(textsent);
            }
        }
    }
    public JPanel getMessagePan()
    {
        return MessagePan();
    }
}