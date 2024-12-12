package facebook.pro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentGUI {
    private Reaction reaction;

    public CommentGUI(Reaction reaction) {
        this.reaction = reaction;
        createCommentGUI();
    }

    private void createCommentGUI() {
        JFrame frame = new JFrame("Comments and Replies");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for adding comments
        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextArea commentInput = new JTextArea(3, 30);
        commentInput.setLineWrap(true);
        commentInput.setWrapStyleWord(true);
        JButton addCommentButton = new JButton("Add Comment");

        inputPanel.add(new JScrollPane(commentInput), BorderLayout.CENTER);
        inputPanel.add(addCommentButton, BorderLayout.EAST);

        // Panel for displaying comments
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Action listener to add a comment
        addCommentButton.addActionListener(e -> {
            String user = JOptionPane.showInputDialog(frame, "Enter your username:");
            if (user == null || user.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username cannot be empty!");
                return;
            }
            String commentText = commentInput.getText().trim();
            if (commentText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Comment cannot be empty!");
                return;
            }
            reaction.addComment(user, commentText);
            refreshComments(commentsPanel);
            commentInput.setText("");
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(commentsScrollPane, BorderLayout.CENTER);

        refreshComments(commentsPanel);
        frame.setVisible(true);
    }

    private void refreshComments(JPanel commentsPanel) {
        commentsPanel.removeAll();

        for (int i = 0; i < reaction.getComments().size(); i++) {
            Reaction.Comment comment = reaction.getComments().get(i);

            JPanel commentPanel = new JPanel();
            commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
            commentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Display the main comment
            JLabel commentLabel = new JLabel("#" + (i + 1) + " " + comment.toString());
            commentLabel.setVerticalAlignment(SwingConstants.TOP);

            JButton replyButton = new JButton("Reply");
            int commentIndex = i; // Required for lambda
            replyButton.addActionListener(e -> handleReply(commentIndex, commentsPanel));

            JPanel commentHeaderPanel = new JPanel(new BorderLayout());
            commentHeaderPanel.add(commentLabel, BorderLayout.CENTER);
            commentHeaderPanel.add(replyButton, BorderLayout.EAST);

            commentPanel.add(commentHeaderPanel);

            // Display replies for the comment
            for (Reaction.Comment reply : comment.getReplies()) {
                JLabel replyLabel = new JLabel("    ↳ " + reply.toString());
                replyLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                commentPanel.add(replyLabel);
            }

            commentsPanel.add(commentPanel);
        }

        commentsPanel.revalidate();
        commentsPanel.repaint();
    }

    private void handleReply(int commentIndex, JPanel commentsPanel) {
        String user = JOptionPane.showInputDialog(null, "Enter your username:");
        if (user == null || user.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
            return;
        }
        String replyMessage = JOptionPane.showInputDialog(null, "Enter your reply:");
        if (replyMessage == null || replyMessage.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Reply cannot be empty!");
            return;
        }
        reaction.addReply(commentIndex, user, replyMessage);
        refreshComments(commentsPanel);
    }

}
